package com.cip.appcip;

import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

public class Utilidad {
    public static void showToash(Context context, String msj){
        Toast.makeText(context, msj, Toast.LENGTH_LONG).show();
    }
    public static void showToash(Context context, Exception ex){
        ex.printStackTrace();
        showToash(context, ex.getMessage());
    }
    public static int getScreenWidth() {
        if(mheight == 0)cargarTamaño();
        return mwidth;
    }
    public static int getScreenHeight() {
        if(mheight == 0)cargarTamaño();
        return mheight;
    }
    static  int mwidth = 0;
    static  int mheight= 0;
    public static void cargarTamaño() {
        mwidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        mheight = Resources.getSystem().getDisplayMetrics().heightPixels;
    }
}
