package com.dtt.titiappv1.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dtt.titiappv1.DesignPatterns.VolleySingleton;
import com.dtt.titiappv1.Entities.ChiTietDonHang;
import com.dtt.titiappv1.Entities.GioHang;
import com.dtt.titiappv1.Fragments.DangNhapFrg;
import com.dtt.titiappv1.R;
import com.dtt.titiappv1.Utilities.Server;
import com.dtt.titiappv1.Utilities.SupportClassLogic;
import com.dtt.titiappv1.Utilities.SupportClassUI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThanhToanAct extends AppCompatActivity {
    private static final String TAG = "ThanhToanAct";
    private EditText edtSoDienThoai, edtDiaChiGiao, edtGhiChu;
    private Button btXacNhan;
    private Toolbar tbarThanhToan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_thanh_toan);

        initView();
        initData();
    }

    private void initData() {

    }

    private void initView() {
        edtSoDienThoai = findViewById(R.id.edt_so_dien_thoai);
        edtDiaChiGiao = findViewById(R.id.edt_dia_chi_giao);
        edtGhiChu = findViewById(R.id.edt_ghi_chu);
        tbarThanhToan = findViewById(R.id.tbar_thanh_toan);
        btXacNhan = findViewById(R.id.bt_xac_nhan);
        btXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleWhenConfirm(edtSoDienThoai, edtDiaChiGiao, edtGhiChu);
            }
        });
        tbarThanhToan.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void handleWhenConfirm(EditText edtSoDienThoai, EditText edtDiaChiGiao, EditText edtGhiChu) {
        if(edtSoDienThoai.getText().toString().isEmpty()
                || edtDiaChiGiao.getText().toString().isEmpty()){
            SupportClassUI.showMessShort(this, "Nhập đầy đủ số điện thoại & địa chỉ");
        } else{
            //Lấy thông tin Khách hàng
            SharedPreferences shared = getSharedPreferences("account_file", Context.MODE_PRIVATE);
            String accountStr = shared.getString(DangNhapFrg.KEY_ACCOUNT, "");
            int id = 1;
            try {
                JSONObject accountObj = new JSONObject(accountStr);
                id = accountObj.getInt("id");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            final int idKhachHang = id;
            final String soDienThoai = edtSoDienThoai.getText().toString();
            final String diaChiGiao = edtDiaChiGiao.getText().toString();
            final String ngayMua;
            Calendar currentDate = Calendar.getInstance();
            ngayMua = SupportClassLogic.revertDateString(SupportClassLogic.convertDateToString(currentDate),
                    "/");
            final String ghiChu = edtGhiChu.getText().toString();
            //Insert dữ liệu
            StringRequest request = new StringRequest(Request.Method.POST, Server.API_INSERT_DON_HANG,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d(TAG, "res = " + response);
                            SupportClassUI.showMessShort(ThanhToanAct.this, "THanh cong");
                            insertChiTietDonHang(Integer.parseInt(response));
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
                    params.put("soDienThoai", soDienThoai);
                    params.put("diaChiGiao", diaChiGiao);
                    params.put("ngayMua", ngayMua);
                    params.put("ghiChu", ghiChu);

                    return params;
                }
            };
            VolleySingleton.getInstance(this).getRequestQueue().add(request);
            //Trở về trang chủ

        }
    }

    private void insertChiTietDonHang(int idDonHang) {
        //Lấy dữ liệu ở giỏ hàng lên Insert
        List<ChiTietDonHang> arrChiTietDH = new ArrayList<>();
        ChiTietDonHang chiTietDH;
        final SharedPreferences shared = getSharedPreferences("cart_file", Context.MODE_PRIVATE);
        String strChiTietDH = shared.getString(ChiTietSanPhamAct.KEY_CART, "");
        try {
            JSONArray arrChiTietDHJson = new JSONArray(strChiTietDH);
            JSONObject chiTietDHJson;
            for (int i = 0; i < arrChiTietDHJson.length(); i++) {
                chiTietDHJson = arrChiTietDHJson.getJSONObject(i);
                chiTietDH = new ChiTietDonHang();
                chiTietDH.setIdDH(idDonHang);
                chiTietDH.setIdSP(chiTietDHJson.getInt("idSP"));
                chiTietDH.setTenSP(chiTietDHJson.getString("tenSP"));
                chiTietDH.setHinhSP(chiTietDHJson.getString("hinhSP"));
                chiTietDH.setGia(chiTietDHJson.getInt("giaSP"));
                chiTietDH.setSoLuong(chiTietDHJson.getInt("soLuongSP"));
                arrChiTietDH.add(chiTietDH);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Convert ArrayList to String
        Gson gson = new GsonBuilder().create();
        JsonArray jsonArray = gson.toJsonTree(arrChiTietDH).getAsJsonArray();
        //JsonObject jsonObject = gson.toJsonTree(arrChiTietDH).getAsJsonObject();
        final String str = jsonArray.toString();
        StringRequest request = new StringRequest(Request.Method.POST, Server.API_INSERT_CHI_TIET_DON_HANG,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.d(TAG, "res receiver = " + response);
                        if(response.equals("1")){
                            startActivity(new Intent(ThanhToanAct.this, MainActivity.class));
                            //Clear giỏ hàng
                            SharedPreferences.Editor editor = shared.edit();
                            editor.remove(ChiTietSanPhamAct.KEY_CART);
                            editor.commit();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params = new HashMap<>();
                params.put("strChiTietDH", str);
                return params;
            }
        };
        VolleySingleton.getInstance(this).getRequestQueue().add(request);
    }
}
