package com.example.projectfinal.view.frg;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectfinal.viewmodel.CommonVM;
import com.example.samplemvvm.databinding.HomeFragmentBinding;

public class HomeFrag extends  BaseFragment<HomeFragmentBinding, CommonVM>{
    @Override
    protected void initView() {
        callBack.checkMapPermission();
    }

    @Override
    protected Class<CommonVM> getClassViewModel() {
        return CommonVM.class;
    }

    @Override
    protected HomeFragmentBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return HomeFragmentBinding.inflate(getLayoutInflater());
    }
}
