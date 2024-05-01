package com.example.projectfinal.view.act;


import android.Manifest;
import android.content.pm.PackageManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.projectfinal.App;
import com.example.projectfinal.db.entities.Disease;
import com.example.projectfinal.view.frg.M000SplashFrag;
import com.example.projectfinal.viewmodel.CommonVM;
import com.example.projectfinal.databinding.ActivityHomeBinding;

import java.util.List;

public class HomeActivity extends BaseAct<ActivityHomeBinding, CommonVM> {
    public static final String TAG = HomeActivity.class.getName();
    private List<Disease> listDisease;

    @Override
    public void backToPrevious() {
        onBackPressed();
    }
    @Override
    public void checkMapPermission() {
        if (checkSelfPermission(Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, 101);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Please acceptthis permission to show Map", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void initView() {
        showFragment(M000SplashFrag.TAG, null, false);
        new Thread(() -> {
            listDisease = App.getInstance().getDb().getDiseaseDAO().getAllDisease();
            runOnUiThread(this::loadDBSUccess);
        }).start();

    }

    private void loadDBSUccess() {
        // Toast.makeText(this,"Size : "+listDisease.size(),Toast.LENGTH_SHORT).show();
        Log.i(TAG, listDisease.toString());
    }

    @Override
    protected ActivityHomeBinding initViewBinding() {
        return ActivityHomeBinding.inflate(getLayoutInflater());
    }

    @Override
    protected Class<CommonVM> initViewModel() {
        return CommonVM.class;
    }
}