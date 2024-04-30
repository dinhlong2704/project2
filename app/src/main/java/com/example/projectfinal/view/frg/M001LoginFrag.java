package com.example.projectfinal.view.frg;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectfinal.viewmodel.CommonVM;
import com.example.samplemvvm.databinding.M001LoginBinding;

public class M001LoginFrag extends BaseFragment<M001LoginBinding, CommonVM>{
    public static final String TAG = M001LoginFrag.class.getName();
    @Override
    protected void initView() {
        binding.btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoRegisterScreen();
            }
        });
    }

    private void gotoMainScreen() {

    }

    private void gotoRegisterScreen() {
                callBack.showFragment(M002RegisterFrag.TAG,null,true);
    }

    @Override
    protected Class<CommonVM> getClassViewModel() {
        return CommonVM.class;
    }

    @Override
    protected M001LoginBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return M001LoginBinding.inflate(getLayoutInflater());
    }
}
