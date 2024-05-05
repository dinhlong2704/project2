package com.example.projectfinal.view.frg;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectfinal.App;
import com.example.projectfinal.databinding.M002RegisterBinding;
import com.example.projectfinal.db.entities.Account;
import com.example.projectfinal.viewmodel.CommonVM;

import java.util.Random;

public class M002RegisterFrag extends BaseFragment<M002RegisterBinding, CommonVM> {
    public static final String TAG = M002RegisterFrag.class.getName();

    @Override
    protected void initView() {
        binding.btSignIn.setOnClickListener(v -> gotoLoginScreen());
        binding.btRegister.setOnClickListener(v -> addUser());
    }

    private void addUser() {
        new Thread(() -> {
            Random rd = new Random();
            Account account = new Account();
            account.idUser = "S0" + rd.nextInt(100);
            account.name = binding.txtName.getText().toString();
            account.phoneemail = binding.txtemail.getText().toString();
            account.pass = binding.txtpass.getText().toString();
            account.confirmPass = binding.txtconfirmpass.getText().toString();
            account.birth = binding.txtDob.getText().toString();
            App.getInstance().getDb().getAccountDAO().insertAccount(account);
        }).start();

    }

    private void gotoLoginScreen() {
        callBack.showFragment(M001LoginFrag.TAG, null, true);
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
