package com.example.projectfinal.view.frg;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectfinal.view.act.STTActivity;
import com.example.projectfinal.viewmodel.CommonVM;
import com.example.projectfinal.databinding.HomeFragmentBinding;

public class HomeFrag extends BaseFragment<HomeFragmentBinding, CommonVM> {
    public static final String TAG = HomeFrag.class.getName();
    @Override
    protected void initView() {
        callBack.checkMapPermission();
        binding.imAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoRequestAct();
            }
        });

    }

    private void gotoRequestAct() {
        Intent intent = new Intent(getActivity(), STTActivity.class);
        intent.putExtra("key", "value");

        // Bắt đầu Activity mới
        startActivity(intent);
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
