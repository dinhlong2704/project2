package com.example.projectfinal.view.act.hospital;

import android.Manifest;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.projectfinal.R;
import com.example.projectfinal.databinding.ActivityMapBinding;
import com.example.projectfinal.view.act.BaseAct;
import com.example.projectfinal.view.frg.M006ChonTinhFrag;
import com.example.projectfinal.viewmodel.CommonVM;

public class HospitalActivity extends BaseAct<ActivityMapBinding, CommonVM> {
    private static final String TAG = HospitalActivity.class.getName();

    @Override
    protected void initView() {
        showFragmentMap();
    }

    public void showFragmentMap() {
        M006ChonTinhFrag fragment = new M006ChonTinhFrag();
        fragment.setCallBack(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.ln_main_map, fragment, M006ChonTinhFrag.TAG).commit();
    }

    @Override
    public void backToPrevious() {

    }
    @Override
    public void checkMapPermission() {
        if (checkSelfPermission(Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
            }, 101);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Please accept this permission to show Map", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected ActivityMapBinding initViewBinding() {
        return ActivityMapBinding.inflate(getLayoutInflater());
    }

    @Override
    protected Class<CommonVM> initViewModel() {
        return CommonVM.class;
    }
}
