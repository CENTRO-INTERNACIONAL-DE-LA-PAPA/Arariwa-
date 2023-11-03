package com.cip.appcip;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import Clases.HelperJson;
import Clases.InfoVariedad;

public class MainActivity extends AppCompatActivity {
    private final int REQUEST_CODE_INFO = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            android.support.v7.app.ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayShowCustomEnabled(true);
                actionBar.setCustomView(R.layout.action_bar_title);
            }
            Intent intent = new Intent(getApplicationContext(), AppInfo.class);
            intent.putExtra("Muestra", false);
            startActivityForResult(intent, REQUEST_CODE_INFO);
        } catch (Exception ex) {
            AppCipAplication.showToash(this, ex);
        }
    }

    private void mostrar() {
        ((TextView) findViewById(R.id.lbl_verde_peru)).setText(InfoVariedad.VariedadResistente.PeruVariedad);
        ((TextView) findViewById(R.id.lbl_verde_subtitulo)).setText(InfoVariedad.VariedadResistente.SubTitulo);
        ((TextView) findViewById(R.id.lbl_verde_titulo)).setText(InfoVariedad.VariedadResistente.Titulo);

        ((TextView) findViewById(R.id.lbl_amarillo_peru)).setText(InfoVariedad.VariedadIntermedio.PeruVariedad);
        ((TextView) findViewById(R.id.lbl_amarillo_subtitulo)).setText(InfoVariedad.VariedadIntermedio.SubTitulo);
        ((TextView) findViewById(R.id.lbl_amarillo_titulo)).setText(InfoVariedad.VariedadIntermedio.Titulo);

        ((TextView) findViewById(R.id.lbl_rojo_peru)).setText(InfoVariedad.VariedadVulnerable.PeruVariedad);
        ((TextView) findViewById(R.id.lbl_rojo_subtitulo)).setText(InfoVariedad.VariedadVulnerable.SubTitulo);
        ((TextView) findViewById(R.id.lbl_rojo_titulo)).setText(InfoVariedad.VariedadVulnerable.Titulo);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        TextView lblTile = actionBar.getCustomView().findViewById(R.id.action_bar_title);
        lblTile.setGravity(Gravity.NO_GRAVITY);
        lblTile.setText(InfoVariedad.Titulo);
        ((TextView) actionBar.getCustomView().findViewById(R.id.action_bar_subtitle)).setText(InfoVariedad.SubTituloMain);

    }

    public void verde_click(View view) {
        AppCipAplication.cargarTamaño();
        Intent intent = new Intent(getApplicationContext(), Ruleta.class);
        intent.putExtra("TIPO_VARIEDAD", InfoVariedad.VARIEDAD_RESISTENTE);
        startActivity(intent);
    }

    public void amarillo_click(View view) {
        AppCipAplication.cargarTamaño();
        Intent intent = new Intent(getApplicationContext(), Ruleta.class);
        intent.putExtra("TIPO_VARIEDAD", InfoVariedad.VARIEDAD_INTERMEDIO);
        startActivity(intent);
    }

    public void rojo_click(View view) {
        AppCipAplication.cargarTamaño();
        Intent intent = new Intent(getApplicationContext(), Ruleta.class);
        intent.putExtra("TIPO_VARIEDAD", InfoVariedad.VARIEDAD_VULNERABLE);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_INFO) {
            try {
                HelperJson.Cargar(this, AppCipAplication.IDIOMA_ESPAÑOL);
                mostrar();
                ImageView layoutmain = (ImageView) findViewById(R.id.imageView);
                layoutmain.animate().translationY(-100).setDuration(20000);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ImageView layoutmain1 = (ImageView) MainActivity.this.findViewById(R.id.imageView);
                        layoutmain1.animate().translationY(70).setDuration(15000);
                    }
                }, 20000);
            } catch (Exception ex) {
                AppCipAplication.showToash(this, ex);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //menu.findItem(R.id.switchidioma).setActionView(R.layout.switch_idioma);
        //final Switch swIdioma = (Switch) menu.findItem(R.id.switchidioma).getActionView().findViewById(R.id.switch1);
        //swIdioma.setTextOff("ES");
        //swIdioma.setTextOn("EN");
        //swIdioma.setText("");
        /*swIdioma.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                try {
                    swIdioma.setText(isChecked ? "EN" : "ES");
                    AppCipAplication.IDIOMA = (isChecked ? AppCipAplication.IDIOMA_INGLES : AppCipAplication.IDIOMA_ESPAÑOL);
                    HelperJson.Cargar(MainActivity.this, AppCipAplication.IDIOMA);
                    mostrar();
                } catch (Exception ex) {
                    AppCipAplication.showToash(MainActivity.this, ex);
                }
            }
        });*/
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_info) {
            Intent intent = new Intent(getApplicationContext(), AppInfo.class);
            intent.putExtra("Muestra", true);
            startActivity(intent);
        }else if(item.getItemId() == R.id.switchidioma){
            AppCipAplication.IDIOMA = (AppCipAplication.IDIOMA == AppCipAplication.IDIOMA_ESPAÑOL ? AppCipAplication.IDIOMA_INGLES : AppCipAplication.IDIOMA_ESPAÑOL);
            try {
                HelperJson.Cargar(MainActivity.this, AppCipAplication.IDIOMA);
                mostrar();
            } catch (Exception ex) {
                AppCipAplication.showToash(MainActivity.this, ex);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}