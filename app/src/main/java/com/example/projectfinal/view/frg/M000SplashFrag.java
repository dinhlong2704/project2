package com.example.projectfinal.view.frg;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.projectfinal.view.MapManager;
import com.example.projectfinal.viewmodel.CommonVM;
import com.example.samplemvvm.R;
import com.example.samplemvvm.databinding.M000SplashBinding;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

public class M000SplashFrag extends BaseFragment<M000SplashBinding, CommonVM> {
    public static final String TAG = M000SplashFrag.class.getName();
    @Override
    protected void initView() {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//               gotoLoginScreen();
//            }
//        },2000);
        binding.btStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoLoginScreen();
            }
        });
    }

    private void gotoLoginScreen() {


        callBack.showFragment(M004MapHospitalFrag.TAG,null,false);
        //NavHostFragment.findNavController(this).navigate(R.id.loginFragment);

    }
    @Override
    protected Class<CommonVM> getClassViewModel() {
        return CommonVM.class;
    }
    @Override
    protected M000SplashBinding initViewBinding(@NonNull LayoutInflater inflater,
                                                @Nullable ViewGroup container) {
        return M000SplashBinding.inflate(inflater,container,false);
    }
}
