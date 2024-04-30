package com.example.projectfinal.view.act;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;


import com.example.projectfinal.view.OnMainCallBack;
import com.example.projectfinal.view.frg.BaseFragment;
import com.example.samplemvvm.R;

import java.lang.reflect.Constructor;

public abstract class BaseAct<T extends ViewBinding, M extends ViewModel>
        extends AppCompatActivity implements View.OnClickListener, OnMainCallBack {
    protected T binding;
    protected M viewModel;
    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = initViewBinding();
        viewModel = new ViewModelProvider(this).get(initViewModel());
        setContentView(binding.getRoot());
        initView();
    }

    protected abstract void initView();
    protected abstract T initViewBinding();
    protected abstract Class<M> initViewModel();


    @Override
    public final void onClick(View v) {
        v.startAnimation(AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_fade_in));
        clickView(v);
    }
    protected void clickView(View v) {
        //
    }
    @Override
    public void showFragment(String tag, Object data, boolean isBack) {
        try {
            Class<?> clazz = Class.forName(tag); //Trò vào 1 fragment class
            Constructor<?> cons = clazz.getConstructor();
            BaseFragment<?, ?> frg = (BaseFragment<?, ?>) cons.newInstance(); // Tạo ra 1 thực thể từ tên
            frg.setData(data);
            frg.setCallBack(this);
            FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
            if (isBack) {
                trans.addToBackStack(null);//go back to previous screen
            }
            trans.replace(R.id.lnmain, frg, tag).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



