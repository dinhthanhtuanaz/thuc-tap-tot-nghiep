package com.dtt.titiappv1.Activities;

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
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dtt.titiappv1.Adapters.SanPhamTheoTaiKhoanAdapter;
import com.dtt.titiappv1.DesignPatterns.VolleySingleton;
import com.dtt.titiappv1.Entities.SanPham;
import com.dtt.titiappv1.Fragments.DangNhapFrg;
import com.dtt.titiappv1.Fragments.PersonalFrg;
import com.dtt.titiappv1.R;
import com.dtt.titiappv1.Utilities.Constant;
import com.dtt.titiappv1.Utilities.Server;
import com.dtt.titiappv1.Utilities.SupportClassLogic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SanPhamTheoTaiKhoanAct extends AppCompatActivity {
    private static final String TAG = "SanPhamTheoTaiKhoanAct";
    private TextView tvTenSPTheoTK;
    private Toolbar tbarSPTheoTK;
    private RecyclerView rvSPTheoTK;
    private List<SanPham> arrSanPham;
    private SanPhamTheoTaiKhoanAdapter sanPhamAdapter;

    int idKhachHang = 0; // Tuan2
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_san_pham_theo_tai_khoan);

        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        String key = intent.getStringExtra(PersonalFrg.KEY_KIEU_SP);
        arrSanPham = new ArrayList<>();
        if(key.equals(PersonalFrg.KEY_SP_DA_MUA)){
            tvTenSPTheoTK.setText(R.string.txt_san_pham_da_mua);
            loadAccountFromClient();
            callDataFromServer();
        } else if(key.equals(PersonalFrg.KEY_SP_DA_XEM)){
            tvTenSPTheoTK.setText(R.string.txt_san_pham_da_xem);
            callSPDaXemFromClient();
        } else if(key.equals(Constant.KEY_SP_TIM_KIEM)){
            tvTenSPTheoTK.setText(R.string.txt_ket_qua);
            callSPTimKiemFromServer(intent.getStringExtra(Constant.KEY_WORD));
        }

    }

    private void callSPTimKiemFromServer(String keyWord) {
        keyWord = SupportClassLogic.removeAccentsInString(keyWord.trim());
        final String finalKeyWord = keyWord;
        StringRequest request = new StringRequest(Request.Method.POST, Server.API_GET_SAN_PHAM_TIM_KIEM,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loadSanPhamTimKiemIntoView(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("keyWord", finalKeyWord);
                return params;
            }
        };
        VolleySingleton.getInstance(this).getRequestQueue().add(request);
    }

    private void loadSanPhamTimKiemIntoView(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            SanPham sanPham;
            JSONObject jsonObject;
            for(int i = jsonArray.length()-1; i > -1 ; i--){
                jsonObject = jsonArray.getJSONObject(i);
                sanPham = new SanPham();
                sanPham.setId(jsonObject.getInt("id"));
                sanPham.setTen(jsonObject.getString("ten"));
                sanPham.setHinh(jsonObject.getString("hinh"));
                sanPham.setGia(jsonObject.getInt("gia"));
                arrSanPham.add(sanPham);
            }

            sanPhamAdapter = new SanPhamTheoTaiKhoanAdapter(this, arrSanPham);
            rvSPTheoTK.setAdapter(sanPhamAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void callSPDaXemFromClient() {
        SharedPreferences shared = getSharedPreferences("product_file", Context.MODE_PRIVATE);
        String viewedProducts = shared.getString(ChiTietSanPhamAct.KEY_VIEWED_PRODUCT,"");
        if(!viewedProducts.isEmpty()){
            try {
                JSONArray jsonArray = new JSONArray(viewedProducts);
                SanPham sanPham;
                JSONObject jsonObject;
                for(int i =jsonArray.length()-1; i > -1; i--){
                    jsonObject = jsonArray.getJSONObject(i);
                    sanPham = new SanPham();
                    sanPham.setId(jsonObject.getInt("id"));
                    sanPham.setTen(jsonObject.getString("ten"));
                    sanPham.setHinh(jsonObject.getString("hinh"));
                    sanPham.setGia(jsonObject.getInt("gia"));
                    arrSanPham.add(sanPham);
                }
                sanPhamAdapter = new SanPhamTheoTaiKhoanAdapter(this, arrSanPham);
                rvSPTheoTK.setAdapter(sanPhamAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void callDataFromServer() {
        StringRequest request = new StringRequest(Request.Method.POST, Server.API_GET_SAN_PHAM_DA_MUA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loadSanPhamIntoView(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("idKhachHang", idKhachHang + "");
                return params;
            }
        };
        VolleySingleton.getInstance(this).getRequestQueue().add(request);
    }

    private void loadSanPhamIntoView(String response) {
        Log.d(TAG, "r = " + response);
        try {
            JSONArray jsonArray = new JSONArray(response);
            SanPham sanPham;
            JSONObject jsonObject;
            for(int i = jsonArray.length()-1; i > -1 ; i--){
                jsonObject = jsonArray.getJSONObject(i);
                sanPham = new SanPham();
                sanPham.setId(jsonObject.getInt("id"));
                sanPham.setTen(jsonObject.getString("tenSP"));
                sanPham.setHinh(jsonObject.getString("hinhSP"));
                sanPham.setGia(jsonObject.getInt("giaMotSP"));
                arrSanPham.add(sanPham);
            }

            sanPhamAdapter = new SanPhamTheoTaiKhoanAdapter(this, arrSanPham);
            //Log.d(TAG, "s = " + arrSanPham.size() + " s = " + sanPhamAdapter.getItemCount());
            rvSPTheoTK.setAdapter(sanPhamAdapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void loadAccountFromClient() {
        SharedPreferences shared = getSharedPreferences("account_file",
                Context.MODE_PRIVATE);
        String acc = shared.getString(DangNhapFrg.KEY_ACCOUNT, "");
        try {
            JSONObject jsonObject = new JSONObject(acc);
            idKhachHang = jsonObject.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "idKhachHang = " + idKhachHang);
    }

    private void initView() {
        tvTenSPTheoTK = findViewById(R.id.tv_ten_sp_theo_tk);
        tbarSPTheoTK = findViewById(R.id.tbar_sp_theo_tk);
        tbarSPTheoTK.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        rvSPTheoTK = findViewById(R.id.rv_sp_theo_tk);
        rvSPTheoTK.setLayoutManager(new LinearLayoutManager(this));
    }
}
