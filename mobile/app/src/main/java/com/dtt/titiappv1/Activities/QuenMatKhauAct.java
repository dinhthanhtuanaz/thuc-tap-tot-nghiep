package com.dtt.titiappv1.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

import com.dtt.titiappv1.R;

public class QuenMatKhauAct extends AppCompatActivity {
    Toolbar tbarQuenMK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_quen_mat_khau);

        initView();
        initData();
    }

    private void initData() {

    }

    private void initView() {
        tbarQuenMK = findViewById(R.id.tbar_quen_mk);
        setSupportActionBar(tbarQuenMK);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tbarQuenMK.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
