package com.example.projectfinal.view.frg;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.example.projectfinal.view.OnMainCallBack;


public abstract class BaseFragment<B extends ViewBinding, V extends ViewModel>
        extends Fragment implements View.OnClickListener {
    public Object data;
    protected Context context;
    protected B binding;
    protected V viewModel;
    protected OnMainCallBack callBack;

    @Override
    public final void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater,
                                   @Nullable ViewGroup container,
                                   @Nullable Bundle savedInstanceState) {
        binding = initViewBinding(inflater, container);
        viewModel = new ViewModelProvider(this).get(getClassViewModel());
        initView();
        return binding.getRoot();
    }


    public void setCallBack(OnMainCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public final void onClick(View v) {
        v.startAnimation(AnimationUtils.loadAnimation(context, androidx.appcompat.R.anim.abc_fade_in));
        clickView(v);
    }

    protected abstract void initView();

    protected abstract Class<V> getClassViewModel();

    protected abstract B initViewBinding(@NonNull LayoutInflater inflater,
                                         @Nullable ViewGroup container);

    protected void clickView(View v) {

    }

    public void setData(Object data) {
        this.data = data;
    }


}
