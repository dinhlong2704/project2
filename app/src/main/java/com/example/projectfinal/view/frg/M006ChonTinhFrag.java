package com.example.projectfinal.view.frg;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfinal.Message;
import com.example.projectfinal.R;
import com.example.projectfinal.databinding.M013ChonTinhBinding;
import com.example.projectfinal.model.TinhThanh;
import com.example.projectfinal.view.adapter.AdapterMedicalRecord;
import com.example.projectfinal.view.adapter.ChatAdapter;
import com.example.projectfinal.view.adapter.TinhThanhAdapter;
import com.example.projectfinal.viewmodel.CommonVM;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class M006ChonTinhFrag extends BaseFragment<M013ChonTinhBinding, CommonVM>{
    public static final String TAG = M006ChonTinhFrag.class.getName();
    private TinhThanhAdapter adapter;
    private RecyclerView recyclerView;

    private final List<TinhThanh> listTinhThanh = new ArrayList<>();
    @Override
    protected void initView() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.rcvTinh.setLayoutManager(linearLayoutManager);
        adapter = new TinhThanhAdapter(getContext(), listTinhThanh, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    M003NoteHospitalFrag fragment = new M003NoteHospitalFrag();
                    FragmentTransaction trans = getChildFragmentManager().beginTransaction();
                    trans.replace(R.id.ln_frag_chon_tinh, fragment, M003NoteHospitalFrag.TAG).commit();

            }
        }); // Using getContext() instead of context
        binding.rcvTinh.setAdapter(adapter);

        listTinhThanh.add(new TinhThanh("Ha noi"));
        listTinhThanh.add(new TinhThanh("Item 2"));
        listTinhThanh.add(new TinhThanh("Item 3"));
        listTinhThanh.add(new TinhThanh("Item 4"));
        listTinhThanh.add(new TinhThanh("Item 5"));

        // Notify adapter of data changes
        adapter.notifyDataSetChanged();

    }

    @Override
    protected Class<CommonVM> getClassViewModel() {
        return CommonVM.class;
    }

    @Override
    protected M013ChonTinhBinding initViewBinding(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return M013ChonTinhBinding.inflate(inflater,container,false);
    }


}
