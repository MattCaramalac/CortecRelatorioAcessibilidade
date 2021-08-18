package com.mpms.relatorioacessibilidadecortec.data;

import android.content.Context;

import com.mpms.relatorioacessibilidadecortec.entities.CounterEntry;
import com.mpms.relatorioacessibilidadecortec.entities.DoorEntry;
import com.mpms.relatorioacessibilidadecortec.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.entities.FlightStairsEntry;
import com.mpms.relatorioacessibilidadecortec.entities.FreeSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.entities.GateObsEntry;
import com.mpms.relatorioacessibilidadecortec.entities.OtherSpaces;
import com.mpms.relatorioacessibilidadecortec.entities.ParkingLotElderlyEntry;
import com.mpms.relatorioacessibilidadecortec.entities.ParkingLotEntry;
import com.mpms.relatorioacessibilidadecortec.entities.ParkingLotPDMREntry;
import com.mpms.relatorioacessibilidadecortec.entities.PayPhoneEntry;
import com.mpms.relatorioacessibilidadecortec.entities.RoomEntry;
import com.mpms.relatorioacessibilidadecortec.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.entities.StairsEntry;
import com.mpms.relatorioacessibilidadecortec.entities.SwitchEntry;
import com.mpms.relatorioacessibilidadecortec.entities.TableEntry;
import com.mpms.relatorioacessibilidadecortec.entities.WaterFountainEntry;
import com.mpms.relatorioacessibilidadecortec.entities.WindowEntry;

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
ParkingLotEntry.class, ParkingLotPDMREntry.class, ParkingLotElderlyEntry.class, RoomEntry.class, DoorEntry.class,
FreeSpaceEntry.class, SwitchEntry.class, TableEntry.class, WindowEntry.class, GateObsEntry.class, PayPhoneEntry.class,
        CounterEntry.class, StairsEntry.class, FlightStairsEntry.class}, version = 15)
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
                    dbWriteExecutor.execute(() -> { RoomEntryDao roomEntryDao = INSTANCE.roomEntryDao(); });
                    dbWriteExecutor.execute(() -> { DoorEntryDao doorEntryDao = INSTANCE.doorEntryDao(); });
                    dbWriteExecutor.execute(() -> { SwitchEntryDao switchEntryDao = INSTANCE.switchEntryDao(); });
                    dbWriteExecutor.execute(() -> { FreeSpaceEntryDao freeSpaceEntryDao = INSTANCE.freeSpaceEntryDao(); });
                    dbWriteExecutor.execute(() -> { TableEntryDao tableEntryDao = INSTANCE.tableEntryDao(); });
                    dbWriteExecutor.execute(() -> { WindowEntryDao windowEntryDao = INSTANCE.windowEntryDao(); });
                    dbWriteExecutor.execute(() -> { PayPhoneDao payPhoneDao = INSTANCE.payPhoneDao(); });
                    dbWriteExecutor.execute(() -> { GateObsDao gateObsDao = INSTANCE.gateObsDao(); });
                    dbWriteExecutor.execute(() -> { CounterEntryDao counterEntryDao = INSTANCE.counterEntryDao(); });
                    dbWriteExecutor.execute(() -> { StairsEntryDao stairsEntryDao = INSTANCE.stairsEntryDao(); });
                    dbWriteExecutor.execute(() -> { FligthStairsDao fligthStairsDao = INSTANCE.fligthStairsDao(); });
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

    static final Migration MIGRATION_8_9 = new Migration(8,9) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE RoomEntry (roomID INTEGER PRIMARY KEY AUTOINCREMENT, schoolEntryID INTEGER, " +
                    "roomType INTEGER, roomHasVisualVertSing INTEGER, roomObsVisualVertSign TEXT, roomHasTactileSing INTEGER, " +
                    "roomObsTactileSign TEXT, libraryDistanceShelvesOK INTEGER, libraryPcrManeuversOK INTEGER, " +
                    "libraryAccessiblePcOK INTEGER, cafeteriaTurnAroundPossible INTEGER, classroomBlackboardHeight REAL, " +
                    "secretFixedSeats INTEGER, secretHasPcrSpace INTEGER, secretWidthPcrSpace REAL, secretLengthPcrSpace REAL, " +
                    "secretTurnAroundPossible INTEGER, FOREIGN KEY (schoolEntryID) REFERENCES SchoolEntry (cadID) ON UPDATE " +
                    "CASCADE ON DELETE CASCADE)");

        }
    };

    static final Migration MIGRATION_9_10 = new Migration(9,10) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE DoorEntry(doorID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, schoolID INTEGER NOT NULL, roomID INTEGER NOT NULL," +
                    " doorLocation TEXT, doorWidth REAL, doorSillType INTEGER, sillInclinationHeight REAL, sillStepHeight REAL, sillSlopeHeight REAL, " +
                    "sillSlopeWidth REAL, doorObs TEXT, FOREIGN KEY (schoolID) REFERENCES SchoolEntry (cadID) ON UPDATE CASCADE ON DELETE CASCADE, " +
                    "FOREIGN KEY (roomID) REFERENCES RoomEntry (roomID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("CREATE TABLE SwitchEntry (switchID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, schoolID INTEGER NOT NULL, roomID INTEGER NOT NULL, " +
                    "switchLocation TEXT, switchType TEXT, switchHeight REAL, switchObs TEXT, " +
                    "FOREIGN KEY (schoolID) REFERENCES SchoolEntry (cadID) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (roomID) REFERENCES RoomEntry (roomID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("CREATE TABLE FreeSpaceEntry (freeSpaceID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, schoolID INTEGER NOT NULL, roomID INTEGER NOT NULL," +
                    "freeSpaceLocation TEXT, freeSpaceWidth REAL, freeSpaceObs TEXT, " +
                    "FOREIGN KEY (schoolID) REFERENCES SchoolEntry (cadID) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (roomID) REFERENCES RoomEntry (roomID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("CREATE TABLE TableEntry (tableID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, schoolID INTEGER NOT NULL, roomID INTEGER NOT NULL, " +
                    "isClassroom INTEGER NOT NULL, tableType INTEGER, inferiorBorderHeight REAL, superiorBorderHeight REAL, tableWidth REAL, tableFrontalApprox REAL, " +
                    "tableObs TEXT, FOREIGN KEY (schoolID) REFERENCES SchoolEntry (cadID) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (roomID) REFERENCES RoomEntry (roomID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("CREATE TABLE WindowEntry (windowID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, schoolID INTEGER NOT NULL, roomID INTEGER NOT NULL, " +
                    "windowLocation TEXT, windowSillHeight REAL, windowObs TEXT, FOREIGN KEY (schoolID) REFERENCES SchoolEntry (cadID) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (roomID) REFERENCES RoomEntry (roomID) ON UPDATE CASCADE ON DELETE CASCADE)");

        }
    };

    static final Migration MIGRATION_10_11 = new Migration(10,11) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE GateObsEntry(gateObsID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, schoolEntryID INTEGER NOT NULL, accessRefPoint TEXT," +
                    "accessibleEntrance INTEGER, accessType INTEGER, gateDoorWidth REAL, barrierHeight REAL, barrierWidth REAL, gateObstacleObs TEXT," +
                    "FOREIGN KEY (schoolEntryID) REFERENCES SchoolEntry (cadID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("CREATE TABLE PayPhoneEntry(payPhoneID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, schoolEntryID INTEGER NOT NULL," +
                    "phoneRefPoint TEXT, phoneOpHeight REAL, hasTactileFloor INTEGER, payPhoneObs TEXT, FOREIGN KEY (schoolEntryID) REFERENCES SchoolEntry (cadID)" +
                    "ON UPDATE CASCADE ON DELETE CASCADE)");
        }
    };

    static final Migration MIGRATION_11_12 = new Migration(11,12) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE GateObsEntry");
            database.execSQL("DROP TABLE PayPhoneEntry");
            database.execSQL("CREATE TABLE GateObsEntry(gateObsID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, externalAccessID INTEGER NOT NULL, accessRefPoint TEXT," +
                    "accessibleEntrance INTEGER, accessType INTEGER, gateDoorWidth REAL, barrierHeight REAL, barrierWidth REAL, gateObstacleObs TEXT," +
                    "FOREIGN KEY (externalAccessID) REFERENCES ExternalAccess (externalAccessID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("CREATE TABLE PayPhoneEntry(payPhoneID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, externalAccessID INTEGER NOT NULL," +
                    "phoneRefPoint TEXT, phoneOpHeight REAL, hasTactileFloor INTEGER, payPhoneObs TEXT, FOREIGN KEY (externalAccessID) " +
                    "REFERENCES ExternalAccess (externalAccessID) ON UPDATE CASCADE ON DELETE CASCADE)");
        }
    };

    static final Migration MIGRATION_12_13 = new Migration(12,13) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE CounterEntry(counterID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, roomID INTEGER NOT NULL, counterUpperEdge REAL NOT NULL," +
                    "counterLowerEdge REAL NOT NULL, counterFrontalApprox REAL NOT NULL, counterObs TEXT, FOREIGN KEY (roomID) REFERENCES RoomEntry (roomID)" +
                    "ON UPDATE CASCADE ON DELETE CASCADE)");
        }
    };


    static final Migration MIGRATION_13_14 = new Migration(13,14) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE CounterEntry");
            database.execSQL("CREATE TABLE CounterEntry(counterID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, roomID INTEGER NOT NULL, " +
                    "counterLocation TEXT, counterUpperEdge REAL NOT NULL, counterLowerEdge REAL NOT NULL, counterFrontalApprox REAL NOT NULL, " +
                    "counterObs TEXT, FOREIGN KEY (roomID) REFERENCES RoomEntry (roomID) ON UPDATE CASCADE ON DELETE CASCADE)");
        }
    };

    static final Migration MIGRATION_14_15 = new Migration(14,15) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE StairsEntry(stairsID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, schoolID INTEGER NOT NULL," +
                    "stairsLocation TEXT, flightStairsQuantity INTEGER NOT NULL, FOREIGN KEY (schoolID) REFERENCES SchoolEntry (cadID)" +
                    "ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("CREATE TABLE FlightStairsEntry(flightID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, stairsID INTEGER NOT NULL," +
                    "flightWidth REAL NOT NULL, signPavement INTEGER NOT NULL, signPavementObs TEXT, tactileFloor INTEGER NOT NULL," +
                    "tactileFloorObs TEXT, borderSign INTEGER NOT NULL, borderSignWidth REAL NOT NULL, borderSignIdentifiable INTEGER NOT NULL," +
                    "borderSignObs TEXT, FOREIGN KEY (stairsID) REFERENCES StairsEntry (stairsID) ON UPDATE CASCADE ON DELETE CASCADE)");
        }
    };

    public static ReportDatabase getDatabase(final Context context) {
        if (INSTANCE == null){
            synchronized (ReportDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ReportDatabase.class, "ReportDatabase")
                            .addCallback(roomCallback).addMigrations(MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_6,
                                    MIGRATION_6_7, MIGRATION_7_8, MIGRATION_8_9, MIGRATION_9_10, MIGRATION_10_11, MIGRATION_11_12,
                                    MIGRATION_12_13, MIGRATION_13_14, MIGRATION_14_15).build();
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
    public abstract RoomEntryDao roomEntryDao();
    public abstract DoorEntryDao doorEntryDao();
    public abstract SwitchEntryDao switchEntryDao();
    public abstract FreeSpaceEntryDao freeSpaceEntryDao();
    public abstract TableEntryDao tableEntryDao();
    public abstract WindowEntryDao windowEntryDao();
    public abstract GateObsDao gateObsDao();
    public abstract PayPhoneDao payPhoneDao();
    public abstract CounterEntryDao counterEntryDao();
    public abstract  StairsEntryDao stairsEntryDao();
    public abstract FligthStairsDao fligthStairsDao();

}
