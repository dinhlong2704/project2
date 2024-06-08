package com.example.projectfinal.interfacecallback;

import android.widget.SeekBar;

public interface OnSeekBarChange extends SeekBar.OnSeekBarChangeListener {
    @Override
    default void onStartTrackingTouch(SeekBar seekBar){

    };
    @Override
   default void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){

    };
}
