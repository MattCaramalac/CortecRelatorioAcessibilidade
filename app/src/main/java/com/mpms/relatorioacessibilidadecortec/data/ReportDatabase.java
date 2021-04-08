package com.mpms.relatorioacessibilidadecortec.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.mpms.relatorioacessibilidadecortec.entities.SchoolEntry;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {SchoolEntry.class}, version = 1, exportSchema = false)
public abstract class ReportDatabase extends RoomDatabase {

    public static final int NUMBER_THREADS = 4;

    public abstract SchoolEntryDao schoolEntryDao();

    private static volatile ReportDatabase INSTANCE;

    public static final ExecutorService dbWriteExecutor = Executors.newFixedThreadPool(NUMBER_THREADS);

    public static ReportDatabase getDatabase(final Context context) {
        if (INSTANCE == null){
            synchronized (ReportDatabase.class) {
                if (INSTANCE == null) {
                    Room.databaseBuilder(context.getApplicationContext(), ReportDatabase.class, "ReportDatabase")
                            .addCallback(roomCallback).build();
                }
            }
        }
        return INSTANCE;
    }

    public static final RoomDatabase.Callback  roomCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);

                    dbWriteExecutor.execute(() -> {
                        SchoolEntryDao schoolEntryDao = INSTANCE.schoolEntryDao();
                        List<SchoolEntry> entry = (List<SchoolEntry>) INSTANCE.schoolEntryDao().getAllEntries();

                        for (int i = 0; i < entry.size(); i++) {
                            schoolEntryDao.insertEntry(entry.get(i));
                        }

                    });
                }
            };
}
