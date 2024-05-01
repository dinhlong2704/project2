package com.example.projectfinal.view.act;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;



import androidx.annotation.Nullable;

import com.example.projectfinal.databinding.M011ChatBinding;
import com.example.projectfinal.view.frg.M004MapHospitalFrag;
import com.example.projectfinal.viewmodel.CommonVM;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class STTActivity extends BaseAct<M011ChatBinding, CommonVM> {
    public static final String TAG = STTActivity.class.getName();
    private ImageView mic;
    private TextView speech_to_text;
    private static final int REQUEST_CODE_SPEECH_INPUT = 1;
    @Override
    public void backToPrevious() {

    }

    @Override
    public void checkMapPermission() {

    }

    @Override
    protected void initView() {
         mic = binding.ivMicro;
         speech_to_text = binding.tvRequest;

        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent
                        = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                        Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text");

                try {
                    startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(
                        RecognizerIntent.EXTRA_RESULTS);
                speech_to_text.setText(
                        Objects.requireNonNull(result).get(0));
            }
        }
    }

    @Override
    protected M011ChatBinding initViewBinding() {
        return M011ChatBinding.inflate(getLayoutInflater());
    }

    @Override
    protected Class<CommonVM> initViewModel() {
        return CommonVM.class;
    }
}
