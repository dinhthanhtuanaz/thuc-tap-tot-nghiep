package com.dtt.titiappv1.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dtt.titiappv1.Activities.DangKyDangNhapAct;
import com.dtt.titiappv1.Activities.QuenMatKhauAct;
import com.dtt.titiappv1.DesignPatterns.VolleySingleton;
import com.dtt.titiappv1.R;
import com.dtt.titiappv1.Utilities.Server;
import com.dtt.titiappv1.Utilities.SupportClassUI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DangNhapFrg extends Fragment implements View.OnClickListener {
    private static final String TAG =  "DangNhapFrg";
    public static final String KEY_ACCOUNT = "KEY_ACCOUNT";
    private EditText edtTaiKhoan, edtMatKhau;
    private TextView tvQuenMK, tvHienMK;
    private Button btDangNhap;

    private boolean showPassword = false;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_dang_nhap, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {

    }

    private void initView(View view) {
        btDangNhap = view.findViewById(R.id.bt_dang_nhap);
        edtTaiKhoan = view.findViewById(R.id.edt_tai_khoan);
        edtMatKhau = view.findViewById(R.id.edt_mat_khau);
        tvQuenMK = view.findViewById(R.id.tv_quen_mk);
        tvHienMK = view.findViewById(R.id.tv_hien_mk);

        btDangNhap.setOnClickListener(this);
        tvQuenMK.setOnClickListener(this);
        tvHienMK.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_hien_mk:
                handleStatePassword();
                break;
            case R.id.tv_quen_mk:
                handleForgotPasword();
            case R.id.bt_dang_nhap:
                handleLogin();
            default:
                break;
        }
    }

    private void handleLogin() {
        //JsonObjectRequest sẽ bỏ qua getParams => vẫn dùng StringRequest
        final String taiKhoan = edtTaiKhoan.getText().toString().trim();
        final String matKhau = edtMatKhau.getText().toString().trim();
        if(taiKhoan.isEmpty() || taiKhoan.isEmpty()){
            SupportClassUI.showMessShort(getContext(), String.valueOf(R.string.txt_nhap_day_du_tt));
            return;
        }

        //Check dữ liệu trên Server
        StringRequest strRequest = new StringRequest(Request.Method.POST, Server.API_GET_KHACH_HANG,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //Convert String to JSON
                            JSONObject jsonObj= new JSONObject(response);
                            if(jsonObj.length() != 0){
                                SupportClassUI.showMessShort(getContext(), "Đăng nhập thành công!");
                                SharedPreferences shared = getActivity().getSharedPreferences("account_file",
                                        Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = shared.edit();
                                Log.d(TAG, "account " + response);
                                editor.putString(KEY_ACCOUNT, response);
                                editor.commit();
                                getActivity().finish(); // Back
                            } else{
                                SupportClassUI.showMessShort(getContext(), "Tài khoản không đúng");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "DangNhapFrg : "
                                + error.toString());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("taiKhoan", taiKhoan);
                params.put("matKhau", matKhau);
                return params;
            }
        };

        VolleySingleton.getInstance(getContext()).getRequestQueue().add(strRequest);
    }

    private void handleForgotPasword() {
        //Chuyển đến Activity lấy Mật khẩu
        Intent intent = new Intent(getContext(), QuenMatKhauAct.class);
        startActivity(intent);
    }

    private void handleStatePassword() {
        //Có thể xử lý State Drawable để làm. ( Fixed sau )
        if(!showPassword){
            //Cho hiện password
            edtMatKhau.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            tvHienMK.setText(R.string.txt_an);
        } else{
            edtMatKhau.setTransformationMethod(PasswordTransformationMethod.getInstance());
            tvHienMK.setText(R.string.txt_hien);
        }
        showPassword = !showPassword;
    }
}
