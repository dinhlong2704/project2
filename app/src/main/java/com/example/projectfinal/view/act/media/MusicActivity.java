package com.example.projectfinal.view.act.media;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.projectfinal.R;
import com.example.projectfinal.databinding.M0005MusicBinding;
import com.example.projectfinal.interfacecallback.OnSeekBarChange;
import com.example.projectfinal.model.Song;
import com.example.projectfinal.service.MediaService;
import com.example.projectfinal.view.act.BaseAct;
import com.example.projectfinal.view.adapter.SongAdapter;
import com.example.projectfinal.viewmodel.CommonVM;

public class MusicActivity extends BaseAct<M0005MusicBinding, CommonVM> {
    private static final int LEVEL_PLAY = 1;
    private static final int LEVEL_IDLE = 0;
    private boolean appRunning = false;
    @Override
    protected void initView() {
        stopService(new Intent(this, MediaService.class));
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            }, 102);
        } else {
            Mp3Player.getInstance().loadOffline();
            Mp3Player.getInstance().setCompleteCallBackCallBack(mp -> updateUI());
            initListSong();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 102) {
            boolean allPermissionsGranted = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allPermissionsGranted = false;
                    break;
                }
            }
            if (allPermissionsGranted) {
                Mp3Player.getInstance().loadOffline();
                initListSong();
            } else {
                Toast.makeText(this, "Please allow these permissions to use app", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initListSong() {
        binding.include.imPlay.setOnClickListener(this);
        binding.include.imNext.setOnClickListener(this);
        binding.include.imBack.setOnClickListener(this);
        binding.include.sbTime.setOnSeekBarChangeListener((OnSeekBarChange) seekBar -> {
            Mp3Player.getInstance().seekTo(seekBar.getProgress());
        });
        binding.rvSong.setLayoutManager(new LinearLayoutManager(this));
        binding.rvSong.setAdapter((new SongAdapter(this, Mp3Player.getInstance().getListSong(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(AnimationUtils.loadAnimation(MusicActivity.this, androidx.appcompat.R.anim.abc_fade_in));
                MusicActivity.this.doClickItemSong((Song) v.getTag());
            }
        })));
        appRunning = true;
        new Thread(() -> updateSeekBar()).start();
        updateUI();
    }

    private void updateSeekBar() {
        while (appRunning) {
            try {
                Thread.sleep(500);
                runOnUiThread(() -> {
                    String currentTimeText = Mp3Player.getInstance().getCurrentTimeText();
                    String totalTimeText = Mp3Player.getInstance().getTotalTimeText();
                    int currentTime = Mp3Player.getInstance().getCurrentTime();
                    int totalTime = Mp3Player.getInstance().getTotalTime();
                    binding.include.sbTime.setMax(totalTime);
                    binding.include.sbTime.setProgress(currentTime);
                    binding.include.tvDuration.setText(String.format("%s/%s", currentTimeText, totalTimeText));
                });
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void onDestroy() {
        appRunning = false;
        super.onDestroy();
        startService(new Intent(this, MediaService.class));
    }

    @Override
    protected void clickView(View v) {
        if (v.getId() == R.id.im_play) {
            Mp3Player.getInstance().play();
        } else if (v.getId() == R.id.im_back) {
            Mp3Player.getInstance().back();
        } else if (v.getId() == R.id.im_next) {
            Mp3Player.getInstance().next();
        }
        updateUI();
    }

    private void updateUI() {
        if (Mp3Player.getInstance().getState() == Mp3Player.STATE_PLAYING) {
            binding.include.imPlay.setImageLevel(LEVEL_PLAY);
        } else {
            binding.include.imPlay.setImageLevel(LEVEL_IDLE);
        }
        Song song = Mp3Player.getInstance().getCurrentSong();
        binding.include.tvNameSong.setText(song.title);
        binding.include.tvAlbum.setText(song.album);
        ((SongAdapter) binding.rvSong.getAdapter()).updateUI(Mp3Player.getInstance().getCurrentIndex());
    }

    private void doClickItemSong(Song song) {
        Mp3Player.getInstance().playSong(song);
        updateUI();

    }

    @Override
    public void backToPrevious() {

    }

    @Override
    public void checkMapPermission() {

    }

    @Override
    protected M0005MusicBinding initViewBinding() {
        return M0005MusicBinding.inflate(getLayoutInflater());
    }

    @Override
    protected Class<CommonVM> initViewModel() {
        return CommonVM.class;
    }
}
