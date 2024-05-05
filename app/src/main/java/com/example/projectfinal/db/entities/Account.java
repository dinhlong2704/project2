package com.example.projectfinal.db.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Account {

    @ColumnInfo(name = "IdUser")
    @PrimaryKey
    @NonNull
    public String idUser;
    @ColumnInfo(name = "Name")
    public String name;
    @ColumnInfo(name = "Phone or email")
    public String phoneemail;
    @ColumnInfo(name = "Pass")
    public String pass;
    @ColumnInfo(name = "ConfirmPass")
    public String confirmPass;
    @ColumnInfo(name = "DOB")
    public String birth;

}
