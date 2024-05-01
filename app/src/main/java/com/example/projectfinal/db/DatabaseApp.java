package com.example.projectfinal.db;



import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.projectfinal.db.entities.Account;
import com.example.projectfinal.db.entities.AccountDAO;
import com.example.projectfinal.db.entities.Disease;
import com.example.projectfinal.db.entities.DiseaseDAO;

@Database(entities = {Account.class, Disease.class}, version = 1)
public abstract class DatabaseApp extends RoomDatabase {
    public abstract AccountDAO getAccountDAO();

    public abstract DiseaseDAO getDiseaseDAO();
}
