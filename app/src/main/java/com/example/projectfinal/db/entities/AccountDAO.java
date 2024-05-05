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

    @Query("Select * From Account Where `Phone or email` ")
    List<Account> getPhoneEmail();

    @Query("Select * From Account Where `Pass` ")
    List<Account> getPass();

    @Insert
    void insertAccount(Account account);

    @Delete
    void deleteAccount(Account account);
}

