package com.example.projectfinal.view;

public interface OnMainCallBack {
    void showFragment(String tag, Object data, boolean isBack);

    void backToPrevious();


    void checkMapPermission();
}
