package com.example.projectfinal.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity(primaryKeys = {"IdUser"})
public class Account {
    @NonNull
    @ColumnInfo(name = "IdUser")
    public String idUser;
    @NonNull
    @ColumnInfo(name = "Name")
    public String name;
    @ColumnInfo(name = "Account")
    public String account;
    @NonNull
    @ColumnInfo(name = "Pass")
    public String pass;
    @NonNull
    @ColumnInfo(name = "Phone")
    public String phone;
    @NonNull
    @ColumnInfo(name = "Email")
    public String email;

    @NonNull
    @ColumnInfo(name = "DOB")
    public String birth;


}
