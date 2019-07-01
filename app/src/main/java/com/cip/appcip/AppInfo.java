package com.cip.appcip;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AppInfo extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_info);
        getSupportActionBar().hide();
        if (!getIntent().getBooleanExtra("Muestra", false)) {
            findViewById(R.id.lbl_info_descripcion).setVisibility(View.GONE);
            findViewById(R.id.layout_masinfo).setVisibility(View.GONE);
            final View imageView = (View)findViewById(R.id.imageView).getParent();
            imageView.setAlpha(0);
            imageView.animate().alpha(1).setDuration(2000);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    imageView.animate().translationY(-80).setDuration(5000);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 5000);

                }
            }, 2000);

        }
    }

}
