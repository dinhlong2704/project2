package com.example.projectfinal.view.act;

import android.content.Intent;
import android.provider.AlarmClock;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.projectfinal.App;
import com.example.projectfinal.Message;
import com.example.projectfinal.Storage;
import com.example.projectfinal.databinding.M011ChatBinding;
import com.example.projectfinal.view.adapter.ChatAdapter;
import com.example.projectfinal.viewmodel.CommonVM;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class RequestActivity extends BaseAct<M011ChatBinding, CommonVM> {
    private ChatAdapter adapter;
    private List<Message> listMsg;
    public static final String TAG = RequestActivity.class.getName();
    public static final String[] ALARM_KEYS = {"ĐẶT BÁO THỨC", "CÀI BÁO THỨC", "CÀI ĐẶT BÁO THỨC", "BÁO THỨC", "ĐÁNH THỨC", "SET UP ALARM", "ALARM"};
    private static final int REQUEST_CODE_SPEECH_INPUT = 1;

    @Override
    public void backToPrevious() {

    }

    @Override
    public void checkMapPermission() {

    }

    @Override
    protected void initView() {
        ImageView mic = binding.ivMicro;
        mic.setOnClickListener(v -> {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text");
            try {
                startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                String txt = Objects.requireNonNull(result).get(0);

                sendToChatAdapter(txt);
                //processRequest(txt);
            }
        }
    }

    private void sendToChatAdapter(String txt) {
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);
        binding.rcvMsg.setLayoutManager(linearLayoutManager);
        adapter = new ChatAdapter(this );
        adapter.setData(listMsg);
        binding.rcvMsg.setAdapter(adapter);
        binding.btSend.setOnClickListener(v -> sendMess());

    }

    private void sendMess() {
        String str = binding.edtMsg.getText().toString();
        listMsg.add(new Message(str));
    }

    private void processRequest(String txt) {
        if (isAlarm(txt)) {
            setupAlarm();
        }
    }

    private boolean isAlarm(String txt) {
        String data = txt.toUpperCase(Locale.ROOT);
        for (String key : ALARM_KEYS) {
            if (data.contains(key.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    private void setupAlarm() {
        Log.i(TAG, "setupAlarm...");
        Intent openClockIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
        openClockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(openClockIntent);
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
