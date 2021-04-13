package com.midTrans.enums;

public enum ScreenConstants {

    HOME_SCREEN_TITLE("HOME SCREEN"),
    PURCHASE_SCREEN_TITLE("PURCHASE");

    private String screenTitle;

    private ScreenConstants(String title){}

    public String getScreenTitle()
    {
        return screenTitle;
    }
}
