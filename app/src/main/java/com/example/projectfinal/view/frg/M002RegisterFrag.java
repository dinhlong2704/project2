package com.example.projectfinal.view.frg;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectfinal.viewmodel.CommonVM;
import com.example.samplemvvm.databinding.M002RegisterBinding;

public class M002RegisterFrag extends BaseFragment<M002RegisterBinding, CommonVM>{
    public static final String TAG = M002RegisterFrag.class.getName();
    @Override
    protected void initView() {

    }

    @Override
    protected Class<CommonVM> getClassViewModel() {
        return CommonVM.class;
    }

    @Override
    protected M002RegisterBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return M002RegisterBinding.inflate(getLayoutInflater());
    }
}
