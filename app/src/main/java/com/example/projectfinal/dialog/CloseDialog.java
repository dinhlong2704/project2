package com.example.projectfinal.dialog;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

import com.example.projectfinal.databinding.ViewCloseDialogBinding;


public class CloseDialog extends Dialog {
    public OnCloseDialog callBack;

    public void setCloseDialog(OnCloseDialog closeDialog) {
        this.callBack = closeDialog;
    }

    private final ViewCloseDialogBinding binding;
    public CloseDialog(@NonNull Context context) {
        super(context);
        binding =ViewCloseDialogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
    binding.btOk.setOnClickListener(v -> closeApp());
    binding.btDont.setOnClickListener(v -> dismiss());
    setCancelable(false);
    }

    private void closeApp() {
        callBack.closeApp();
    }
    public  interface OnCloseDialog{
        void closeApp();
    }
}
