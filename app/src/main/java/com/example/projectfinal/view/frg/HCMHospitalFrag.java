package com.example.projectfinal.view.frg;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.ImageViewCompat;
import androidx.fragment.app.FragmentTransaction;

import com.example.projectfinal.R;
import com.example.projectfinal.databinding.HcmNotehospitalFragBinding;
import com.example.projectfinal.view.act.hospital.MapBoxActivity;
import com.example.projectfinal.viewmodel.M003HospitalVM;

import java.lang.reflect.Constructor;

public class HCMHospitalFrag extends BaseFragment<HcmNotehospitalFragBinding, M003HospitalVM> {
    public static final String TAG = HCMHospitalFrag.class.getName();
    private static final int TYPE_LIST = 1;
    private static final int TYPE_MAP = 2;


    @Override
    protected void initView() {
        binding.ivList.setOnClickListener(this);
        binding.ivMap.setOnClickListener(this);
        try {
            viewModel.loadData(context.getAssets().open("hospitalHCM.txt"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        showMenu(TYPE_LIST);
    }

    @Override
    protected void clickView(View v) {
        if(v.getId()== R.id.iv_list){
            showMenu(TYPE_LIST);
        }else if(v.getId()== R.id.iv_map){
            showMenu(TYPE_MAP);
        }
    }

    private void showMenu(int type) {
        ImageViewCompat.setImageTintList(binding.ivList, ColorStateList.valueOf(context.getColor(type == 1 ? R.color.white : R.color.black)));
        ImageViewCompat.setImageTintList(binding.ivMap, ColorStateList.valueOf(context.getColor(type == 2 ? R.color.white : R.color.black)));
        if (type == 1) {
            gotoListHospital();
        } else if (type == 2) {
            gotoMapHospital();
        }
    }

    private void gotoListHospital() {
        showFragment(M004ListHospitalFrag.TAG, viewModel.getListHospital());
    }


    private void gotoMapHospital() {
        Intent intent = new Intent(getActivity(), MapBoxActivity.class);
        intent.putExtra("key", "value");
        startActivity(intent);
    }

    @Override
    protected Class<M003HospitalVM> getClassViewModel() {
        return M003HospitalVM.class;
    }

    @Override
    protected HcmNotehospitalFragBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return HcmNotehospitalFragBinding.inflate(getLayoutInflater());
    }

    public void showFragment(String tag, Object data) {
        try {
            Class<?> clazz = Class.forName(tag);
            Constructor<?> cons = clazz.getConstructor();
            BaseFragment<?, ?> frg = (BaseFragment<?, ?>) cons.newInstance(); // Tạo ra 1 thực thể từ tên
            frg.setData(data);
            FragmentTransaction trans = getChildFragmentManager().beginTransaction();
            trans.replace(R.id.ln_frag_map, frg, tag).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
