package com.dtt.titiappv1.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.dtt.titiappv1.Adapters.PagerDangNhapAdapter;
import com.dtt.titiappv1.R;
import com.google.android.material.tabs.TabLayout;

public class DangKyDangNhapAct extends AppCompatActivity {
    private ViewPager vpDangNhap;
    private TabLayout tabDangNhap;
    private ImageView ivClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_dang_ky_dang_nhap);
        initView();
        initData();
    }

    private void initData() {
        FragmentManager manager = getSupportFragmentManager();
        PagerDangNhapAdapter adapter = new PagerDangNhapAdapter(manager);
        vpDangNhap.setAdapter(adapter);
        tabDangNhap.setupWithViewPager(vpDangNhap);
        vpDangNhap.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabDangNhap));
        tabDangNhap.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(vpDangNhap));
    }

    private void initView() {
        vpDangNhap = findViewById(R.id.vp_dang_nhap);
        tabDangNhap = findViewById(R.id.tab_dang_nhap);
        ivClose = findViewById(R.id.iv_close);

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
