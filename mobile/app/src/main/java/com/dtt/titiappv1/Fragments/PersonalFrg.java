package com.dtt.titiappv1.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.dtt.titiappv1.Activities.DangKyDangNhapAct;
import com.dtt.titiappv1.Activities.SanPhamTheoTaiKhoanAct;
import com.dtt.titiappv1.Activities.ThanhToanAct;
import com.dtt.titiappv1.Activities.ThongTinTaiKhoanAct;
import com.dtt.titiappv1.R;
import com.dtt.titiappv1.Utilities.SupportClassLogic;
import com.dtt.titiappv1.Utilities.SupportClassUI;

import org.json.JSONException;
import org.json.JSONObject;

public class PersonalFrg extends Fragment implements View.OnClickListener {
    //private static final String KEY_ACCOUNT = "KEY_ACCOUNT";
    public static final String TAG = "PersonalFrg";
    public static final String KEY_SP_DA_MUA = "KEY_SP_DA_MUA";
    public static final String KEY_SP_DA_XEM = "KEY_SP_DA_XEM";
    public static final String KEY_KIEU_SP = "KEY_KIEU_SP";
    LinearLayout lnThongTin1, lnThongTin2;
    Button btDangXuat;
    TextView tvTenUser, tvEmailUser, tvNgayThamGia;
    Toolbar tbarCaNhan;

    RelativeLayout rtSanPhamDaMua, rtSanPhamDaXem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView");
        View view = inflater.inflate(R.layout.frg_personal, container, false);
        initView(view);
        initData();
        return view;
    }

    //Finish ở Activity khác rồi sẽ quay về onStart
    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
        initData();
    }

    private void checkAccountAlreadyExists() {
        SharedPreferences shared = this.getActivity().getSharedPreferences("account_file",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        String accString = shared.getString(DangNhapFrg.KEY_ACCOUNT, "");
        if(accString.isEmpty()){
            //Chưa đăng nhập
            lnThongTin1.setVisibility(View.VISIBLE);
            lnThongTin2.setVisibility(View.GONE);
            btDangXuat.setVisibility(View.GONE);
        } else{
            //Đã đăng nhập rồi thì load thông tin vào
            lnThongTin2.setVisibility(View.VISIBLE);
            lnThongTin1.setVisibility(View.GONE);
            btDangXuat.setVisibility(View.VISIBLE);
            shared = getActivity().getSharedPreferences("account_file",
                    Context.MODE_PRIVATE);
            //editor = shared.edit();
            String accountStr = shared.getString(DangNhapFrg.KEY_ACCOUNT, "");
            try {
                JSONObject accountObj = new JSONObject(accountStr);
                tvTenUser.setText(accountObj.getString("hoTen"));
                tvEmailUser.setText(accountObj.getString("email"));
                String ngayTG = SupportClassLogic.revertDateString(accountObj.getString("ngayTao"), "-");
                tvNgayThamGia.setText(ngayTG);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void initData() {
        checkAccountAlreadyExists();
    }

    private void initView(View view) {
        tvTenUser = view.findViewById(R.id.tv_ten_user);
        tvEmailUser = view.findViewById(R.id.tv_email_user);
        tvNgayThamGia = view.findViewById(R.id.tv_ngay_tham_gia);

        tbarCaNhan = view.findViewById(R.id.tbar_ca_nhan);
        lnThongTin1 = view.findViewById(R.id.ln_thong_tin_1);
        lnThongTin2 = view.findViewById(R.id.ln_thong_tin_2);
        btDangXuat = view.findViewById(R.id.bt_dang_xuat);
        lnThongTin1.setOnClickListener(this);
        lnThongTin2.setOnClickListener(this);
        btDangXuat.setOnClickListener(this);

        tbarCaNhan.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        rtSanPhamDaMua = view.findViewById(R.id.rt_san_pham_da_mua);
        rtSanPhamDaMua.setOnClickListener(this);
        rtSanPhamDaXem = view.findViewById(R.id.rt_san_pham_da_xem);
        rtSanPhamDaXem.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ln_thong_tin_1:
                startActivity(new Intent(this.getActivity(), DangKyDangNhapAct.class));
                break;
            case R.id.ln_thong_tin_2:
                SupportClassUI.showMessShort(this.getActivity(), "Đến phần thông tin cá nhân");
                startActivity(new Intent(this.getActivity(), ThongTinTaiKhoanAct.class));
                break;
            case R.id.bt_dang_xuat:
                //Remove Account
                SharedPreferences shared = getActivity().getSharedPreferences("account_file",
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = shared.edit();
                editor.remove(DangNhapFrg.KEY_ACCOUNT);
                editor.commit();
                getFragmentManager().beginTransaction().detach(this).attach(this).commit(); //reload?
                break;

            case R.id.rt_san_pham_da_mua:
                Intent intent = new Intent(getActivity(), SanPhamTheoTaiKhoanAct.class);
                shared = getActivity().getSharedPreferences("account_file",
                        Context.MODE_PRIVATE);
                //editor = shared.edit();
                String accString = shared.getString(DangNhapFrg.KEY_ACCOUNT, "");
                if(accString.isEmpty()){
                    startActivity(new Intent(getActivity(), DangKyDangNhapAct.class));
                } else{
                    intent.putExtra(KEY_KIEU_SP, KEY_SP_DA_MUA);
                    startActivity(intent);
                }
                break;
            case R.id.rt_san_pham_da_xem:
                intent = new Intent(getActivity(), SanPhamTheoTaiKhoanAct.class);
                intent.putExtra(KEY_KIEU_SP, KEY_SP_DA_XEM);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
