package com.example.tally.thread;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.tally.AppDatabase;
import com.example.tally.bean.Expenditure;
import com.orhanobut.logger.Logger;

/**
 * android不能用主线程进行可能妨碍UI运行的操作，所以需要新线程
 */

public class TallyThread implements Runnable {

    private Expenditure expenditure;
    private Context context;
    private Migration MIGRATION_1_2;
    {
        MIGRATION_1_2 = new Migration(1, 2) {
            @Override
            public void migrate(SupportSQLiteDatabase database) {
//                database.execSQL("ALTER TABLE expenditure ");
            }
        };
    }

    public TallyThread(Expenditure expenditure,Context context) {
        this.expenditure = expenditure;
        this.context = context;
    }

    @Override
    public void run() {
        Log.d("TallyThread","insert data");
        AppDatabase db = Room.databaseBuilder(context,
                AppDatabase.class, "expenditure")
                .allowMainThreadQueries()
                .addMigrations(MIGRATION_1_2)
                .build();
        db.expenditureDao().insert(expenditure);
        db.close();
    }
}
