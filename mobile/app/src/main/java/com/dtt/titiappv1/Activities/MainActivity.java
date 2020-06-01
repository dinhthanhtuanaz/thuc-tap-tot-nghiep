package com.dtt.titiappv1.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.dtt.titiappv1.Fragments.CategoryFrg;
import com.dtt.titiappv1.Fragments.HomeFrg;
import com.dtt.titiappv1.Fragments.NotificationFrg;
import com.dtt.titiappv1.Fragments.PersonalFrg;
import com.dtt.titiappv1.Fragments.SearchFrg;
import com.dtt.titiappv1.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    LinearLayout lnTrangChu, lnDanhMuc, lnTimKiem, lnThongBao, lnCaNhan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    private void initData() {
        //Nhận intent từ bên OptionMenu gửi về
        Intent intent = getIntent();
        if(intent != null){
            int key = intent.getIntExtra(SanPhamAct.KEY_OPEN_FRG, 0);
            if(key == 1){
                moveToHomeFrg();
            }
            else if(key == 2){
                moveToCategoryFrg();
            } else if(key == 5){
                moveToPersonalFrg();
            }
        }
    }

    private void initView() {

        attachHomeFrg();
        lnTrangChu = findViewById(R.id.ln_trang_chu);
        lnDanhMuc = findViewById(R.id.ln_danh_muc);
        lnTimKiem = findViewById(R.id.ln_tim_kiem);
        lnThongBao = findViewById(R.id.ln_thong_bao);
        lnCaNhan = findViewById(R.id.ln_ca_nhan);

        lnTrangChu.setOnClickListener(this);
        lnDanhMuc.setOnClickListener(this);
        lnTimKiem.setOnClickListener(this);
        lnThongBao.setOnClickListener(this);
        lnCaNhan.setOnClickListener(this);
    }

    private void attachHomeFrg() {
        replaceFrg(new HomeFrg());
    }

    private void replaceFrg(Fragment frg){
        FragmentManager frgManager = getSupportFragmentManager();
        FragmentTransaction frgTransaction = frgManager.beginTransaction();
        frgTransaction.replace(R.id.fr_home_container, frg);
        frgTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ln_trang_chu:
                moveToHomeFrg();
                break;
            case R.id.ln_danh_muc:
                moveToCategoryFrg();
                break;
            case R.id.ln_tim_kiem:
                moveToSearchFrg();
                break;
            case R.id.ln_thong_bao:
                moveToNotificationFrg();
                break;
            case R.id.ln_ca_nhan:
                moveToPersonalFrg();
                break;
            default:
                break;
        }
    }

    private void moveToPersonalFrg() {
        replaceFrg(new PersonalFrg());
    }

    private void moveToNotificationFrg() {
        replaceFrg(new NotificationFrg());
    }

    private void moveToSearchFrg() {
        replaceFrg(new SearchFrg());
    }

    private void moveToCategoryFrg() {
        replaceFrg(new CategoryFrg());
    }

    private void moveToHomeFrg() {
        replaceFrg(new HomeFrg());
    }
}
/*
* - Mới học về Fragment nên chấp nhận là Click Mục nào thì KHỞI TẠO lại Fragment vậy
* - Tức là dùng replace (ko có cơ chế BackStack) đó chứ ko dùng add.
* => Fixed : Click Mục lại thì nó load lại Fragment chứ ko khởi tạo nữa
* */
