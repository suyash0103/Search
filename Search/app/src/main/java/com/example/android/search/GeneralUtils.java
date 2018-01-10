package com.example.android.search;

import android.util.DisplayMetrics;
import android.util.TypedValue;

public class GeneralUtils {

    public static int convertDpToPx(int myPixels){
        DisplayMetrics displaymetrics = new DisplayMetrics();
        int dp = (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, myPixels, displaymetrics );
        return dp;
    }
}
