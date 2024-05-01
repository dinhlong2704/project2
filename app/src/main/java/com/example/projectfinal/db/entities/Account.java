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
    @NonNull
    @ColumnInfo(name = "Phone or email")
    public String phoneemail;
    @NonNull
    @ColumnInfo(name = "Pass")
    public String pass;
    @ColumnInfo(name = "ConfirmPass")
    public String confirmPass;
    @NonNull
    @ColumnInfo(name = "DOB")
    public String birth;


}
