package com.example.projectfinal.viewmodel;

import android.util.Log;

import com.example.projectfinal.model.Hospital;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class M003HospitalVM extends BaseViewModel {
    private ArrayList<Hospital> listHospital;
    private static final String TAG = M003HospitalVM.class.getName();

    public void listHospital(InputStream in) throws Exception {
        try (InputStreamReader isr = new InputStreamReader(in, StandardCharsets.UTF_8);
             BufferedReader br = new BufferedReader(isr)) {
            double lat = 0;
            double lgn = 0;
            String location = null;
            String str;
            String name = null;
            String linkPhoto = null;
            StringBuilder desc = new StringBuilder();
            listHospital = new ArrayList<>();
            while ((str = br.readLine()) != null) {
                if (str.isEmpty()) continue;
                if (name == null) {
                    name = str;
                } else if (linkPhoto == null) {
                    linkPhoto = str;
                } else if (location == null) {
                    location = str;
                    lat = Double.parseDouble(location.split(",")[0]);
                    lgn = Double.parseDouble(location.split(",")[1]);
                }else if (!str.startsWith("0-0-0-0-0-0")) {
                    desc.append(str).append("\n");
                } else {
                    listHospital.add(new Hospital(name,  linkPhoto,desc.toString(),lat,lgn));
                    name = null;
                    linkPhoto = null;
                    desc = new StringBuilder();
                }
            }

            br.close();
            isr.close();
            in.close();
            Log.i(TAG, " hospital " + listHospital.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
