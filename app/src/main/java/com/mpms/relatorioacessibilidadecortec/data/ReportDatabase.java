package com.mpms.relatorioacessibilidadecortec.data;

import android.content.Context;

import com.mpms.relatorioacessibilidadecortec.entities.SchoolEntry;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {SchoolEntry.class}, version = 2)
public abstract class ReportDatabase extends RoomDatabase {

    public static final int NUMBER_THREADS = 4;
    private static volatile ReportDatabase INSTANCE;
    public static final ExecutorService dbWriteExecutor = Executors.newFixedThreadPool(NUMBER_THREADS);

    public static final RoomDatabase.Callback  roomCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);

                    dbWriteExecutor.execute(() -> {
                        SchoolEntryDao schoolEntryDao = INSTANCE.schoolEntryDao();
                    });
                }
            };
//    Como Fazer Migrações para outros níveis de DB. Útil para persistência dos dados!
//    static final Migration MIGRATION_1_2 = new Migration(1,2) {
//        @Override
//        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE SchoolEntry ADD COLUMN pub_year INTEGER default 0 NOT NULL");
//        }
//    };

    public static ReportDatabase getDatabase(final Context context) {
        if (INSTANCE == null){
            synchronized (ReportDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ReportDatabase.class, "ReportDatabase")
                            .addCallback(roomCallback).fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }


    public abstract SchoolEntryDao schoolEntryDao();
}
