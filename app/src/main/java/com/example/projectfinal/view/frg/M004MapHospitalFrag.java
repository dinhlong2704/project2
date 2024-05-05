package com.example.projectfinal.view.frg;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.example.projectfinal.view.MapManager;
import com.example.projectfinal.viewmodel.CommonVM;
import com.example.projectfinal.R;
import com.example.projectfinal.databinding.M004HospitalMapBinding;
import com.google.android.gms.maps.SupportMapFragment;

import java.lang.reflect.Constructor;

public class M004MapHospitalFrag extends BaseFragment<M004HospitalMapBinding, CommonVM> {
    public static final String TAG = M004MapHospitalFrag.class.getName();
    @Override
    protected void initView() {
        gotoMapHospital();
    }

    private void gotoMapHospital() {
        SupportMapFragment fragment = new SupportMapFragment();
        FragmentTransaction trans = getChildFragmentManager().beginTransaction();
        trans.replace(R.id.ln_frg_map, fragment, "SupportMapFragment").commit();
        fragment.getMapAsync(googleMap -> {
            MapManager.getInstance().initMap(context, googleMap);
        });
    }
    @Override
    protected Class<CommonVM> getClassViewModel() {
        return CommonVM.class;
    }

    @Override
    protected M004HospitalMapBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return M004HospitalMapBinding.inflate(getLayoutInflater());
    }
    public void showFragment(String tag, Object data, boolean isBack) {
        try {
            Class<?> clazz = Class.forName(tag); //Trò vào 1 fragment class
            Constructor<?> cons = clazz.getConstructor();
            BaseFragment<?, ?> frg = (BaseFragment<?, ?>) cons.newInstance(); // Tạo ra 1 thực thể từ tên
            frg.setData(data);
            FragmentTransaction trans = getChildFragmentManager().beginTransaction();
            trans.replace(R.id.ln_frg_map, frg, tag).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
