package com.fb.smartfarm.UtilsTools;

import android.util.Log;

/**
 * Created by echo on 2017/4/26.
 */

public class LogUtil {
    private static final String TAG = "SmartFarm";
    public static void d(String tag,String msg){
        Log.d(TAG,"["+tag+"] : "+msg);
    }
}
