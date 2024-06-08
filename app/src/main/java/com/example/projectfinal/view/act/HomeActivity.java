package com.example.projectfinal.view.act;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.example.projectfinal.App;
import com.example.projectfinal.databinding.ActivityHomeBinding;
import com.example.projectfinal.db.entities.Disease;
import com.example.projectfinal.view.frg.M000SplashFrag;
import com.example.projectfinal.viewmodel.CommonVM;
import com.example.projectfinal.viewmodel.GPTHelper;

import java.util.List;

public class HomeActivity extends BaseAct<ActivityHomeBinding, CommonVM> {
    public static final String TAG = HomeActivity.class.getName();

    private List<Disease> listDisease;


    @Override
    public void backToPrevious() {
        onBackPressed();
    }

    @Override
    public void checkMapPermission() {

    }


//    public void checkMediaPermission() {
//        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) !=
//                PackageManager.PERMISSION_GRANTED)
//            requestPermissions(new String[]{
//                    Manifest.permission.READ_EXTERNAL_STORAGE
//            }, 102);
//    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0) { // Check to avoid ArrayIndexOutOfBoundsException
              if (requestCode == 102) {
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Please allow this permission to use app", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    @Override
    protected void initView() {
        showFragment(M000SplashFrag.TAG, null, false);

        new Thread(() -> {
            listDisease = App.getInstance().getDb().getDiseaseDAO().getAllDisease();
            runOnUiThread(this::loadDBSuccess);
        }).start();

        GPTHelper helper = new ViewModelProvider(this).get(GPTHelper.class);
        helper.getGPTResponse("How are you?", answer -> {
            runOnUiThread(() -> Toast.makeText(HomeActivity.this, answer, Toast.LENGTH_SHORT).show());
            return null;
        });
    }

    private void loadDBSuccess() {
    }

    @Override
    protected ActivityHomeBinding initViewBinding() {
        return ActivityHomeBinding.inflate(getLayoutInflater());
    }

    @Override
    protected Class<CommonVM> initViewModel() {
        return CommonVM.class;
    }
}