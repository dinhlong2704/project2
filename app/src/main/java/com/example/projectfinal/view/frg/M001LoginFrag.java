package com.example.projectfinal.view.frg;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectfinal.App;
import com.example.projectfinal.CommonUtils;
import com.example.projectfinal.databinding.M001LoginBinding;
import com.example.projectfinal.db.entities.Account;
import com.example.projectfinal.viewmodel.CommonVM;

import java.util.List;

public class M001LoginFrag extends BaseFragment<M001LoginBinding, CommonVM> {
    public static final String TAG = M001LoginFrag.class.getName();
    public static final String KEY_ACC = "KEY_ACC";
    private List<Account> listPOE, listPass;
    @Override
    protected void initView() {
        binding.btSignUp.setOnClickListener(v -> gotoRegisterScreen());
        binding.btLogin.setOnClickListener(v -> gotoMainScreen());
        binding.cbRememberMe.setOnClickListener(v -> doSaveAcc());

        String phoneOrEmail = CommonUtils.getInstance().getPref(KEY_ACC);
        if (phoneOrEmail != null) {
            binding.edtPhoneemail.setText(phoneOrEmail);
            binding.cbRememberMe.setChecked(true);
        }
    }
    private void doSaveAcc() {
        if (binding.edtPhoneemail.getText().toString().isEmpty()) {
            Toast.makeText(context, "Please input phone or email first !", Toast.LENGTH_SHORT).show();
            return;
        }
        if (binding.cbRememberMe.isChecked()) {
            CommonUtils.getInstance().savePref(KEY_ACC, binding.edtPhoneemail.getText().toString());
        } else {
            CommonUtils.getInstance().clearPref(KEY_ACC);
        }
    }
    private void gotoMainScreen() {
        new Thread(() -> {
            listPOE = App.getInstance().getDb().getAccountDAO().getPhoneEmail();
            Log.i(TAG, listPOE.toString());
            listPass = App.getInstance().getDb().getAccountDAO().getPass();
            String poe = binding.edtPhoneemail.getText().toString();
            String pass = binding.edtPass.getText().toString();
            for (Account e : listPOE) {
                if (!poe.equals(e.phoneemail)) {
                    continue;
                }
                for (Account p : listPass) {
                    if (pass.equals(p.pass)) {
                        callBack.showFragment(HomeFrag.TAG, null, false);
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
