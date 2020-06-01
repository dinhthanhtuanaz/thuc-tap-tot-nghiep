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
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dtt.titiappv1.Activities.SanPhamTheoTaiKhoanAct;
import com.dtt.titiappv1.Adapters.KeyWordAdapter;
import com.dtt.titiappv1.R;
import com.dtt.titiappv1.Utilities.Constant;
import com.dtt.titiappv1.Utilities.SupportClassUI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchFrg extends Fragment implements View.OnClickListener {
    public static final String TAG = "SearchFrg";
    EditText edtTimKiem;
    Button btTimKiem;
    TextView tvXoaLichSu;
    RecyclerView rvTimKiem;
    List<String> keyWordArr;
    KeyWordAdapter keyWordAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_search, container, false);

        initView(view);
        initData();
        return view;
    }


    private void loadData() {
        SharedPreferences shared = getActivity().getSharedPreferences(Constant.KEY_WORD_FILE,
                Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = shared.edit();
        String valueStr = shared.getString(Constant.KEY_WORD, "");
        if(valueStr.isEmpty() || valueStr.equals("[]")){
            rvTimKiem.setPadding(0,0,0,0);
        } else{
            try {
                JSONArray jsonArray = new JSONArray(valueStr);
//                for(int i = jsonArray.length()-1; i > -1; i--){
//                    keyWordArr.add(jsonArray.getString(i));
//                }
                //Log.d(TAG, "length = " + jsonArray.length());
                for(int i = jsonArray.length()-1; i > -1; i--){
                    //Log.d(TAG, "name: " + jsonArray.getString(i));
                    keyWordArr.add(jsonArray.getString(i));
                }
                keyWordAdapter = new KeyWordAdapter(getActivity(), keyWordArr);
                //Log.d(TAG, "s = " + keyWordArr.size() + "-" + keyWordAdapter.getItemCount());
                rvTimKiem.setAdapter(keyWordAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        //Log.d(TAG, "data = " + valueStr);
    }

    private void initData() {
        keyWordArr = new ArrayList<>();
        loadData();
    }

    private void initView(View view) {
        edtTimKiem = view.findViewById(R.id.edt_tim_kiem);
        btTimKiem = view.findViewById(R.id.bt_tim_kiem);
        tvXoaLichSu = view.findViewById(R.id.tv_xoa_lich_su);
        rvTimKiem = view.findViewById(R.id.rv_tim_kiem);

        rvTimKiem.setLayoutManager(new LinearLayoutManager(getActivity()));
        btTimKiem.setOnClickListener(this);
        tvXoaLichSu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.bt_tim_kiem){
            String keyWord = edtTimKiem.getText().toString();
            if(keyWord.isEmpty()){
                SupportClassUI.showMessShort(getActivity(), getResources().getString(R.string.txt_ban_chua_nhap_gi));
                return;
            }
            //Bước 1: Lưu xuống SharedPreferences
            SharedPreferences shared = getActivity().getSharedPreferences(Constant.KEY_WORD_FILE,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = shared.edit();
            String valueStr = shared.getString(Constant.KEY_WORD, "");
//            editor.remove(Constant.KEY_WORD);
//            editor.commit();

            if(valueStr.isEmpty() || valueStr.equals("[]")){
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(keyWord);
                Gson gson = new GsonBuilder().create();
                JsonArray keyWordArr = gson.toJsonTree(arrayList).getAsJsonArray();
                editor.putString(Constant.KEY_WORD, keyWordArr.toString());
                editor.commit();

            } else{
                try {
                    JSONArray jsonArray = new JSONArray(valueStr);
                    ArrayList<String> arrayList = new ArrayList<>();
                    for(int i =0; i < jsonArray.length(); i++){
                        arrayList.add(jsonArray.getString(i));
                    }
                    arrayList.add(keyWord);
                    Gson gson = new GsonBuilder().create();
                    JsonArray keyWordArr = gson.toJsonTree(arrayList).getAsJsonArray();
                    editor.putString(Constant.KEY_WORD, keyWordArr.toString());
                    editor.commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            //Bước 2: Gửi request lên Server => Nhận data và trả về cho SanPhamTheoTaiKhoanAct
            //startActivity(new Intent(getActivity(), SanPhamTheoTaiKhoanAct.class));
            Intent intent = new Intent(getActivity(), SanPhamTheoTaiKhoanAct.class);
            intent.putExtra(Constant.KEY_KIEU_SP, Constant.KEY_SP_TIM_KIEM);
            intent.putExtra(Constant.KEY_WORD, keyWord);
            startActivity(intent);



        }
        else if(v.getId() == R.id.tv_xoa_lich_su){
            rvTimKiem.setPadding(0,0,0,0);
            if(keyWordArr.size() == 0){
                return;
            }
            SharedPreferences shared = getActivity().getSharedPreferences(Constant.KEY_WORD_FILE,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = shared.edit();
            //String valueStr = shared.getString(Constant.KEY_WORD, "");
            editor.remove(Constant.KEY_WORD);
            editor.commit();
            keyWordArr.clear();
            keyWordAdapter.notifyDataSetChanged();
            //rvTimKiem.setAdapter(keyWordAdapter);
        }
    }
}


/*data = ["laptop","may tinh","ti vi tu lanh"]*/
