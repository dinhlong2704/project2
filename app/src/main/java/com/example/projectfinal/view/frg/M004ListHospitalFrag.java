package com.example.projectfinal.view.frg;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectfinal.databinding.M004ListHostpitalBinding;
import com.example.projectfinal.model.Hospital;
import com.example.projectfinal.view.adapter.HostpitalAdapter;
import com.example.projectfinal.viewmodel.CommonVM;

import java.util.ArrayList;
import java.util.List;

public class M004ListHospitalFrag extends BaseFragment<M004ListHostpitalBinding, CommonVM>{


    public static final String TAG = M004ListHospitalFrag.class.getName();

    @Override
    protected void initView() {
      List<Hospital> listData = (List<Hospital>) data;
        HostpitalAdapter adapter = new HostpitalAdapter((ArrayList<Hospital>) listData, context, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        binding.vpPlace.setAdapter(adapter);
    }

    @Override
    protected Class<CommonVM> getClassViewModel() {
        return CommonVM.class;
    }

    @Override
    protected M004ListHostpitalBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return M004ListHostpitalBinding.inflate(inflater,container,false);
    }
}
