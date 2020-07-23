package com.example.tally.bean;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Expenditure {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "time")
    private String time;
    @ColumnInfo(name = "type")
    private int type;
    @ColumnInfo(name = "num")
    private double num;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getNum() {
        return num;
    }

    public void setNum(double num) {
        this.num = num;
    }
}
