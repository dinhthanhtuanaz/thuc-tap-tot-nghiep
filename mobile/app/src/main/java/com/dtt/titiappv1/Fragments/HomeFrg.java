package com.dtt.titiappv1.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.dtt.titiappv1.Activities.ChiTietSanPhamAct;
import com.dtt.titiappv1.Activities.GioHangAct;
import com.dtt.titiappv1.Adapters.BlockDanhMucAdapter;
import com.dtt.titiappv1.Adapters.DanhMucAdapter;
import com.dtt.titiappv1.DesignPatterns.VolleySingleton;
import com.dtt.titiappv1.Entities.DanhMuc;
import com.dtt.titiappv1.Entities.SanPham;
import com.dtt.titiappv1.R;
import com.dtt.titiappv1.Utilities.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFrg extends Fragment {
    public final String TAG = "HomeFrg";
    private List<DanhMuc> arrDanhMuc, arrDanhMuc2;
    private BlockDanhMucAdapter blockDanhMucAdapter;
    private DanhMucAdapter danhMucAdapter;
    private RecyclerView rvCategoryBlock, rvDanhMuc;

    private Toolbar tbarTrangChu;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_home, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        arrDanhMuc = new ArrayList<>();
        arrDanhMuc2 = new ArrayList<>();
        callDataFromServer();
        //Log.d(TAG, "arrDanhMuc = " + arrDanhMuc.size()); // sẽ là 0 vì chạy kiểu bất đồng bộ(do Volley)
        callDanhMucFromServer();
    }

    private void callDanhMucFromServer() {
        arrDanhMuc2 = new ArrayList<>();
        JsonArrayRequest request = new JsonArrayRequest(Server.API_GET_DANH_MUC,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Log.d(TAG, "r = " + response.toString());
                        loadDanhMucIntoView(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Lỗi ở HomeFrg" + error.toString());
            }
        });
        VolleySingleton.getInstance(getActivity()).getRequestQueue().add(request);
    }

    private void loadDanhMucIntoView(JSONArray response) {
        JSONObject obj;
        DanhMuc danhMuc;
        for(int i =0; i < response.length(); i++){
            try {
                obj = response.getJSONObject(i);
                danhMuc = new DanhMuc(
                        obj.getInt("id"),
                        obj.getString("tenDanhMuc"),
                        obj.getString("hinhNen"),
                        obj.getString("moTa")
                );
                arrDanhMuc2.add(danhMuc);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        danhMucAdapter = new DanhMucAdapter(getActivity(), arrDanhMuc2);
        rvDanhMuc.setAdapter(danhMucAdapter);
    }

    private void callDataFromServer() {
        //Log.d(TAG, "r = " + "HIHI");
        JsonArrayRequest request = new JsonArrayRequest(Server.API_GET_DANH_MUC_VA_SAN_PHAM,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        loadDataIntoView(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Lỗi ở HomeFrg " + error.toString());
            }
        });
        VolleySingleton.getInstance(getActivity()).getRequestQueue().add(request);
    }

    private void loadDataIntoView(JSONArray response) {
        JSONObject danhMucObj;
        DanhMuc danhMuc;
        JSONObject sanPhamObj;
        JSONArray arrSanPhamObj;
        List<SanPham> arrSanPham;
        SanPham sanPham;
        for (int i = 0; i < response.length(); i++) {
            try {
                danhMucObj = response.getJSONObject(i);

                arrSanPhamObj = danhMucObj.getJSONArray("arrSanPham");
                arrSanPham = new ArrayList<>();
                for (int j = 0; j < arrSanPhamObj.length(); j++) {
                    sanPhamObj = arrSanPhamObj.getJSONObject(j);
                    sanPham = new SanPham();
                    sanPham.setId(sanPhamObj.getInt("id"));
                    sanPham.setTen(sanPhamObj.getString("ten"));
                    sanPham.setGia(sanPhamObj.getInt("gia"));
                    sanPham.setHinh(sanPhamObj.getString("hinh"));
                    arrSanPham.add(sanPham);
                }
                //ADD into DanhMuc
                danhMuc = new DanhMuc();
                danhMuc.setId(danhMucObj.getInt("id"));
                danhMuc.setTenDanhMuc(danhMucObj.getString("tenDanhMuc"));
                danhMuc.setHinhNen(danhMucObj.getString("hinhNen"));
                danhMuc.setArrSanPham(arrSanPham);

                arrDanhMuc.add(danhMuc);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Đã có dữ liệu => Load vào RecycleView
        blockDanhMucAdapter = new BlockDanhMucAdapter(getActivity(), arrDanhMuc);
        rvCategoryBlock.setAdapter(blockDanhMucAdapter);
    }

    private void initView(View view) {
        tbarTrangChu = view.findViewById(R.id.tbar_trang_chu);
        tbarTrangChu.inflateMenu(R.menu.menu_gio_hang); //Khởi tạo
        tbarTrangChu.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId() == R.id.menu_gio_hang){
                    startActivity(new Intent(getActivity(), GioHangAct.class));
                }
                return false;
            }
        });


        rvCategoryBlock = view.findViewById(R.id.rv_category_block);
        rvCategoryBlock.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvDanhMuc = view.findViewById(R.id.rv_danh_muc);
        rvDanhMuc.setLayoutManager(new GridLayoutManager(getActivity(),
                2, LinearLayoutManager.HORIZONTAL, false));
    }
}
