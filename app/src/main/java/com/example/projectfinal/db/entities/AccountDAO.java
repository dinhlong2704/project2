package com.example.projectfinal.db.entities;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AccountDAO {
    @Query("Select * From Account")
    List<Account> getAccount();

    @Insert
    void insertAccount(Account account);

    @Delete
    void deleteAccount(Account account);
}

