package com.personal.sven.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.personal.sven.autoplayvideo.MyApplication;

/**
 * Created by Administrator on 2018/4/4 0004.
 */

public class UiUtil {

    public static Context getContext() {
        return MyApplication.instance.getAppContext();
    }

    /**
     * @param auto true:跟随屏幕获取宽高，false:一律按竖屏方式取宽高（宽小于高）
     * @return
     */
    public static int getScreenWidth(boolean auto) {
        WindowManager wm = (WindowManager) UiUtil.getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        if (!auto) {
            return Math.min(outMetrics.widthPixels, outMetrics.heightPixels);
        }
        return outMetrics.widthPixels;
    }


}
