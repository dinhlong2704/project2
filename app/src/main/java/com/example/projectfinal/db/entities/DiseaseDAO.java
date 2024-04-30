package com.example.projectfinal.db.entities;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DiseaseDAO {
    @Query("Select * From Disease")
    List<Disease> getAllDisease();

    @Insert
    void insertDisease(Disease disease);

    @Delete
    void deleteDisease(Disease disease);
}
