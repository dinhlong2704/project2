package com.example.projectfinal.model;

public class MedicalRecords {
    public int id;
    public String ten;
    public String ngay;
    public byte[] anh;

    public MedicalRecords(int id, String ten, String ngay, byte[] anh) {
        this.id = id;
        this.ten = ten;
        this.ngay = ngay;
        this.anh = anh;
    }
}
