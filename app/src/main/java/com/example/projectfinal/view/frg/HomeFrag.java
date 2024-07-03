package com.example.projectfinal.view.frg;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;

import com.example.projectfinal.databinding.HomeFragmentBinding;
import com.example.projectfinal.dialog.CloseDialog;
import com.example.projectfinal.view.act.medicalrecord.MedicalRecordActivity;
import com.example.projectfinal.view.act.chat.RequestActivity;
import com.example.projectfinal.view.act.hospital.HospitalActivity;
import com.example.projectfinal.view.act.media.MusicActivity;
import com.example.projectfinal.viewmodel.CommonVM;

public class HomeFrag extends BaseFragment<HomeFragmentBinding, CommonVM> {
    public static final String TAG = HomeFrag.class.getName();
    private CloseDialog closeDialog;

    @Override
    protected void initView() {
        binding.imMedicalrecords.setOnClickListener(v -> gotoMedicalRecord());
        binding.imAlarm.setOnClickListener(v -> gotoRequestAct());
        binding.imMenu.setOnClickListener(v -> openMenu());
        binding.imMusic.setOnClickListener(v -> openMusic());
        binding.imMedica.setOnClickListener(v -> gotoHospital());
    }

    private void gotoMedicalRecord() {
        Intent intent = new Intent(getActivity(), MedicalRecordActivity.class);
        intent.putExtra("key", "value");
        startActivity(intent);
    }

    private void gotoHospital() {
        Intent intent = new Intent(getActivity(), HospitalActivity.class);
        intent.putExtra("key", "value");
        // Bắt đầu Activity mới
        startActivity(intent);
    }

    private void openMusic() {
        Intent intent = new Intent(getActivity(), MusicActivity.class);
        intent.putExtra("key", "value");
        // Bắt đầu Activity mới
        startActivity(intent);
    }

    private void openMenu() {
        binding.drawer.openDrawer(GravityCompat.START);
        binding.includeMenu.btLogout.setOnClickListener(v -> closeApp());
    }

    private void closeApp() {
        if (closeDialog == null) {
            closeDialog = new CloseDialog(requireContext());
        }
        closeDialog.setCloseDialog(() -> requireActivity().finish());
        closeDialog.show();
    }

    private void gotoRequestAct() {
        Intent intent = new Intent(getActivity(), RequestActivity.class);
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
        return HomeFragmentBinding.inflate(getLayoutInflater(), container, false);
    }


}
