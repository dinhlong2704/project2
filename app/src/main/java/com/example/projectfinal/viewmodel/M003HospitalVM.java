package com.example.projectfinal.viewmodel;

import android.util.Log;

import com.example.projectfinal.model.Hospital;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class M003HospitalVM extends BaseViewModel {
    private static final String TAG = M003HospitalVM.class.getName();
    private ArrayList<Hospital> listHospital;

    public ArrayList<Hospital> getListHospital() {
        return listHospital;
    }

    public void loadData(InputStream in) throws Exception {

           BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
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
                }
                else if (location == null) {
                    location = str;
                    lat = Double.parseDouble(location.split(",")[0]);
                    lgn = Double.parseDouble(location.split(",")[1]);
                }else if (linkPhoto == null) {
                    linkPhoto = str;
                }
              else if (!str.startsWith("0-0-0-0-0-0")) {
                    desc.append(str).append("\n");
                }  else {
                    listHospital.add(new Hospital(name, linkPhoto, desc.toString(), lat, lgn));
                    name = null;
                    linkPhoto = null;
                    location = null;
                    desc = new StringBuilder();

                }
            }
            br.close();
            in.close();
            Log.i(TAG, " hospital " + listHospital.size());
        }
    }

