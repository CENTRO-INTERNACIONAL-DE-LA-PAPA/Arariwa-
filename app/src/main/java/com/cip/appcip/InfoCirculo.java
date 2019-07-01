package com.cip.appcip;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import Clases.AppCipAplication;

public class InfoCirculo {
    private static final  int TIPO_CLIMA = 1;
    private static final  int TIPO_FUNGICIDA = 2;
    public InfoCirculo(Ruleta _context, ImageView _image, final int tipo){
        context =  _context;
        this.imageView = _image;
        tipoCirculo = tipo;
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() != 0) return true;
                if(TIPO_FUNGICIDA == tipoCirculo && context.infoCirculoClima._valor == -1) return true;
                try {
                    v.buildDrawingCache();
                    int touch_x = (int) event.getX();
                    int touch_y = (int) event.getY();
                    int positionClickeado = getTipoClickeado(touch_x, touch_y);
                    if(positionClickeado ==0 && _countClikeado >=0)return true;
                    Bitmap bitmap = Bitmap.createBitmap(v.getDrawingCache());
                    int colSelec = bitmap.getPixel(touch_x, touch_y);
                    if (colSelec == Color.TRANSPARENT) {return false; }
                    img_click(positionClickeado);
                } catch (Exception ex) {
                    Utilidad.showToash(context, ex);
                }
                return true;
            }
        });
    }

    private int tipoCirculo;
    private ImageView imageView;
    private Ruleta context;
    private int _CurrRotation = 0;
    public int _valor = -1;
    private int _countClikeado =-1;
    public void img_click(int positionClickeado) {
        try {
            _countClikeado++;
            if (_countClikeado == 0) {
                if(positionClickeado == 0){
                    _valor = 0;
                    rotar(-60);
                }else if(positionClickeado == 1){
                    rotar(-180);
                    _valor= 1;
                }else if(positionClickeado == 2){
                    rotar(60);
                    _valor= 2;
                }else {
                    _valor++;
                    rotar(-60);
                }
            } else {
                if (positionClickeado == 2) {
                    _valor--;
                    rotar(120);
                } else {
                    rotar(-120);
                    _valor++;
                }
            }
            if (_valor > 2) _valor = 0;
            else if(_valor < 0) _valor = 2;
            context.calcular();
        } catch (Exception ex) {
            Utilidad.showToash(context, ex);
        }
    }
    private int getTipoClickeado(int x , int y){
        int width = imageView.getWidth();
        int tercio = width / 3;
        int medio = width / 2;
        if(_countClikeado >= 0) {
            if(y > tercio && x < medio){
                return 2;
            }
            if(y < tercio)return 0;
            else return  1;
        }else {
            if(x > medio && y < (medio + tercio))return 0;
            else if(x < medio && y < (medio + tercio))return 2;
            else if(y > (medio+tercio))return 1;
        }
        return -1;
    }
    private  void  rotar(int angulo){
        if(angulo ==0)angulo=120;
        _CurrRotation %= 360;
        float fromRotation = _CurrRotation;
        float toRotation = _CurrRotation += angulo;
        final RotateAnimation rotateAnim = new RotateAnimation(fromRotation, toRotation, imageView.getWidth() / 2, imageView.getHeight() / 2);
        rotateAnim.setDuration(1000);
        rotateAnim.setFillAfter(true);
        imageView.startAnimation(rotateAnim);
    }
}