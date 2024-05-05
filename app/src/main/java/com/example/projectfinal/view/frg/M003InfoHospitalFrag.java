package com.example.projectfinal.view.frg;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectfinal.viewmodel.M003HospitalVM;
import com.example.projectfinal.databinding.M003HospitalInfoBinding;

public class M003InfoHospitalFrag extends BaseFragment<M003HospitalInfoBinding, M003HospitalVM> {
    public static final String TAG = M003InfoHospitalFrag.class.getName();

    @Override
    protected void initView() {
        try {
            viewModel.listHospital(context.getAssets().open("db/hospital.txt"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected Class<M003HospitalVM> getClassViewModel() {
        return M003HospitalVM.class;
    }

    @Override
    protected M003HospitalInfoBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return M003HospitalInfoBinding.inflate(getLayoutInflater());
    }
}
