package com.example.projectfinal.view.frg;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectfinal.databinding.M000SplashBinding;
import com.example.projectfinal.viewmodel.CommonVM;

public class M000SplashFrag extends BaseFragment<M000SplashBinding, CommonVM> {
    public static final String TAG = M000SplashFrag.class.getName();

    @Override
    protected void initView() {
        binding.btStart.setOnClickListener(v -> gotoLoginScreen());
    }

    private void gotoLoginScreen() {
        callBack.showFragment(M001LoginFrag.TAG, null, false);
    }

    @Override
    protected Class<CommonVM> getClassViewModel() {
        return CommonVM.class;
    }
    @Override
    protected M000SplashBinding initViewBinding(@NonNull LayoutInflater inflater,
                                                @Nullable ViewGroup container) {
        return M000SplashBinding.inflate(inflater, container, false);
    }
}
