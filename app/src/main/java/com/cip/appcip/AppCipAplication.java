package com.cip.appcip;
import android.content.Context;
import android.content.res.Resources;
import android.widget.Toast;

public class AppCipAplication {
    public static final int IDIOMA_ESPAÑOL = 1;
    public static final int IDIOMA_INGLES = 2;
    public static int IDIOMA = 1;
    public static int mwidth = 0;
    public static int mheight = 0;

    public static boolean isEspañol() {
        return IDIOMA == IDIOMA_ESPAÑOL;
    }

    public static void showToash(Context context, String msj) {
        Toast.makeText(context, msj, Toast.LENGTH_LONG).show();
    }

    public static void showToash(Context context, Exception ex) {
        ex.printStackTrace();
        showToash(context, ex.getMessage());
    }

    public static int getScreenWidth() {
        if (mheight == 0) cargarTamaño();
        return mwidth;
    }

    public static int getScreenHeight() {
        if (mheight == 0) cargarTamaño();
        return mheight;
    }

    public static void cargarTamaño() {
        mwidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        mheight = Resources.getSystem().getDisplayMetrics().heightPixels;
    }
}