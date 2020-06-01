package com.dtt.titiappv1.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dtt.titiappv1.Adapters.DanhMucAdapter;
import com.dtt.titiappv1.Adapters.SanPhamAdapter;
import com.dtt.titiappv1.DesignPatterns.VolleySingleton;
import com.dtt.titiappv1.Entities.DanhMuc;
import com.dtt.titiappv1.Entities.SanPham;
import com.dtt.titiappv1.Fragments.CategoryFrg;
import com.dtt.titiappv1.R;
import com.dtt.titiappv1.Utilities.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SanPhamAct extends AppCompatActivity {
    public static final String TAG = "SanPhamAct";
    public static final String KEY_OPEN_FRG = "KEY_OPEN_FRG";
    private Toolbar tbarSanPham;
    private ImageView ivDanhMuc;
    private RecyclerView rvTatCaSanPham, rvSanPhamMoi;
    private List<SanPham> arrTatCaSanPham, arrSanPhamMoi;
    private SanPhamAdapter sanPhamAdapter, sanPhamMoiAdapter;
    private int idDanhMuc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_san_pham);

        initView();
        initData();
    }

    private void initData() {
        //Get intent from CategoryFrg
        Intent intent = getIntent();
        if(intent == null){
            idDanhMuc = 1;
        } else{
            idDanhMuc = intent.getIntExtra(DanhMucAdapter.KEY_ID_DANH_MUC, 1);
        }

        arrTatCaSanPham = new ArrayList<>();
        arrSanPhamMoi = new ArrayList<>();
        loadTatCaSanPham();
        loadSanPhamMoi();
        sanPhamAdapter = new SanPhamAdapter(this, arrTatCaSanPham);
//        rvTatCaSanPham.setNestedScrollingEnabled(false);
        rvTatCaSanPham.setAdapter(sanPhamAdapter);

        loadCategoryImage();

        //sanPhamMoiAdapter = new SanPhamAdapter(this, arrSanPhamMoi);
        //rvSanPhamMoi.setAdapter(sanPhamMoiAdapter);
        sanPhamAdapter = new SanPhamAdapter(this, arrSanPhamMoi);
        rvSanPhamMoi.setAdapter(sanPhamAdapter);

    }

    private void loadSanPhamMoi() {
        StringRequest request = new StringRequest(Request.Method.POST,
                Server.API_GET_TAT_CA_SAN_PHAM_MOI_NHAT_THEO_DANH_MUC,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.d(TAG, "spm = " + response);
                        JSONObject objJSON;
                        SanPham sanPham;
                        try {
                            JSONArray arrJSON = new JSONArray(response);
                            for (int i = 0; i < arrJSON.length(); i++) {
                                objJSON = arrJSON.getJSONObject(i);
                                sanPham = new SanPham();
                                sanPham.setId(objJSON.getInt("id"));
                                sanPham.setTen(objJSON.getString("ten"));
                                sanPham.setGia(objJSON.getInt("gia"));
                                sanPham.setHinh(objJSON.getString("hinh"));
                                arrSanPhamMoi.add(sanPham);
                                //sanPhamMoiAdapter.notifyDataSetChanged();
                                sanPhamAdapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("idDanhMuc", String.valueOf(idDanhMuc));
                return params;
            }
        };
        VolleySingleton.getInstance(this).getRequestQueue().add(request);
    }

    private void loadCategoryImage() {
        final StringRequest request = new StringRequest(Request.Method.POST, Server.API_GET_DANH_MUC_THEO_ID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.d(TAG, "res = " + response);
                        try {
                            JSONObject objJSON = new JSONObject(response);
                            if(objJSON != null){
                                //Gán luôn ko cần tạo obj DanhMuc nữa
                                Picasso.get().load(Server.IMAGES_URL + objJSON.getString("hinhNen"))
                                        .placeholder(R.drawable.bg_placeholder).into(ivDanhMuc);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Lỗi ở SanPhamAct : " + error.toString());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("idDanhMuc", String.valueOf(idDanhMuc));
                return params;
            }
        };
        VolleySingleton.getInstance(this).getRequestQueue().add(request);
    }

    private void loadTatCaSanPham() {
        StringRequest request = new StringRequest(Request.Method.POST,
                Server.API_GET_TAT_CA_SAN_PHAM_THEO_DANH_MUC,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.d(TAG, "r = " + response);
                        JSONObject objJSON;
                        SanPham sanPham;
                        try {
                            JSONArray arrJSON = new JSONArray(response);
                            for (int i = 0; i < arrJSON.length(); i++) {
                                objJSON = arrJSON.getJSONObject(i);
                                sanPham = new SanPham();
                                sanPham.setId(objJSON.getInt("id"));
                                sanPham.setTen(objJSON.getString("ten"));
                                sanPham.setGia(objJSON.getInt("gia"));
                                sanPham.setHinh(objJSON.getString("hinh"));
                                sanPham.setThongSoChiTiet(objJSON.getString("thongSoChiTiet"));
                                arrTatCaSanPham.add(sanPham);
                                sanPhamAdapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d(TAG, "SP01: " + arrTatCaSanPham.get(0).getThongSoChiTiet());
                        try {
                            JSONArray chiTiets = new JSONArray(arrTatCaSanPham.get(0).getThongSoChiTiet());
                            JSONObject chiTiet;
                            for (int i = 0; i < chiTiets.length(); i++) {
                                chiTiet = chiTiets.getJSONObject(i);
                                String key = chiTiet.keys().next();
                                //Log.d(TAG, "chiTiet = " + chiTiet.toString());
                                Log.d(TAG, key + " : " + chiTiet.getString(key));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("idDanhMuc", String.valueOf(idDanhMuc));
                return params;
            }
        };
        VolleySingleton.getInstance(this).getRequestQueue().add(request);
    }

    private void initView() {
        tbarSanPham = findViewById(R.id.tbar_san_pham);
        rvTatCaSanPham = findViewById(R.id.rv_tat_ca_san_pham);
        rvTatCaSanPham.setLayoutManager(new GridLayoutManager(this, 2));
        //Bây giờ mới Load hết sản phẩm nhưng không có thanh ScrollBar tác động
        //Chỉ dùng Scrollbar của Màn to nhất
        //=>Sử dụng cặp thẻ NestedScrollView thay cho ScrollView

        ivDanhMuc = findViewById(R.id.iv_danh_muc);

        rvSanPhamMoi = findViewById(R.id.rv_san_pham_moi);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                                        LinearLayoutManager.HORIZONTAL, false);
        rvSanPhamMoi.setLayoutManager(layoutManager);

        tbarSanPham.inflateMenu(R.menu.menu); // khởi tạo
        tbarSanPham.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    //Chưa xử lý được mở CategoryFrg mà chạy thẳng về MainActivity
                    case R.id.menu_gio_hang:
                        startActivity(new Intent(SanPhamAct.this, GioHangAct.class));
                        break;
                    case R.id.menu_trang_chu:
                        Intent intent = new Intent(SanPhamAct.this, MainActivity.class);
                        intent.putExtra(KEY_OPEN_FRG, 1);
                        startActivity(intent);
                        break;
                    case R.id.menu_danh_muc:
                        intent = new Intent(SanPhamAct.this, MainActivity.class);
                        intent.putExtra(KEY_OPEN_FRG, 2);
                        startActivity(intent);
                        break;
                    case R.id.menu_ca_nhan:
                        intent = new Intent(SanPhamAct.this, MainActivity.class);
                        intent.putExtra(KEY_OPEN_FRG, 5);
                        startActivity(intent);
                        break;
                    case R.id.menu_yeu_thich:
                        startActivity(new Intent(SanPhamAct.this, GioHangAct.class));
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        tbarSanPham.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    //Khởi tạo Menu

    //Bắt sự kiện Menu
}
