package com.example.projectfinal.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Insert;

@Entity(primaryKeys = {"DiseaseId"})
public class Disease {
    @NonNull
    @ColumnInfo(name = "DiseaseId")
    public String id;
    @NonNull
    @ColumnInfo(name = "Title")
    public String title;
    @NonNull
    @ColumnInfo(name = "Medicine")
    public String medicine;
    @NonNull
    @ColumnInfo(name = "Frequence")
    public String frequence;
    @NonNull
    @ColumnInfo(name = "IdMgr")
    public String idMgr;

    @Override
    public String toString() {
        return "Disease{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", medicine='" + medicine + '\'' +
                ", frequence='" + frequence + '\'' +
                ", idMgr='" + idMgr + '\'' +
                '}';
    }
}
