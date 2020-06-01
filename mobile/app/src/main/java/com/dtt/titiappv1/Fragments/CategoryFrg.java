package com.dtt.titiappv1.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.dtt.titiappv1.Adapters.DanhMucAdapter;
import com.dtt.titiappv1.DesignPatterns.VolleySingleton;
import com.dtt.titiappv1.Entities.DanhMuc;
import com.dtt.titiappv1.R;
import com.dtt.titiappv1.Utilities.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CategoryFrg extends Fragment {
    public final static String TAG = "CategoryFrg";
    RecyclerView rvDanhMuc;
    ArrayList<DanhMuc> arrDanhMuc;
    DanhMucAdapter danhMucAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_category, container, false);

        initView(view);
        initData();
        return view;
    }

    private void initData() {
        loadCacDanhMuc();
//        arrDanhMuc = new ArrayList<>();
//        arrDanhMuc.add(new DanhMuc(1, "tuan1", "tuan1", "tuan1"));
//        arrDanhMuc.add(new DanhMuc(2, "tuan2", "tuan2", "tuan2"));
        //Log.d(TAG, "size = " + arrDanhMuc.size());
        danhMucAdapter = new DanhMucAdapter(getActivity(), arrDanhMuc);
        rvDanhMuc.setAdapter(danhMucAdapter);
    }

    private void loadCacDanhMuc() {
        arrDanhMuc = new ArrayList<>();
        JsonArrayRequest request = new JsonArrayRequest(Server.API_GET_DANH_MUC,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Log.d(TAG, "r = " + response.toString());
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
                                arrDanhMuc.add(danhMuc);
                                danhMucAdapter.notifyDataSetChanged();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.d(TAG, "size danh muc ="+arrDanhMuc.size());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Lỗi ở CategoryFrg" + error.toString());
                    }
        });
        VolleySingleton.getInstance(getActivity()).getRequestQueue().add(request);
        //Log.d(TAG, "size = " + arrDanhMuc.size()); //=>Luôn in ra 0 vì nó chạy dòng này trước rồi mới get data
    }

    private void initView(View view) {
        rvDanhMuc = view.findViewById(R.id.rv_danh_muc);
        rvDanhMuc.setLayoutManager(new GridLayoutManager(getActivity(), 3));
    }
}
