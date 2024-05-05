package com.example.projectfinal.view.frg;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectfinal.App;
import com.example.projectfinal.db.entities.Account;
import com.example.projectfinal.viewmodel.CommonVM;
import com.example.projectfinal.databinding.M001LoginBinding;

import java.util.ArrayList;
import java.util.List;

public class M001LoginFrag extends BaseFragment<M001LoginBinding, CommonVM> {
    public static final String TAG = M001LoginFrag.class.getName();
    private List<Account> listpoe,listpass;
    @Override
    protected void initView() {
        binding.btSignUp.setOnClickListener(v -> gotoRegisterScreen());
        binding.btLogin.setOnClickListener(v -> gotoMainScreen());

    }

    private void gotoMainScreen() {
        new Thread(() -> {
             listpoe =  App.getInstance().getDb().getAccountDAO().getPhoneEmail();
            Log.i(TAG, listpoe.toString());
              listpass =  App.getInstance().getDb().getAccountDAO().getPass();
            String poe = binding.txtphoneemail.getText().toString();
            String pass = binding.txtpass.getText().toString();

            for ( Account e : listpoe  ) {
                if (!poe.equals(e) ){
                    continue;
                }
                for (Account p: listpass) {
                    if (pass.equals(p)){
                        callBack.showFragment(HomeFrag.TAG,null,false);
                    }
                }
            }
        }).start();

    }

    private void gotoRegisterScreen() {
        callBack.showFragment(M002RegisterFrag.TAG, null, true);
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
