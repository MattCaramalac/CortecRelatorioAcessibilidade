package com.mpms.relatorioacessibilidadecortec.data.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.mpms.relatorioacessibilidadecortec.data.Dao.BlackboardEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.BlockSpaceDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.CounterEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.DoorEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.DoorLockDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.EquipEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.ExternalAccessDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.FlightRampStairsDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.FreeSpaceEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.GateObsDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.ParkingLotElderlyDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.ParkingLotEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.ParkingLotPcdDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.PayPhoneDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.PlaygroundEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.RampStairsEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.RampStairsHandrailDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.RampStairsRailingDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.RestBoxDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.RestroomEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.RoomEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.SchoolEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.SidewalkEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.SidewalkSlopeDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.SwitchEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.TableEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.WaterFountainDao;
import com.mpms.relatorioacessibilidadecortec.data.Dao.WindowEntryDao;
import com.mpms.relatorioacessibilidadecortec.data.entities.BlackboardEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.BlockSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.CounterEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.DoorLockEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.EquipmentEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ExternalAccess;
import com.mpms.relatorioacessibilidadecortec.data.entities.FreeSpaceEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.GateObsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotElderlyEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.ParkingLotPCDEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PayPhoneEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.PlaygroundEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsFlightEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsHandrailEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RampStairsRailingEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestBoxEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RestroomEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.RoomEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SchoolEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SidewalkSlopeEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.SwitchEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.TableEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.WaterFountainEntry;
import com.mpms.relatorioacessibilidadecortec.data.entities.WindowEntry;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {SchoolEntry.class, WaterFountainEntry.class, ExternalAccess.class,
        ParkingLotEntry.class, ParkingLotPCDEntry.class, ParkingLotElderlyEntry.class, RoomEntry.class, DoorEntry.class,
        FreeSpaceEntry.class, SwitchEntry.class, TableEntry.class, WindowEntry.class, GateObsEntry.class, PayPhoneEntry.class,
        CounterEntry.class, RampStairsEntry.class, RampStairsFlightEntry.class, RestroomEntry.class, SidewalkEntry.class,
        SidewalkSlopeEntry.class, RampStairsHandrailEntry.class, RampStairsRailingEntry.class, BlockSpaceEntry.class,
        PlaygroundEntry.class, BlackboardEntry.class, DoorLockEntry.class, RestBoxEntry.class, EquipmentEntry.class}, version = 68)
public abstract class ReportDatabase extends RoomDatabase {

    public static final int NUMBER_THREADS = 8;
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
                        SidewalkEntryDao sidewalkEntryDao = INSTANCE.sidewalkEntryDao();
                    });
                    dbWriteExecutor.execute(() -> {
                        SidewalkSlopeDao sidewalkSlopeDao = INSTANCE.sidewalkSlopeDao();
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
                        PlaygroundEntryDao playgroundEntryDao = INSTANCE.playgroundEntryDao();
                    });

                    dbWriteExecutor.execute(() -> {
                        BlackboardEntryDao blackboardEntryDao = INSTANCE.blackboardEntryDao();
                    });

                    dbWriteExecutor.execute(() -> {
                        DoorLockDao doorLockDao = INSTANCE.doorLockDao();
                    });
                    dbWriteExecutor.execute(() -> {
                        RestBoxDao restBoxDao = INSTANCE.restBoxDao();
                    });
                    dbWriteExecutor.execute(() -> {
                        EquipEntryDao equipEntryDao = INSTANCE.equipEntryDao();
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

    static final Migration MIGRATION_33_34 = new Migration(33, 34) {
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

    static final Migration MIGRATION_34_35 = new Migration(34, 35) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE BlackboardEntry (boardID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, roomID INTEGER NOT NULL, boardLocation TEXT, " +
                    "infBorderHeight REAL NOT NULL, boardObs TEXT, FOREIGN KEY (roomID) REFERENCES RoomEntry (roomID) ON UPDATE CASCADE ON DELETE CASCADE)");

            database.execSQL("CREATE TABLE DoorLockEntry (lockID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, doorID INTEGER NOT NULL, lockType INTEGER NOT NULL," +
                    "lockDesc TEXT, lockHeight REAL NOT NULL, lockObs TEXT, FOREIGN KEY (doorID) REFERENCES DoorEntry (doorID) ON UPDATE CASCADE ON DELETE CASCADE)");

            database.execSQL("ALTER TABLE PlaygroundEntry ADD COLUMN slopeMeasureQnt INTEGER");
            database.execSQL("ALTER TABLE PlaygroundEntry ADD COLUMN slopeSillAngle2 REAL");
            database.execSQL("ALTER TABLE PlaygroundEntry ADD COLUMN slopeSillAngle3 REAL");
            database.execSQL("ALTER TABLE PlaygroundEntry ADD COLUMN slopeSillAngle4 REAL");

            database.execSQL("ALTER TABLE ExternalAccess ADD COLUMN slopeMeasureQnt INTEGER");
            database.execSQL("ALTER TABLE ExternalAccess ADD COLUMN sillSlopeAngle2 REAL");
            database.execSQL("ALTER TABLE ExternalAccess ADD COLUMN sillSlopeAngle3 REAL");
            database.execSQL("ALTER TABLE ExternalAccess ADD COLUMN sillSlopeAngle4 REAL");


            database.execSQL("CREATE TABLE DoorEntry_2(doorID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, roomID INTEGER NOT NULL," +
                    "doorLocation TEXT, doorWidth REAL, doorHandleType INTEGER, doorHandleHeight REAL, doorHandleObs TEXT, doorHasLocks INTEGER," +
                    "doorHasTactileSign INTEGER, doorTactileSignObs TEXT, doorSillType INTEGER, sillInclinationHeight REAL, sillStepHeight REAL, sillSlopeQnt INTEGER," +
                    "sillSlopeAngle1 REAL, sillSlopeAngle2 REAL, sillSlopeAngle3 REAL, sillSlopeAngle4 REAL, sillSlopeWidth REAL, doorSillObs TEXT, doorObs TEXT, " +
                    "FOREIGN KEY (roomID) REFERENCES RoomEntry (roomID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("INSERT INTO DoorEntry_2 (doorID, roomID, doorLocation, doorWidth, doorSillType, sillInclinationHeight, sillStepHeight, sillSlopeAngle1," +
                    "sillSlopeWidth, doorObs) SELECT doorID, roomID, doorLocation, doorWidth, doorSillType, sillInclinationHeight, sillStepHeight, sillSlopeHeight," +
                    "sillSlopeWidth, doorObs FROM DoorEntry");
            database.execSQL("DROP TABLE DoorEntry");
            database.execSQL("ALTER TABLE DoorEntry_2 RENAME TO DoorEntry");

            database.execSQL("CREATE TABLE FreeSpaceEntry_2 (freeSpaceID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, roomID INTEGER NOT NULL, freeSpaceLocation TEXT," +
                    "freeSpaceWidth REAL, freeSpaceObs TEXT, FOREIGN KEY (roomID) REFERENCES RoomEntry (roomID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("INSERT INTO FreeSpaceEntry_2(freeSpaceID, roomID, freeSpaceLocation, freeSpaceWidth, freeSpaceObs) SELECT freeSpaceID, roomID," +
                    "freeSpaceLocation, freeSpaceWidth, freeSpaceObs FROM FreeSpaceEntry");
            database.execSQL("DROP TABLE FreeSpaceEntry");
            database.execSQL("ALTER TABLE FreeSpaceEntry_2 RENAME TO FreeSpaceEntry");

            database.execSQL("CREATE TABLE RoomEntry_2 (roomID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, blockID INTEGER NOT NULL, roomType INTEGER NOT NULL," +
                    "roomLocation TEXT, roomDescription TEXT, roomHasVertSing INTEGER, roomVertSignObs TEXT, roomHasLooseCarpet INTEGER, looseCarpetObs TEXT," +
                    "roomAccessFloor INTEGER, accessFloorObs TEXT, libDistShelvesOK INTEGER, libPcrManeuversOK INTEGER, libAccessPcOK INTEGER, secHasFixedSeats INTEGER," +
                    "secHasPcrSpace INTEGER, secPcrSpaceWidth REAL, secPcrSpaceDepth REAL, secPCRSpaceObs TEXT, roomObs TEXT, FOREIGN KEY (blockID) " +
                    "REFERENCES BlockSpaceEntry (blockSpaceID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("INSERT INTO RoomEntry_2 (roomID, blockID, roomType, roomLocation, roomHasVertSing, roomVertSignObs, roomHasLooseCarpet, looseCarpetObs," +
                    "libDistShelvesOK, libPcrManeuversOK, libAccessPcOK, secHasFixedSeats, secHasPcrSpace, secPcrSpaceWidth, secPcrSpaceDepth, secPCRSpaceObs, roomObs)" +
                    "SELECT roomID, blockID, roomType, roomLocation, roomHasVisualVertSing, roomObsVisualVertSign, roomHasLooseCarpet, looseCarpetObs, libraryDistanceShelvesOK," +
                    "libraryPcrManeuversOK, libraryAccessiblePcOK, secretHasFixedSeats, secretHasPcrSpace, secretWidthPcrSpace, secretLengthPcrSpace, secretPCRSpaceObs," +
                    "roomObs FROM RoomEntry");
            database.execSQL("DROP TABLE RoomEntry");
            database.execSQL("ALTER TABLE RoomEntry_2 RENAME TO RoomEntry");

            database.execSQL("CREATE TABLE SwitchEntry_2 (switchID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, roomID INTEGER NOT NULL, switchLocation TEXT, switchType TEXT," +
                    "switchHeight REAL, switchObs TEXT, FOREIGN KEY (roomID) REFERENCES RoomEntry (roomID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("INSERT INTO SwitchEntry_2 (switchID, roomID, switchLocation, switchType, switchHeight, switchObs) SELECT switchID, roomID, switchLocation, " +
                    "switchType, switchHeight, switchObs FROM SwitchEntry");
            database.execSQL("DROP TABLE SwitchEntry");
            database.execSQL("ALTER TABLE SwitchEntry_2 RENAME TO SwitchEntry");

            database.execSQL("CREATE TABLE TableEntry_2 (tableID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, roomID INTEGER NOT NULL, roomType INTEGER NOT NULL DEFAULT 0," +
                    "tableType INTEGER, inferiorBorderHeight REAL NOT NULL, superiorBorderHeight REAL NOT NULL, tableWidth REAL NOT NULL, tableFrontalApprox REAL NOT NULL, " +
                    "tableObs TEXT, FOREIGN KEY (roomID) REFERENCES RoomEntry (roomID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("INSERT INTO TableEntry_2 (tableID, roomID, tableType, inferiorBorderHeight, superiorBorderHeight, tableWidth, tableFrontalApprox," +
                    "tableObs) SELECT tableID, roomID, tableType, inferiorBorderHeight, superiorBorderHeight, tableWidth, tableFrontalApprox, tableObs FROM TableEntry");
            database.execSQL("DROP TABLE TableEntry");
            database.execSQL("ALTER TABLE TableEntry_2 RENAME TO TableEntry");

            database.execSQL("CREATE TABLE WindowEntry_2 (windowID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, roomID INTEGER NOT NULL, " +
                    "windowLocation TEXT, windowCommandHeight REAL, windowObs TEXT, FOREIGN KEY (roomID) REFERENCES RoomEntry (roomID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("INSERT INTO WindowEntry_2 (windowID, roomID, windowLocation, windowCommandHeight, windowObs) SELECT windowID, roomID, windowLocation, " +
                    "windowSillHeight, windowObs FROM WindowEntry");
            database.execSQL("DROP TABLE WindowEntry");
            database.execSQL("ALTER TABLE WindowEntry_2 RENAME TO WindowEntry");

        }
    };

    static final Migration MIGRATION_35_36 = new Migration(35, 36) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE SidewalkEntry_2(sidewalkID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, blockID INTEGER NOT NULL, sidewalkLocation TEXT," +
                    "streetPavement INTEGER, sidewalkWidth REAL, sideFreeSpaceWidth REAL, sideMeasureObs TEXT, slopeMeasureQnt INTEGER, sideTransSlope1 REAL, " +
                    "sideTransSlope2 REAL, sideTransSlope3 REAL, sideTransSlope4 REAL, sideTransSlope5 REAL, sideTransSlope6 REAL, hasSpecialFloor INTEGER, " +
                    "specialFloorRightColor INTEGER, specialTileDirectionLength REAL, specialTileDirectionWidth REAL, specialTileAlertLength REAL, specialTileAlertWidth REAL, " +
                    "specialFloorObs TEXT, sideFloorIsAccessible INTEGER, accessFloorObs TEXT, sideHasSlope INTEGER, sidewalkObs TEXT, " +
                    "FOREIGN KEY (blockID) REFERENCES BlockSpaceEntry (blockSpaceID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("INSERT INTO SidewalkEntry_2 (sidewalkID, blockID, sidewalkLocation, sidewalkWidth, hasSpecialFloor, specialFloorObs, sideHasSlope, sidewalkObs)" +
                    "SELECT sidewalkID, blockID, sidewalkLocation, widthSidewalk, sidewalkHasTactileFloor, tactileFloorObs, sidewalkHasSlope, sidewalkObs FROM SidewalkEntry");
            database.execSQL("DROP TABLE SidewalkEntry");
            database.execSQL("ALTER TABLE SidewalkEntry_2 RENAME TO SidewalkEntry");
        }
    };

    static final Migration MIGRATION_36_37 = new Migration(36, 37) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE SidewalkEntry ADD COLUMN hasAerialObstacle INTEGER");
            database.execSQL("ALTER TABLE SidewalkEntry ADD COLUMN aerialObstacleDesc TEXT");
            database.execSQL("ALTER TABLE SidewalkEntry ADD COLUMN sidewalkHasLids INTEGER");
            database.execSQL("ALTER TABLE SidewalkEntry ADD COLUMN sidewalkLidDesc TEXT");
        }
    };

    static final Migration MIGRATION_37_38 = new Migration(37, 38) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE SidewalkEntry ADD COLUMN sideConStatus INTEGER");
            database.execSQL("ALTER TABLE SidewalkEntry ADD COLUMN sideConsObs TEXT");

        }
    };

    static final Migration MIGRATION_38_39 = new Migration(38, 39) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE SidewalkSlopeEntry ADD COLUMN streetSlopeJunction INTEGER");
            database.execSQL("ALTER TABLE SidewalkSlopeEntry ADD COLUMN inclinationJunctionHeight REAL");
            database.execSQL("ALTER TABLE SidewalkSlopeEntry ADD COLUMN stepJunctionHeight REAL");
            database.execSQL("ALTER TABLE SidewalkSlopeEntry ADD COLUMN streetSlopeObs TEXT");

            database.execSQL("ALTER TABLE WaterFountainEntry ADD COLUMN fountainTypeObs TEXT");
            database.execSQL("ALTER TABLE WaterFountainEntry ADD COLUMN lateralApproxObs TEXT");
            database.execSQL("ALTER TABLE WaterFountainEntry ADD COLUMN spoutFrontalApproxDepth REAL");
        }
    };

    static final Migration MIGRATION_39_40 = new Migration(39, 40) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE ExternalAccess2(externalAccessID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, blockID INTEGER NOT NULL, " +
                    "accessLocation TEXT, entranceType INTEGER, floorIsAccessible INTEGER, accessibleFloorObs TEXT, hasSIA INTEGER, obsSIA TEXT, " +
                    "entranceGateType INTEGER, entranceGateDesc TEXT, freeSpaceWidth1 REAL, freeSpaceWidth2 REAL, gateHandleType INTEGER, gateHandleHeight REAL, gateObs TEXT," +
                    "gateHasTracks INTEGER, gateTrackHeight REAL, gateHasTrackRamp INTEGER, trackRampQuantity INTEGER, trackRampMeasure1 REAL, trackRampMeasure2 REAL," +
                    "trackRampMeasure3 REAL, trackRampMeasure4 REAL, gateSillType INTEGER, sillInclinationHeight REAL, sillStepHeight REAL, slopeMeasureQnt INTEGER," +
                    "sillSlopeAngle REAL, sillSlopeAngle2 REAL, sillSlopeAngle3 REAL, sillSlopeAngle4 REAL, sillSlopeWidth REAL, gateSillObs TEXT, gateHasObstacles INTEGER," +
                    "gateHasPayphones INTEGER, gateHasIntercom INTEGER, intercomHeight REAL, gateHasSoundSign INTEGER, extAccessObs TEXT," +
                    "FOREIGN KEY (blockID) REFERENCES BlockSpaceEntry (blockSpaceID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("INSERT INTO ExternalAccess2 (externalAccessID, blockID, accessLocation, entranceType, hasSIA, obsSIA, gateHasTracks, gateTrackHeight," +
                    "gateHasTrackRamp, trackRampQuantity, trackRampMeasure1, trackRampMeasure2, trackRampMeasure3, trackRampMeasure4, gateSillType, sillInclinationHeight," +
                    "sillStepHeight, sillSlopeAngle, sillSlopeWidth, gateSillObs, gateHasObstacles, gateHasPayphones, gateHasIntercom, intercomHeight, gateHasSoundSign," +
                    "extAccessObs) SELECT externalAccessID, blockID, accessLocation, entranceType, hasSIA, obsSIA, gateHasTracks, gateTrackHeight, gateHasTrackRamp," +
                    "trackRampQuantity, trackRampMeasure1, trackRampMeasure2, trackRampMeasure3, trackRampMeasure4, gateSillType, sillInclinationHeight, sillStepHeight," +
                    "sillSlopeAngle, sillSlopeWidth, gateSillObs, gateHasObstacles, gateHasPayphones, gateHasIntercom, intercomHeight, gateHasSoundSign, extAccessObs FROM ExternalAccess");
            database.execSQL("DROP TABLE ExternalAccess");
            database.execSQL("ALTER TABLE ExternalAccess2 RENAME TO ExternalAccess");

            database.execSQL("CREATE TABLE DoorLockEntry2(lockID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, doorID INTEGER, extAccessID INTEGER, lockType INTEGER NOT NULL," +
                    "lockDesc TEXT, lockHeight REAL NOT NULL, lockObs TEXT, FOREIGN KEY (doorID) REFERENCES DoorEntry (doorID) ON UPDATE CASCADE ON DELETE CASCADE," +
                    " FOREIGN KEY (extAccessID) REFERENCES ExternalAccess (externalAccessID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("INSERT INTO DoorLockEntry2(lockID, doorID, lockType, lockDesc, lockHeight, lockObs) SELECT lockID, doorID, lockType, lockDesc, lockHeight, lockObs " +
                    "FROM DoorLockEntry");
            database.execSQL("DROP TABLE DoorLockEntry");
            database.execSQL("ALTER TABLE DoorLockEntry2 RENAME TO DoorLockEntry");
        }
    };

    static final Migration MIGRATION_40_41 = new Migration(40, 41) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE GateObsEntry2(gateObsID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, externalAccessID INTEGER NOT NULL, accessRefPoint TEXT," +
                    "accessType INTEGER, gateDoorHeight REAL, gateDoorWidth REAL, barrierHeight REAL, barrierWidth REAL, obsHasSia INTEGER, gateObstacleObs TEXT," +
                    "FOREIGN KEY (externalAccessID) REFERENCES ExternalAccess (externalAccessID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("INSERT INTO GateObsEntry2(gateObsID, externalAccessID, accessRefPoint, accessType, gateDoorWidth, barrierHeight, barrierWidth, gateObstacleObs) " +
                    "SELECT gateObsID, externalAccessID, accessRefPoint, accessType, gateDoorWidth, barrierHeight, barrierWidth, gateObstacleObs FROM GateObsEntry");
            database.execSQL("DROP TABLE GateObsEntry");
            database.execSQL("ALTER TABLE GateObsEntry2 RENAME TO GateObsEntry");

            database.execSQL("CREATE TABLE PayPhoneEntry2(payPhoneID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, extAccessID INTEGER, sidewalkID INTEGER," +
                    "phoneRefPoint TEXT, phoneKeyboardHeight REAL NOT NULL, phoneHeight REAL NOT NULL, hasTactileFloor INTEGER NOT NULL, rightColorTactileFloor INTEGER," +
                    "tactFloorLength REAL, tactFloorWidth REAL, tactFloorObs TEXT, phoneIsWorking INTEGER NOT NULL, payPhoneObs TEXT," +
                    "FOREIGN KEY (extAccessID) REFERENCES ExternalAccess (externalAccessID) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (sidewalkID) REFERENCES SidewalkEntry (sidewalkID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("INSERT INTO PayPhoneEntry2 (payPhoneID, extAccessID, phoneRefPoint, phoneKeyboardHeight, phoneHeight, hasTactileFloor, phoneIsWorking, payPhoneObs)" +
                    " SELECT payPhoneID, externalAccessID, phoneRefPoint, phoneKeyboardHeight, phoneHeight, hasTactileFloor, phoneIsWorking, payPhoneObs FROM PayPhoneEntry");
            database.execSQL("DROP TABLE PayPhoneEntry");
            database.execSQL("ALTER TABLE PayPhoneEntry2 RENAME TO PayPhoneEntry");

            database.execSQL("ALTER TABLE SidewalkEntry ADD COLUMN sideHasPayphones INTEGER");
        }

    };

    static final Migration MIGRATION_41_42 = new Migration(41, 42) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("UPDATE RoomEntry SET roomType = 2 WHERE roomType = 3");
            database.execSQL("UPDATE RoomEntry SET roomType = 3 WHERE roomType = 5");
            database.execSQL("UPDATE RoomEntry SET roomType = 4 WHERE roomType = 6");
            database.execSQL("UPDATE RoomEntry SET roomType = 5 WHERE roomType = 10");
            database.execSQL("UPDATE RoomEntry SET roomType = 6 WHERE roomType = 11");
            database.execSQL("UPDATE RoomEntry SET roomType = 7 WHERE roomType = 12");
            database.execSQL("UPDATE RoomEntry SET roomType = 8 WHERE roomType = 13");
            database.execSQL("UPDATE RoomEntry SET roomType = 9 WHERE roomType = 14");
            database.execSQL("UPDATE RoomEntry SET roomType = 11 WHERE roomType = 15");
            database.execSQL("UPDATE RoomEntry SET roomType = 12 WHERE roomType = 16");

            database.execSQL("CREATE TABLE WaterFountainEntry2(waterFountainID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, blockID INTEGER NOT NULL, roomID INTEGER, " +
                    "fountainLocation TEXT, fountainType INTEGER, fountainTypeObs TEXT, allowSideApprox INTEGER, sideApproxObs TEXT, faucetHeight REAL, " +
                    "hasCupHolder INTEGER, cupHolderHeight REAL, hasSpoutsDifferentHeights INTEGER, highestSpoutHeight REAL, lowestSpoutHeight REAL, " +
                    "allowFrontApprox INTEGER, frontalApproxDepth REAL, frontalApproxLowestSpout REAL, fountainObs TEXT, " +
                    "FOREIGN KEY (blockID) REFERENCES BlockSpaceEntry (blockSpaceID) ON UPDATE CASCADE ON DELETE CASCADE, " +
                    "FOREIGN KEY (roomID) REFERENCES RoomEntry (roomID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("INSERT INTO WaterFountainEntry2 (waterFountainID, blockID, fountainLocation, fountainType, fountainTypeObs, allowSideApprox, sideApproxObs, " +
                    "faucetHeight, hasCupHolder, cupHolderHeight, hasSpoutsDifferentHeights, highestSpoutHeight, lowestSpoutHeight, allowFrontApprox, frontalApproxDepth, " +
                    "frontalApproxLowestSpout, fountainObs) SELECT waterFountainID, blockID, fountainLocation, typeWaterFountain, fountainTypeObs,  otherAllowSideApproximation, " +
                    "lateralApproxObs, otherFaucetHeight, otherHasCupHolder, otherCupHolderHeight, hasSpoutsDifferentHeights, highestSpoutHeight, lowestSpoutHeight, " +
                    "spoutAllowFrontalApproximation, spoutFrontalApproxDepth, frontalApproxLowestSpout, fountainObs FROM WaterFountainEntry");
            database.execSQL("DROP TABLE WaterFountainEntry");
            database.execSQL("ALTER TABLE WaterFountainEntry2 RENAME TO WaterFountainEntry");

            database.execSQL("CREATE TABLE ParkingLotEntry2(parkingLotID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, blockID INTEGER NOT NULL, sidewalkID INTEGER," +
                    "typeParkingLot INTEGER NOT NULL, parkingLotFloorType TEXT, hasPCDVacancy INTEGER NOT NULL, hasElderVacancy INTEGER NOT NULL," +
                    "FOREIGN KEY (blockID) REFERENCES BlockSpaceEntry (blockSpaceID) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (sidewalkID) REFERENCES SidewalkEntry (sidewalkID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("INSERT INTO ParkingLotEntry2(parkingLotID, blockID, typeParkingLot, parkingLotFloorType, hasPCDVacancy, hasElderVacancy) SELECT " +
                    "parkingLotID, blockID, typeParkingLot, parkingLotFloorType, hasPCDVacancy, hasElderVacancy FROM ParkingLotEntry");
            database.execSQL("DROP TABLE ParkingLotEntry");
            database.execSQL("ALTER TABLE ParkingLotEntry2 RENAME TO ParkingLotEntry");
        }
    };

    static final Migration MIGRATION_42_43 = new Migration(42, 43) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE RampStairsEntry");
            database.execSQL("CREATE TABLE RampStairsEntry(rampStairsID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, blockID INTEGER NOT NULL, ambientType INTEGER NOT NULL," +
                    "ambientID INTEGER NOT NULL, rampStairsIdentifier INTEGER NOT NULL, rampStairsLocation TEXT, flightsQuantity INTEGER NOT NULL, " +
                    "FOREIGN KEY (blockID) REFERENCES BlockSpaceEntry (blockSpaceID) ON UPDATE CASCADE ON DELETE CASCADE)");
        }
    };

    static final Migration MIGRATION_43_44 = new Migration(43, 44) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE RampStairsEntry");
            database.execSQL("CREATE TABLE RampStairsEntry(rampStairsID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, blockID INTEGER NOT NULL, ambientType INTEGER NOT NULL," +
                    "ambientID INTEGER NOT NULL, rampStairsIdentifier INTEGER NOT NULL, rampStairsLocation TEXT, flightsQuantity INTEGER NOT NULL, " +
                    "FOREIGN KEY (blockID) REFERENCES BlockSpaceEntry (blockSpaceID) ON UPDATE CASCADE ON DELETE CASCADE)");

            database.execSQL("CREATE TABLE Park2(parkingID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, blockID INTEGER NOT NULL, sidewalkID INTEGER," +
                    "typeParkingLot INTEGER NOT NULL, parkAccessFloor INTEGER, parkFloorObs TEXT, hasPCDVacancy INTEGER, hasElderVacancy INTEGER, " +
                    "parkAccessRoute INTEGER, parkAccessRouteObs TEXT, parkingHasStairs INTEGER, parkingHasRamps INTEGER," +
                    "FOREIGN KEY (blockID) REFERENCES BlockSpaceEntry (blockSpaceID) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (sidewalkID) REFERENCES SidewalkEntry (sidewalkID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("INSERT INTO Park2(parkingID, blockID, sidewalkID, typeParkingLot, parkFloorObs, hasPCDVacancy, hasElderVacancy) SELECT parkingLotID," +
                    "blockID, sidewalkID, typeParkingLot, parkingLotFloorType, hasPCDVacancy, hasElderVacancy FROM ParkingLotEntry");
            database.execSQL("DROP TABLE ParkingLotEntry");
            database.execSQL("ALTER TABLE Park2 RENAME TO ParkingLotEntry");


            database.execSQL("ALTER TABLE ExternalAccess ADD COLUMN gateHasStairs INTEGER");
            database.execSQL("ALTER TABLE ExternalAccess ADD COLUMN gateHasRamps INTEGER");

            database.execSQL("ALTER TABLE SidewalkEntry ADD COLUMN sideHasStairs INTEGER");
            database.execSQL("ALTER TABLE SidewalkEntry ADD COLUMN sideHasRamps INTEGER");

            database.execSQL("ALTER TABLE RoomEntry ADD COLUMN roomHasStairs INTEGER");
            database.execSQL("ALTER TABLE RoomEntry ADD COLUMN roomHasRamps INTEGER");
        }
    };

    static final Migration MIGRATION_44_45 = new Migration(44, 45) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE ParkingLotPCDEntry2(parkingPcdID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "parkingLotID INTEGER NOT NULL, hasVisualPcdVertSign INTEGER NOT NULL, vertPcdSignLength REAL, vertPcdSignWidth REAL," +
                    "vertPcdSignObs TEXT, pcdVacancyLength REAL NOT NULL, pcdVacancyWidth REAL NOT NULL, pcdVacancyLimitWidth REAL NOT NULL," +
                    "pcdVacancyObs TEXT, hasSecurityZone INTEGER NOT NULL, securityZoneWidth REAL, securityZoneObs TEXT," +
                    "hasPcdSia INTEGER NOT NULL, pcdSiaWidth REAL, pcdSiaLength REAL, pcdSiaObs TEXT, FOREIGN KEY (parkingLotID)" +
                    "REFERENCES ParkingLotEntry (parkingID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("CREATE TABLE ParkingLotElderlyEntry2(parkingElderlyID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    "parkingLotID INTEGER NOT NULL, hasElderlyVertSign INTEGER NOT NULL, elderlyVertSignLength REAL," +
                    "elderlyVertSingWidth REAL, elderlyVertSignObs TEXT, elderlyVacancyLength REAL NOT NULL," +
                    "elderlyVacancyWidth REAL NOT NULL, elderlyVacancyLimiterWidth REAL NOT NULL, elderlyVacancyObs TEXT," +
                    "hasElderlyFloorIndicator INTEGER NOT NULL, floorIndicatorLength REAL, floorIndicatorWidth REAL, " +
                    "floorIndicatorObs TEXT, FOREIGN KEY (parkingLotID) REFERENCES ParkingLotEntry (parkingID)" +
                    "ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("INSERT INTO ParkingLotPCDEntry2 (parkingPcdID, parkingLotID, hasVisualPcdVertSign, vertPcdSignLength, vertPcdSignWidth," +
                    "vertPcdSignObs, pcdVacancyLength, pcdVacancyWidth, pcdVacancyLimitWidth, pcdVacancyObs, hasSecurityZone, securityZoneWidth, " +
                    "securityZoneObs, hasPcdSia, pcdSiaWidth, pcdSiaLength, pcdSiaObs) SELECT parkingPcdID, parkingLotID, hasVisualPcdVertSign, " +
                    "vertPcdSignLength, vertPcdSignWidth, vertPcdSignObs, pcdVacancyLength, pcdVacancyWidth, pcdVacancyLimitWidth, pcdVacancyObs, " +
                    "hasSecurityZone, securityZoneWidth, securityZoneObs, hasPcdSia, pcdSiaWidth, pcdSiaLength, pcdSiaObs FROM ParkingLotPCDEntry");
            database.execSQL("INSERT INTO ParkingLotElderlyEntry2 (parkingElderlyID, parkingLotID, hasElderlyVertSign, elderlyVertSignLength, elderlyVertSingWidth," +
                    "elderlyVertSignObs, elderlyVacancyLength, elderlyVacancyWidth, elderlyVacancyLimiterWidth, elderlyVacancyObs, hasElderlyFloorIndicator, " +
                    "floorIndicatorLength, floorIndicatorWidth, floorIndicatorObs) SELECT parkingElderlyID, parkingLotID, hasElderlyVertSign, elderlyVertSignLength, " +
                    "elderlyVertSingWidth, elderlyVertSignObs, elderlyVacancyLength, elderlyVacancyWidth, elderlyVacancyLimiterWidth, elderlyVacancyObs, " +
                    "hasElderlyFloorIndicator, floorIndicatorLength, floorIndicatorWidth, floorIndicatorObs FROM ParkingLotElderlyEntry");
            database.execSQL("DROP TABLE ParkingLotPCDEntry");
            database.execSQL("DROP TABLE ParkingLotElderlyEntry");
            database.execSQL("ALTER TABLE ParkingLotPCDEntry2 RENAME TO ParkingLotPCDEntry");
            database.execSQL("ALTER TABLE ParkingLotElderlyEntry2 RENAME TO ParkingLotElderlyEntry");
        }
    };

    static final Migration MIGRATION_45_46 = new Migration(45, 46) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE Ramp2(rampStairsID INTEGER, blockID, ambientType INTEGER ," +
                    "extAccessID INTEGER, sidewalkID INTEGER, parkingID INTEGER, roomID INTEGER, rampStairsIdentifier INTEGER," +
                    " rampStairsLocation TEXT, flightsQuantity INTEGER)");
            database.execSQL("INSERT INTO Ramp2(rampStairsID, blockID, ambientType, extAccessID, rampStairsIdentifier, rampStairsLocation, flightsQuantity)" +
                    " SELECT rampStairsID, blockID, ambientType, ambientID, rampStairsIdentifier, rampStairsLocation, flightsQuantity FROM RampStairsEntry");

            database.execSQL("UPDATE Ramp2 SET sidewalkID = extAccessID WHERE ambientType = 2");
            database.execSQL("UPDATE Ramp2 SET extAccessID = NULL WHERE ambientType = 2");
            database.execSQL("UPDATE Ramp2 SET parkingID = extAccessID WHERE ambientType = 3");
            database.execSQL("UPDATE Ramp2 SET extAccessID = NULL WHERE ambientType = 3");
            database.execSQL("UPDATE Ramp2 SET roomID = extAccessID WHERE ambientType = 4");
            database.execSQL("UPDATE Ramp2 SET extAccessID = NULL WHERE ambientType = 4");

            database.execSQL("DROP TABLE RampStairsEntry");

            database.execSQL("CREATE TABLE RampStairsEntry(rampStairsID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, blockID INTEGER NOT NULL," +
                    "extAccessID INTEGER, sidewalkID INTEGER, parkingID INTEGER, roomID INTEGER, rampStairsIdentifier INTEGER NOT NULL, rampStairsLocation TEXT, " +
                    "flightsQuantity INTEGER NOT NULL, " +
                    "FOREIGN KEY (blockID) REFERENCES BlockSpaceEntry (blockSpaceID) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (extAccessID) REFERENCES ExternalAccess (externalAccessID) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (sidewalkID) REFERENCES SidewalkEntry (sidewalkID) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (parkingID) REFERENCES ParkingLotEntry (parkingID) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (roomID) REFERENCES RoomEntry (roomID) ON UPDATE CASCADE ON DELETE CASCADE)");

            database.execSQL("INSERT INTO RampStairsEntry(rampStairsID, blockID, extAccessID, sidewalkID, parkingID, roomID, rampStairsIdentifier, rampStairsLocation, flightsQuantity)" +
                    " SELECT rampStairsID, blockID, extAccessID, sidewalkID, parkingID, roomID, rampStairsIdentifier, rampStairsLocation, flightsQuantity FROM Ramp2");

            database.execSQL("DROP TABLE Ramp2");

            database.execSQL("ALTER TABLE FlightsRampStairsEntry RENAME TO RampStairsFlightEntry");
            database.execSQL("ALTER TABLE RampStairsFlightEntry ADD COLUMN flightNumber INTEGER NOT NULL DEFAULT 1");
            database.execSQL("ALTER TABLE RampStairsFlightEntry ADD COLUMN mirrorCounter INTEGER");
            database.execSQL("ALTER TABLE RampStairsFlightEntry ADD COLUMN stairMirror1 REAL");
            database.execSQL("ALTER TABLE RampStairsFlightEntry ADD COLUMN stairMirror2 REAL");
            database.execSQL("ALTER TABLE RampStairsFlightEntry ADD COLUMN stairMirror3 REAL");
            database.execSQL("ALTER TABLE RampStairsFlightEntry ADD COLUMN stairMirror4 REAL");
            database.execSQL("ALTER TABLE RampStairsFlightEntry ADD COLUMN stepCounter INTEGER");
            database.execSQL("ALTER TABLE RampStairsFlightEntry ADD COLUMN stairStep1 REAL");
            database.execSQL("ALTER TABLE RampStairsFlightEntry ADD COLUMN stairStep2 REAL");
            database.execSQL("ALTER TABLE RampStairsFlightEntry ADD COLUMN stairStep3 REAL");
            database.execSQL("ALTER TABLE RampStairsFlightEntry ADD COLUMN stairStep4 REAL");
            database.execSQL("ALTER TABLE RampStairsFlightEntry ADD COLUMN slopeCounter INTEGER");
            database.execSQL("ALTER TABLE RampStairsFlightEntry ADD COLUMN rampSlope1 REAL");
            database.execSQL("ALTER TABLE RampStairsFlightEntry ADD COLUMN rampSlope2 REAL");
            database.execSQL("ALTER TABLE RampStairsFlightEntry ADD COLUMN rampSlope3 REAL");
            database.execSQL("ALTER TABLE RampStairsFlightEntry ADD COLUMN rampSlope4 REAL");

            database.execSQL("CREATE TABLE RampStairsRailingEntry2(railingID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, flightID INTEGER NOT NULL," +
                    "railingSide INTEGER NOT NULL, hasRailing INTEGER NOT NULL, railingHeight REAL, railingObs TEXT, hasBeacon INTEGER, " +
                    "beaconHeight REAL, beaconObs TEXT, FOREIGN KEY (flightID) REFERENCES RampStairsFlightEntry(flightID)ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("INSERT INTO RampStairsRailingEntry2(railingID, flightID, railingSide, hasRailing, railingHeight, railingObs, hasBeacon, beaconHeight, beaconObs)" +
                    " SELECT railingID, flightID, railingSide, hasRailing, railingHeight, railingObs, hasBeacon, beaconHeight, beaconObs FROM RampStairsRailingEntry");
            database.execSQL("DROP TABLE RampStairsRailingEntry");
            database.execSQL("ALTER TABLE RampStairsRailingEntry2 RENAME TO RampStairsRailingEntry");

            database.execSQL("DROP TABLE RampStairsHandrailEntry");
            database.execSQL("CREATE TABLE RampStairsHandrailEntry(handrailID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, flightID INTEGER NOT NULL," +
                    "handrailPlacement INTEGER NOT NULL, handrailHeight REAL NOT NULL, handrailGrip REAL NOT NULL, handrailObs TEXT, " +
                    "hasInitExtension INTEGER NOT NULL, initExtLength REAL, hasFinalExtension INTEGER NOT NULL, finalExtLength REAL, extensionObs TEXT," +
                    "FOREIGN KEY (flightID) REFERENCES RampStairsFlightEntry(flightID) ON UPDATE CASCADE ON DELETE CASCADE)");


            database.execSQL("DROP TABLE StairsStepEntry");
            database.execSQL("DROP TABLE StairsMirrorEntry");
            database.execSQL("DROP TABLE RampInclinationEntry");
        }

    };

    static final Migration MIGRATION_46_47 = new Migration(46, 47) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE RampStairsEntry");
            database.execSQL("CREATE TABLE RampStairsEntry(rampStairsID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, blockID INTEGER NOT NULL," +
                    "extAccessID INTEGER, sidewalkID INTEGER, parkingID INTEGER, roomID INTEGER, rampStairsIdentifier INTEGER NOT NULL, rampStairsLocation TEXT, " +
                    "FOREIGN KEY (blockID) REFERENCES BlockSpaceEntry (blockSpaceID) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (extAccessID) REFERENCES ExternalAccess (externalAccessID) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (sidewalkID) REFERENCES SidewalkEntry (sidewalkID) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (parkingID) REFERENCES ParkingLotEntry (parkingID) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (roomID) REFERENCES RoomEntry (roomID) ON UPDATE CASCADE ON DELETE CASCADE)");
        }
    };

    static final Migration MIGRATION_47_48 = new Migration(47, 48) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE RestroomEntry");
            database.execSQL("CREATE TABLE RestroomEntry(restroomID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, blockID INTEGER NOT NULL," +
                    "restType INTEGER, restLocation TEXT, accessRoute INTEGER, accessRouteObs TEXT, intRestroom INTEGER," +
                    "intRestObs TEXT, restDistance INTEGER, restDistObs TEXT, exEntrance INTEGER, exEntObs TEXT, antiDriftFloor INTEGER, " +
                    "antiDriftFloorObs TEXT, restDrain INTEGER, restDrainObs TEXT, restSwitch INTEGER, switchHeight REAL, switchObs TEXT, " +
                    "doorWidth REAL, hasPict INTEGER, pictObs TEXT, opDir INTEGER, opDirObs TEXT, hasCoat INTEGER, coatHeight REAL, coatObs TEXT, " +
                    "hasVertSign INTEGER, vertSignObs TEXT, sillType INTEGER, sillIncHeight REAL, sillStepHeight REAL, sillSlopeQnt INTEGER, sillSlopeAngle1 REAL, " +
                    "sillSlopeAngle2 REAL, sillSlopeAngle3 REAL, sillSlopeAngle4 REAL, sillSlopeWidth REAL, sillTypeObs TEXT, hasTactSign INTEGER, " +
                    "tactSignObs TEXT, hasHorHandle INTEGER, handleHeight REAL, handleLength REAL, handleDiam REAL, handleObs TEXT, upViewLength REAL, " +
                    "upViewWidth REAL, upViewMeasureA REAL, upViewMeasureB REAL, upViewMeasureC REAL, upViewMeasureD REAL, upViewMeasureE REAL, upViewObs TEXT," +
                    "toType INTEGER, toHeightNoSeat REAL, toHasSeat INTEGER, toHeightSeat REAL, toHasSoculo INTEGER, frSoculo REAL, latSoculo REAL, socCorners INTEGER," +
                    "toHasFrontBar INTEGER, frBarA REAL, frBarB REAL, frBarC REAL, frBarSect REAL, frBarDist REAL, toHasWall INTEGER, hasHorBar INTEGER," +
                    "horBarD REAL, horBarE REAL, horBarF REAL, horBarDistG REAL, horBarSect REAL, horBarDist REAL, hasVertBar INTEGER, vertBarH REAL, vertBarI REAL," +
                    "vertBarJ REAL, vertBarSect REAL, vertBarDist REAL, hasArtBar INTEGER, artBarH REAL, artBarI REAL, artBarJ REAL, artBarSect REAL," +
                    "toActDesc TEXT, toActHeight REAL, toActObs TEXT, hasPapHolder INTEGER, papHolderType INTEGER, papEmbDist REAL, papEmbHeight REAL, papSupAlign INTEGER," +
                    "papSupHeight REAL, papHoldObs TEXT, hasDouche INTEGER, doucheHeight REAL, doucheObs TEXT, toiletObs TEXT, hasHanger INTEGER, hangerHeight REAL," +
                    "hangerObs TEXT, hasObjHold INTEGER, objHoldCorrect INTEGER, objHoldHeight REAL, objHoldObs TEXT, hasSoapHold INTEGER, soapHoldHeight REAL," +
                    "soapHoldObs TEXT, hasTowelHold INTEGER, towelHoldHeight REAL, towelHoldObs TEXT, hasWallMirror INTEGER, wallMirrorLow REAL," +
                    "wallMirrorHigh REAL, wallMirrorObs TEXT, sinkType INTEGER, approxMeasureA REAL, approxMeasureB REAL, approxMeasureC REAL, approxMeasureD REAL," +
                    "approxMeasureE REAL, hasSinkBar INTEGER, hasLeftBar INTEGER, leftHorMeasureA REAL, leftHorMeasureB REAL, leftHorMeasureC REAL, leftHorMeasureD REAL," +
                    "leftVertMeasureA REAL, leftVertMeasureB REAL, leftVertMeasureC REAL, leftVertMeasureD REAL, leftVertMeasureE REAL, leftBarDiam REAL," +
                    "leftBarDist REAL, hasRightBar INTEGER, rightHorMeasureA REAL, rightHorMeasureB REAL, rightHorMeasureC REAL, rightHorMeasureD REAL, " +
                    "rightVertMeasureA REAL, rightBarDiam REAL, rightBarDist REAL, sinkHasMirror INTEGER, siMirrorLow REAL, siMirrorHigh REAL," +
                    "sinkObs TEXT, hasUrinal INTEGER, hasAccessUrinal INTEGER, urinalType INTEGER, urMeasureA REAL, urMeasureB REAL, urMeasureC REAL, urMeasureD REAL," +
                    "urMeasureE REAL, urMeasureF REAL, urMeasureG REAL, urMeasureH REAL, urMeasureI REAL, urMeasureJ REAL, urMeasureK REAL, urMeasureL REAL," +
                    "urMeasureM REAL, urObs TEXT, FOREIGN KEY (blockID) REFERENCES BlockSpaceEntry (blockSpaceID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("DROP TABLE RestroomSinkEntry");
            database.execSQL("DROP TABLE RestroomSupportBarEntry");
            database.execSQL("DROP TABLE RestroomUpViewEntry");
            database.execSQL("DROP TABLE RestroomUrinalEntry");
            database.execSQL("DROP TABLE RestroomMirrorEntry");

        }
    };

    static final Migration MIGRATION_48_49 = new Migration(48, 49) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE RestroomEntry");
            database.execSQL("CREATE TABLE RestroomEntry(restroomID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, blockID INTEGER NOT NULL," +
                    "restType INTEGER, restLocation TEXT, accessRoute INTEGER, accessRouteObs TEXT, intRestroom INTEGER," +
                    "intRestObs TEXT, restDistance INTEGER, restDistObs TEXT, exEntrance INTEGER, exEntObs TEXT, antiDriftFloor INTEGER, " +
                    "antiDriftFloorObs TEXT, restDrain INTEGER, restDrainObs TEXT, restSwitch INTEGER, switchHeight REAL, switchObs TEXT, " +
                    "doorWidth REAL, hasPict INTEGER, pictObs TEXT, opDir INTEGER, opDirObs TEXT, hasCoat INTEGER, coatHeight REAL, coatObs TEXT, " +
                    "hasVertSign INTEGER, vertSignObs TEXT, sillType INTEGER, sillIncHeight REAL, sillStepHeight REAL, sillSlopeQnt INTEGER, sillSlopeAngle1 REAL, " +
                    "sillSlopeAngle2 REAL, sillSlopeAngle3 REAL, sillSlopeAngle4 REAL, sillSlopeWidth REAL, sillTypeObs TEXT, hasTactSign INTEGER, " +
                    "tactSignObs TEXT, hasHorHandle INTEGER, handleHeight REAL, handleLength REAL, handleDiam REAL, handleObs TEXT, upViewLength REAL, " +
                    "upViewWidth REAL, upViewMeasureA REAL, upViewMeasureB REAL, upViewMeasureC REAL, upViewMeasureD REAL, upViewMeasureE REAL, upViewObs TEXT," +
                    "toType INTEGER, toHeightNoSeat REAL, toHasSeat INTEGER, toHeightSeat REAL, toHasSoculo INTEGER, frSoculo REAL, latSoculo REAL, socCorners INTEGER," +
                    "toHasFrontBar INTEGER, frBarA REAL, frBarB REAL, frBarC REAL, frBarSect REAL, frBarDist REAL, toHasWall INTEGER, hasHorBar INTEGER," +
                    "horBarD REAL, horBarE REAL, horBarF REAL, horBarDistG REAL, horBarSect REAL, horBarDist REAL, hasVertBar INTEGER, vertBarH REAL, vertBarI REAL," +
                    "vertBarJ REAL, vertBarSect REAL, vertBarDist REAL, hasArtBar INTEGER, artBarH REAL, artBarI REAL, artBarJ REAL, artBarSect REAL," +
                    "toActDesc TEXT, toActHeight REAL, toActObs TEXT, hasPapHolder INTEGER, papHolderType INTEGER, papEmbDist REAL, papEmbHeight REAL, papSupAlign INTEGER," +
                    "papSupHeight REAL, papHoldObs TEXT, hasDouche INTEGER, doucheHeight REAL, doucheObs TEXT, toiletObs TEXT, hasHanger INTEGER, hangerHeight REAL," +
                    "hangerObs TEXT, hasObjHold INTEGER, objHoldCorrect INTEGER, objHoldHeight REAL, objHoldObs TEXT, hasSoapHold INTEGER, soapHoldHeight REAL," +
                    "soapHoldObs TEXT, hasTowelHold INTEGER, towelHoldHeight REAL, towelHoldObs TEXT, hasWallMirror INTEGER, wallMirrorLow REAL," +
                    "wallMirrorHigh REAL, wallMirrorObs TEXT, sinkType INTEGER, approxMeasureA REAL, approxMeasureB REAL, approxMeasureC REAL, approxMeasureD REAL," +
                    "approxMeasureE REAL, hasSinkBar INTEGER, hasLeftBar INTEGER, leftHorMeasureA REAL, leftHorMeasureB REAL, leftHorMeasureC REAL, leftHorMeasureD REAL," +
                    "leftVertMeasureA REAL, leftVertMeasureB REAL, leftVertMeasureC REAL, leftVertMeasureD REAL, leftVertMeasureE REAL, leftBarDiam REAL," +
                    "leftBarDist REAL, hasRightBar INTEGER, rightHorMeasureA REAL, rightHorMeasureB REAL, rightHorMeasureC REAL, rightHorMeasureD REAL, " +
                    "rightVertMeasureA REAL, rightBarDiam REAL, rightBarDist REAL, sinkHasMirror INTEGER, siMirrorLow REAL, siMirrorHigh REAL," +
                    "sinkObs TEXT, hasUrinal INTEGER, hasAccessUrinal INTEGER, urinalType INTEGER, urMeasureA REAL, urMeasureB REAL, urMeasureC REAL, urMeasureD REAL," +
                    "urMeasureE REAL, urMeasureF REAL, urMeasureG REAL, urMeasureH REAL, urMeasureI REAL, urMeasureJ REAL, urMeasureK REAL, urMeasureL REAL," +
                    "urMeasureM REAL, urObs TEXT, FOREIGN KEY (blockID) REFERENCES BlockSpaceEntry (blockSpaceID) ON UPDATE CASCADE ON DELETE CASCADE)");
        }
    };

    static final Migration MIGRATION_49_50 = new Migration(49, 50) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE RoomEntry_3 (roomID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, blockID INTEGER NOT NULL, roomType INTEGER NOT NULL," +
                    "roomLocation TEXT, roomDescription TEXT, roomHasVertSing INTEGER, roomVertSignObs TEXT, roomHasLooseCarpet INTEGER, looseCarpetObs TEXT," +
                    "roomAccessFloor INTEGER, accessFloorObs TEXT, libDistShelvesOK INTEGER, libPcrManeuversOK INTEGER, libAccessPcOK INTEGER, secHasFixedSeats INTEGER," +
                    "secHasPcrSpace INTEGER, secPcrSpaceWidth REAL, secPcrSpaceDepth REAL, secPCRSpaceObs TEXT, roomObs TEXT, roomHasStairs INTEGER," +
                    "hasBellControl INTEGER, roomHasRamps INTEGER, bellControlHeight REAL, bellControlObs TEXT, hasInternalPhone INTEGER, internalPhoneHeight REAL, " +
                    "internalPhoneObs TEXT, hasBiometricClock INTEGER, biometricClockHeight REAL, biometricClockObs TEXT, FOREIGN KEY (blockID) " +
                    "REFERENCES BlockSpaceEntry (blockSpaceID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("INSERT INTO RoomEntry_3 (roomID, blockID, roomType, roomLocation, roomHasVertSing, roomVertSignObs, roomHasLooseCarpet, looseCarpetObs," +
                    "roomAccessFloor, accessFloorObs, libDistShelvesOK, libPcrManeuversOK, libAccessPcOK, secHasFixedSeats, secHasPcrSpace, secPcrSpaceWidth, " +
                    "secPcrSpaceDepth, secPCRSpaceObs, roomObs) SELECT roomID, blockID, roomType, roomLocation, roomHasVertSing, roomVertSignObs, roomHasLooseCarpet, " +
                    "looseCarpetObs, roomAccessFloor, accessFloorObs, libDistShelvesOK, libPcrManeuversOK, libAccessPcOK, secHasFixedSeats, secHasPcrSpace, secPcrSpaceWidth," +
                    "secPcrSpaceDepth, secPCRSpaceObs, roomObs FROM RoomEntry");
            database.execSQL("DROP TABLE RoomEntry");
            database.execSQL("ALTER TABLE RoomEntry_3 RENAME TO RoomEntry");

            database.execSQL("DROP TABLE AdmEquipEntry");
        }
    };

    static final Migration MIGRATION_50_51 = new Migration(50, 51) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE OtherSpaces");
        }
    };

    static final Migration MIGRATION_51_52 = new Migration(51, 52) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE RSE (rampStairsID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                    "extAccessID INTEGER, sidewalkID INTEGER, parkingID INTEGER, roomID INTEGER, rampStairsIdentifier INTEGER NOT NULL, rampStairsLocation TEXT, " +
                    "FOREIGN KEY (extAccessID) REFERENCES ExternalAccess (externalAccessID) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (sidewalkID) REFERENCES SidewalkEntry (sidewalkID) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (parkingID) REFERENCES ParkingLotEntry (parkingID) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (roomID) REFERENCES RoomEntry (roomID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("INSERT INTO RSE (rampStairsID, extAccessID, sidewalkID, parkingID, roomID, rampStairsIdentifier, rampStairsLocation)" +
                    "SELECT rampStairsID, extAccessID, sidewalkID, parkingID, roomID, rampStairsIdentifier, rampStairsLocation FROM RampStairsEntry");
            database.execSQL("DROP TABLE RampStairsEntry");
            database.execSQL("ALTER TABLE RSE RENAME TO RampStairsEntry");

        }
    };

    static final Migration MIGRATION_52_53 = new Migration(52, 53) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE SchoolEntry ADD COLUMN emailAddress TEXT");
        }
    };

    static final Migration MIGRATION_53_54 = new Migration(53, 54) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE RampStairsFlightEntry");
            database.execSQL("CREATE TABLE RampStairsFlightEntry (flightID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, rampStairsID INTEGER NOT NULL, " +
                    "flightNumber INTEGER NOT NULL DEFAULT 1, flightWidth REAL, flightLength REAL, rampHeight REAL, mirrorCounter INTEGER, stairMirror1 REAL, stairMirror2 REAL, " +
                    "stairMirror3 REAL, stairMirror4 REAL, stepCounter INTEGER, stairStep1 REAL, stairStep2 REAL, stairStep3 REAL, stairStep4 REAL, slopeCounter INTEGER, " +
                    "rampSlope1 REAL, rampSlope2 REAL, rampSlope3 REAL, rampSlope4 REAL, signPavement INTEGER, signPavementObs TEXT, hasLowTactFloor INTEGER, " +
                    "lowTactWidth REAL, lowTactDist REAL, hasUpTactFloor INTEGER, upTactWidth REAL, upTactDist REAL, tactileFloorObs TEXT, hasInterLevel INTEGER, " +
                    "interLevelLength REAL, interLevelObs TEXT, borderSign INTEGER, borderSignWidth REAL, borderSignIdentifiable INTEGER, borderSignObs TEXT, flightObs TEXT, " +
                    "FOREIGN KEY (rampStairsID) REFERENCES RampStairsEntry (rampStairsID) ON UPDATE CASCADE ON DELETE CASCADE)");
        }
    };

    static final Migration MIGRATION_54_55 = new Migration(54, 55) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE SidewalkEntry");
            database.execSQL("DROP TABLE PayPhoneEntry");
            database.execSQL("CREATE TABLE SidewalkEntry (sidewalkID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, blockID INTEGER NOT NULL, sidewalkLocation TEXT," +
                    "streetPavement INTEGER, sidewalkWidth REAL, sideFreeSpaceWidth REAL, sideMeasureObs TEXT, slopeMeasureQnt INTEGER, sideTransSlope1 REAL," +
                    "sideTransSlope2 REAL, sideTransSlope3 REAL, sideTransSlope4 REAL, sideTransSlope5 REAL, sideTransSlope6 real, hasSpecialFloor INTEGER," +
                    "specialFloorRightColor INTEGER, specialTileDirectionWidth REAL, specialTileAlertWidth REAL, specialFloorObs TEXT, sideFloorIsAccessible INTEGER," +
                    "accessFloorObs TEXT, sideHasSlope INTEGER, sidewalkObs TEXT, hasAerialObstacle INTEGER, aerialObstacleDesc TEXT, sidewalkHasLids INTEGER," +
                    "sidewalkLidDesc TEXT, sideConStatus INTEGER, sideConsObs TEXT, sideHasPayphones INTEGER, sideHasStairs INTEGER, sideHasRamps INTEGER, sideReqSlopes INTEGER," +
                    "FOREIGN KEY (blockID) REFERENCES BlockSpaceEntry (blockSpaceID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("CREATE TABLE PayPhoneEntry (payPhoneID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, extAccessID INTEGER, sidewalkID INTEGER," +
                    "phoneRefPoint TEXT, phoneKeyboardHeight REAL NOT NULL, phoneHeight REAL NOT NULL, hasTactileFloor INTEGER NOT NULL, rightColorTactileFloor INTEGER, " +
                    "tactFloorDist REAL, tactFloorWidth REAL, tactFloorObs TEXT, phoneIsWorking INTEGER NOT NULL, payPhoneObs TEXT, " +
                    "FOREIGN KEY (extAccessID) REFERENCES ExternalAccess (externalAccessID) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (sidewalkID) REFERENCES SidewalkEntry (sidewalkID) ON UPDATE CASCADE ON DELETE CASCADE)");
        }
    };

    static final Migration MIGRATION_55_56 = new Migration(55, 56) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE ExternalAccess2 (externalAccessID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, blockID INTEGER NOT NULL, accessLocation TEXT," +
                    "entranceType INTEGER, floorIsAccessible INTEGER, accessibleFloorObs TEXT, hasSIA INTEGER, obsSIA TEXT, entranceGateType INTEGER, entranceGateDesc TEXT," +
                    "freeSpaceWidth1 REAL, freeSpaceWidth2 REAL, gateHandleType INTEGER, gateHandleHeight REAL, gateObs TEXT,  gateHasTracks INTEGER, gateTrackHeight REAL," +
                    "gateHasTrackRamp INTEGER, trackRampQuantity INTEGER, trackRampMeasure1 REAL, trackRampMeasure2 REAL, trackRampMeasure3 REAL, trackRampMeasure4 REAL," +
                    "hasFloorGap INTEGER, gapCounter INTEGER, gapMeasure1 REAL, gapMeasure2 REAL, gapMeasure3 REAL, gapMeasure4 REAL, gateSillType INTEGER," +
                    "sillInclinationHeight REAL, sillStepHeight REAL, sillSlopeWidth REAL, sillSlopeHeight REAL, slopeMeasureQnt INTEGER, sillSlopeAngle REAL," +
                    "sillSlopeAngle2 REAL, sillSlopeAngle3 REAL, sillSlopeAngle4 REAL, gateSillObs TEXT, gateHasObstacles INTEGER, gateHasPayphones INTEGER," +
                    "gateHasIntercom INTEGER, intercomHeight REAL, gateHasStairs INTEGER, gateHasRamps INTEGER, gateHasSoundSign INTEGER, extAccessObs TEXT," +
                    "FOREIGN KEY (blockID) REFERENCES BlockSpaceEntry (blockSpaceID) ON UPDATE CASCADE ON DELETE CASCADE)");

            database.execSQL("INSERT INTO ExternalAccess2(externalAccessID, blockID, accessLocation, entranceType, floorIsAccessible, accessibleFloorObs, hasSIA," +
                    "obsSIA, entranceGateType, entranceGateDesc, freeSpaceWidth1, freeSpaceWidth2, gateHandleType, gateHandleHeight, gateObs, gateHasTracks," +
                    "gateTrackHeight, gateHasTrackRamp, trackRampQuantity, trackRampMeasure1, trackRampMeasure2, trackRampMeasure3, trackRampMeasure4, gateSillType, " +
                    "sillInclinationHeight, sillStepHeight, sillSlopeWidth, slopeMeasureQnt, sillSlopeAngle, sillSlopeAngle2, sillSlopeAngle3, sillSlopeAngle4," +
                    "gateSillObs, gateHasObstacles, gateHasPayphones, gateHasIntercom, intercomHeight, gateHasSoundSign, extAccessObs)" +
                    " SELECT externalAccessID, blockID, accessLocation, entranceType, floorIsAccessible, accessibleFloorObs, hasSIA, obsSIA, entranceGateType, entranceGateDesc, " +
                    "freeSpaceWidth1, freeSpaceWidth2, gateHandleType, gateHandleHeight, gateObs, gateHasTracks, gateTrackHeight, gateHasTrackRamp, trackRampQuantity, " +
                    "trackRampMeasure1, trackRampMeasure2, trackRampMeasure3, trackRampMeasure4, gateSillType, sillInclinationHeight, sillStepHeight, sillSlopeWidth, " +
                    "slopeMeasureQnt, sillSlopeAngle, sillSlopeAngle2, sillSlopeAngle3, sillSlopeAngle4, gateSillObs, gateHasObstacles, gateHasPayphones, gateHasIntercom, " +
                    "intercomHeight, gateHasSoundSign, extAccessObs FROM ExternalAccess");
            database.execSQL("DROP TABLE ExternalAccess");
            database.execSQL("ALTER TABLE ExternalAccess2 RENAME TO ExternalAccess");

            database.execSQL("ALTER TABLE DoorEntry ADD COLUMN sillSlopeHeight REAL");
            database.execSQL("ALTER TABLE PlaygroundEntry ADD COLUMN sillSlopeHeight REAL");
            database.execSQL("ALTER TABLE RestroomEntry ADD COLUMN sillSlopeHeight REAL");

        }
    };

    static final Migration MIGRATION_56_57 = new Migration(56, 57) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE GateObsEntry");
            database.execSQL("CREATE TABLE GateObsEntry (gateObsID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, extAccessID INTEGER NOT NULL, accessRefPoint TEXT, accessType INTEGER," +
                    " obsHeight REAL, obsWidth REAL, obsHasSia INTEGER, gateObstacleObs TEXT, " +
                    "FOREIGN KEY (extAccessID) REFERENCES ExternalAccess (externalAccessID) ON UPDATE CASCADE ON DELETE CASCADE)");
        }
    };

    static final Migration MIGRATION_57_58 = new Migration(57, 58) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE RampStairsHandrailEntry2 (handrailID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, flightID INTEGER NOT NULL, " +
                    " handrailPlacement INTEGER NOT NULL, hasHandrail INTEGER NOT NULL DEFAULT 1, handrailHeight REAL, handrailGrip REAL, handrailDist REAL, " +
                    "hasInitExtension INTEGER, initExtLength REAL, hasFinalExtension INTEGER, finalExtLength REAL, handrailObs TEXT," +
                    "FOREIGN KEY (flightID) REFERENCES RampStairsFlightEntry (flightID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("INSERT INTO RampStairsHandrailEntry2 (handrailID, flightID, handrailPlacement,  handrailHeight, handrailGrip, hasInitExtension, initExtLength," +
                    "hasFinalExtension, finalExtLength, handrailObs) SELECT handrailID, flightID, handrailPlacement,  handrailHeight, handrailGrip, hasInitExtension, initExtLength," +
                    "hasFinalExtension, finalExtLength, handrailObs FROM RampStairsHandrailEntry");
            database.execSQL("DROP TABLE RampStairsHandrailEntry");
            database.execSQL("ALTER TABLE RampStairsHandrailEntry2 RENAME TO RampStairsHandrailEntry");

            database.execSQL("ALTER TABLE RampStairsFlightEntry ADD COLUMN borderSignLength REAL");
        }
    };

    static final Migration MIGRATION_58_59 = new Migration(58, 59) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE FreeSpaceEntry ADD COLUMN obstacleWidth REAL");

            database.execSQL("ALTER TABLE CounterEntry ADD COLUMN counterWidth REAL");
            database.execSQL("ALTER TABLE CounterEntry ADD COLUMN counterFreeWidth REAL");

            database.execSQL("ALTER TABLE TableEntry ADD COLUMN tableFreeWidth REAL");

            database.execSQL("DROP TABLE PlaygroundEntry");
            database.execSQL("CREATE TABLE PlaygroundEntry (playID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, blockID INTEGER NOT NULL, playLocation TEXT, playFloorType TEXT, " +
                    "playGateWidth REAL, playGateHasFloorTrack INTEGER, playFloorTrackHeight REAL, playFloorTrackHasRamp INTEGER, rampMeasureQnt INTEGER, rampMeasure1 REAL," +
                    "rampMeasure2 REAL,rampMeasure3 REAL, rampMeasure4 REAL, hasFloorGap INTEGER, floorGapQnt INTEGER, floorGap1 REAL, floorGap2 REAL, floorGap3 REAL, " +
                    "floorGap4 REAL, playGateSillType INTEGER, inclinationSillHeight REAL, stepSillHeight REAL, slopeMeasureQnt INTEGER," +
                    "slopeSillAngle1 REAL, slopeSillAngle2 REAL, slopeSillAngle3 REAL, slopeSillAngle4 REAL, slopeSillWidth REAL, slopeSillHeight REAL, sillObs TEXT," +
                    " accessibleFloor INTEGER, accessibleFloorObs TEXT, accessibleEquip INTEGER, accessibleEquipObs TEXT, playgroundObs TEXT," +
                    "FOREIGN KEY (blockID) REFERENCES BlockSpaceEntry (blockSpaceID) ON UPDATE CASCADE ON DELETE CASCADE)");
        }
    };

    static final Migration MIGRATION_59_60 = new Migration(59, 60) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE ParkingLotPCDEntry");
            database.execSQL("CREATE TABLE ParkingLotPCDEntry (parkPcdID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, parkID INTGER NOT NULL, pcdVacancyLocal TEXT, " +
                    "vacancyPosition INTEGER NOT NULL, hasVisualPcdVertSign INTEGER NOT NULL, vertPcdSignLength REAL, vertPcdSignWidth REAL, vertPcdSignObs TEXT, " +
                    "pcdVacancyLength REAL NOT NULL, pcdVacancyWidth REAL NOT NULL, pcdVacancyLimitWidth REAL NOT NULL, hasSecurityZone INTEGER NOT NULL, " +
                    "securityZoneWidth REAL, securityZoneObs TEXT, hasPcdSia INTEGER NOT NULL, pcdSiaWidth REAL, pcdSiaLength REAL, pcdSiaObs TEXT, pcdVacancyObs TEXT, " +
                    "FOREIGN KEY (parkID) REFERENCES ParkingLotEntry (parkingID) ON UPDATE CASCADE ON DELETE CASCADE)");

            database.execSQL("DROP TABLE ParkingLotElderlyEntry");
            database.execSQL("CREATE TABLE ParkingLotElderlyEntry (parkElderID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, parkID INTEGER NOT NULL, elderVacLocation TEXT," +
                    "hasElderlyVertSign INTEGER NOT NULL, elderlyVertSignLength REAL, elderlyVertSingWidth REAL, elderlyVertSignObs TEXT, elderlyVacancyLength REAL NOT NULL," +
                    "elderlyVacancyWidth REAL NOT NULL, elderlyVacancyLimiterWidth REAL NOT NULL, elderlyVacancyObs TEXT, hasElderlyFloorIndicator INTEGER NOT NULL, " +
                    "floorIndicatorHeight REAL, floorIndicatorObs TEXT," +
                    "FOREIGN KEY (parkID) REFERENCES ParkingLotEntry (parkingID) ON UPDATE CASCADE ON DELETE CASCADE)");

            database.execSQL("ALTER TABLE ParkingLotEntry ADD COLUMN extParkLocation TEXT");

        }
    };

    static final Migration MIGRATION_60_61 = new Migration(60, 61) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE SchoolEntry ADD COLUMN contactName1 TEXT");
            database.execSQL("ALTER TABLE SchoolEntry ADD COLUMN contactName2 TEXT");
            database.execSQL("ALTER TABLE SchoolEntry ADD COLUMN registerStudentObs TEXT");

            database.execSQL("ALTER TABLE SidewalkEntry ADD COLUMN hasSidewalk INTEGER");

            database.execSQL("CREATE TABLE PlaygroundEntry2 (playID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, blockID INTEGER NOT NULL, " +
                    "playLocation TEXT, playHasGate INTEGER DEFAULT 0, playGateWidth REAL, gateHasFloorTrack INTEGER, floorTrackHeight REAL, floorTrackHasRamp INTEGER," +
                    "rampMeasureQnt INTEGER, rampMeasure1 REAL, rampMeasure2 REAL, rampMeasure3 REAL, rampMeasure4 REAL, hasFloorGap INTEGER, floorGapQnt INTEGER," +
                    "floorGap1 REAL, floorGap2 REAL, floorGap3 REAL, floorGap4 REAL, gateSillType INTEGER, inclHeight REAL, inclMeasureQnt INTEGER, " +
                    "inclAngle1 REAL, inclAngle2 REAL, inclAngle3 REAL, inclAngle4 REAL, stepHeight REAL, slopeMeasureQnt INTEGER, slopeAngle1 REAL," +
                    "slopeAngle2 REAL, slopeAngle3 REAL, slopeAngle4 REAL, slopeWidth REAL, slopeHeight REAL, sillObs TEXT, " +
                    "accessibleFloor INTEGER, accessFloorObs TEXT, accessibleEquip INTEGER, accessEquipObs TEXT, playgroundObs TEXT, playPhotoNumber TEXT,"+
                    "FOREIGN KEY (blockID) REFERENCES BlockSpaceEntry (blockSpaceID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("INSERT INTO PlaygroundEntry2 (playLocation, blockID, playGateWidth, gateHasFloorTrack, floorTrackHeight, floorTrackHasRamp, " +
                    "rampMeasureQnt, rampMeasure1, rampMeasure2, rampMeasure3, rampMeasure4, hasFloorGap, floorGapQnt, floorGap1, floorGap2, floorGap3, floorGap4, " +
                    "gateSillType, inclHeight, stepHeight, slopeMeasureQnt, slopeAngle1, slopeAngle2, slopeAngle3, slopeAngle4, slopeWidth, " +
                    "slopeHeight, sillObs, accessibleFloor, accessFloorObs, accessibleEquip, accessEquipObs, playgroundObs) " +
                    "SELECT playLocation, blockID, playGateWidth, playGateHasFloorTrack, playFloorTrackHeight, playFloorTrackHasRamp, rampMeasureQnt, rampMeasure1, " +
                    "rampMeasure2, rampMeasure3, rampMeasure4, hasFloorGap, floorGapQnt, floorGap1, floorGap2, floorGap3, floorGap4, playGateSillType, " +
                    "inclinationSillHeight, stepSillHeight, slopeMeasureQnt, slopeSillAngle1, slopeSillAngle2, slopeSillAngle3, slopeSillAngle4, slopeSillWidth, " +
                    "slopeSillHeight, sillObs, accessibleFloor, accessibleFloorObs, accessibleEquip, accessibleEquipObs, playgroundObs FROM PlaygroundEntry");
            database.execSQL("DROP TABLE PlaygroundEntry");
            database.execSQL("ALTER TABLE PlaygroundEntry2 RENAME TO PlaygroundEntry");

            database.execSQL("CREATE TABLE DoorEntry_2(doorID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, roomID INTEGER, restID INTEGER, doorLocation TEXT, doorType INTEGER," +
                    "doorWidth1 REAL, doorWidth2 REAL, doorHasPict INTEGER, doorPictObs TEXT, opDirection INTEGER, opDirectionObs TEXT, doorHandleType INTEGER, doorHandleHeight REAL, doorHandleObs TEXT, " +
                    "doorHasLocks INTEGER, doorHasHorBar INTEGER, horBarHeight REAL, horBarLength REAL, horBarFrameDist REAL, horBarDiam REAL, horBarDoorDist REAL," +
                    "horBarObs TEXT, doorHasWindow INTEGER, doorWinInfHeight REAL, doorWinSupHeight REAL, doorWinWidth REAL, doorWinObs TEXT, " +
                    "doorHasTactSign INTEGER, tactSignHeight REAL, tactSignIncl REAL, tactSignObs TEXT, doorSillType INTEGER, inclHeight REAL, " +
                    "inclQnt INTEGER, inclAngle1 REAL, inclAngle2 REAL, inclAngle3 REAL, inclAngle4 REAL, stepHeight REAL, slopeWidth REAL, slopeHeight REAL," +
                    "slopeQnt INTEGER, slopeAngle1 REAL, slopeAngle2 REAL, slopeAngle3 REAL, slopeAngle4 REAL,  doorSillObs TEXT, doorObs TEXT, " +
                    "FOREIGN KEY (roomID) REFERENCES RoomEntry (roomID) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (restID) REFERENCES RestroomEntry (restroomID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("INSERT INTO DoorEntry_2 (doorID, roomID, doorLocation, doorWidth1, doorhandleType, doorHandleHeight, doorHandleObs, doorHasLocks, doorHasTactSign," +
                    "doorSillType, inclHeight, stepHeight, slopeQnt, slopeAngle1, slopeAngle2,slopeAngle3, slopeAngle4, slopeWidth, slopeHeight, doorSillObs, doorObs) " +
                    "SELECT doorID, roomID, doorLocation, doorWidth, doorHandleType, doorHandleHeight, doorHandleObs, doorHasLocks, doorHasTactileSign, doorSillType, " +
                    "sillInclinationHeight, sillStepHeight, sillSlopeQnt, sillSlopeAngle1, sillSlopeAngle2, sillSlopeAngle3, sillSlopeAngle4, sillSlopeWidth, sillSlopeHeight, " +
                    "doorSillObs, doorObs FROM DoorEntry");
            database.execSQL("DROP TABLE DoorEntry");
            database.execSQL("ALTER TABLE DoorEntry_2 RENAME TO DoorEntry");

            database.execSQL("CREATE TABLE FreeSpaceEntry2(frSpaceID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, roomID INTEGER, restID INTEGER, frSpaceLocation TEXT," +
                    "obstacleWidth REAL, frSpaceWidth REAL, frSpaceObs TEXT," +
                    "FOREIGN KEY (roomID) REFERENCES RoomEntry (roomID) ON UPDATE CASCADE ON DELETE CASCADE, " +
                    "FOREIGN KEY (restID) REFERENCES RestroomEntry (restroomID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("INSERT INTO FreeSpaceEntry2 (frSpaceID, roomID, frSpaceLocation, obstacleWidth, frSpaceWidth, frSpaceObs) " +
                    "SELECT freeSpaceID, roomID, freeSpaceLocation, obstacleWidth, freeSpaceWidth, freeSpaceObs FROM FreeSpaceEntry");
            database.execSQL("DROP TABLE FreeSpaceEntry");
            database.execSQL("ALTER TABLE FreeSpaceEntry2 RENAME TO FreeSpaceEntry");

            database.execSQL("CREATE TABLE RestEntry (restroomID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, blockID INTEGER NOT NULL, isCollective INTEGER NOT NULL DEFAULT 0," +
                    "restType INTEGER, restLocation TEXT, collectiveHasDoor INTEGER, entranceWidth REAL, entranceDoorSill INTEGER, entranceDoorSillObs TEXT, " +
                    "accessRoute INTEGER, accessRouteObs TEXT, intRestroom INTEGER," +
                    "intRestObs TEXT, antiDriftFloor INTEGER, antiDriftFloorObs TEXT, restDrain INTEGER, restDrainObs TEXT, restSwitch INTEGER, switchHeight REAL, switchObs TEXT," +
                    "upViewLength REAL, upViewWidth REAL, upViewMeasureA REAL, upViewMeasureB REAL, upViewMeasureC REAL, upViewMeasureD REAL, upViewObs TEXT, toType INTEGER," +
                    "toHeightNoSeat REAL, toHasSeat INTEGER, toHeightSeat REAL, toHasSoculo INTEGER, frSoculo REAL, latSoculo REAL, socCorners INTEGER, toHasFrontBar INTEGER," +
                    "frBarA REAL, frBarB REAL, frBarC REAL, frBarSect REAL, frBarDist REAL, toHasWall INTEGER, hasHorBar INTEGER, horBarD REAL, horBarE REAL, horBarF REAL," +
                    "horBarDistG REAL, horBarSect REAL, horBarDist REAL, hasVertBar INTEGER, vertBarH REAL, vertBarI REAL, vertBarJ REAL, vertBarSect REAL, vertBarDist REAL," +
                    "hasSideBar INTEGER, sideBarD REAL, sideBarE REAL, sideBarDistG REAL, sideBarSect REAL," +
                    "hasArtBar INTEGER, artBarH REAL, artBarI REAL, artBarJ REAL, artBarSect REAL, toActDesc TEXT, toActHeight REAL, toActObs TEXT, hasPapHolder INTEGER," +
                    "papHolderType INTEGER, papEmbDist REAL, papEmbHeight REAL, papSupAlign INTEGER, papSupHeight REAL, papHoldObs TEXT, hasDouche INTEGER, douchePressHeight REAL," +
                    "doucheActHeight REAL, doucheObs TEXT, toiletObs TEXT, hasHanger INTEGER, hangerHeight REAL, hangerObs TEXT, hasObjHold INTEGER, objHoldCorrect INTEGER," +
                    "objHoldHeight REAL, objHoldObs TEXT, hasSoapHold INTEGER, soapHoldHeight REAL, soapHoldObs TEXT, hasTowelHold INTEGER, towelHoldHeight REAL, towelHoldObs TEXT," +
                    "hasEmergencyButton INTEGER, emergencyHeight REAL, emergencyObs TEXT, hasWaterValve INTEGER, waterValveType INTEGER, waterValveHeight REAL, waterValveObs TEXT," +
                    "hasWindow INTEGER, winQnt INTEGER, winComType1 TEXT, winComHeight1 REAL, winComType2 TEXT, winComHeight2 REAL, winComType3 TEXT, winComHeight3 REAL, winObs TEXT," +
                    "hasWallMirror INTEGER, wallMirrorLow REAL, wallMirrorHigh REAL, wallMirrorObs TEXT, sinkType INTEGER, hasLowerColSink INTEGER, " +
                    "approxMeasureA REAL, approxMeasureB REAL, approxMeasureC REAL, approxMeasureD REAL, approxMeasureE REAL, hasSinkBar INTEGER, hasLeftFrontHorBar INTEGER, " +
                    "leftFrontHorMeasureA REAL, leftFrontHorMeasureB REAL, leftFrontHorMeasureC REAL, leftFrontHorMeasureD REAL, leftFrontHorDiam REAL, " +
                    "leftFrontHorDist REAL, leftFrontHorObs TEXT," +
                    "hasRightSideVertBar INTEGER, rightSideVertMeasureA REAL, rightSideVertMeasureB REAL, rightSideVertMeasureC REAL, rightSideVertMeasureD REAL, " +
                    "rightSideVertMeasureE REAL, rightSideVertDiam REAL, rightSideVertDist REAL, rightSideVertObs TEXT, sinkHasMirror INTEGER, sinkMirrorLow REAL, " +
                    "sinkMirrorHigh REAL, sinkObs TEXT, hasUrinal INTEGER, hasAccessUrinal INTEGER, urinalType INTEGER, urMeasureA REAL, urMeasureB REAL, urMeasureC REAL," +
                    "urMeasureD REAL, urMeasureE REAL, urMeasureF REAL, urMeasureG REAL, urMeasureH REAL, urMeasureI REAL, urMeasureJ REAL, urMeasureK REAL," +
                    "urMeasureL REAL, urMeasureM REAL, urObs TEXT, " +
                    "FOREIGN KEY (blockID) REFERENCES BlockSpaceEntry (blockSpaceID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("INSERT INTO RestEntry (restroomID, blockID, restType, restLocation, accessRoute, accessRouteObs, intRestroom, intRestObs, antiDriftFloor, " +
                    "antiDriftFloorObs, restDrain, restDrainObs, restSwitch, switchHeight, switchObs,upViewLength, upViewWidth, upViewMeasureA, upViewMeasureB, upViewMeasureC, " +
                    "upViewMeasureD, upViewObs, toType, toHeightNoSeat, toHasSeat, toHeightSeat, toHasSoculo, frSoculo, latSoculo, socCorners, toHasFrontBar, frBarA, frBarB, " +
                    "frBarC, frBarSect, frBarDist, toHasWall, hasHorBar, horBarD, horBarE, horBarF,horBarDistG, horBarSect, horBarDist, hasVertBar, vertBarH, vertBarI, vertBarJ, " +
                    "vertBarSect, vertBarDist, hasArtBar, artBarH, artBarI, artBarJ, artBarSect, toActDesc, toActHeight, toActObs, hasPapHolder, papHolderType, papEmbDist, " +
                    "papEmbHeight, papSupAlign, papSupHeight, papHoldObs, hasDouche, doucheActHeight, doucheObs, toiletObs, hasHanger, hangerHeight, hangerObs, " +
                    "hasObjHold, objHoldCorrect, objHoldHeight, objHoldObs, hasSoapHold, soapHoldHeight, soapHoldObs, hasTowelHold, towelHoldHeight, towelHoldObs," +
                    "hasWallMirror, wallMirrorLow, wallMirrorHigh, wallMirrorObs, sinkType, approxMeasureA, approxMeasureB, approxMeasureC, approxMeasureD, approxMeasureE," +
                    "sinkHasMirror, sinkMirrorLow, sinkMirrorHigh, sinkObs) " +
                    "SELECT restroomID, blockID, restType, restLocation, accessRoute, accessRouteObs, intRestroom, intRestObs, antiDriftFloor, " +
                    "antiDriftFloorObs, restDrain, restDrainObs, restSwitch, switchHeight, switchObs,upViewLength, upViewWidth, upViewMeasureA, upViewMeasureB, upViewMeasureC, " +
                    "upViewMeasureD, upViewObs, toType, toHeightNoSeat, toHasSeat, toHeightSeat, toHasSoculo, frSoculo, latSoculo, socCorners, toHasFrontBar, frBarA, frBarB, " +
                    "frBarC, frBarSect, frBarDist, toHasWall, hasHorBar, horBarD, horBarE, horBarF,horBarDistG, horBarSect, horBarDist, hasVertBar, vertBarH, vertBarI, vertBarJ, " +
                    "vertBarSect, vertBarDist, hasArtBar, artBarH, artBarI, artBarJ, artBarSect, toActDesc, toActHeight, toActObs, hasPapHolder, papHolderType, papEmbDist, " +
                    "papEmbHeight, papSupAlign, papSupHeight, papHoldObs, hasDouche, doucheHeight, doucheObs, toiletObs, hasHanger, hangerHeight, hangerObs, " +
                    "hasObjHold, objHoldCorrect, objHoldHeight, objHoldObs, hasSoapHold, soapHoldHeight, soapHoldObs, hasTowelHold, towelHoldHeight, towelHoldObs," +
                    "hasWallMirror, wallMirrorLow, wallMirrorHigh, wallMirrorObs, sinkType, approxMeasureA, approxMeasureB, approxMeasureC, approxMeasureD, approxMeasureE, " +
                    "sinkHasMirror, siMirrorLow, siMirrorHigh, sinkObs FROM RestroomEntry");
            database.execSQL("DROP TABLE RestroomEntry");
            database.execSQL("ALTER TABLE RestEntry RENAME TO RestroomEntry");



            database.execSQL("ALTER TABLE ExternalAccess ADD COLUMN inclQnt INTEGER");
            database.execSQL("ALTER TABLE ExternalAccess ADD COLUMN inclAngle1 REAL");
            database.execSQL("ALTER TABLE ExternalAccess ADD COLUMN inclAngle2 REAL");
            database.execSQL("ALTER TABLE ExternalAccess ADD COLUMN inclAngle3 REAL");
            database.execSQL("ALTER TABLE ExternalAccess ADD COLUMN inclAngle4 REAL");

            database.execSQL("ALTER TABLE SidewalkSlopeEntry ADD COLUMN inclQnt INTEGER");
            database.execSQL("ALTER TABLE SidewalkSlopeEntry ADD COLUMN inclAngle1 REAL");
            database.execSQL("ALTER TABLE SidewalkSlopeEntry ADD COLUMN inclAngle2 REAL");
            database.execSQL("ALTER TABLE SidewalkSlopeEntry ADD COLUMN inclAngle3 REAL");
            database.execSQL("ALTER TABLE SidewalkSlopeEntry ADD COLUMN inclAngle4 REAL");

        }
    };

    static final Migration MIGRATION_61_62 = new Migration(61, 62) {
        @Override
        public void migrate(@NonNull @NotNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE DoorEntry_2(doorID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, roomID INTEGER, restID INTEGER, boxID INTEGER, doorLocation TEXT, " +
                    "doorType INTEGER, doorWidth1 REAL, doorWidth2 REAL, doorHasPict INTEGER, doorPictObs TEXT, opDirection INTEGER, opDirectionObs TEXT, doorHandleType INTEGER, " +
                    "doorHandleHeight REAL, doorHandleObs TEXT, doorHasLocks INTEGER, doorHasHorBar INTEGER, horBarHeight REAL, horBarLength REAL, horBarFrameDist REAL, " +
                    "horBarDiam REAL, horBarDoorDist REAL, horBarObs TEXT, doorHasWindow INTEGER, doorWinInfHeight REAL, doorWinSupHeight REAL, doorWinWidth REAL, doorWinObs TEXT, " +
                    "doorHasTactSign INTEGER, tactSignHeight REAL, tactSignIncl REAL, tactSignObs TEXT, doorSillType INTEGER, inclHeight REAL, " +
                    "inclQnt INTEGER, inclAngle1 REAL, inclAngle2 REAL, inclAngle3 REAL, inclAngle4 REAL, stepHeight REAL, slopeWidth REAL, slopeHeight REAL," +
                    "slopeQnt INTEGER, slopeAngle1 REAL, slopeAngle2 REAL, slopeAngle3 REAL, slopeAngle4 REAL,  doorSillObs TEXT, doorObs TEXT, " +
                    "FOREIGN KEY (roomID) REFERENCES RoomEntry (roomID) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (restID) REFERENCES RestroomEntry (restroomID) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (BOXID) REFERENCES RestBoxEntry (boxID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("INSERT INTO DoorEntry_2 (doorID, roomID, restID, doorLocation, doorType, doorWidth1, doorWidth2, doorHasPict, doorPictObs, opDirection, " +
                    "opDirectionObs, doorHandleType, doorHandleHeight, doorHandleObs, doorHasLocks, doorHasHorBar, horBarHeight, horBarLength, horBarFrameDist, horBarDiam, " +
                    "horBarDoorDist, horBarObs, doorHasWindow, doorWinInfHeight, doorWinSupHeight, doorWinWidth, doorWinObs, doorHasTactSign, tactSignHeight, tactSignIncl, " +
                    "tactSignObs, doorSillType, inclHeight, inclQnt, inclAngle1, inclAngle2, inclAngle3, inclAngle4, stepHeight, slopeWidth, slopeHeight, slopeQnt, " +
                    "slopeAngle1, slopeAngle2, slopeAngle3, slopeAngle4,  doorSillObs, doorObs) " +
                    "SELECT doorID, roomID, restID, doorLocation, doorType, doorWidth1, doorWidth2, doorHasPict, doorPictObs, opDirection, opDirectionObs, doorHandleType, " +
                    "doorHandleHeight, doorHandleObs, doorHasLocks, doorHasHorBar, horBarHeight, horBarLength, horBarFrameDist, horBarDiam, horBarDoorDist, horBarObs, " +
                    "doorHasWindow, doorWinInfHeight, doorWinSupHeight, doorWinWidth, doorWinObs, doorHasTactSign, tactSignHeight, tactSignIncl, tactSignObs, doorSillType, " +
                    "inclHeight, inclQnt, inclAngle1, inclAngle2, inclAngle3, inclAngle4, stepHeight, slopeWidth, slopeHeight, slopeQnt, slopeAngle1, slopeAngle2, slopeAngle3, " +
                    "slopeAngle4,  doorSillObs, doorObs FROM DoorEntry");
            database.execSQL("DROP TABLE DoorEntry");
            database.execSQL("ALTER TABLE DoorEntry_2 RENAME TO DoorEntry");

            database.execSQL("CREATE TABLE RestBoxEntry(boxID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, restID INTEGER NOT NULL, typeBox INTEGER NOT NULL," +
                    "comBoxDoorWidth REAL, comBoxFreeDiam REAL, comBoxHasBars INTEGER, comBoxToiletDoorDist REAL, comBoxWidth REAL, comBoxHasLeftBar INTEGER," +
                    "comBoxLeftShapeBarA REAL, comBoxLeftShapeBarB REAL, comBoxLeftShapeBarC REAL, comBoxLeftShapeBarD REAL, comBoxLeftShapeBarDiam REAL," +
                    "comBoxLeftShapeBarDist REAL, comBoxLeftHorObs TEXT, comBoxLeftVertBarA REAL, comBoxLeftVertBarB REAL, comBoxLeftVertBarC REAL, comBoxLeftVertBarDiam REAL, " +
                    "comBoxLeftVertBarDist REAL, comBoxLeftVertObs TEXT, comBoxHasRightBar INTEGER, comBoxRightShapeBarA REAL, comBoxRightShapeBarB REAL, " +
                    "comBoxRightShapeBarC REAL, comBoxRightShapeBarD REAL, comBoxRightShapeBarDiam REAL, comBoxRightShapeBarDist REAL, comBoxRightHorObs TEXT, " +
                    "comBoxRightVertBarA REAL, comBoxRightVertBarB REAL, comBoxRightVertBarC REAL, comBoxRightVertBarDiam REAL, comBoxRightVertBarDist REAL, " +
                    "comBoxRightVertObs TEXT, comBoxObs TEXT, upViewLength REAL, upViewWidth REAL, upViewMeasureA REAL, upViewMeasureB REAL, upViewMeasureC REAL, " +
                    "upViewMeasureD REAL, upViewObs TEXT, restDrain INTEGER, restDrainObs TEXT, toType INTEGER, toHeightNoSeat REAL, toHasSeat INTEGER, toHeightSeat REAL, " +
                    "toHasSoculo INTEGER, frSoculo REAL, latSoculo REAL, socCorners INTEGER, toHasFrontBar INTEGER, frBarA REAL, frBarB REAL, frBarC REAL, frBarSect REAL, " +
                    "frBarDist REAL, toHasWall INTEGER, hasHorBar INTEGER, horBarD REAL, horBarE REAL, horBarF REAL, horBarDistG REAL, horBarSect REAL, horBarDist REAL, " +
                    "hasVertBar INTEGER, vertBarH REAL, vertBarI REAL, vertBarJ REAL, vertBarSect REAL, vertBarDist REAL, hasSideBar INTEGER, sideBarD REAL, sideBarE REAL, " +
                    "sideBarDistG REAL, sideBarSect REAL, hasArtBar INTEGER, artBarH REAL, artBarI REAL, artBarJ REAL, artBarSect REAL, toActDesc TEXT, toActHeight REAL, " +
                    "toActObs TEXT, hasPapHolder INTEGER, papHolderType INTEGER, papEmbDist REAL, papEmbHeight REAL, papSupAlign INTEGER, papSupHeight REAL, papHoldObs TEXT, " +
                    "hasDouche INTEGER, douchePressHeight REAL, doucheActHeight REAL, doucheObs TEXT, toiletObs TEXT, hasHanger INTEGER, hangerHeight REAL, hangerObs TEXT, " +
                    "hasObjHold INTEGER, objHoldCorrect INTEGER, objHoldHeight REAL, objHoldObs TEXT, hasSoapHold INTEGER, soapHoldHeight REAL, soapHoldObs TEXT, " +
                    "hasTowelHold INTEGER, towelHoldHeight REAL, towelHoldObs TEXT, hasEmergencyButton INTEGER, emergencyHeight REAL, emergencyObs TEXT, hasWaterValve INTEGER, " +
                    "waterValveType INTEGER, waterValveHeight REAL, waterValveObs TEXT, hasWindow INTEGER, winQnt INTEGER, winComType1 TEXT, winComHeight1 REAL, winComType2 TEXT, " +
                    "winComHeight2 REAL, winComType3 TEXT, winComHeight3 REAL, winObs TEXT, hasWallMirror INTEGER, wallMirrorLow REAL, wallMirrorHigh REAL, wallMirrorObs TEXT, " +
                    "sinkType INTEGER, hasLowerColSink INTEGER, approxMeasureA REAL, approxMeasureB REAL, approxMeasureC REAL, approxMeasureD REAL, approxMeasureE REAL, " +
                    "hasSinkBar INTEGER, hasLeftFrontHorBar INTEGER, leftFrontHorMeasureA REAL, leftFrontHorMeasureB REAL, leftFrontHorMeasureC REAL, leftFrontHorMeasureD REAL, " +
                    "leftFrontHorDiam REAL, leftFrontHorDist REAL, leftFrontHorObs TEXT, hasRightSideVertBar INTEGER, rightSideVertMeasureA REAL, rightSideVertMeasureB REAL, " +
                    "rightSideVertMeasureC REAL, rightSideVertMeasureD REAL, rightSideVertMeasureE REAL, rightSideVertDiam REAL, rightSideVertDist REAL, rightSideVertObs TEXT, " +
                    "sinkHasMirror INTEGER, sinkMirrorLow REAL, sinkMirrorHigh REAL, sinkObs TEXT," +
                    "FOREIGN KEY (restID) REFERENCES RestroomEntry (restroomID) ON UPDATE CASCADE ON DELETE CASCADE )");

            database.execSQL("CREATE TABLE WinEntry(windowID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, roomID INTEGER NOT NULL, windowLocation TEXT," +
                    "winQnt INTEGER NOT NULL DEFAULT 1, comType1 TEXT DEFAULT 'Único', comHeight1 REAL, comType2 TEXT, comHeight2 REAL, comType3 TEXT, " +
                    "comHeight3 REAL, windowObs TEXT," +
                    "FOREIGN KEY (roomID) REFERENCES RoomEntry (roomID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("INSERT INTO WinEntry(windowID, roomID, windowLocation, comHeight1, windowObs) SELECT windowID, roomID, windowLocation," +
                    "windowCommandHeight, windowObs FROM WindowEntry");
            database.execSQL("DROP TABLE WindowEntry");
            database.execSQL("ALTER TABLE WinEntry RENAME TO WindowEntry");
        }
    };

    static final Migration MIGRATION_62_63 = new Migration(62, 63) {
        @Override
        public void migrate(@NonNull @NotNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE WinEntry(windowID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, roomID INTEGER NOT NULL, windowLocation TEXT DEFAULT 'Única'," +
                    "winQnt INTEGER NOT NULL DEFAULT 1, comType1 TEXT, comHeight1 REAL, comType2 TEXT, comHeight2 REAL, comType3 TEXT, " +
                    "comHeight3 REAL, windowObs TEXT," +
                    "FOREIGN KEY (roomID) REFERENCES RoomEntry (roomID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("INSERT INTO WinEntry(windowID, roomID, windowLocation, winQnt, comType1, comHeight1, comType2, comHeight2, comType3," +
                    "comHeight3, windowObs) SELECT windowID, roomID, windowLocation, winQnt, comType1, comHeight1, comType2, comHeight2, comType3," +
                    "comHeight3, windowObs FROM WindowEntry");
            database.execSQL("DROP TABLE WindowEntry");
            database.execSQL("ALTER TABLE WinEntry RENAME TO WindowEntry");

            database.execSQL("CREATE TABLE DoorEntry_2(doorID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, roomID INTEGER, restID INTEGER, boxID INTEGER, doorLocation TEXT, " +
                    "doorType INTEGER, doorWidth1 REAL, doorWidth2 REAL, doorHasPict INTEGER, doorPictObs TEXT, opDirection INTEGER, opDirectionObs TEXT, doorHandleType INTEGER, " +
                    "doorHandleHeight REAL, doorHandleObs TEXT, doorHasLocks INTEGER, doorHasHorBar INTEGER, horBarHeight REAL, horBarLength REAL, horBarFrameDist REAL, " +
                    "horBarDiam REAL, horBarDoorDist REAL, horBarObs TEXT, doorHasWindow INTEGER, doorWinInfHeight REAL, doorWinSupHeight REAL, doorWinWidth REAL, doorWinObs TEXT, " +
                    "doorHasTactSign INTEGER, tactSignHeight REAL, tactSignIncl REAL, tactSignObs TEXT, doorSillType INTEGER, inclHeight REAL, " +
                    "inclQnt INTEGER, inclAngle1 REAL, inclAngle2 REAL, inclAngle3 REAL, inclAngle4 REAL, stepHeight REAL, slopeWidth REAL, slopeHeight REAL," +
                    "slopeQnt INTEGER, slopeAngle1 REAL, slopeAngle2 REAL, slopeAngle3 REAL, slopeAngle4 REAL,  doorSillObs TEXT, doorObs TEXT, " +
                    "FOREIGN KEY (roomID) REFERENCES RoomEntry (roomID) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (restID) REFERENCES RestroomEntry (restroomID) ON UPDATE CASCADE ON DELETE CASCADE," +
                    "FOREIGN KEY (BOXID) REFERENCES RestBoxEntry (boxID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("INSERT INTO DoorEntry_2 (doorID, roomID, restID, doorLocation, doorType, doorWidth1, doorWidth2, doorHasPict, doorPictObs, opDirection, " +
                    "opDirectionObs, doorHandleType, doorHandleHeight, doorHandleObs, doorHasLocks, doorHasHorBar, horBarHeight, horBarLength, horBarFrameDist, horBarDiam, " +
                    "horBarDoorDist, horBarObs, doorHasWindow, doorWinInfHeight, doorWinSupHeight, doorWinWidth, doorWinObs, doorHasTactSign, tactSignHeight, tactSignIncl, " +
                    "tactSignObs, doorSillType, inclHeight, inclQnt, inclAngle1, inclAngle2, inclAngle3, inclAngle4, stepHeight, slopeWidth, slopeHeight, slopeQnt, " +
                    "slopeAngle1, slopeAngle2, slopeAngle3, slopeAngle4,  doorSillObs, doorObs) " +
                    "SELECT doorID, roomID, restID, doorLocation, doorType, doorWidth1, doorWidth2, doorHasPict, doorPictObs, opDirection, opDirectionObs, doorHandleType, " +
                    "doorHandleHeight, doorHandleObs, doorHasLocks, doorHasHorBar, horBarHeight, horBarLength, horBarFrameDist, horBarDiam, horBarDoorDist, horBarObs, " +
                    "doorHasWindow, doorWinInfHeight, doorWinSupHeight, doorWinWidth, doorWinObs, doorHasTactSign, tactSignHeight, tactSignIncl, tactSignObs, doorSillType, " +
                    "inclHeight, inclQnt, inclAngle1, inclAngle2, inclAngle3, inclAngle4, stepHeight, slopeWidth, slopeHeight, slopeQnt, slopeAngle1, slopeAngle2, slopeAngle3, " +
                    "slopeAngle4,  doorSillObs, doorObs FROM DoorEntry");
            database.execSQL("DROP TABLE DoorEntry");
            database.execSQL("ALTER TABLE DoorEntry_2 RENAME TO DoorEntry");
        }
    };

    static final Migration MIGRATION_63_64 = new Migration(63, 64) {
        @Override
        public void migrate(@NonNull @NotNull SupportSQLiteDatabase database) {

            database.execSQL("ALTER TABLE SidewalkEntry ADD COLUMN sidewalkObs2 TEXT");
            database.execSQL("ALTER TABLE SidewalkEntry ADD COLUMN sidePhotos TEXT");
        }
    };

    static final Migration MIGRATION_64_65 = new Migration(64, 65) {
        @Override
        public void migrate(@NonNull @NotNull SupportSQLiteDatabase database) {

            database.execSQL("ALTER TABLE ParkingLotEntry ADD COLUMN parkingObs TEXT");

            database.execSQL("ALTER TABLE DoorEntry ADD COLUMN hasSillIncl INTEGER");
            database.execSQL("ALTER TABLE DoorEntry ADD COLUMN doorPhotos TEXT");

            database.execSQL("ALTER TABLE SidewalkSlopeEntry ADD COLUMN hasSillIncl INTEGER");
            database.execSQL("ALTER TABLE SidewalkSlopeEntry ADD COLUMN sideSlopePhotos TEXT");

            database.execSQL("ALTER TABLE ExternalAccess ADD COLUMN hasSillIncl INTEGER");
            database.execSQL("ALTER TABLE ExternalAccess ADD COLUMN extAccPhotos TEXT");

            database.execSQL("ALTER TABLE PlaygroundEntry ADD COLUMN hasSillIncl INTEGER");

            database.execSQL("ALTER TABLE RestroomEntry ADD COLUMN hasSink INTEGER");
            database.execSQL("UPDATE RestroomEntry SET hasSink = 1");

            database.execSQL("ALTER TABLE RestBoxEntry ADD COLUMN hasSink INTEGER");
            database.execSQL("UPDATE RestBoxEntry SET hasSink = 1");
        }
    };

    static final Migration MIGRATION_65_66 = new Migration(65, 66) {
        @Override
        public void migrate(@NonNull @NotNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE Side2(sidewalkID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, blockID INTEGER NOT NULL, sidewalkLocation TEXT,  streetPavement INTEGER, hasSidewalk INTEGER, " +
                    "sidewalkWidth REAL, sideFreeSpaceWidth REAL, sideMeasureObs TEXT, slopeMeasureQnt INTEGER, sideTransSlope1 REAL, sideTransSlope2 REAL, sideTransSlope3 REAL, sideTransSlope4 REAL," +
                    "sideTransSlope5 REAL, sideTransSlope6 REAL, hasSpecialFloor INTEGER, specialFloorRightColor INTEGER, specialTileDirectionWidth REAL, specialTileAlertWidth REAL, specialFloorObs TEXT," +
                    "sidewalkObs TEXT, sideConStatus INTEGER, sideConsObs TEXT, sideFloorIsAccessible INTEGER, accessFloorObs TEXT, sidewalkHasLids INTEGER, sidewalkLidDesc TEXT, hasAerialObstacle INTEGER, " +
                    "aerialObstacleDesc TEXT, sideHasSlope INTEGER, sideReqSlopes INTEGER, sideHasPayphones INTEGER, sidewalkObs2 TEXT, sidePhotos TEXT, " +
                    "FOREIGN KEY (blockID) REFERENCES BlockSpaceEntry (blockSpaceID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("INSERT INTO Side2 (sidewalkID, blockID, sidewalkLocation, streetPavement, hasSidewalk, sidewalkWidth, sideFreeSpaceWidth, sideMeasureObs, slopeMeasureQnt, " +
                    "sideTransSlope1, sideTransSlope2, sideTransSlope3, sideTransSlope4, sideTransSlope5, sideTransSlope6, hasSpecialFloor, specialFloorRightColor, specialTileDirectionWidth, " +
                    "specialTileAlertWidth, specialFloorObs, sidewalkObs, sideConStatus, sideConsObs, sideFloorIsAccessible, accessFloorObs, sidewalkHasLids, sidewalkLidDesc, hasAerialObstacle, " +
                    "aerialObstacleDesc, sideHasSlope, sideReqSlopes, sideHasPayphones, sidewalkObs2, sidePhotos) SELECT sidewalkID, blockID, sidewalkLocation, streetPavement, hasSidewalk, " +
                    "sidewalkWidth, sideFreeSpaceWidth, sideMeasureObs, slopeMeasureQnt,  sideTransSlope1, sideTransSlope2, sideTransSlope3, sideTransSlope4, sideTransSlope5, sideTransSlope6, " +
                    "hasSpecialFloor, specialFloorRightColor, specialTileDirectionWidth, specialTileAlertWidth, specialFloorObs, sidewalkObs, sideConStatus, sideConsObs, sideFloorIsAccessible, " +
                    "accessFloorObs, sidewalkHasLids, sidewalkLidDesc, hasAerialObstacle, aerialObstacleDesc, sideHasSlope, sideReqSlopes, sideHasPayphones, sidewalkObs2, sidePhotos FROM SidewalkEntry");
            database.execSQL("DROP TABLE SidewalkEntry");
            database.execSQL("ALTER TABLE Side2 RENAME TO SidewalkEntry");

            database.execSQL("CREATE TABLE EquipmentEntry (equipID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, roomID INTEGER NOT NULL, equipName TEXT, equipLocale TEXT, equipHeight REAL NOT NULL," +
                    "equipObs TEXT, FOREIGN KEY (roomID) REFERENCES RoomEntry (roomID) ON UPDATE CASCADE ON DELETE CASCADE)");

            database.execSQL("ALTER TABLE RestroomEntry ADD COLUMN hasLowerSink INTEGER");
        }
    };

    static final Migration MIGRATION_66_67 = new Migration(66, 67) {
        @Override
        public void migrate(@NonNull @NotNull SupportSQLiteDatabase database) {

            database.execSQL("ALTER TABLE TableEntry ADD COLUMN tableDesc TEXT");

            database.execSQL("CREATE TABLE RestEntry (restroomID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, blockID INTEGER NOT NULL, restType INTEGER NOT NULL DEFAULT 0," +
                    "restGender INTEGER, restLocation TEXT, collectiveHasDoor INTEGER, entranceWidth REAL, entranceDoorSill INTEGER, entranceDoorSillObs TEXT, " +
                    "accessRoute INTEGER, accessRouteObs TEXT, intRestroom INTEGER, intRestObs TEXT, antiDriftFloor INTEGER, antiDriftFloorObs TEXT, restDrain INTEGER, " +
                    "restDrainObs TEXT, restSwitch INTEGER, switchHeight REAL, switchObs TEXT," +
                    "upViewLength REAL, upViewWidth REAL, upViewMeasureA REAL, upViewMeasureB REAL, upViewMeasureC REAL, upViewMeasureD REAL, upViewObs TEXT, hasChildToilet INTEGER, " +
                    "toType INTEGER," +
                    "toHeightNoSeat REAL, toHasSeat INTEGER, toHeightSeat REAL, toHasSoculo INTEGER, frSoculo REAL, latSoculo REAL, socCorners INTEGER, toHasFrontBar INTEGER," +
                    "frBarA REAL, frBarB REAL, frBarC REAL, frBarSect REAL, frBarDist REAL, toHasWall INTEGER, hasHorBar INTEGER, horBarD REAL, horBarE REAL, horBarF REAL," +
                    "horBarDistG REAL, horBarSect REAL, horBarDist REAL, hasVertBar INTEGER, vertBarH REAL, vertBarI REAL, vertBarJ REAL, vertBarSect REAL, vertBarDist REAL," +
                    "hasSideBar INTEGER, sideBarD REAL, sideBarE REAL, sideBarDistG REAL, sideBarSect REAL," +
                    "hasArtBar INTEGER, artBarH REAL, artBarI REAL, artBarJ REAL, artBarSect REAL, toActDesc TEXT, toActHeight REAL, toActObs TEXT, hasPapHolder INTEGER," +
                    "papHolderType INTEGER, papEmbDist REAL, papEmbHeight REAL, papSupAlign INTEGER, papSupHeight REAL, papHoldObs TEXT, hasDouche INTEGER, douchePressHeight REAL," +
                    "doucheActHeight REAL, doucheObs TEXT, toiletObs TEXT, hasHanger INTEGER, hangerHeight REAL, hangerObs TEXT, hasObjHold INTEGER, objHoldCorrect INTEGER," +
                    "objHoldHeight REAL, objHoldObs TEXT, hasSoapHold INTEGER, soapHoldHeight REAL, soapHoldObs TEXT, hasTowelHold INTEGER, towelHoldHeight REAL, towelHoldObs TEXT," +
                    "hasEmergencyButton INTEGER, emergencyHeight REAL, emergencyObs TEXT, hasWaterValve INTEGER, waterValveType INTEGER, waterValveHeight REAL, waterValveObs TEXT," +
                    "hasWindow INTEGER, winQnt INTEGER, winComType1 TEXT, winComHeight1 REAL, winComType2 TEXT, winComHeight2 REAL, winComType3 TEXT, winComHeight3 REAL, winObs TEXT," +
                    "hasWallMirror INTEGER, wallMirrorLow REAL, wallMirrorHigh REAL, wallMirrorObs TEXT, hasSink INTEGER, sinkType INTEGER, hasLowerSink INTEGER, " +
                    "hasLowerColSink INTEGER, " +
                    "approxMeasureA REAL, approxMeasureB REAL, approxMeasureC REAL, approxMeasureD REAL, approxMeasureE REAL, hasSinkBar INTEGER, hasLeftFrontHorBar INTEGER, " +
                    "leftFrontHorMeasureA REAL, leftFrontHorMeasureB REAL, leftFrontHorMeasureC REAL, leftFrontHorMeasureD REAL, leftFrontHorDiam REAL, " +
                    "leftFrontHorDist REAL, leftFrontHorObs TEXT," +
                    "hasRightSideVertBar INTEGER, rightSideVertMeasureA REAL, rightSideVertMeasureB REAL, rightSideVertMeasureC REAL, rightSideVertMeasureD REAL, " +
                    "rightSideVertMeasureE REAL, rightSideVertDiam REAL, rightSideVertDist REAL, rightSideVertObs TEXT, sinkHasMirror INTEGER, sinkMirrorLow REAL, " +
                    "sinkMirrorHigh REAL, sinkObs TEXT, hasUrinal INTEGER, hasAccessUrinal INTEGER, urinalType INTEGER, urMeasureA REAL, urMeasureB REAL, urMeasureC REAL," +
                    "urMeasureD REAL, urMeasureE REAL, urMeasureF REAL, urMeasureG REAL, urMeasureH REAL, urMeasureI REAL, urMeasureJ REAL, urMeasureK REAL," +
                    "urMeasureL REAL, urMeasureM REAL, urObs TEXT, FOREIGN KEY (blockID) REFERENCES BlockSpaceEntry (blockSpaceID) ON UPDATE CASCADE ON DELETE CASCADE)");
            database.execSQL("INSERT INTO RestEntry (restroomID, blockID, restType, restGender, restLocation, collectiveHasDoor, entranceWidth, entranceDoorSill, " +
                    "entranceDoorSillObs, accessRoute, accessRouteObs, intRestroom, intRestObs, antiDriftFloor, antiDriftFloorObs, restDrain, restDrainObs, " +
                    "restSwitch, switchHeight, switchObs, upViewLength, upViewWidth, upViewMeasureA, upViewMeasureB, upViewMeasureC, upViewMeasureD, upViewObs, " +
                    "toType, toHeightNoSeat, toHasSeat, toHeightSeat, toHasSoculo, frSoculo, latSoculo, socCorners, toHasFrontBar, frBarA, frBarB, " +
                    "frBarC, frBarSect, frBarDist, toHasWall, hasHorBar, horBarD, horBarE, horBarF, horBarDistG, horBarSect, horBarDist, hasVertBar, vertBarH, " +
                    "vertBarI, vertBarJ, vertBarSect, vertBarDist, hasSideBar, sideBarD, sideBarE, sideBarDistG, sideBarSect, hasArtBar, artBarH, artBarI, artBarJ, " +
                    "artBarSect, toActDesc, toActHeight, toActObs, hasPapHolder, papHolderType, papEmbDist, papEmbHeight, papSupAlign, papSupHeight, papHoldObs, " +
                    "hasDouche, douchePressHeight, doucheActHeight, doucheObs, toiletObs, hasHanger, hangerHeight, hangerObs, hasObjHold, objHoldCorrect," +
                    "objHoldHeight, objHoldObs, hasSoapHold, soapHoldHeight, soapHoldObs, hasTowelHold, towelHoldHeight, towelHoldObs, hasEmergencyButton, " +
                    "emergencyHeight, emergencyObs, hasWaterValve, waterValveType, waterValveHeight, waterValveObs, hasWindow, winQnt, winComType1, winComHeight1, " +
                    "winComType2, winComHeight2, winComType3, winComHeight3, winObs, hasWallMirror, wallMirrorLow, wallMirrorHigh, wallMirrorObs, sinkType, hasLowerSink, " +
                    "hasLowerColSink, approxMeasureA, approxMeasureB, approxMeasureC, approxMeasureD, approxMeasureE, hasSinkBar, hasLeftFrontHorBar, leftFrontHorMeasureA, " +
                    "leftFrontHorMeasureB, leftFrontHorMeasureC, leftFrontHorMeasureD, leftFrontHorDiam, leftFrontHorDist, leftFrontHorObs, hasRightSideVertBar, " +
                    "rightSideVertMeasureA, rightSideVertMeasureB, rightSideVertMeasureC, rightSideVertMeasureD, rightSideVertMeasureE, rightSideVertDiam, rightSideVertDist, " +
                    "rightSideVertObs, sinkHasMirror, sinkMirrorLow, sinkMirrorHigh, sinkObs, hasUrinal, hasAccessUrinal, urinalType, urMeasureA, urMeasureB, urMeasureC," +
                    "urMeasureD, urMeasureE, urMeasureF, urMeasureG, urMeasureH, urMeasureI, urMeasureJ, urMeasureK, urMeasureL, urMeasureM, urObs) SELECT " +
                    "restroomID, blockID, isCollective, restType, restLocation, collectiveHasDoor, entranceWidth, entranceDoorSill, " +
                    "entranceDoorSillObs, accessRoute, accessRouteObs, intRestroom, intRestObs, antiDriftFloor, antiDriftFloorObs, restDrain, restDrainObs, " +
                    "restSwitch, switchHeight, switchObs, upViewLength, upViewWidth, upViewMeasureA, upViewMeasureB, upViewMeasureC, upViewMeasureD, upViewObs, " +
                    "toType, toHeightNoSeat, toHasSeat, toHeightSeat, toHasSoculo, frSoculo, latSoculo, socCorners, toHasFrontBar, frBarA, frBarB, " +
                    "frBarC, frBarSect, frBarDist, toHasWall, hasHorBar, horBarD, horBarE, horBarF, horBarDistG, horBarSect, horBarDist, hasVertBar, vertBarH, " +
                    "vertBarI, vertBarJ, vertBarSect, vertBarDist, hasSideBar, sideBarD, sideBarE, sideBarDistG, sideBarSect, hasArtBar, artBarH, artBarI, artBarJ, " +
                    "artBarSect, toActDesc, toActHeight, toActObs, hasPapHolder, papHolderType, papEmbDist, papEmbHeight, papSupAlign, papSupHeight, papHoldObs, " +
                    "hasDouche, douchePressHeight, doucheActHeight, doucheObs, toiletObs, hasHanger, hangerHeight, hangerObs, hasObjHold, objHoldCorrect," +
                    "objHoldHeight, objHoldObs, hasSoapHold, soapHoldHeight, soapHoldObs, hasTowelHold, towelHoldHeight, towelHoldObs, hasEmergencyButton, " +
                    "emergencyHeight, emergencyObs, hasWaterValve, waterValveType, waterValveHeight, waterValveObs, hasWindow, winQnt, winComType1, winComHeight1, " +
                    "winComType2, winComHeight2, winComType3, winComHeight3, winObs, hasWallMirror, wallMirrorLow, wallMirrorHigh, wallMirrorObs, sinkType, hasLowerSink, " +
                    "hasLowerColSink, approxMeasureA, approxMeasureB, approxMeasureC, approxMeasureD, approxMeasureE, hasSinkBar, hasLeftFrontHorBar, leftFrontHorMeasureA, " +
                    "leftFrontHorMeasureB, leftFrontHorMeasureC, leftFrontHorMeasureD, leftFrontHorDiam, leftFrontHorDist, leftFrontHorObs, hasRightSideVertBar, " +
                    "rightSideVertMeasureA, rightSideVertMeasureB, rightSideVertMeasureC, rightSideVertMeasureD, rightSideVertMeasureE, rightSideVertDiam, rightSideVertDist, " +
                    "rightSideVertObs, sinkHasMirror, sinkMirrorLow, sinkMirrorHigh, sinkObs, hasUrinal, hasAccessUrinal, urinalType, urMeasureA, urMeasureB, urMeasureC," +
                    "urMeasureD, urMeasureE, urMeasureF, urMeasureG, urMeasureH, urMeasureI, urMeasureJ, urMeasureK, urMeasureL, urMeasureM, urObs FROM RestroomEntry");
            database.execSQL("DROP TABLE RestroomEntry");
            database.execSQL("ALTER TABLE RestEntry RENAME TO RestroomEntry");

        }
    };

    static final Migration MIGRATION_67_68 = new Migration(67, 68) {
        @Override
        public void migrate(@NonNull @NotNull SupportSQLiteDatabase database) {

            database.execSQL("DROP TABLE RestBoxEntry");
            database.execSQL("CREATE TABLE RestBoxEntry (boxID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,restID INTEGER NOT NULL,typeBox INTEGER NOT NULL,comBoxDoorWidth REAL, " +
                    "comBoxFreeDiam REAL, comBoxHasBars INTEGER, comBoxToiletDoorDist REAL, comBoxWidth REAL, comBoxHasLeftBar INTEGER, comBoxLeftShapeBarA REAL,  " +
                    "comBoxLeftShapeBarB REAL, comBoxLeftShapeBarC REAL, comBoxLeftShapeBarD REAL, comBoxLeftShapeBarDiam REAL, comBoxLeftShapeBarDist REAL,  " +
                    "comBoxLeftBarObs TEXT, comBoxHasRightBar INTEGER, comBoxRightShapeBarA REAL, comBoxRightShapeBarB REAL, comBoxRightShapeBarC REAL,  " +
                    "comBoxRightShapeBarD REAL, comBoxRightShapeBarDiam REAL, comBoxRightShapeBarDist REAL, comBoxRightBarObs TEXT, comBoxObs TEXT, upViewLength REAL,  " +
                    "upViewWidth REAL, upViewMeasureA REAL, upViewMeasureB REAL, upViewMeasureC REAL, upViewMeasureD REAL, upViewObs TEXT, restDrain INTEGER, restDrainObs TEXT,  " +
                    "toType INTEGER, toHeightNoSeat REAL, toHasSeat INTEGER, toHeightSeat REAL, toHasSoculo INTEGER, frSoculo REAL, latSoculo REAL, socCorners INTEGER,  " +
                    "toHasFrontBar INTEGER, frBarA REAL, frBarB REAL, frBarC REAL, frBarSect REAL, frBarDist REAL, toHasWall INTEGER, hasHorBar INTEGER, horBarD REAL, " +
                    "horBarE REAL, horBarF REAL, horBarDistG REAL, horBarSect REAL, horBarDist REAL, hasVertBar INTEGER, vertBarH REAL, vertBarI REAL, vertBarJ REAL, vertBarSect REAL, " +
                    "vertBarDist REAL, hasSideBar INTEGER, sideBarD REAL, sideBarE REAL, sideBarDistG REAL, sideBarSect REAL, hasArtBar INTEGER, artBarH REAL, artBarI REAL,  " +
                    "artBarJ REAL, artBarSect REAL, toActDesc TEXT, toActHeight REAL, toActObs TEXT, hasPapHolder INTEGER, papHolderType INTEGER, papEmbDist REAL, papEmbHeight REAL, " +
                    "papSupAlign INTEGER, papSupHeight REAL, papHoldObs TEXT, hasDouche INTEGER, douchePressHeight REAL, doucheActHeight REAL, doucheObs TEXT, toiletObs TEXT,  " +
                    "hasHanger INTEGER, hangerHeight REAL, hangerObs TEXT, hasObjHold INTEGER, objHoldCorrect INTEGER, objHoldHeight REAL, objHoldObs TEXT, hasSoapHold INTEGER,  " +
                    "soapHoldHeight REAL, soapHoldObs TEXT, hasTowelHold INTEGER, towelHoldHeight REAL, towelHoldObs TEXT, hasEmergencyButton INTEGER, emergencyHeight REAL,  " +
                    "emergencyObs TEXT, hasWaterValve INTEGER, waterValveType INTEGER, waterValveHeight REAL, waterValveObs TEXT, hasWallMirror INTEGER,  " +
                    "wallMirrorLow REAL, wallMirrorHigh REAL, wallMirrorObs TEXT, hasSink INTEGER, sinkType INTEGER, hasLowerColSink INTEGER, approxMeasureA REAL, approxMeasureB REAL,  " +
                    "approxMeasureC REAL, approxMeasureD REAL, approxMeasureE REAL, hasSinkBar INTEGER, hasLeftFrontHorBar INTEGER, leftFrontHorMeasureA REAL,  " +
                    "leftFrontHorMeasureB REAL, leftFrontHorMeasureC REAL, leftFrontHorMeasureD REAL, leftFrontHorDiam REAL, leftFrontHorDist REAL, leftFrontHorObs TEXT,  " +
                    "hasRightSideVertBar INTEGER, rightSideVertMeasureA REAL, rightSideVertMeasureB REAL, rightSideVertMeasureC REAL, rightSideVertMeasureD REAL,  " +
                    "rightSideVertMeasureE REAL, rightSideVertDiam REAL, rightSideVertDist REAL, rightSideVertObs TEXT, sinkHasMirror INTEGER, sinkMirrorLow REAL,  " +
                    "sinkMirrorHigh REAL, sinkObs TEXT, FOREIGN KEY (restID) REFERENCES RestroomEntry (restroomID) ON UPDATE CASCADE ON DELETE CASCADE)");
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
                                    MIGRATION_30_31, MIGRATION_31_32, MIGRATION_32_33, MIGRATION_33_34, MIGRATION_34_35, MIGRATION_35_36,
                                    MIGRATION_36_37, MIGRATION_37_38, MIGRATION_38_39, MIGRATION_39_40, MIGRATION_40_41, MIGRATION_41_42,
                                    MIGRATION_42_43, MIGRATION_43_44, MIGRATION_44_45, MIGRATION_45_46, MIGRATION_46_47, MIGRATION_47_48,
                                    MIGRATION_48_49, MIGRATION_49_50, MIGRATION_50_51, MIGRATION_51_52, MIGRATION_52_53, MIGRATION_53_54,
                                    MIGRATION_54_55, MIGRATION_55_56, MIGRATION_56_57, MIGRATION_57_58, MIGRATION_58_59, MIGRATION_59_60,
                                    MIGRATION_60_61, MIGRATION_61_62, MIGRATION_62_63, MIGRATION_63_64, MIGRATION_64_65, MIGRATION_65_66,
                                    MIGRATION_66_67, MIGRATION_67_68).build();
                }
            }
        }
        return INSTANCE;
    }


    public abstract SchoolEntryDao schoolEntryDao();

    public abstract WaterFountainDao waterFountainDao();

    public abstract ExternalAccessDao externalAccessDao();

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

    public abstract SidewalkEntryDao sidewalkEntryDao();

    public abstract SidewalkSlopeDao sidewalkSlopeDao();

    public abstract RampStairsHandrailDao rampStairsHandrailDao();

    public abstract RampStairsRailingDao rampStairsRailingDao();

    public abstract BlockSpaceDao blockSpaceDao();

    public abstract PlaygroundEntryDao playgroundEntryDao();

    public abstract BlackboardEntryDao blackboardEntryDao();

    public abstract DoorLockDao doorLockDao();

    public abstract RestBoxDao restBoxDao();

    public abstract EquipEntryDao equipEntryDao();

}
