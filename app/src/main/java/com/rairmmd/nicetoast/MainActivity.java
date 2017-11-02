package com.rairmmd.nicetoast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void show(View view) {
        NiceToast.newNiceToast(this).setText("Nice Toast")
                .alignTop(true)
                .setBgColor(R.color.colorAccent)
                .setIcon(R.mipmap.ic_launcher_round)
                .show();
    }
}
