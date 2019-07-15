package com.cip.appcip;

import android.annotation.TargetApi;
import android.graphics.drawable.ColorDrawable;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import Clases.HelperJson;
import Clases.InfoVariedad;

public class Ruleta extends AppCompatActivity {
    private ImageView imageView1, imageView2, imageView3;
    public InfoCirculo infoCirculoClima, infoCirculoFungicida;
    private int TIPO_VARIEDAD;
    private int countRotacionImgResultado = -1;
    private int _CurrRotation = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruleta);
        findViewById(R.id.layout_resultado).setVisibility(View.GONE);
        this.TIPO_VARIEDAD = getIntent().getIntExtra("TIPO_VARIEDAD", InfoVariedad.VARIEDAD_RESISTENTE);
        InfoVariedad.VARIEDAD_RULETA = this.TIPO_VARIEDAD;
        ((TextView) findViewById(R.id.lbl_lblmasinfo)).setText(AppCipAplication.isEspañol() ? "¿Qué aplicar?" : "What apply?");
        try {
            HelperJson.Cargar(this, AppCipAplication.IDIOMA);
            ((TextView) findViewById(R.id.lbl_ayuda)).setText(InfoVariedad.Ayudas.get(0));
            establecerTitulo();
            imageView1 = (ImageView) findViewById(R.id.img1);
            imageView2 = (ImageView) findViewById(R.id.img2);
            imageView3 = (ImageView) findViewById(R.id.img3);
            cargarImagen();
            int width = AppCipAplication.getScreenWidth();
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) imageView1.getLayoutParams();
            params.height = width;
            imageView1.setLayoutParams(params);

            params = (FrameLayout.LayoutParams) imageView2.getLayoutParams();
            params.width = (width / 3) * 2;
            params.height = params.width;
            imageView2.setLayoutParams(params);

            params = (FrameLayout.LayoutParams) imageView3.getLayoutParams();
            params.width = (width / 3);
            params.height = params.width;
            imageView3.setLayoutParams(params);

            infoCirculoClima = new InfoCirculo(this, imageView1, 1);
            infoCirculoFungicida = new InfoCirculo(this, imageView2, 2);
            imageView3.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
        } catch (Exception ex) {
            AppCipAplication.showToash(this, ex);
            finish();
        }
    }

    private void cargarImagen() {
        ((TextView) findViewById(R.id.lbl_recomendacion)).setText(AppCipAplication.isEspañol() ? "Recomendación:" : "Recommendation:");
        switch (this.TIPO_VARIEDAD) {
            case InfoVariedad.VARIEDAD_RESISTENTE:
                imageView1.setImageResource(AppCipAplication.isEspañol() ? R.drawable.verde : R.drawable.green);
                imageView2.setImageResource(AppCipAplication.isEspañol() ? R.drawable.verde2 : R.drawable.green2);
                imageView3.setImageResource(AppCipAplication.isEspañol() ? R.drawable.verde3 : R.drawable.green3);
                break;
            case InfoVariedad.VARIEDAD_INTERMEDIO:
                imageView1.setImageResource(AppCipAplication.isEspañol() ? R.drawable.amarillo : R.drawable.yellow);
                imageView2.setImageResource(AppCipAplication.isEspañol() ? R.drawable.amarillo2 : R.drawable.yellow2);
                imageView3.setImageResource(AppCipAplication.isEspañol() ? R.drawable.amarillo3 : R.drawable.yellow3);
                break;
            case InfoVariedad.VARIEDAD_VULNERABLE:
                imageView1.setImageResource(AppCipAplication.isEspañol() ? R.drawable.rojo : R.drawable.red);
                imageView2.setImageResource(AppCipAplication.isEspañol() ? R.drawable.rojo2 : R.drawable.red2);
                imageView3.setImageResource(AppCipAplication.isEspañol() ? R.drawable.rojo3 : R.drawable.red3);
                break;
        }
    }

    private void establecerTitulo() {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) return;
        actionBar.setCustomView(R.layout.action_bar_title);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);
        TextView lblTile = ((TextView) actionBar.getCustomView().findViewById(R.id.action_bar_title));
        lblTile.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
        lblTile.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        (actionBar.getCustomView().findViewById(R.id.action_bar_subtitle)).setVisibility(View.GONE);
        int colorFondo = 0;
        switch (this.TIPO_VARIEDAD) {
            case InfoVariedad.VARIEDAD_RESISTENTE:
                lblTile.setText(InfoVariedad.VariedadResistente.Titulo);
                colorFondo = getResources().getColor(R.color.colorVerdeFondo);
                break;
            case InfoVariedad.VARIEDAD_INTERMEDIO:
                lblTile.setText(InfoVariedad.VariedadIntermedio.Titulo);
                colorFondo = getResources().getColor(R.color.colorAmarillo);
                lblTile.setTextColor(getResources().getColor(R.color.colorPlomo));
                break;
            case InfoVariedad.VARIEDAD_VULNERABLE:
                lblTile.setText(InfoVariedad.VariedadVulnerable.Titulo);
                colorFondo = getResources().getColor(R.color.colorRojoFondoTitulo);
                break;
        }
        actionBar.setBackgroundDrawable(new ColorDrawable(colorFondo));
        ((TextView) findViewById(R.id.lbl_ayuda)).setTextColor(getResources().getColor(R.color.colorPlomo));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_ayuda) {
            android.support.v7.app.AlertDialog.Builder alert = new android.support.v7.app.AlertDialog.Builder(this);
            View view = View.inflate(this, R.layout.layout_info, null);
            ((TextView) view.findViewById(R.id.lbl_info_paso1)).setText("1. " + InfoVariedad.Ayudas.get(0));
            ((TextView) view.findViewById(R.id.lbl_info_paso2)).setText("2. " + InfoVariedad.Ayudas.get(1));
            ((TextView) view.findViewById(R.id.lbl_info_paso3)).setText("3. " + InfoVariedad.Ayudas.get(2));
            switch (this.TIPO_VARIEDAD) {
                case InfoVariedad.VARIEDAD_RESISTENTE:
                    ((ImageView) view.findViewById(R.id.img_info_img1)).setImageResource(AppCipAplication.isEspañol() ? R.drawable.verde : R.drawable.green);
                    ((ImageView) view.findViewById(R.id.img_info_img2)).setImageResource(AppCipAplication.isEspañol() ? R.drawable.verde2 : R.drawable.green2);
                    ((ImageView) view.findViewById(R.id.img_info_img3)).setImageResource(AppCipAplication.isEspañol() ? R.drawable.verde3 : R.drawable.green3);
                    alert.setTitle(InfoVariedad.VariedadResistente.MetodoEvaluacion);
                    break;
                case InfoVariedad.VARIEDAD_INTERMEDIO:
                    ((ImageView) view.findViewById(R.id.img_info_img1)).setImageResource(AppCipAplication.isEspañol() ? R.drawable.amarillo : R.drawable.yellow);
                    ((ImageView) view.findViewById(R.id.img_info_img2)).setImageResource(AppCipAplication.isEspañol() ? R.drawable.amarillo2 : R.drawable.yellow2);
                    ((ImageView) view.findViewById(R.id.img_info_img3)).setImageResource(AppCipAplication.isEspañol() ? R.drawable.amarillo3 : R.drawable.yellow3);
                    alert.setTitle(InfoVariedad.VariedadIntermedio.MetodoEvaluacion);
                    break;
                case InfoVariedad.VARIEDAD_VULNERABLE:
                    ((ImageView) view.findViewById(R.id.img_info_img1)).setImageResource(AppCipAplication.isEspañol() ? R.drawable.rojo : R.drawable.red);
                    ((ImageView) view.findViewById(R.id.img_info_img2)).setImageResource(AppCipAplication.isEspañol() ? R.drawable.rojo2 : R.drawable.red2);
                    ((ImageView) view.findViewById(R.id.img_info_img3)).setImageResource(AppCipAplication.isEspañol() ? R.drawable.rojo3 : R.drawable.red3);
                    alert.setTitle(InfoVariedad.VariedadVulnerable.MetodoEvaluacion);
                    break;
            }
            alert.setView(view);
            alert.create().show();
        } else
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_ruleta, menu);
        return true;
    }

    public void calcular() throws Exception {
        int valor1 = infoCirculoClima._valor;
        int valor2 = infoCirculoFungicida._valor;
        if (valor1 > -1 && valor2 == -1) {
            TextView lblayuda = findViewById(R.id.lbl_ayuda);
            lblayuda.setVisibility(View.VISIBLE);
            lblayuda.setText((AppCipAplication.isEspañol() ? "Ahora " : "Now ") + InfoVariedad.Ayudas.get(1).toLowerCase());
            lblayuda.setAlpha(0);
            lblayuda.animate().alpha(1).setDuration(1500);
        }
        if (valor1 == -1 || valor2 == -1) return;
        int resultado = InfoVariedad.calcular(this.TIPO_VARIEDAD, valor1, valor2);
        findViewById(R.id.lbl_lblmasinfo).setVisibility(View.GONE);
        if (resultado > 0) findViewById(R.id.lbl_lblmasinfo).setVisibility(View.VISIBLE);
        if (resultado < 0) return;
        int angulo = 0;
        if (resultado == countRotacionImgResultado) return;
        if (countRotacionImgResultado == -1) {
            switch (resultado) {
                case 0:
                    angulo = 0;
                    break;
                case 1:
                    angulo = 120;
                    break;
                case 2:
                    angulo = 240;
                    break;
            }
            angulo += 60;
        } else {
            if (countRotacionImgResultado == 0) {
                angulo = resultado * 120;
            } else if (countRotacionImgResultado == 1) {
                if (resultado == 0) angulo = 240;
                if (resultado == 2) angulo = 120;
            } else if (countRotacionImgResultado == 2) {
                if (resultado == 0) angulo = 120;
                if (resultado == 1) angulo = 240;
            } else throw new Exception("Posicion actual no valido");
            if (angulo == 0) return;
        }
        _CurrRotation %= 360;
        float fromRotation = _CurrRotation;
        float toRotation = _CurrRotation += (-angulo);
        RotateAnimation rotateAnim = new RotateAnimation(fromRotation, toRotation, imageView3.getWidth() / 2, imageView3.getHeight() / 2);
        rotateAnim.setDuration(1000);
        rotateAnim.setFillAfter(true);
        imageView3.startAnimation(rotateAnim);
        countRotacionImgResultado = resultado;
        findViewById(R.id.lbl_ayuda).setVisibility(View.GONE);
        LinearLayout layout_resultado = (LinearLayout) findViewById(R.id.layout_resultado);
        layout_resultado.setVisibility(View.VISIBLE);
        layout_resultado.setAlpha(0);
        layout_resultado.animate().alpha(1).setDuration(1500);
        ((TextView) findViewById(R.id.txt_resultado)).setText(InfoVariedad.FungicidaAplicar.get(resultado));
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void masinfo_click(View xview) {
        String mAplicar = "";
        if (countRotacionImgResultado == 1)
            mAplicar = InfoVariedad.getAplicarRecomendacion(this.TIPO_VARIEDAD);
        if (countRotacionImgResultado == 2)
            mAplicar = InfoVariedad.getAplicarRecomendacion2(this.TIPO_VARIEDAD);
        if (mAplicar.equals("")) return;
        android.support.v7.app.AlertDialog.Builder alert = new android.support.v7.app.AlertDialog.Builder(this);
        View view = View.inflate(this, R.layout.layout_recomendacion, null);

        if (!AppCipAplication.isEspañol())
            ((TextView) view.findViewById(R.id.lbl_epp)).setText("Wear personal protective equipment");
        alert.setTitle(AppCipAplication.isEspañol() ? "Recomendación" : "Recomendation");
        String[] infos = mAplicar.split(",");
        int count = 0;
        for (String item : infos) {
            if (item.trim().equals("")) continue;
            count++;
            TextView textView1 = new TextView(this);
            if (infos.length != count) textView1.setPadding(0, 3, 0, 0);
            textView1.setText("- " + item);
            textView1.setTextAppearance(R.style.TextAppearance_AppCompat_Medium);
            ((LinearLayout) view.findViewById(R.id.view_listaaplicar)).addView(textView1);
        }
        alert.setView(view);
        alert.create().show();
    }
}