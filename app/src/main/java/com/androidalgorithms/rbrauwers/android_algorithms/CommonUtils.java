package com.androidalgorithms.rbrauwers.android_algorithms;

import android.util.Log;

/**
 * Created by rodrigobrauwers on 04/12/17.
 */

public class CommonUtils {

    public static void log(String message) {
        int maxLogSize = 900;
        for (int i = 0; i <= message.length() / maxLogSize; i++) {
            int start = i * maxLogSize;
            int end = (i + 1) * maxLogSize;
            end = end > message.length() ? message.length() : end;
            Log.d("msg", message.substring(start, end));
        }
    }

}
