package com.dtt.titiappv1.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dtt.titiappv1.Activities.SanPhamAct;
import com.dtt.titiappv1.Entities.DanhMuc;
import com.dtt.titiappv1.Entities.SanPham;
import com.dtt.titiappv1.R;
import com.dtt.titiappv1.Utilities.Server;
import com.dtt.titiappv1.Utilities.SupportClassUI;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DanhMucAdapter extends RecyclerView.Adapter<DanhMucAdapter.DanhMucHolder> {
    public static final String KEY_ID_DANH_MUC = "KEY_ID_DANH_MUC";
    Context context;
    List<DanhMuc> arrDanhMuc;
    public DanhMucAdapter(Context context, List<DanhMuc> arrDanhMuc) {
        this.context = context;
        this.arrDanhMuc = arrDanhMuc;
    }

    public class DanhMucHolder extends RecyclerView.ViewHolder {
        private LinearLayout lnDanhMuc;
        private ImageView ivDanhMuc;
        private TextView tvDanhMuc;
        public DanhMucHolder(@NonNull View itemView) {
            super(itemView);
            lnDanhMuc = itemView.findViewById(R.id.ln_danh_muc);
            ivDanhMuc = itemView.findViewById(R.id.iv_danh_muc);
            tvDanhMuc = itemView.findViewById(R.id.tv_danh_muc);
        }
    }

    @NonNull
    @Override
    public DanhMucHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.context).inflate(R.layout.item_category, parent,
                                                    false);
        return new DanhMucHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DanhMucHolder holder, int position) {
        final DanhMuc danhMuc = arrDanhMuc.get(position);
        holder.tvDanhMuc.setText(danhMuc.getTenDanhMuc());
        String duongDanAnh = Server.IMAGES_URL + danhMuc.getMoTa();
        Picasso.get().load(duongDanAnh).into(holder.ivDanhMuc);

        //Event
        holder.lnDanhMuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SupportClassUI.showMessShort(context, "ID = " + danhMuc.getId());
                Intent intent = new Intent(context, SanPhamAct.class);
                intent.putExtra(KEY_ID_DANH_MUC, danhMuc.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrDanhMuc.size();
    }


}
