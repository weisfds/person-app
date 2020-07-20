package com.example.tally;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.tally.bean.Expenditure;
import com.example.tally.dao.ExpenditureDao;

@Database(entities = {Expenditure.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ExpenditureDao expenditureDao();
}
