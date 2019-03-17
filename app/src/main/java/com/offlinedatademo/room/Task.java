package com.offlinedatademo.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Task implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int idd;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "id")
    private String ailment_id;

    @ColumnInfo(name = "finished")
    private boolean finished;

    public Task() {
    }

    public int getIdd() {
        return idd;
    }

    public void setIdd(int idd) {
        this.idd = idd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAilment_id() {
        return ailment_id;
    }

    public void setAilment_id(String ailment_id) {
        this.ailment_id = ailment_id;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
