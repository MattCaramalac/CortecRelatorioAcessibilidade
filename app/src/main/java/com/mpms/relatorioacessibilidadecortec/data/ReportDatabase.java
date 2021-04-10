package com.mpms.relatorioacessibilidadecortec.data;

import android.content.Context;

import com.mpms.relatorioacessibilidadecortec.data.SchoolEntryDao;
import com.mpms.relatorioacessibilidadecortec.entities.SchoolEntry;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {SchoolEntry.class}, version = 1, exportSchema = false)
public abstract class ReportDatabase extends RoomDatabase {

    public static final int NUMBER_THREADS = 4;
//    public abstract SchoolEntryDao schoolEntryDao();
    private static volatile ReportDatabase INSTANCE;
    public static final ExecutorService dbWriteExecutor = Executors.newFixedThreadPool(NUMBER_THREADS);

    public static final RoomDatabase.Callback  roomCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);

                    dbWriteExecutor.execute(() -> {
                        SchoolEntryDao schoolEntryDao = INSTANCE.schoolEntryDao();

//                        SchoolEntry schoolEntry = new SchoolEntry("E.E.Zélia Quevedo Chaves", "Maria das Couves",
//                                "Campo Grande", Long.parseLong("452255555"), 1524, 23, 125,
//                                15, 12);
//                        schoolEntryDao.insertEntry(schoolEntry);
//                        List<SchoolEntry> entry = (List<SchoolEntry>) INSTANCE.schoolEntryDao().getAllEntries();
//                        for (int i = 0; i < entry.size(); i++) {
//                            schoolEntryDao.insertEntry(entry.get(i));
//                        }
                    });
                }
            };

    public static ReportDatabase getDatabase(final Context context) {
        if (INSTANCE == null){
            synchronized (ReportDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ReportDatabase.class, "ReportDatabase")
                            .addCallback(roomCallback).build();
                }
            }
        }
        return INSTANCE;
    }


    public abstract SchoolEntryDao schoolEntryDao();
}
