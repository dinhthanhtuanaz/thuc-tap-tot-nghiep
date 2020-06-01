package com.dtt.titiappv1.Fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.dtt.titiappv1.DesignPatterns.VolleySingleton;
import com.dtt.titiappv1.R;
import com.dtt.titiappv1.Utilities.Server;
import com.dtt.titiappv1.Utilities.SupportClassLogic;
import com.dtt.titiappv1.Utilities.SupportClassUI;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DangKyFrg extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    private static final String TAG = "DangKyFrg";
    private EditText edtHoTen, edtSoDienThoai, edtEmail, edtMatKhau, edtNgaySinh;
    private TextView tvHienMK;
    private RadioButton rdbNam, rdbNu;
    private CheckBox chbNhanEmail;
    private Button btDangKy;
    private RadioGroup gRdbGioiTinh;

    private boolean showPassword = false;
    private int isMale = 1; // true
    private int isReceiveEmail = 0; // false
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_dang_ky, container, false);

        initView(view);
        initData();
        return view;
    }

    private void initData() {

    }

    private void initView(View view) {
        edtHoTen = view.findViewById(R.id.edt_ho_ten);
        edtSoDienThoai = view.findViewById(R.id.edt_so_dien_thoai);
        edtEmail = view.findViewById(R.id.edt_email);
        edtMatKhau = view.findViewById(R.id.edt_mat_khau);
        edtNgaySinh = view.findViewById(R.id.edt_ngay_sinh);
        tvHienMK = view.findViewById(R.id.tv_hien_mk);
        rdbNam = view.findViewById(R.id.rdb_nam);
        rdbNu = view.findViewById(R.id.rdb_nu);
        gRdbGioiTinh = view.findViewById(R.id.g_rdb_gioi_tinh);
        chbNhanEmail = view.findViewById(R.id.chb_nhan_email);
        btDangKy = view.findViewById(R.id.bt_dang_ky);

        tvHienMK.setOnClickListener(this);
        btDangKy.setOnClickListener(this);
        edtNgaySinh.setOnClickListener(this);
        chbNhanEmail.setOnClickListener(this);
        gRdbGioiTinh.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_hien_mk:
                handleStatePassword();
                break;
            case R.id.bt_dang_ky:
                handleRegister();
                break;
            case R.id.edt_ngay_sinh:
                handlePickDate();
                break;
            case R.id.chb_nhan_email:
                handleReceiveEmail();
            default:
                break;
        }
    }

    private void handleReceiveEmail() {
        //isReceiveEmail = !isReceiveEmail;
        isReceiveEmail = isReceiveEmail == 1 ? 0 : 1;
    }

    private void handlePickDate() {
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog dateDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    calendar.set(year, month, dayOfMonth);
                    edtNgaySinh.setText(SupportClassLogic.convertDateToString(calendar));
                }
            }, year, month, day);
        dateDialog.show();
    }

    private void handleRegister() {
        //Bước 0: Check các trường xem có rỗng không
        if(edtEmail.getText().toString().isEmpty() || edtSoDienThoai.getText().toString().isEmpty()
            || edtMatKhau.getText().toString().isEmpty() || edtHoTen.getText().toString().isEmpty()
            || edtNgaySinh.getText().toString().isEmpty()){
            SupportClassUI.showMessShort(getActivity(),
                    getResources().getString(R.string.txt_nhap_day_du_du_lieu));
            return;
        }

        //Bước 1: Check xem email đăng ký tồn tại chưa?
        checkUserExists();

        //Bước 2: Insert
    }

    private void checkUserExists() {
        StringRequest strRequest = new StringRequest(Request.Method.POST, Server.API_CHECK_KHACH_HANG,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "data = " + response);
                        if(response.equals("1")){
                            SupportClassUI.showMessShort(getContext(), "Tài khoản này đã tồn tại!");
                        } else{
                            registerUser();
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
                        params.put("email", edtEmail.getText().toString());
                        return params;
                    }
                };
        VolleySingleton.getInstance(getContext()).getRequestQueue().add(strRequest);
    }

    private void registerUser() {
        StringRequest strRequest = new StringRequest(Request.Method.POST, Server.API_INSERT_KHACH_HANG,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.d(TAG, "registerUser() " + response);
                        SupportClassUI.showMessLong(getActivity(),
                                getResources().getString(R.string.txt_dang_ky_thanh_cong));
                        //Chuyển qua DangNhapFragMent


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse() " + error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap<>();
                params.put("email", edtEmail.getText().toString());
                params.put("soDienThoai", edtSoDienThoai.getText().toString());
                params.put("matKhau", edtMatKhau.getText().toString());
                params.put("hoTen", edtHoTen.getText().toString());
                params.put("ngaySinh", SupportClassLogic.revertDateString(edtNgaySinh.getText().toString(),
                                                                            "/"));
                //Lấy thời gian hiện tại
                Calendar calendar = Calendar.getInstance();
                params.put("ngayTao", SupportClassLogic.revertDateString(
                        SupportClassLogic.convertDateToString(calendar),
                        "/"));
                params.put("gioiTinh", String.valueOf(isMale));
                params.put("nhanThongBao", String.valueOf(isReceiveEmail));

                return params;
            }
        };
        VolleySingleton.getInstance(getContext()).getRequestQueue().add(strRequest);
        //Chuyển từ DangKyFrg->DangNhapFrg để sau.
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

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(group.getId() == R.id.g_rdb_gioi_tinh){
            isMale = checkedId == R.id.rdb_nam ? 1 : 0;
        }
    }
}
