package com.dtt.titiappv1.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import com.dtt.titiappv1.Adapters.GioHangAdapter;
import com.dtt.titiappv1.Entities.GioHang;
import com.dtt.titiappv1.Event.ChangeStateGioHang;
import com.dtt.titiappv1.Fragments.DangNhapFrg;
import com.dtt.titiappv1.R;
import com.dtt.titiappv1.Utilities.SupportClassLogic;
import com.dtt.titiappv1.Utilities.SupportClassUI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GioHangAct extends AppCompatActivity implements View.OnClickListener, ChangeStateGioHang {
    private static final String TAG = "GioHangAct";
    RecyclerView rvGioHang;
    GioHangAdapter gioHangAdapter;
    List<GioHang> arrGioHang;
    private TextView tvTongTien;
    private Button btCapNhat, btThanhToan;
    private Toolbar tbarGioHang;
    private TableRow tblDieuKhienGioHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_gio_hang);

        initView();
        initData();
    }

    private void initData() {
        arrGioHang = new ArrayList<>();
        loadArrGioHangFromDevice();
        gioHangAdapter = new GioHangAdapter(this, arrGioHang);
        rvGioHang.setAdapter(gioHangAdapter);

        gioHangAdapter.changeStateGioHang(this);
        setTongTienVaoTextView();
    }

    private void setTongTienVaoTextView() {
        int tongTien = 0;
        for(int i =0; i < arrGioHang.size(); i++){
            tongTien += arrGioHang.get(i).getGiaSP();
        }
        tvTongTien.setText(SupportClassLogic.formatMoney(tongTien));
    }

    private void loadArrGioHangFromDevice() {
        //Convert String from Device to ArrayList
        SharedPreferences shared = getSharedPreferences("cart_file", Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = shared.edit();
        String cartString = shared.getString(ChiTietSanPhamAct.KEY_CART, "");
        //if(!cartString.isEmpty() ){
        if(!cartString.equals("[]")){
            tblDieuKhienGioHang.setVisibility(View.VISIBLE);
//            btCapNhat.setVisibility(View.VISIBLE);
//            btThanhToan.setVisibility(View.VISIBLE);
            //SupportClassUI.showMessShort(this, "Có hàng [" + cartString + "]");
            Log.d(TAG, "-"+cartString+"-");
            try {
                JSONArray cartJSONArr = new JSONArray(cartString);
                JSONObject cartJSONObj;
                GioHang gioHang;
                for(int i =0; i < cartJSONArr.length(); i++){
                    cartJSONObj = cartJSONArr.getJSONObject(i);
                    gioHang = new GioHang();
                    gioHang.setIdSP(cartJSONObj.getInt("idSP"));
                    gioHang.setHinhSP(cartJSONObj.getString("hinhSP"));
                    gioHang.setGiaSP(cartJSONObj.getInt("giaSP"));
                    gioHang.setTenSP(cartJSONObj.getString("tenSP"));
                    gioHang.setSoLuongSP(cartJSONObj.getInt("soLuongSP"));
                    arrGioHang.add(gioHang);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else{
            //Nếu giỏ hàng rỗng!
            SupportClassUI.showMessShort(this, "!rỗng");
            tblDieuKhienGioHang.setVisibility(View.GONE);
//            btCapNhat.setVisibility(View.GONE);
//            btThanhToan.setVisibility(View.GONE);
        }
    }

    private void initView() {
        rvGioHang = findViewById(R.id.rv_gio_hang);
        rvGioHang.setLayoutManager(new LinearLayoutManager(this));
        tvTongTien = findViewById(R.id.tv_tong_tien);
        btThanhToan = findViewById(R.id.bt_thanh_toan);
        btCapNhat = findViewById(R.id.bt_cap_nhat);
        tbarGioHang = findViewById(R.id.tbar_gio_hang);
        tblDieuKhienGioHang = findViewById(R.id.tbl_dieu_khien_gio_hang);
        btThanhToan.setOnClickListener(this);
        btCapNhat.setOnClickListener(this);
        tbarGioHang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void xuLyCapNhatGioHang() {
        //Lưu lại xuống SharedPreferences
        SharedPreferences shared = getSharedPreferences("cart_file",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        Gson gson = new GsonBuilder().create();
        JsonArray arrCart = gson.toJsonTree(arrGioHang).getAsJsonArray();
        String cart = arrCart.toString();
        editor.putString(ChiTietSanPhamAct.KEY_CART, cart);
        if(editor.commit()){
            Log.d("check", "Lưu thành công!");
        } else{
            Log.d("check", "Lưu thất bại!");
        }
    }

    private void xuLyThanhToanGioHang() {
        xuLyCapNhatGioHang();
        //Nếu chưa Đăng nhập thì chuyển đến màn hình Đăng nhập
        SharedPreferences shared = getSharedPreferences("account_file",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        String accString = shared.getString(DangNhapFrg.KEY_ACCOUNT, "");
        if(accString.isEmpty()){
            startActivity(new Intent(this, DangKyDangNhapAct.class));
        } else{
            startActivity(new Intent(this, ThanhToanAct.class));
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.bt_thanh_toan){
            xuLyThanhToanGioHang();
        } else if(v.getId() == R.id.bt_cap_nhat){
            xuLyCapNhatGioHang();
        }
    }

    @Override
    public void changeStateGioHang(String tag, GioHang gioHang, int position) {
        if(tag == GioHangAdapter.KEY_GIO_HANG){
            //Log.d(TAG, "p = " + position + " " + gioHang.toString());
            setTongTienVaoTextView();
        } else if(tag == GioHangAdapter.KEY_XOA_ITEM){
            arrGioHang.remove(position);
            setTongTienVaoTextView();
            gioHangAdapter.notifyDataSetChanged();

            if(arrGioHang.size()==0){
                //tblDieuKhienGioHang.setVisibility(View.GONE);
                //btCapNhat.setVisibility(View.GONE);
                btThanhToan.setVisibility(View.GONE);
            }
        }
    }
}
