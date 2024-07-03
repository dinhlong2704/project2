package com.example.projectfinal.view.act.chat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.provider.AlarmClock;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.projectfinal.Message;
import com.example.projectfinal.databinding.M011ChatBinding;
import com.example.projectfinal.view.act.BaseAct;
import com.example.projectfinal.view.adapter.ChatAdapter;
import com.example.projectfinal.viewmodel.CommonVM;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class RequestActivity extends BaseAct<M011ChatBinding, CommonVM> {
    public static final String TAG = RequestActivity.class.getName();
    public static final String[] ALARM_KEYS = {"ĐẶT BÁO THỨC", "CÀI BÁO THỨC", "CÀI ĐẶT BÁO THỨC", "BÁO THỨC", "ĐÁNH THỨC", "SET UP ALARM", "ALARM"};
    public static final String[] CALL_KEYS = {"CALL", "GỌI ĐIỆN","GỌI ĐIỆN THOẠI"};
    private static final int REQUEST_CODE_SPEECH_INPUT = 1;
    private final List<Message> listMsg = new ArrayList<>();
    private ChatAdapter adapter;

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
        initAdapter();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                String txt = Objects.requireNonNull(result).get(0);
                sendToChatAdapter(txt);
            }
        }
    }
    private void initAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rcvMsg.setLayoutManager(linearLayoutManager);
        adapter = new ChatAdapter(this, listMsg);
        binding.rcvMsg.setAdapter(adapter);
    }
    @SuppressLint("NotifyDataSetChanged")
    private void sendToChatAdapter(String txt) {
        listMsg.add(new Message(txt, Message.TYPE_LEFT));
        adapter.notifyDataSetChanged();
        processRequest(txt);
    }


    private void processRequest(String txt) {
        if (isAlarm(txt)) {
            setupAlarm();
        } else if (isCall(txt)) {
            callPhone();
        }
    }

    private void callPhone() {
        listMsg.add(new Message("Wait a moment, call is processing...", Message.TYPE_RIGHT));
        doDelayTask(() -> {
            Intent openPhoneIntent = new Intent(Intent.ACTION_DIAL);
            openPhoneIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(openPhoneIntent);
        });
    }

    private boolean isCall(String txt) {
        String data = txt.toUpperCase(Locale.ROOT);
        for (String key : CALL_KEYS) {
            if (data.contains(key.toUpperCase())) {
                return true;
            }
        }
        return false;
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
        listMsg.add(new Message("Wait a moment, alarm is processing...", Message.TYPE_RIGHT));
        doDelayTask(() -> {
            Intent openClockIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
            openClockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(openClockIntent);
        });
    }

    private void doDelayTask(Runnable runnable) {
        new Handler().postDelayed(runnable, 1000);
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
