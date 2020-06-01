package com.dtt.titiappv1.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dtt.titiappv1.Adapters.SanPhamAdapter;
import com.dtt.titiappv1.DesignPatterns.VolleySingleton;
import com.dtt.titiappv1.Entities.GioHang;
import com.dtt.titiappv1.Entities.SanPham;
import com.dtt.titiappv1.R;
import com.dtt.titiappv1.Utilities.Server;
import com.dtt.titiappv1.Utilities.SupportClassLogic;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChiTietSanPhamAct extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "ChiTietSanPhamAct";
    public static final String KEY_CART = "KEY_CART";
    public static final String KEY_VIEWED_PRODUCT = "KEY_VIEWED_PRODUCT";
    private int id; //ID sản phẩm đó
    private SanPham sanPham;
    private ImageView ivHinhSP;
    private TextView tvTenSP, tvGiaSP, tvMoTa;
    private Button btMua;
    private TableLayout tblThongTinChiTiet;
    private Toolbar tbarChiTietSP;

    private boolean flag = false; //Cờ cho Giỏ Hàng.
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_chi_tiet_san_pham);

        //loadSharedPreferences();
        initView();
        initData();
    }

    private void loadSharedPreferences() {
        SharedPreferences shared = getSharedPreferences("cart_file", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        String cartString = shared.getString(KEY_CART, "");
        Log.d(TAG, "load giỏ hàng = " + cartString);

    }

    private void initData() {
        id = getIntent().getIntExtra(SanPhamAdapter.KEY_ID_SAN_PHAM, 1);
        callProductFromServer();

    }

    //Dùng dữ liệu này ở phần PersonalFrg => Sản phẩm đã xem => Duyệt ngược lại
    private void saveVaoSanPhamDaXem(JSONObject sanPhamObj) {
        SharedPreferences shared = getSharedPreferences("product_file", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        String viewedProducts = shared.getString(KEY_VIEWED_PRODUCT,"");
        if(viewedProducts.isEmpty()){
            Log.d(TAG, "da xem Rỗng!");
            Log.d(TAG, "SP DA XEM: " + sanPhamObj);
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(sanPhamObj);
            editor.putString(KEY_VIEWED_PRODUCT, jsonArray.toString());
            editor.commit();
            Log.d(TAG, "sss= " + jsonArray.length());
        } else{
            //PUSH THÊM
            try {
                JSONArray jsonArray = new JSONArray(viewedProducts);

                //Phải duyệt để Đè lại sản phẩm Đã xem
                for(int i =0; i < jsonArray.length(); i++){
                    if(sanPhamObj.getInt("id") == jsonArray.getJSONObject(i).getInt("id")){
                        jsonArray.remove(i);
                        break;
                    }
                }
                jsonArray.put(sanPhamObj); // push thêm vào Các sản phẩm đã xem.
                editor.putString(KEY_VIEWED_PRODUCT, jsonArray.toString());
                editor.commit();

                Log.d(TAG, "da xem = " + jsonArray.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


    }

    private void callProductFromServer() {
        StringRequest request = new StringRequest(Request.Method.POST, Server.API_GET_SAN_PHAM_THEO_ID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            xuLyLoadSanPham(jsonObject);
                            saveVaoSanPhamDaXem(jsonObject);
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
                params.put("id", String.valueOf(id));
                return params;
            }
        };
        VolleySingleton.getInstance(this).getRequestQueue().add(request);
    }

    private void xuLyLoadSanPham(JSONObject sanPhamJSON) {
        sanPham = new SanPham();
        try {
            sanPham.setId(sanPhamJSON.getInt("id"));
            sanPham.setTen(sanPhamJSON.getString("ten"));
            sanPham.setGia(sanPhamJSON.getInt("gia"));
            sanPham.setHinh(sanPhamJSON.getString("hinh"));
            sanPham.setMoTa(sanPhamJSON.getString("moTa"));
            sanPham.setThongSoChiTiet(sanPhamJSON.getString("thongSoChiTiet"));
            sanPham.setAlbumHinh(sanPhamJSON.getString("albumHinh"));
            sanPham.setIdDanhMuc(sanPhamJSON.getInt("idDanhMuc"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Đắng lẽ đoạn code dưới này phải tách ra xử lý ở trong initData()
        //Nhưng vì Volley nó là Bất đồng bộ nên tạm thời xử lý ở đây.
        Picasso.get().load(Server.IMAGES_URL + sanPham.getHinh())
                .placeholder(R.drawable.bg_placeholder)
                .into(ivHinhSP);
        tvTenSP.setText(sanPham.getTen());
        tvGiaSP.setText(SupportClassLogic.formatMoney(sanPham.getGia()));
        tvMoTa.setText(sanPham.getMoTa());
        //Change Thông tin chi tiết
        try {
            JSONArray jsonArrTTChiTiet = new JSONArray(sanPham.getThongSoChiTiet());
            JSONObject jsonTTChiTiet;
            for(int i =0; i < jsonArrTTChiTiet.length(); i++){
                jsonTTChiTiet = jsonArrTTChiTiet.getJSONObject(i);
                pushTTChiTietIntoTable(i, jsonTTChiTiet);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void pushTTChiTietIntoTable(int i, JSONObject jsonTTChiTiet) {
        String key = jsonTTChiTiet.keys().next();
        String value = null;
        try {
            value = jsonTTChiTiet.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Log.d(TAG, "json = " + key + "-" + value);
        TableRow row= new TableRow(this);
        if(i % 2 == 0){
            row.setBackgroundResource(R.color.cGray2);
        }
        row.setPadding(20, 20, 20, 20);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT);
        row.setLayoutParams(layoutParams);
        row.setWeightSum(10);
        TextView tvTenTT = new TextView(this);
        TextView tvGiaTriTT = new TextView(this);
        tvTenTT.setText(key);
        //tvTenTT.setBackgroundColor(Color.RED);
        tvTenTT.setTextSize(16);
        tvTenTT.setPadding(10, 10, 10, 10);
        tvGiaTriTT.setText(value);
        //tvGiaTriTT.setBackgroundColor(Color.YELLOW);
        tvGiaTriTT.setTextSize(16);
        tvGiaTriTT.setPadding(10, 10, 10, 10);

        TableRow.LayoutParams params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 3);
        tvTenTT.setLayoutParams(params);
        params = new TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 7);
        tvGiaTriTT.setLayoutParams(params);

        row.addView(tvTenTT);
        row.addView(tvGiaTriTT);

        tblThongTinChiTiet.addView(row,i);
    }

    private void initView() {
        tbarChiTietSP = findViewById(R.id.tbar_chi_tiet_sp);
        ivHinhSP = findViewById(R.id.iv_hinh_sp);
        tvTenSP = findViewById(R.id.tv_ten_sp);
        tvGiaSP = findViewById(R.id.tv_gia_sp);
        tvMoTa = findViewById(R.id.tv_mo_ta);
        btMua = findViewById(R.id.bt_mua);
        tblThongTinChiTiet = findViewById(R.id.tbl_thong_tin_chi_tiet);

        tbarChiTietSP.inflateMenu(R.menu.menu); // khởi tạo
        tbarChiTietSP.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.menu_gio_hang){
                    startActivity(new Intent(ChiTietSanPhamAct.this, GioHangAct.class));
                }
                return false;
            }
        });
        tbarChiTietSP.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tbarChiTietSP.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    //Chưa xử lý được mở CategoryFrg mà chạy thẳng về MainActivity
                    case R.id.menu_gio_hang:
                        startActivity(new Intent(ChiTietSanPhamAct.this, GioHangAct.class));
                        break;
                    case R.id.menu_trang_chu:
                        Intent intent = new Intent(ChiTietSanPhamAct.this, MainActivity.class);
                        intent.putExtra(SanPhamAct.KEY_OPEN_FRG, 1);
                        startActivity(intent);
                        break;
                    case R.id.menu_danh_muc:
                        intent = new Intent(ChiTietSanPhamAct.this, MainActivity.class);
                        intent.putExtra(SanPhamAct.KEY_OPEN_FRG, 2);
                        startActivity(intent);
                        break;
                    case R.id.menu_ca_nhan:
                        intent = new Intent(ChiTietSanPhamAct.this, MainActivity.class);
                        intent.putExtra(SanPhamAct.KEY_OPEN_FRG, 5);
                        startActivity(intent);
                        break;
                    case R.id.menu_yeu_thich:
                        startActivity(new Intent(ChiTietSanPhamAct.this, GioHangAct.class));
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        btMua.setOnClickListener(this);
    }

    private void addProductIntoCart() {
        List<GioHang> arrGioHang = new ArrayList<>();
        //B1: Check giỏ hàng đã
        SharedPreferences shared = getSharedPreferences("cart_file", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        String cartString = shared.getString(KEY_CART, "");
        if(cartString.isEmpty()){
            Log.d(TAG, "Giỏ hàng trống!!!");
            GioHang gioHang = new GioHang(sanPham.getId(), sanPham.getTen(), sanPham.getHinh(),
                    1, sanPham.getGia());
            arrGioHang.add(gioHang);
            //Convert ArrayList to String
            Gson gson = new GsonBuilder().create();
            JsonArray arrCart = gson.toJsonTree(arrGioHang).getAsJsonArray();
            String cart = arrCart.toString();
            editor.putString(KEY_CART, cart);
            if(editor.commit()){
                Log.d(TAG, "Lưu thành công!!!");
            } else{
                Log.d(TAG, "Lưu thất bại!!!");
            }
        } else{
            try {
                JSONArray cartJSONArr = new JSONArray(cartString);
                JSONObject cartJSONObj;
                for(int i = 0; i < cartJSONArr.length(); i++){
                    cartJSONObj = cartJSONArr.getJSONObject(i);
                    if(cartJSONObj.getInt("idSP") == id){
                        flag = true;
                        //Update Sản Phẩm này trong Giỏ Hàng
                        GioHang gioHang = new GioHang();
                        gioHang.setIdSP(id);
                        gioHang.setTenSP(cartJSONObj.getString("tenSP"));
                        gioHang.setHinhSP(cartJSONObj.getString("hinhSP"));
                        gioHang.setGiaSP(cartJSONObj.getInt("giaSP") + sanPham.getGia());
                        gioHang.setSoLuongSP(cartJSONObj.getInt("soLuongSP") + 1);
                        arrGioHang.add(gioHang);
                    } else{
                        //ADD các Sản phẩm đã có trong Giỏ Hàng
                        GioHang gioHang = new GioHang();
                        gioHang.setIdSP(cartJSONObj.getInt("idSP"));
                        gioHang.setTenSP(cartJSONObj.getString("tenSP"));
                        gioHang.setHinhSP(cartJSONObj.getString("hinhSP"));
                        gioHang.setGiaSP(cartJSONObj.getInt("giaSP"));
                        gioHang.setSoLuongSP(cartJSONObj.getInt("soLuongSP"));
                        arrGioHang.add(gioHang);
                    }
                }
                if(!flag){
                    //Sản phẩm đang Mua chính là Sản phẩm Mới, chưa có trong Giỏ hàng
                    GioHang gioHang = new GioHang();
                    gioHang.setIdSP(sanPham.getId());
                    gioHang.setTenSP(sanPham.getTen());
                    gioHang.setHinhSP(sanPham.getHinh());
                    gioHang.setGiaSP(sanPham.getGia());
                    gioHang.setSoLuongSP(1); // Mỗi lần Click mua chỉ Mua được 1 Sản Phẩm
                    arrGioHang.add(gioHang);
                }

                //Lưu xuống SharedPreferences
                Gson gson = new GsonBuilder().create();
                JsonArray arrCart = gson.toJsonTree(arrGioHang).getAsJsonArray();
                String cart = arrCart.toString();
                editor.putString(KEY_CART, cart);
                if(editor.commit()){
                    Log.d("check", "Cập nhật giỏ hàng thành công!");
                } else{
                    Log.d("check", "Cập nhật thất bại!");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        loadSharedPreferences();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.bt_mua){
            addProductIntoCart();
            Intent intent = new Intent(this, GioHangAct.class);
            startActivity(intent);
        }
    }

    /*
    //Khởi tạo Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //Bắt sự kiện Menu

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_gio_hang:
                startActivity(new Intent(this, GioHangAct.class));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    */
}
