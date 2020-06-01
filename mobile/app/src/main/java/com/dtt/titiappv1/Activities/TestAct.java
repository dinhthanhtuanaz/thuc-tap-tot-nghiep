package com.dtt.titiappv1.Activities;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.dtt.titiappv1.DesignPatterns.VolleySingleton;
import com.dtt.titiappv1.Entities.DanhMuc;
import com.dtt.titiappv1.R;
import com.dtt.titiappv1.Utilities.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TestAct extends AppCompatActivity {
    private static final String TAG = "TestAct";
    ArrayList<DanhMuc> arrDanhMuc;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_quen_mat_khau);

        initData();
    }

    private void initData() {
        arrDanhMuc = new ArrayList<>();
        JsonArrayRequest request = new JsonArrayRequest(Server.API_GET_DANH_MUC,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, "r = " + response.toString());
                        JSONObject obj;

                        for(int i =0; i < response.length(); i++){
                            try {
                                obj = response.getJSONObject(i);
                                Log.d(TAG, "objjj = " + obj.getString("tenDanhMuc"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "Lỗi ở CategoryFrg" + error.toString());
            }
        });
        VolleySingleton.getInstance(this).getRequestQueue().add(request);
    }
}
