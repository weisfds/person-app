package com.example.tally.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.tally.bean.Expenditure;

import java.util.List;

@Dao
public interface ExpenditureDao {

    @Query("SELECT * FROM expenditure")
    List<Expenditure> getAll();

    @Insert
    public void insert(Expenditure expenditure);

}
