package com.example.tally;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.tally.bean.Expenditure;
import com.example.tally.dao.ExpenditureDao;

/**
 * 数据库对象
 */
// 实体类 版本号
@Database(entities = {Expenditure.class}, version = 2,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ExpenditureDao expenditureDao();


}
