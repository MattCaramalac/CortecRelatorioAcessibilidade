package com.mpms.relatorioacessibilidadecortec.data.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.mpms.relatorioacessibilidadecortec.data.Dao.AdmEquipDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.BlockSpaceDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.CounterEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.DoorEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.ExternalAccessDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.FlightRampStairsDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.FreeSpaceEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.GateObsDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.OtherSpacesDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.ParkingLotElderlyDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.ParkingLotEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.ParkingLotPcdDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.PayPhoneDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.PlaygroundEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.RampInclinationDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.RampStairsEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.RampStairsHandrailDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.RampStairsRailingDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.RestroomEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.RestroomMirrorDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.RestroomSinkDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.RestroomSupportBarDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.RestroomUpViewDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.RestroomUrinalDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.RoomEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.SchoolEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.SidewalkEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.SidewalkSlopeDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.StairsMirrorDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.StairsStepDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.SwitchEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.TableEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.WaterFountainDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.WindowEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.entities.AdmEquipEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.BlockSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.CounterEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.data.entities.FlightsRampStairsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.FreeSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.GateObsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.OtherSpaces;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotElderlyEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotPCDEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PayPhoneEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PlaygroundEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampInclinationEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsHandrailEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsRailingEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomMirrorEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomSinkEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomSupportBarEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomUpViewEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomUrinalEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RoomEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkSlopeEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.StairsMirrorEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.StairsStepEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SwitchEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.TableEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.WaterFountainEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.WindowEntry;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {SchoolEntry.class, WaterFountainEntry.class, OtherSpaces.class, ExternalAccess.class,
        ParkingLotEntry.class, ParkingLotPCDEntry.class, ParkingLotElderlyEntry.class, RoomEntry.class, DoorEntry.class,
        FreeSpaceEntry.class, SwitchEntry.class, TableEntry.class, WindowEntry.class, GateObsEntry.class, PayPhoneEntry.class,
        CounterEntry.class, RampStairsEntry.class, FlightsRampStairsEntry.class, RestroomEntry.class, RestroomMirrorEntry.class,
        RestroomSinkEntry.class, RestroomSupportBarEntry.class, RestroomUpViewEntry.class, RestroomUrinalEntry.class, SidewalkEntry.class,
        SidewalkSlopeEntry.class, StairsStepEntry.class, StairsMirrorEntry.class, RampInclinationEntry.class, RampStairsHandrailEntry.class,
        RampStairsRailingEntry.class, BlockSpaceEntry.class, AdmEquipEntry.class, PlaygroundEntry.class}, version = 34)
public abstract class ReportDatabase extends RoomDatabase {

    public static final int NUMBER_THREADS = 4;
    private static volatile ReportDatabase INSTANCE;
    public static final ExecutorService dbWriteExecutor = Executors.newFixedThreadPool(NUMBER_THREADS);

    public static final RoomDatabase.Callback roomCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);

                    dbWriteExecutor.execute(() -> {
                        SchoolEntryDao schoolEntryDao = INSTANCE.schoolEntryDao();
                    });
                    dbWriteExecutor.execute(() -> {
                        WaterFountainDao waterFountainDao = INSTANCE.waterFountainDao();
                    });
                    dbWriteExecutor.execute(() -> {
                        ExternalAccessDao externalAccessDao = INSTANCE.externalAccessDao();
                    });
                    dbWriteExecutor.execute(() -> {
                        OtherSpacesDao otherSpacesDao = INSTANCE.otherSpacesDao();
                    });
                    dbWriteExecutor.execute(() -> {
                        ParkingLotEntryDao parkingLotEntryDao = INSTANCE.parkingLotEntryDao();
                    });
                    dbWriteExecutor.execute(() -> {
                        ParkingLotPcdDao parkingLotPcdDao = INSTANCE.parkingLotPdmrDao();
                    });
                    dbWriteExecutor.execute(() -> {
                        ParkingLotElderlyDao parkingLotElderlyDao = INSTANCE.parkingLotElderlyDao();
                    });
                    dbWriteExecutor.execute(() -> {
                        RoomEntryDao roomEntryDao = INSTANCE.roomEntryDao();
                    });
                    dbWriteExecutor.execute(() -> {
                        DoorEntryDao doorEntryDao = INSTANCE.doorEntryDao();
                    });
                    dbWriteExecutor.execute(() -> {
                        SwitchEntryDao switchEntryDao = INSTANCE.switchEntryDao();
                    });
                    dbWriteExecutor.execute(() -> {
                        FreeSpaceEntryDao freeSpaceEntryDao = INSTANCE.freeSpaceEntryDao();
                    });
                    dbWriteExecutor.execute(() -> {
                        TableEntryDao tableEntryDao = INSTANCE.tableEntryDao();
                    });
                    dbWriteExecutor.execute(() -> {
                        WindowEntryDao windowEntryDao = INSTANCE.windowEntryDao();
                    });
                    dbWriteExecutor.execute(() -> {
                        PayPhoneDao payPhoneDao = INSTANCE.payPhoneDao();
                    });
                    dbWriteExecutor.execute(() -> {
                        GateObsDao gateObsDao = INSTANCE.gateObsDao();
                    });
                    dbWriteExecutor.execute(() -> {
                        CounterEntryDao counterEntryDao = INSTANCE.counterEntryDao();
                    });
                    dbWriteExecutor.execute(() -> {
                        RampStairsEntryDao rampStairsDao = INSTANCE.rampStairsDao();
                    });
                    dbWriteExecutor.execute(() -> {
                        FlightRampStairsDao flightRampStairsDao = INSTANCE.flightRampStairsDao();
                    });
                    dbWriteExecutor.execute(() -> {
                        RestroomEntryDao restroomEntryDao = INSTANCE.restroomEntryDao();
                    });
                    dbWriteExecutor.execute(() -> {
                        RestroomMirrorDao restroomMirrorDao = INSTANCE.restroomMirrorDao();
                    });
                    dbWriteExecutor.execute(() -> {
                        RestroomSinkDao restroomSinkDao = INSTANCE.restroomSinkDao();
                    });
                    dbWriteExecutor.execute(() -> {
                        RestroomSupportBarDao restroomSupportBarDao = INSTANCE.restroomSupportBarDao();
                    });
                    dbWriteExecutor.execute(() -> {
                        RestroomUpViewDao restroomUpViewDao = INSTANCE.restroomUpViewDao();
                    });
                    dbWriteExecutor.execute(() -> {
                        RestroomUrinalDao restroomUrinalDao = INSTANCE.restroomUrinalDao();
                    });
                    dbWriteExecutor.execute(() -> {
                        SidewalkEntryDao sidewalkEntryDao = INSTANCE.sidewalkEntryDao();
                    });
                    dbWriteExecutor.execute(() -> {
                        SidewalkSlopeDao sidewalkSlopeDao = INSTANCE.sidewalkSlopeDao();
                    });
                    dbWriteExecutor.execute(() -> {
                        StairsMirrorDao mirrorDao = INSTANCE.stairsMirrorDao();
                    });
                    dbWriteExecutor.execute(() -> {
                        StairsStepDao stepDao = INSTANCE.stairsStepDao();
                    });
                    dbWriteExecutor.execute(() -> {
                        RampInclinationDao rampDao = INSTANCE.rampInclinationDao();
                    });
                    dbWriteExecutor.execute(() -> {
                        RampStairsRailingDao railingDao = INSTANCE.rampStairsRailingDao();
                    });
                    dbWriteExecutor.execute(() -> {
                        RampStairsHandrailDao handrailDao = INSTANCE.rampStairsHandrailDao();
                    });

                    dbWriteExecutor.execute(() -> {
                        BlockSpaceDao blockSpaceDao = INSTANCE.blockSpaceDao();
                    });

                    dbWriteExecutor.execute(() -> {
                        AdmEquipDao admEquipDao = INSTANCE.admEquipDao();
                    });

                    dbWriteExecutor.execute(() -> {
                        PlaygroundEntryDao playgroundEntryDao = INSTANCE.playgroundEntryDao();
                    });

                }
            };


    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE WaterFountainEntry (waterFountainID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "schoolEntryID INTEGER NOT NULL, waterFountainHeight REAL NOT NULL, cupHolderHeight REAL NOT NULL," +
                    "waterFountApproximation REAL NOT NULL, FOREIGN KEY (schoolEntryID) REFERENCES SchoolEntry (cadID)" +
                    "ON UPDATE CASCADE ON DELETE CASCADE) ");
        }
    };

    static final Migration MIGRATION_3_4 = new Migration(3, 4) {
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

    static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE ExternalAccess (externalAccessID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "schoolEntryID INTEGER NOT NULL, hasSIA INTEGER, floorType TEXT, gateWidth REAL, trailHeight REAL," +
                    "hasTrailRamp INTEGER, hasGateObstacles INTEGER, hasGatePayphones INTEGER, FOREIGN KEY (schoolEntryID) REFERENCES " +
                    "SchoolEntry (cadID) ON UPDATE CASCADE ON DELETE CASCADE)");
        }
    };

    static final Migration MIGRATION_5_6 = new Migration(5, 6) {

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

    static final Migration MIGRATION_6_7 = new Migration(6, 7) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE ParkingLotEntry (parkingLotID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "schoolEntryID INTEGER, typeParkingLot INTEGER, totalParkingVacancy INTEGER, parkingLotFloorType TEXT," +
                    "FOREIGN KEY (schoolEntryID) REFERENCES SchoolEntry (cadID) ON UPDATE CASCADE ON DELETE CASCADE)");
        }
    };

    static final Migration MIGRATION_7_8 = new Migration(7, 8) {
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

    static final Migration MIGRATION_8_9 = new Migration(8, 9) {
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

    static final Migration MIGRATION_9_10 = new Migration(9, 10) {
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

    static final Migration MIGRATION_10_11 = new Migration(10, 11) {
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

    static final Migration MIGRATION_11_12 = new Migration(11, 12) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE GateObsEntry");
            database.execSQL("DROP TABLE PayPhoneEntry");
            database.execSQL("CREATE TABLE GateObsEntry(gateObsID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, externalAccessID INTEGER NOT NULL, accessRefPoint TEXT," +
                    "accessibleEntrance INTEGER, accessType INTEGER, gateDoorWidth REAL, barrierHeight REAL, barrierWidth REAL, gateObstacleObs TEXT," +
                    "FOREIGN KEY (externalAccessID) REFERENCES ExternalAccess (externalAccessID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("CREATE TABLE PayPhoneEntry(payPhoneID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, externalAccessID INTEGER NOT NULL," +
                    "phoneRefPoint TEXT, phoneOpHeight REAL, hasTactileFloor INTEGER, payPhoneObs TEXT, FOREIGN KEY (externalAccessID) " +
                    "REFERENCES ExternalAccess (extAccessID) ON UPDATE CASCADE ON DELETE CASCADE)");
        }
    };

    static final Migration MIGRATION_12_13 = new Migration(12, 13) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE CounterEntry(counterID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, roomID INTEGER NOT NULL, counterUpperEdge REAL NOT NULL," +
                    "counterLowerEdge REAL NOT NULL, counterFrontalApprox REAL NOT NULL, counterObs TEXT, FOREIGN KEY (roomID) REFERENCES RoomEntry (roomID)" +
                    "ON UPDATE CASCADE ON DELETE CASCADE)");
        }
    };


    static final Migration MIGRATION_13_14 = new Migration(13, 14) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE CounterEntry");
            database.execSQL("CREATE TABLE CounterEntry(counterID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, roomID INTEGER NOT NULL, " +
                    "counterLocation TEXT, counterUpperEdge REAL NOT NULL, counterLowerEdge REAL NOT NULL, counterFrontalApprox REAL NOT NULL, " +
                    "counterObs TEXT, FOREIGN KEY (roomID) REFERENCES RoomEntry (roomID) ON UPDATE CASCADE ON DELETE CASCADE)");
        }
    };

    static final Migration MIGRATION_14_15 = new Migration(14, 15) {
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

    static final Migration MIGRATION_15_16 = new Migration(15, 16) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE FlightStairsEntry");
            database.execSQL("CREATE TABLE FlightStairsEntry(flightID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, stairsID INTEGER NOT NULL," +
                    "flightWidth REAL NOT NULL, signPavement INTEGER NOT NULL, signPavementObs TEXT, tactileFloor INTEGER NOT NULL," +
                    "tactileFloorObs TEXT, borderSign INTEGER NOT NULL, borderSignWidth REAL, borderSignIdentifiable INTEGER," +
                    "borderSignObs TEXT, FOREIGN KEY (stairsID) REFERENCES StairsEntry (stairsID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("CREATE TABLE rampEntry(rampID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, schoolID INTEGER NOT NULL," +
                    "rampLocation TEXT, flightRampQuantity INTEGER NOT NULL, FOREIGN KEY (schoolID) REFERENCES SchoolEntry (cadID)" +
                    "ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("CREATE TABLE FlightRampEntry(flightRampID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, rampID INTEGER NOT NULL," +
                    "rampFlightWidth REAL NOT NULL, pavementSignRamp INTEGER NOT NULL, pavementSignRampObs TEXT, tactileFloorRamp INTEGER NOT NULL," +
                    "tactileFloorRampObs TEXT, FOREIGN KEY (rampID) REFERENCES RampEntry (rampID) ON UPDATE CASCADE ON DELETE CASCADE)");
        }
    };


    static final Migration MIGRATION_16_17 = new Migration(16, 17) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE StairsEntry");
            database.execSQL("DROP TABLE rampEntry");
            database.execSQL("DROP TABLE FlightStairsEntry");
            database.execSQL("DROP TABLE FlightRampEntry");
            database.execSQL("CREATE TABLE RampStairsEntry(rampStairsID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, schoolID INTEGER NOT NULL," +
                    "rampStairsIdentifier INTEGER NOT NULL, rampStairsLocation TEXT, flightsQuantity INTEGER NOT NULL, " +
                    "FOREIGN KEY (schoolID) REFERENCES SchoolEntry (cadID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("CREATE TABLE FlightsRampStairsEntry(flightID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, rampStairsID INTEGER NOT NULL," +
                    "flightWidth REAL NOT NULL, signPavement INTEGER NOT NULL, signPavementObs TEXT, tactileFloor INTEGER NOT NULL," +
                    "tactileFloorObs TEXT, borderSign INTEGER, borderSignWidth REAL, borderSignIdentifiable INTEGER," +
                    "borderSignObs TEXT, FOREIGN KEY (rampStairsID) REFERENCES RampStairsEntry (rampStairsID) ON UPDATE CASCADE ON DELETE CASCADE)");

        }
    };

    static final Migration MIGRATION_17_18 = new Migration(17, 18) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE FlightsRampStairsEntry");
            database.execSQL("CREATE TABLE FlightsRampStairsEntry(flightID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, rampStairsID INTEGER NOT NULL," +
                    "flightWidth REAL, signPavement INTEGER, signPavementObs TEXT, tactileFloor INTEGER, tactileFloorObs TEXT, borderSign INTEGER, " +
                    "borderSignWidth REAL, borderSignIdentifiable INTEGER, borderSignObs TEXT, FOREIGN KEY (rampStairsID) REFERENCES " +
                    "RampStairsEntry (rampStairsID) ON UPDATE CASCADE ON DELETE CASCADE)");

        }
    };

    static final Migration MIGRATION_18_19 = new Migration(18, 19) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE RestroomEntry(restroomID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, schoolID INTEGER NOT NULL," +
                    "restroomType INTEGER, restroomLocation TEXT, accessibleRoute INTEGER, accessibleRouteObs TEXT, integratedRestroom INTEGER," +
                    "integratedRestroomObs TEXT, restroomDistance INTEGER, restroomDistanceObs TEXT, exclusiveEntrance INTEGER, " +
                    "exclusiveEntranceObs TEXT, antiDriftingFloor INTEGER, antiDriftingFloorObs TEXT, restroomDrain INTEGER, " +
                    "restroomDrainObs TEXT, restroomSwitch INTEGER, switchHeight DOUBLE, switchObs TEXT, doorWidth DOUBLE, doorSIA INTEGER," +
                    "doorSIAObs TEXT, doorExtOp INTEGER, doorExtOpObs TEXT, doorVertSign INTEGER, doorVertSignObs TEXT, doorSillType INTEGER," +
                    "inclinationSillHeight DOUBLE, stepSillHeight DOUBLE, slopeSillInclination DOUBLE, slopeSillWidth DOUBLE, doorSillTypeObs TEXT," +
                    "doorTactileSign INTEGER, doorTactileSignObs TEXT, doorIntCoating INTEGER, doorIntCoatingObs TEXT, doorHorizontalHandle INTEGER," +
                    "handleHeight DOUBLE, handleLength DOUBLE, handleObs TEXT, FOREIGN KEY (schoolID) REFERENCES SchoolEntry (cadID)" +
                    "ON UPDATE CASCADE ON DELETE CASCADE)");

        }
    };

    static final Migration MIGRATION_19_20 = new Migration(19, 20) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE RestroomMirrorEntry(mirrorID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, restroomID INTEGER NOT NULL," +
                    "restroomHasMirror INTEGER NOT NULL, mirrorMeasureA REAL, mirrorMeasureB REAL, mirrorObs TEXT, FOREIGN KEY (restroomID) " +
                    "REFERENCES RestroomEntry (restroomID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("CREATE TABLE RestroomSinkEntry(sinkID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, restroomID INTEGER NOT NULL," +
                    "sinkMeasureA REAL, sinkMeasureB REAL, sinkMeasureC REAL, sinkMeasureD REAL, sinkMeasureE REAL, sinkObsAtoE TEXT," +
                    "sinkMeasureF REAL, sinkMeasureG REAL, sinkMeasureH REAL, sinkObsFtoH TEXT, sinkMeasureI REAL, sinkMeasureJ REAL," +
                    "sinkMeasureK REAL, sinkMeasureL REAL, sinkMeasureM REAL, sinkMeasureN REAL, sinkObsItoN TEXT, sinkMeasureO REAL," +
                    "sinkMeasureP REAL, sinkMeasureQ REAL, sinkMeasureR REAL, sinkMeasureS REAL, sinkMeasureT REAL, sinkObsOtoT TEXT, " +
                    "FOREIGN KEY (restroomID) REFERENCES RestroomEntry (restroomID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("CREATE TABLE RestroomSupportBarEntry(supBarID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, restroomID INTEGER NOT NULL," +
                    "supBarDiameter REAL NOT NULL, supBarMeasureA REAL NOT NULL, supBarMeasureB REAL NOT NULL, supBarMeasureC REAL NOT NULL," +
                    "supBarMeasureD REAL NOT NULL, supBarMeasureE REAL NOT NULL, supBarMeasureF REAL NOT NULL, supBarMeasureG REAL NOT NULL," +
                    "supBarMeasureH REAL NOT NULL, supBarMeasureI REAL NOT NULL, supBarMeasureJ REAL NOT NULL, supBarObs TEXT, toiletHeight REAL NOT NULL," +
                    "toiletFlushHeight REAL NOT NULL, paperHolderType INTEGER NOT NULL, paperHolderDistance REAL NOT NULL, paperHolderHeight REAL NOT NULL," +
                    "paperHolderObs TEXT, hasEmergencySignal INTEGER NOT NULL, emergencySignalHeight REAL, emergencySignalObs TEXT, hasBidet INTEGER NOT NULL, " +
                    "bidetObs TEXT, FOREIGN KEY (restroomID) REFERENCES RestroomEntry (restroomID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("CREATE TABLE RestroomUpViewEntry(upViewID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, restroomID INTEGER NOT NULL," +
                    "upViewMeasureA REAL NOT NULL, upViewMeasureB REAL NOT NULL, upViewMeasureC REAL NOT NULL, upViewMeasureD REAL NOT NULL, " +
                    "upViewMeasureE REAL NOT NULL, upViewObs TEXT, FOREIGN KEY (restroomID) REFERENCES RestroomEntry (restroomID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("CREATE TABLE RestroomUrinalEntry(urinalID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, restroomID INTEGER NOT NULL," +
                    "restroomHasUrinal INTEGER NOT NULL, urinalMeasureA REAL, urinalMeasureB REAL, urinalMeasureC REAL, urinalMeasureD REAL, urinalMeasureE REAL," +
                    "urinalMeasureF REAL, urinalMeasureG REAL, urinalMeasureH REAL, urinalMeasureI REAL, urinalMeasureJ REAL, urinalMeasureK REAL," +
                    "urinalObs TEXT, FOREIGN KEY (restroomID) REFERENCES RestroomEntry (restroomID) ON UPDATE CASCADE ON DELETE CASCADE)");
        }
    };

    static final Migration MIGRATION_20_21 = new Migration(20, 21) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE SidewalkEntry(sidewalkID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, schoolEntryID INTEGER NOT NULL," +
                    "sidewalkLocation TEXT, sidewalkConservationStatus TEXT, widthSidewalk REAL, sidewalkHasTactileFloor INTEGER, " +
                    "tactileFloorConservationStatus INTEGER, tactileFloorObs TEXT, obligatorySidewalkSlope INTEGER, sidewalkHasSlope INTEGER," +
                    "FOREIGN KEY (schoolEntryID) REFERENCES SchoolEntry (cadID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("CREATE TABLE SidewalkSlopeEntry(sidewalkSlopeID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, sidewalkID INTEGER NOT NULL," +
                    "slopeLocation TEXT, slopeWidth REAL NOT NULL, slopeFrontalInclination REAL NOT NULL, slopeLeftBrimInclination REAL NOT NULL," +
                    "slopeRightBrimInclination REAL NOT NULL, slopeHasTactileFloor INTEGER NOT NULL, slopeFreeSpace REAL NOT NULL, slopeObs TEXT," +
                    "FOREIGN KEY (sidewalkID) REFERENCES SidewalkEntry(sidewalkID) ON UPDATE CASCADE ON DELETE CASCADE)");
        }
    };

    static final Migration MIGRATION_21_22 = new Migration(21, 22) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE SidewalkEntry ADD COLUMN sidewalkObs TEXT");
        }
    };

    static final Migration MIGRATION_22_23 = new Migration(22, 23) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE StairsStepEntry(stairsStepID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, flightID INTEGER NOT NULL," +
                    "flightStepNumber INTEGER NOT NULL, stepWidth REAL NOT NULL, FOREIGN KEY (flightID) REFERENCES FlightsRampStairsEntry(flightID)" +
                    "ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("CREATE TABLE StairsMirrorEntry(stairsMirrorID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, flightID INTEGER NOT NULL," +
                    "flightMirrorNumber INTEGER NOT NULL, mirrorHeight REAL NOT NULL, FOREIGN KEY (flightID) REFERENCES FlightsRampStairsEntry(flightID)" +
                    "ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("CREATE TABLE RampInclinationEntry(rampInclinationID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, flightID INTEGER NOT NULL," +
                    "flightInclinationNumber INTEGER NOT NULL, inclinationValue REAL NOT NULL, FOREIGN KEY (flightID) REFERENCES " +
                    "FlightsRampStairsEntry(flightID) ON UPDATE CASCADE ON DELETE CASCADE)");
        }
    };

    static final Migration MIGRATION_23_24 = new Migration(23, 24) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE RampStairsRailingEntry(railingID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, flightID INTEGER NOT NULL," +
                    "railingSide INTEGER NOT NULL, hasRailing INTEGER NOT NULL, railingHeight REAL, railingObs TEXT, hasBeacon INTEGER NOT NULL, " +
                    "beaconHeight REAL, beaconObs TEXT, FOREIGN KEY (flightID) REFERENCES FlightsRampStairsEntry(flightID)ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("CREATE TABLE RampStairsHandrailEntry(handrailID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, flightID INTEGER NOT NULL," +
                    "hasHandrail INTEGER NOT NULL, handrailPlacement INTEGER, handrailHeight REAL, handrailGrip REAL, handrailObs TEXT," +
                    "handrailHasExtension INTEGER, extensionLength REAL, extensionObs TEXT,  FOREIGN KEY (flightID) REFERENCES FlightsRampStairsEntry(flightID)" +
                    "ON UPDATE CASCADE ON DELETE CASCADE)");
        }
    };

    static final Migration MIGRATION_24_25 = new Migration(24, 25) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE WaterFountainEntry ADD COLUMN fountainObs TEXT");
            database.execSQL("ALTER TABLE ExternalAccess ADD COLUMN externalObs TEXT");
        }
    };

    static final Migration MIGRATION_25_26 = new Migration(25, 26) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE SchoolEntry ADD COLUMN workingHoursObs TEXT");
            database.execSQL("ALTER TABLE SchoolEntry ADD COLUMN servicesObs TEXT");
            database.execSQL("ALTER TABLE SchoolEntry ADD COLUMN dateInspectionEnd TEXT");
        }
    };

    static final Migration MIGRATION_26_27 = new Migration(26, 27) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE ParkingLotEntry");
            database.execSQL("DROP TABLE ParkingLotPDMREntry");
            database.execSQL("DROP TABLE ParkingLotElderlyEntry");
            database.execSQL("CREATE TABLE ParkingLotEntry(parkingLotID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, schoolID INTEGER NOT NULL," +
                    "typeParkingLot INTEGER NOT NULL, parkingLotFloorType TEXT, hasPCDVacancy INTEGER NOT NULL, hasElderVacancy INTEGER NOT NULL," +
                    "FOREIGN KEY (schoolID) REFERENCES SchoolEntry(cadID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("CREATE TABLE ParkingLotPCDEntry(parkingPcdID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "parkingLotID INTEGER NOT NULL, hasVisualPcdVertSign INTEGER NOT NULL, vertPcdSignLength REAL, vertPcdSignWidth REAL," +
                    "vertPcdSignObs TEXT, pcdVacancyLength REAL NOT NULL, pcdVacancyWidth REAL NOT NULL, pcdVacancyLimitWidth REAL NOT NULL," +
                    "pcdVacancyObs TEXT, hasSecurityZone INTEGER NOT NULL, securityZoneWidth REAL, securityZoneObs TEXT," +
                    "hasPcdSia INTEGER NOT NULL, pcdSiaWidth REAL, pcdSiaLength REAL, pcdSiaObs TEXT, FOREIGN KEY (parkingLotID)" +
                    "REFERENCES ParkingLotEntry (parkingLotID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("CREATE TABLE ParkingLotElderlyEntry(parkingElderlyID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "parkingLotID INTEGER NOT NULL, hasElderlyVertSign INTEGER NOT NULL, elderlyVertSignLength REAL," +
                    "elderlyVertSingWidth REAL, elderlyVertSignObs TEXT, elderlyVacancyLength REAL NOT NULL," +
                    "elderlyVacancyWidth REAL NOT NULL, elderlyVacancyLimiterWidth REAL NOT NULL, elderlyVacancyObs TEXT," +
                    "hasElderlyFloorIndicator INTEGER NOT NULL, floorIndicatorLength REAL, floorIndicatorWidth REAL, " +
                    "floorIndicatorObs TEXT, FOREIGN KEY (parkingLotID) REFERENCES ParkingLotEntry(parkingLotID)" +
                    "ON UPDATE CASCADE ON DELETE CASCADE)");
        }
    };

    static final Migration MIGRATION_27_28 = new Migration(27, 28) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE SchoolEntry_Backup(cadID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, schoolName TEXT, schoolAddress TEXT, " +
                    "addressComplement TEXT, addressNumber TEXT, addressNeighborhood TEXT, nameCity TEXT, nameDistrict TEXT, contactPhone1 TEXT, contactPhone2 TEXT, " +
                    "nameResponsibleVisit TEXT, nameInspectionTeam TEXT, hasMorningClasses INTEGER, morningStart TEXT, morningEnd TEXT, hasAfternoonClasses INTEGER, " +
                    "afternoonStart TEXT, afternoonEnd TEXT, hasEveningClasses INTEGER, eveningStart TEXT, eveningEnd TEXT, workingHoursObs TEXT, hasNursery INTEGER, " +
                    "hasDayCare INTEGER, hasMaternal INTEGER, maternalFirstGrade TEXT, maternalLastGrade TEXT, hasPreschool INTEGER, preschoolFirstGrade TEXT, " +
                    "preschoolLastGrade TEXT, hasElementaryMiddle INTEGER, elementaryMiddleFirstGrade TEXT, elementaryMiddleLastGrade TEXT, hasHighSchool INTEGER, " +
                    "highFirstGrade TEXT, highLastGrade TEXT, hasEja INTEGER, ejaFirstGrade TEXT, ejaLastGrade TEXT, servicesObs TEXT, youngestStudentAge INTEGER, " +
                    "monthYearYoungest INTEGER, oldestStudentAge INTEGER, monthYearOldest INTEGER, numberStudents INTEGER, numberStudentsPCD INTEGER, " +
                    "studentsPCDDescription TEXT, numberWorkers INTEGER, numberWorkersPCD INTEGER, workersPCDDescription TEXT, hasWorkersLibras INTEGER, " +
                    "numberWorkersLibras INTEGER, workersLibrasDescriptions TEXT, initialDateInspection TEXT, finalDateInspection TEXT)");
            database.execSQL("INSERT INTO SchoolEntry_Backup(cadID, schoolName, schoolAddress, addressComplement, addressNumber, addressNeighborhood, nameCity, " +
                    "contactPhone1, contactPhone2, nameResponsibleVisit, nameInspectionTeam, hasMorningClasses, morningStart, morningEnd, hasAfternoonClasses, " +
                    "afternoonStart, afternoonEnd, hasEveningClasses, eveningStart, eveningEnd, workingHoursObs, hasMaternal, maternalFirstGrade, maternalLastGrade, " +
                    "hasPreschool, preschoolFirstGrade, preschoolLastGrade, hasHighSchool, highFirstGrade, highLastGrade, hasEja, ejaFirstGrade, ejaLastGrade, servicesObs, " +
                    "numberStudents, numberStudentsPCD, studentsPCDDescription, numberWorkers, numberWorkersPCD, workersPCDDescription, numberWorkersLibras, " +
                    "initialDateInspection, finalDateInspection) SELECT cadID, schoolName, schoolAddress, addressComplement, addressNumber, addressNeighborhood, nameCity, " +
                    "contactPhone1, contactPhone2, nameResponsibleVisit, nameInspectionTeam, hasMorningClasses, morningStart, morningEnd, hasAfternoonClasses, afternoonStart, " +
                    "afternoonEnd, hasEveningClasses, eveningStart, eveningEnd, workingHoursObs, hasMaternal, maternalFirstGrade, maternalLastGrade, hasPreschool, " +
                    "preschoolFirstGrade, preschoolLastGrade, hasHighSchool, highFirstGrade, highLastGrade, hasEja, ejaFirstGrade, ejaLastGrade, servicesObs, numberStudents, " +
                    "numberStudentsPCD, studentsPcdDescription, numberWorkers, numberWorkersPcd, workersPcdDescription, numberWorkersLibras, dateInspection, " +
                    "dateInspectionEnd FROM SchoolEntry");
            database.execSQL("DROP TABLE SchoolEntry");
            database.execSQL("ALTER TABLE SchoolEntry_Backup RENAME TO SchoolEntry");

            database.execSQL("CREATE TABLE WaterFountainEntry_Backup(waterFountainID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, schoolEntryID INTEGER NOT NULL, " +
                    "fountainLocation TEXT, typeWaterFountain INTEGER, otherAllowSideApproximation INTEGER, otherFaucetHeight REAL, otherHasCupHolder INTEGER, " +
                    "otherCupHolderHeight REAL, hasSpoutsDifferentHeights INTEGER, highestSpoutHeight REAL, lowestSpoutHeight REAL, spoutAllowFrontalApproximation INTEGER, " +
                    "frontalApproxLowestSpout REAL, fountainObs TEXT, FOREIGN KEY (schoolEntryID) REFERENCES SchoolEntry (cadID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("INSERT INTO WaterFountainEntry_Backup (waterFountainID, schoolEntryID, typeWaterFountain, otherAllowSideApproximation, otherFaucetHeight, " +
                    "otherHasCupHolder, otherCupHolderHeight, highestSpoutHeight, lowestSpoutHeight, spoutAllowFrontalApproximation, frontalApproxLowestSpout) " +
                    "SELECT waterFountainID, schoolEntryID, typeWaterFountain, otherAllowSideApproximation, otherFaucetHeight, otherHasCupHolder, otherCupHolderHeight, " +
                    "highestSpoutHeight, lowestSpoutHeight, spoutAllowFrontalApproximation, freeSpaceLowestSpout FROM WaterFountainEntry");
            database.execSQL("DROP TABLE WaterFountainEntry");
            database.execSQL("ALTER TABLE WaterFountainEntry_Backup RENAME TO WaterFountainEntry");

            database.execSQL("CREATE TABLE RoomEntry_backup (roomID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, schoolEntryID INTEGER NOT NULL, roomType INTEGER NOT NULL, " +
                    "roomLocation TEXT, roomHasVisualVertSing INTEGER, roomObsVisualVertSign TEXT, roomHasTactileSing INTEGER, roomObsTactileSign TEXT, " +
                    "roomHasLooseCarpet INTEGER, looseCarpetObs TEXT, libraryDistanceShelvesOK INTEGER, libraryPcrManeuversOK INTEGER, libraryAccessiblePcOK INTEGER, " +
                    "secretHasFixedSeats INTEGER, secretHasPcrSpace INTEGER, secretWidthPcrSpace REAL, secretLengthPcrSpace REAL, secretPCRSpaceObs TEXT, roomObs TEXT, " +
                    "FOREIGN KEY (schoolEntryID) REFERENCES SchoolEntry (cadID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("INSERT INTO RoomEntry_backup (roomID, schoolEntryID, roomType, roomHasVisualVertSing, roomObsVisualVertSign, roomHasTactileSing, " +
                    "roomObsTactileSign, libraryDistanceShelvesOK, libraryPcrManeuversOK, libraryAccessiblePcOK, secretHasFixedSeats, secretHasPcrSpace, " +
                    "secretWidthPcrSpace, secretLengthPcrSpace) SELECT roomID, schoolEntryID, roomType, roomHasVisualVertSing, roomObsVisualVertSign, roomHasTactileSing, " +
                    "roomObsTactileSign, libraryDistanceShelvesOK, libraryPcrManeuversOK, libraryAccessiblePcOK, secretFixedSeats, secretHasPcrSpace, secretWidthPcrSpace, " +
                    "secretLengthPcrSpace FROM RoomEntry");
            database.execSQL("DROP TABLE RoomEntry");
            database.execSQL("ALTER TABLE RoomEntry_backup RENAME TO RoomEntry");

            database.execSQL("CREATE TABLE SidewalkEntry_backup (sidewalkID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, schoolEntryID INTEGER NOT NULL, " +
                    "sidewalkLocation TEXT, sidewalkConservationStatus INTEGER, sidewalkConservationObs TEXT, widthSidewalk REAL, sidewalkHasTactileFloor INTEGER, " +
                    "tactileFloorConservationStatus INTEGER, tactileFloorObs TEXT, sidewalkHasSlope INTEGER, sidewalkObs TEXT, FOREIGN KEY (schoolEntryID) " +
                    "REFERENCES SchoolEntry (cadID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("INSERT INTO SidewalkEntry_backup (sidewalkID, schoolEntryID, sidewalkLocation, sidewalkConservationObs, widthSidewalk, " +
                    "sidewalkHasTactileFloor, tactileFloorConservationStatus, tactileFloorObs,sidewalkHasSlope, sidewalkObs) SELECT sidewalkID, schoolEntryID, " +
                    "sidewalkLocation, sidewalkConservationStatus, widthSidewalk, sidewalkHasTactileFloor, tactileFloorConservationStatus, tactileFloorObs, sidewalkHasSlope, " +
                    "sidewalkObs FROM SidewalkEntry");
            database.execSQL("DROP TABLE SidewalkEntry");
            database.execSQL("ALTER TABLE SidewalkEntry_backup RENAME TO SidewalkEntry");
        }
    };

    static final Migration MIGRATION_28_29 = new Migration(28, 29) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE BlockSpaceEntry(blockSpaceID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, schoolID INTEGER NOT NULL, " +
                    "blockSpaceType INTEGER NOT NULL, blockSpaceNumber INTEGER NOT NULL, FOREIGN KEY (schoolID) REFERENCES SchoolEntry (cadID) " +
                    "ON UPDATE CASCADE ON DELETE CASCADE)");

            database.execSQL("CREATE TABLE ExternalAccess_Backup(externalAccessID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, blockID INTEGER NOT NULL, " +
                    "accessLocation TEXT, entranceType INTEGER, extAccessObs TEXT, hasSIA INTEGER, obsSIA TEXT, floorType TEXT, gateWidth REAL, " +
                    "gateHasTracks INTEGER, gateTrackHeight REAL, gateHasTrackRamp INTEGER, gateSillType INTEGER, sillInclinationHeight REAL, sillStepHeight REAL, " +
                    "sillSlopeAngle REAL, sillSlopeWidth REAL, gateSillObs TEXT, gateHasObstacles INTEGER, gateHasPayphones INTEGER, gateHasIntercom INTEGER, " +
                    "intercomHeight REAL, gateHasSoundSign INTEGER, FOREIGN KEY (blockID) REFERENCES BlockSpaceEntry (blockSpaceID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("INSERT INTO ExternalAccess_Backup (externalAccessID, blockID, entranceType, extAccessObs, hasSIA, floorType, gateWidth, gateTrackHeight, " +
                    "gateHasTrackRamp, gateHasObstacles, gateHasPayphones) SELECT externalAccessID, schoolEntryID, entranceType, externalObs, hasSIA, floorType, " +
                    "gateWidth,gateTrailHeight, gateHasTrailRamp, gateHasObstacles, gateHasPayphones FROM ExternalAccess");
            database.execSQL("DROP TABLE ExternalAccess");
            database.execSQL("ALTER TABLE ExternalAccess_Backup RENAME TO ExternalAccess");
        }
    };

    static final Migration MIGRATION_29_30 = new Migration(29, 30) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE ExternalAccess");
            database.execSQL("CREATE TABLE ExternalAccess(externalAccessID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, blockID INTEGER NOT NULL, " +
                    "accessLocation TEXT, entranceType INTEGER, extAccessObs TEXT, hasSIA INTEGER, obsSIA TEXT, floorType TEXT, gateWidth REAL, " +
                    "gateHasTracks INTEGER, gateTrackHeight REAL, gateHasTrackRamp INTEGER, trackRampQuantity INTEGER, trackRampMeasure1 DOUBLE, trackRampMeasure2 DOUBLE," +
                    "trackRampMeasure3 DOUBLE, trackRampMeasure4 DOUBLE, gateSillType INTEGER, sillInclinationHeight REAL, sillStepHeight REAL, " +
                    "sillSlopeAngle REAL, sillSlopeWidth REAL, gateSillObs TEXT, gateHasObstacles INTEGER, gateHasPayphones INTEGER, gateHasIntercom INTEGER, " +
                    "intercomHeight REAL, gateHasSoundSign INTEGER, FOREIGN KEY (blockID) REFERENCES BlockSpaceEntry (blockSpaceID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("DROP TABLE WaterFountainEntry");
            database.execSQL("CREATE TABLE WaterFountainEntry(waterFountainID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, blockID INTEGER NOT NULL, " +
                    "fountainLocation TEXT, typeWaterFountain INTEGER, otherAllowSideApproximation INTEGER, otherFaucetHeight REAL, otherHasCupHolder INTEGER, " +
                    "otherCupHolderHeight REAL, hasSpoutsDifferentHeights INTEGER, highestSpoutHeight REAL, lowestSpoutHeight REAL, spoutAllowFrontalApproximation INTEGER, " +
                    "frontalApproxLowestSpout REAL, fountainObs TEXT, FOREIGN KEY (blockID) REFERENCES BlockSpaceEntry (blockSpaceID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("DROP TABLE SidewalkEntry");
            database.execSQL("CREATE TABLE SidewalkEntry(sidewalkID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, blockID INTEGER NOT NULL, " +
                    "sidewalkLocation TEXT, sidewalkConservationStatus INTEGER, sidewalkConservationObs TEXT, widthSidewalk REAL, sidewalkHasTactileFloor INTEGER, " +
                    "tactileFloorConservationStatus INTEGER, tactileFloorObs TEXT, sidewalkHasSlope INTEGER, sidewalkObs TEXT, FOREIGN KEY (blockID) REFERENCES " +
                    "BlockSpaceEntry (blockSpaceID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("DROP TABLE ParkingLotEntry");
            database.execSQL("DROP TABLE ParkingLotPCDEntry");
            database.execSQL("DROP TABLE ParkingLotElderlyEntry");
            database.execSQL("CREATE TABLE ParkingLotEntry(parkingLotID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, blockID INTEGER NOT NULL," +
                    "typeParkingLot INTEGER NOT NULL, parkingLotFloorType TEXT, hasPCDVacancy INTEGER NOT NULL, hasElderVacancy INTEGER NOT NULL," +
                    "FOREIGN KEY (blockID) REFERENCES BlockSpaceEntry (blockSpaceID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("CREATE TABLE ParkingLotPCDEntry(parkingPcdID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "parkingLotID INTEGER NOT NULL, hasVisualPcdVertSign INTEGER NOT NULL, vertPcdSignLength REAL, vertPcdSignWidth REAL," +
                    "vertPcdSignObs TEXT, pcdVacancyLength REAL NOT NULL, pcdVacancyWidth REAL NOT NULL, pcdVacancyLimitWidth REAL NOT NULL," +
                    "pcdVacancyObs TEXT, hasSecurityZone INTEGER NOT NULL, securityZoneWidth REAL, securityZoneObs TEXT," +
                    "hasPcdSia INTEGER NOT NULL, pcdSiaWidth REAL, pcdSiaLength REAL, pcdSiaObs TEXT, FOREIGN KEY (parkingLotID)" +
                    "REFERENCES ParkingLotEntry (parkingLotID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("CREATE TABLE ParkingLotElderlyEntry(parkingElderlyID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "parkingLotID INTEGER NOT NULL, hasElderlyVertSign INTEGER NOT NULL, elderlyVertSignLength REAL," +
                    "elderlyVertSingWidth REAL, elderlyVertSignObs TEXT, elderlyVacancyLength REAL NOT NULL," +
                    "elderlyVacancyWidth REAL NOT NULL, elderlyVacancyLimiterWidth REAL NOT NULL, elderlyVacancyObs TEXT," +
                    "hasElderlyFloorIndicator INTEGER NOT NULL, floorIndicatorLength REAL, floorIndicatorWidth REAL, " +
                    "floorIndicatorObs TEXT, FOREIGN KEY (parkingLotID) REFERENCES ParkingLotEntry(parkingLotID)" +
                    "ON UPDATE CASCADE ON DELETE CASCADE)");
        }
    };

    static final Migration MIGRATION_30_31 = new Migration(30, 31) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE PayPhoneEntry");
            database.execSQL("CREATE TABLE PayPhoneEntry(payPhoneID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, externalAccessID INTEGER NOT NULL," +
                    "phoneRefPoint TEXT, phoneKeyboardHeight REAL NOT NULL, phoneHeight REAL NOT NULL, hasTactileFloor INTEGER NOT NULL, phoneIsWorking INTEGER NOT NULL," +
                    "payPhoneObs TEXT, FOREIGN KEY (externalAccessID) REFERENCES ExternalAccess (externalAccessID) ON UPDATE CASCADE ON DELETE CASCADE)");
        }
    };

    static final Migration MIGRATION_31_32 = new Migration(31, 32) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE AdmEquipEntry(admEquipID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, blockID INTEGER NOT NULL, admEquipLocation TEXT," +
                    "hasBellControl INTEGER NOT NULL, bellControlHeight REAL, bellControlObs TEXT, hasInternalPhone INTEGER NOT NULL, internalPhoneHeight REAL," +
                    "internalPhoneObs TEXT, hasBiometricClock INTEGER NOT NULL, biometricClockHeight REAL, biometricClockObs TEXT, FOREIGN KEY (blockID) " +
                    "REFERENCES BlockSpaceEntry (blockSpaceID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("CREATE TABLE PlaygroundEntry(playgroundID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, blockID INTEGER NOT NULL, playLocation TEXT," +
                    "playFloorType TEXT, playGateWidth REAL, playGateHasFloorTrack INTEGER, playFloorTrackHeight REAL, playFloorTrackHasRamp INTEGER, " +
                    "rampMeasureQnt INTEGER, rampMeasure1 REAL, rampMeasure2 REAL, rampMeasure3 REAL, rampMeasure4 REAL, playGateSillType INTEGER," +
                    "inclinationSillHeight REAL, stepSillHeight REAL, slopeSillAngle REAL, slopeSillWidth REAL, sillObs TEXT, accessibleFloor INTEGER, " +
                    "accessibleFloorObs TEXT, accessibleEquip INTEGER, accessibleEquipObs TEXT, playgroundObs TEXT, FOREIGN KEY (blockID) " +
                    "REFERENCES BlockSpaceEntry (blockSpaceID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("DROP TABLE OtherSpaces");
            database.execSQL("DROP TABLE RampStairsEntry");
            database.execSQL("DROP TABLE RoomEntry");
            database.execSQL("CREATE TABLE RoomEntry (roomID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, blockID INTEGER NOT NULL, roomType INTEGER NOT NULL, " +
                    "roomLocation TEXT, roomHasVisualVertSing INTEGER, roomObsVisualVertSign TEXT, roomHasTactileSing INTEGER, roomObsTactileSign TEXT, " +
                    "roomHasLooseCarpet INTEGER, looseCarpetObs TEXT, libraryDistanceShelvesOK INTEGER, libraryPcrManeuversOK INTEGER, libraryAccessiblePcOK INTEGER, " +
                    "secretHasFixedSeats INTEGER, secretHasPcrSpace INTEGER, secretWidthPcrSpace REAL, secretLengthPcrSpace REAL, secretPCRSpaceObs TEXT, roomObs TEXT, " +
                    "FOREIGN KEY (blockID) REFERENCES BlockSpaceEntry (blockSpaceID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("CREATE TABLE RampStairsEntry(rampStairsID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, blockID INTEGER NOT NULL," +
                    "rampStairsIdentifier INTEGER NOT NULL, rampStairsLocation TEXT, flightsQuantity INTEGER NOT NULL, " +
                    "FOREIGN KEY (blockID) REFERENCES BlockSpaceEntry (blockSpaceID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("CREATE TABLE OtherSpaces (otherSpacesID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "blockID INTEGER NOT NULL, otherSpaceName TEXT, otherSpaceDescription TEXT, isAccessible INTEGER NOT NULL," +
                    "FOREIGN KEY (blockID) REFERENCES BlockSpaceEntry (blockSpaceID) ON UPDATE CASCADE ON DELETE CASCADE)");
        }
    };

    static final Migration MIGRATION_32_33 = new Migration(32, 33) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE SidewalkSlopeEntry");
            database.execSQL("CREATE TABLE SidewalkSlopeEntry(sidewalkSlopeID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, sidewalkID INTEGER NOT NULL," +
                    "slopeLocation TEXT, slopeWidth REAL NOT NULL, longMeasureQnt INTEGER NOT NULL, longMeasure1 REAL, longMeasure2 REAL, longMeasure3 REAL," +
                    "longMeasure4 REAL, hasLeftWingSlope INTEGER NOT NULL, leftWingMeasureQnt INTEGER, leftMeasure1 REAL, leftMeasure2 REAL, leftMeasure3 REAL," +
                    "leftMeasure4 REAL, hasRightWingSlope INTEGER NOT NULL, rightWingMeasureQnt INTEGER, rightMeasure1 REAL, rightMeasure2 REAL, rightMeasure3 REAL," +
                    "rightMeasure4 REAL, hasTactileFloor INTEGER NOT NULL, tactileFloorObs TEXT, accessibleSlopeFloor INTEGER NOT NULL, accessibleSlopeFloorObs TEXT," +
                    "slopeObs TEXT, FOREIGN KEY (sidewalkID) REFERENCES SidewalkEntry (sidewalkID) ON UPDATE CASCADE ON DELETE CASCADE)");
        }
    };

    static final Migration MIGRATION_33_34 = new Migration(33,34) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE DoorEntry");
            database.execSQL("DROP TABLE FreeSpaceEntry");
            database.execSQL("DROP TABLE SwitchEntry");
            database.execSQL("DROP TABLE TableEntry");
            database.execSQL("DROP TABLE WindowEntry");
            database.execSQL("CREATE TABLE DoorEntry(doorID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, blockID INTEGER NOT NULL, roomID INTEGER NOT NULL," +
                    " doorLocation TEXT, doorWidth REAL, doorSillType INTEGER, sillInclinationHeight REAL, sillStepHeight REAL, sillSlopeHeight REAL, " +
                    "sillSlopeWidth REAL, doorObs TEXT, FOREIGN KEY (blockID) REFERENCES BlockSpaceEntry (blockSpaceID) ON UPDATE CASCADE ON DELETE CASCADE, " +
                    "FOREIGN KEY (roomID) REFERENCES RoomEntry (roomID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("CREATE TABLE FreeSpaceEntry (freeSpaceID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, blockID INTEGER NOT NULL, roomID INTEGER NOT NULL," +
                    "freeSpaceLocation TEXT, freeSpaceWidth REAL, freeSpaceObs TEXT, " +
                    "FOREIGN KEY (blockID) REFERENCES BlockSpaceEntry (blockSpaceID) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (roomID) REFERENCES RoomEntry (roomID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("CREATE TABLE SwitchEntry (switchID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, blockID INTEGER NOT NULL, roomID INTEGER NOT NULL, " +
                    "switchLocation TEXT, switchType TEXT, switchHeight REAL, switchObs TEXT, " +
                    "FOREIGN KEY (blockID) REFERENCES BlockSpaceEntry (blockSpaceID) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (roomID) REFERENCES RoomEntry (roomID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("CREATE TABLE TableEntry (tableID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, blockID INTEGER NOT NULL, roomID INTEGER NOT NULL, " +
                    "isClassroom INTEGER NOT NULL, tableType INTEGER, inferiorBorderHeight REAL, superiorBorderHeight REAL, tableWidth REAL, tableFrontalApprox REAL, " +
                    "tableObs TEXT, FOREIGN KEY (blockID) REFERENCES BlockSpaceEntry (blockSpaceID) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (roomID) REFERENCES RoomEntry (roomID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("CREATE TABLE WindowEntry (windowID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, blockID INTEGER NOT NULL, roomID INTEGER NOT NULL, " +
                    "windowLocation TEXT, windowSillHeight REAL, windowObs TEXT, FOREIGN KEY (blockID) REFERENCES BlockSpaceEntry (blockSpaceID) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (roomID) REFERENCES RoomEntry (roomID) ON UPDATE CASCADE ON DELETE CASCADE)");
        }
    };

    public static ReportDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ReportDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ReportDatabase.class, "ReportDatabase")
                            .addCallback(roomCallback).addMigrations(MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_6,
                                    MIGRATION_6_7, MIGRATION_7_8, MIGRATION_8_9, MIGRATION_9_10, MIGRATION_10_11, MIGRATION_11_12,
                                    MIGRATION_12_13, MIGRATION_13_14, MIGRATION_14_15, MIGRATION_15_16, MIGRATION_16_17, MIGRATION_17_18,
                                    MIGRATION_18_19, MIGRATION_19_20, MIGRATION_20_21, MIGRATION_21_22, MIGRATION_22_23, MIGRATION_23_24,
                                    MIGRATION_24_25, MIGRATION_25_26, MIGRATION_26_27, MIGRATION_27_28, MIGRATION_28_29, MIGRATION_29_30,
                                    MIGRATION_30_31, MIGRATION_31_32, MIGRATION_32_33, MIGRATION_33_34).build();
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

    public abstract ParkingLotPcdDao parkingLotPdmrDao();

    public abstract RoomEntryDao roomEntryDao();

    public abstract DoorEntryDao doorEntryDao();

    public abstract SwitchEntryDao switchEntryDao();

    public abstract FreeSpaceEntryDao freeSpaceEntryDao();

    public abstract TableEntryDao tableEntryDao();

    public abstract WindowEntryDao windowEntryDao();

    public abstract GateObsDao gateObsDao();

    public abstract PayPhoneDao payPhoneDao();

    public abstract CounterEntryDao counterEntryDao();

    public abstract RampStairsEntryDao rampStairsDao();

    public abstract FlightRampStairsDao flightRampStairsDao();

    public abstract RestroomEntryDao restroomEntryDao();

    public abstract RestroomMirrorDao restroomMirrorDao();

    public abstract RestroomSinkDao restroomSinkDao();

    public abstract RestroomSupportBarDao restroomSupportBarDao();

    public abstract RestroomUpViewDao restroomUpViewDao();

    public abstract RestroomUrinalDao restroomUrinalDao();

    public abstract SidewalkEntryDao sidewalkEntryDao();

    public abstract SidewalkSlopeDao sidewalkSlopeDao();

    public abstract StairsStepDao stairsStepDao();

    public abstract StairsMirrorDao stairsMirrorDao();

    public abstract RampInclinationDao rampInclinationDao();

    public abstract RampStairsHandrailDao rampStairsHandrailDao();

    public abstract RampStairsRailingDao rampStairsRailingDao();

    public abstract BlockSpaceDao blockSpaceDao();

    public abstract AdmEquipDao admEquipDao();

    public abstract PlaygroundEntryDao playgroundEntryDao();

}