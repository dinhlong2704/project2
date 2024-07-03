package com.example.projectfinal.view.frg;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.projectfinal.R;
import com.example.projectfinal.databinding.M013ChonTinhBinding;
import com.example.projectfinal.model.TinhThanh;
import com.example.projectfinal.view.adapter.TinhThanhAdapter;
import com.example.projectfinal.viewmodel.CommonVM;

import java.util.ArrayList;
import java.util.List;

public class M006ChonTinhFrag extends BaseFragment<M013ChonTinhBinding, CommonVM> {
    public static final String TAG = M006ChonTinhFrag.class.getName();
    private TinhThanhAdapter adapter;
    private final List<TinhThanh> listTinhThanh = new ArrayList<>();

    @Override
    protected void initView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.rcvTinh.setLayoutManager(linearLayoutManager);
        adapter = new TinhThanhAdapter(getContext(), listTinhThanh);


        // Using getContext() instead of context
        binding.rcvTinh.setAdapter(adapter);

        listTinhThanh.add(new TinhThanh("Hà Nội"));
        listTinhThanh.add(new TinhThanh("Hồ Chí Minh"));
        listTinhThanh.add(new TinhThanh("Đà Nẵng"));
        listTinhThanh.add(new TinhThanh("Cần Thơ"));
        listTinhThanh.add(new TinhThanh("Hải Phòng"));
        adapter.setOnItemClickListener(position -> {
            if (position == 0) {
                hospitalHaNoi();
            } else if (position == 1) {
                hospitalHoChiMinh();
            } else if (position == 2) {
                hospitalDaNang();
            } else if (position == 3) {
                hospitalCanTho();
            } else if (position == 4) {
                hospitalHaiPhong();
            } else if (position == 5) {
                Toast.makeText(context, "Clicked item at position: " + position, Toast.LENGTH_SHORT).show();
            }
        });
        // Notify adapter of data changes
        adapter.notifyDataSetChanged();
    }

    private void hospitalHaNoi() {
        HaNoiHospitalFrag fragment = new HaNoiHospitalFrag();
        FragmentTransaction trans = getChildFragmentManager().beginTransaction();
        trans.replace(R.id.ln_frag_chon_tinh, fragment, HaNoiHospitalFrag.TAG);
        trans.addToBackStack(HaNoiHospitalFrag.TAG);
        trans.commit();
    }

    private void hospitalHoChiMinh() {
        HCMHospitalFrag fragment = new HCMHospitalFrag();
        FragmentTransaction trans = getChildFragmentManager().beginTransaction();
        trans.replace(R.id.ln_frag_chon_tinh, fragment, HCMHospitalFrag.TAG).commit();
    }

    private void hospitalDaNang() {
        DaNangHospitalFrag fragment = new DaNangHospitalFrag();
        FragmentTransaction trans = getChildFragmentManager().beginTransaction();
        trans.replace(R.id.ln_frag_chon_tinh, fragment, DaNangHospitalFrag.TAG).commit();
    }

    private void hospitalCanTho() {
        CanThoHospitalFrag fragment = new CanThoHospitalFrag();
        FragmentTransaction trans = getChildFragmentManager().beginTransaction();
        trans.replace(R.id.ln_frag_chon_tinh, fragment, CanThoHospitalFrag.TAG).commit();
    }

    private void hospitalHaiPhong() {
        HaiPhongHospitalFrag fragment = new HaiPhongHospitalFrag();
        FragmentTransaction trans = getChildFragmentManager().beginTransaction();
        trans.replace(R.id.ln_frag_chon_tinh, fragment, HaiPhongHospitalFrag.TAG).commit();
    }

    @Override
    protected Class<CommonVM> getClassViewModel() {
        return CommonVM.class;
    }

    @Override
    protected M013ChonTinhBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return M013ChonTinhBinding.inflate(inflater, container, false);
    }
}
