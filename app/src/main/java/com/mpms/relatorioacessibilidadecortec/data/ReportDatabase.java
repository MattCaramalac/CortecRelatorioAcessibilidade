package com.mpms.relatorioacessibilidadecortec.data;

import android.content.Context;

import com.mpms.relatorioacessibilidadecortec.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.entities.OtherSpaces;
import com.mpms.relatorioacessibilidadecortec.entities.ParkingLotElderlyEntry;
import com.mpms.relatorioacessibilidadecortec.entities.ParkingLotEntry;
import com.mpms.relatorioacessibilidadecortec.entities.ParkingLotPDMREntry;
import com.mpms.relatorioacessibilidadecortec.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.entities.WaterFountainEntry;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.PrimaryKey;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {SchoolEntry.class, WaterFountainEntry.class, OtherSpaces.class, ExternalAccess.class,
ParkingLotEntry.class, ParkingLotPDMREntry.class, ParkingLotElderlyEntry.class}, version = 8)
public abstract class ReportDatabase extends RoomDatabase {

    public static final int NUMBER_THREADS = 4;
    private static volatile ReportDatabase INSTANCE;
    public static final ExecutorService dbWriteExecutor = Executors.newFixedThreadPool(NUMBER_THREADS);

    public static final RoomDatabase.Callback  roomCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);

                    dbWriteExecutor.execute(() -> { SchoolEntryDao schoolEntryDao = INSTANCE.schoolEntryDao(); });
                    dbWriteExecutor.execute(() -> { WaterFountainDao waterFountainDao = INSTANCE.waterFountainDao(); });
                    dbWriteExecutor.execute(() -> { ExternalAccessDao externalAccessDao = INSTANCE.externalAccessDao(); });
                    dbWriteExecutor.execute(() -> { OtherSpacesDao otherSpacesDao = INSTANCE.otherSpacesDao(); });
                    dbWriteExecutor.execute(() -> { ParkingLotEntryDao parkingLotEntryDao = INSTANCE.parkingLotEntryDao(); });
                    dbWriteExecutor.execute(() -> { ParkingLotPdmrDao parkingLotPdmrDao = INSTANCE.parkingLotPdmrDao(); });
                    dbWriteExecutor.execute(() -> { ParkingLotElderlyDao parkingLotElderlyDao = INSTANCE.parkingLotElderlyDao(); });
                }
            };


    static final Migration MIGRATION_2_3 = new Migration(2,3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE WaterFountainEntry (waterFountainID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "schoolEntryID INTEGER NOT NULL, waterFountainHeight REAL NOT NULL, cupHolderHeight REAL NOT NULL," +
                    "waterFountApproximation REAL NOT NULL, FOREIGN KEY (schoolEntryID) REFERENCES SchoolEntry (cadID)" +
                    "ON UPDATE CASCADE ON DELETE CASCADE) ");
        }
    };

    static final Migration MIGRATION_3_4 = new Migration(3,4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE WaterFountainEntry");
            database.execSQL("CREATE TABLE WaterFountainEntry (waterFountainID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "schoolEntryID INTEGER NOT NULL, typeWaterFountain INTEGER NOT NULL, otherAllowSideApproximation INTEGER," +
                    "otherFaucetHeight REAL, otherHasCupHolder INTEGER, otherCupHolderHeight REAL, spoutAllowFrontalApproximation INTEGER," +
                    " highestSpoutHeight REAL, lowestSpoutHeight REAL, freeSpaceLowestSpout REAL, FOREIGN KEY (schoolEntryID) REFERENCES " +
                    "SchoolEntry (cadID) ON UPDATE CASCADE ON DELETE CASCADE)");
        }
    };

    static final Migration MIGRATION_4_5 = new Migration(4,5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE ExternalAccess (externalAccessID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "schoolEntryID INTEGER NOT NULL, hasSIA INTEGER, floorType TEXT, gateWidth REAL, trailHeight REAL," +
                    "hasTrailRamp INTEGER, hasGateObstacles INTEGER, hasGatePayphones INTEGER, FOREIGN KEY (schoolEntryID) REFERENCES " +
                    "SchoolEntry (cadID) ON UPDATE CASCADE ON DELETE CASCADE)");
        }
    };

    static final Migration MIGRATION_5_6 = new Migration(5,6) {

        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE ExternalAccess");
            database.execSQL("CREATE TABLE ExternalAccess (externalAccessID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "schoolEntryID INTEGER NOT NULL, entranceType INTEGER, hasSIA INTEGER, floorType TEXT, gateWidth REAL, " +
                    "gateTrailHeight REAL, gateHasTrailRamp INTEGER, gateHasObstacles INTEGER, gateHasPayphones INTEGER, " +
                    "FOREIGN KEY (schoolEntryID) REFERENCES SchoolEntry (cadID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("CREATE TABLE OtherSpaces (otherSpacesID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "schoolEntryID INTEGER NOT NULL, otherSpaceName TEXT, otherSpaceDescription TEXT, isAccessible INTEGER NOT NULL," +
                    "FOREIGN KEY (schoolEntryID) REFERENCES SchoolEntry (cadID) ON UPDATE CASCADE ON DELETE CASCADE)");
        }
    };

    static final Migration MIGRATION_6_7 = new Migration(6,7) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE ParkingLotEntry (parkingLotID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "schoolEntryID INTEGER, typeParkingLot INTEGER, totalParkingVacancy INTEGER, parkingLotFloorType TEXT," +
                    "FOREIGN KEY (schoolEntryID) REFERENCES SchoolEntry (cadID) ON UPDATE CASCADE ON DELETE CASCADE)");
        }
    };

    static final Migration MIGRATION_7_8 = new Migration(7,8) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE ParkingLotPDMREntry (parkingPdmrID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "schoolEntryID INTEGER NOT NULL, parkingLotID INTEGER NOT NULL, hasPdmrVacancy INTEGER, totalPdmrVacancy INTEGER," +
                    "hasVisualPdmrVertSign INTEGER, visualPdmrVertSignObs TEXT, hasVisualPdmrHorizSign INTEGER, " +
                    "visualPdmrHorizSignWidth REAL, visualPdmrHorizSignLength REAL, visualPdmrHorizSignObs TEXT, hasPdmrSecurityZone INTEGER," +
                    "securityZoneWidth REAL, securityZoneObs TEXT, hasPdmrSia INTEGER, pdmrSiaWidth REAL, pdmrSiaLength REAL, pdmrSiaObs TEXT, " +
                    "FOREIGN KEY (schoolEntryID) REFERENCES SchoolEntry (cadID) ON UPDATE CASCADE ON DELETE CASCADE, " +
                    "FOREIGN KEY (parkingLotID) REFERENCES ParkingLotEntry (parkingLotID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("CREATE TABLE ParkingLotElderlyEntry (parkingElderlyID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "schoolEntryID INTEGER NOT NULL, parkingLotID INTEGER NOT NULL, hasElderlyVacancy INTEGER, " +
                    "totalElderlyVacancy INTEGER, hasVisualElderlyVertSign INTEGER, visualElderlyVertSignObs TEXT," +
                    "hasVisualElderlyHorizSign INTEGER, visualElderlyHorizSignWidth REAL, visualElderlyHorizSignLength REAL," +
                    "visualElderlyHorizSignObs TEXT, hasPictogramElderly INTEGER, pictogramElderlyWidth REAL," +
                    "pictogramElderlyLength REAL, pictogramElderlyObs TEXT, FOREIGN KEY (schoolEntryID) REFERENCES SchoolEntry (cadID) " +
                    "ON UPDATE CASCADE ON DELETE CASCADE, FOREIGN KEY (parkingLotID) REFERENCES ParkingLotEntry (parkingLotID) " +
                    "ON UPDATE CASCADE ON DELETE CASCADE)");
        }
    };

    public static ReportDatabase getDatabase(final Context context) {
        if (INSTANCE == null){
            synchronized (ReportDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ReportDatabase.class, "ReportDatabase")
                            .addCallback(roomCallback).addMigrations(MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_6,
                                    MIGRATION_6_7, MIGRATION_7_8).build();
                }
            }
        }
        return INSTANCE;
    }


    public abstract SchoolEntryDao schoolEntryDao();
    public abstract WaterFountainDao waterFountainDao();
    public abstract ExternalAccessDao externalAccessDao();
    public abstract OtherSpacesDao otherSpacesDao();
    public abstract ParkingLotEntryDao parkingLotEntryDao();
    public abstract ParkingLotElderlyDao parkingLotElderlyDao();
    public abstract ParkingLotPdmrDao parkingLotPdmrDao();

}
