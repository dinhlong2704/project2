package com.example.projectfinal.view.act.medicalrecord;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.projectfinal.Database;
import com.example.projectfinal.databinding.ActivityMedicalrecordBinding;
import com.example.projectfinal.model.MedicalRecords;
import com.example.projectfinal.view.act.BaseAct;
import com.example.projectfinal.view.adapter.AdapterMedicalRecord;
import com.example.projectfinal.viewmodel.CommonVM;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecordActivity extends BaseAct<ActivityMedicalrecordBinding, CommonVM> {
    private AdapterMedicalRecord adapter;
    SQLiteDatabase database;
    String DATABASE_NAME = "BenhAn.db";
    private final List<MedicalRecords> listMedicalRecords = new ArrayList<>();
    @Override
    protected void initView() {
        binding.btAdd.setOnClickListener(v -> addMedicalRecord());

        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM benhan", null);
        cursor.moveToFirst();
        adapter = new AdapterMedicalRecord(this, listMedicalRecords);
        binding.rcvMr.setLayoutManager(new LinearLayoutManager(this));
        binding.rcvMr.setAdapter(adapter);
        readData();
    }

    private void addMedicalRecord() {
        Intent intent = new Intent(MedicalRecordActivity.this,AddActivity.class);
        startActivity(intent);
    }

    @SuppressLint("NotifyDataSetChanged")
    protected void readData() {
        database = Database.initDatabase(this, DATABASE_NAME);

        Cursor cursor = database.rawQuery("SELECT * FROM benhan", null);
        listMedicalRecords.clear();
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToPosition(i);
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            String ngay = cursor.getString(2);
            byte[] anh = cursor.getBlob(3);
            listMedicalRecords.add(new MedicalRecords(id, ten, ngay, anh));
        }
        adapter.notifyDataSetChanged();
    }


    @Override
    protected ActivityMedicalrecordBinding initViewBinding() {
        return ActivityMedicalrecordBinding.inflate(getLayoutInflater());
    }

    @Override
    protected Class<CommonVM> initViewModel() {
        return CommonVM.class;
    }

    @Override
    public void backToPrevious() {

    }

    @Override
    public void checkMapPermission() {

    }
}
