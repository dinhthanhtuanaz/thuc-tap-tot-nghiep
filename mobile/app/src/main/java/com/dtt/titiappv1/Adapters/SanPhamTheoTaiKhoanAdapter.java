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

import com.dtt.titiappv1.Activities.ChiTietSanPhamAct;
import com.dtt.titiappv1.Entities.SanPham;
import com.dtt.titiappv1.R;
import com.dtt.titiappv1.Utilities.Server;
import com.dtt.titiappv1.Utilities.SupportClassLogic;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SanPhamTheoTaiKhoanAdapter extends
        RecyclerView.Adapter<SanPhamTheoTaiKhoanAdapter.SanPhamTheoTaiKhoanHolder> {
    Context context;
    List<SanPham> arrSanPham;

    public SanPhamTheoTaiKhoanAdapter(Context context, List<SanPham> arrSanPham) {
        this.context = context;
        this.arrSanPham = arrSanPham;
    }

    @NonNull
    @Override
    public SanPhamTheoTaiKhoanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_acc,
                parent, false);
        return new SanPhamTheoTaiKhoanHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamTheoTaiKhoanHolder holder, int position) {
        final SanPham sanPham = arrSanPham.get(position);
        Picasso.get().load(Server.IMAGES_URL + sanPham.getHinh())
                .placeholder(R.drawable.bg_placeholder).into(holder.ivHinhSP);
        holder.tvTenSP.setText(sanPham.getTen());
        holder.tvGiaSP.setText(SupportClassLogic.formatMoney(sanPham.getGia()));
        holder.lnProductAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChiTietSanPhamAct.class);
                intent.putExtra(SanPhamAdapter.KEY_ID_SAN_PHAM, sanPham.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrSanPham.size();
    }

    public class SanPhamTheoTaiKhoanHolder extends RecyclerView.ViewHolder {
        private ImageView ivHinhSP;
        private TextView tvTenSP, tvGiaSP;
        private LinearLayout lnProductAcc;
        public SanPhamTheoTaiKhoanHolder(@NonNull View itemView) {
            super(itemView);
            ivHinhSP = itemView.findViewById(R.id.iv_hinh_sp);
            tvTenSP = itemView.findViewById(R.id.tv_ten_sp);
            tvGiaSP = itemView.findViewById(R.id.tv_gia_sp);
            lnProductAcc = itemView.findViewById(R.id.ln_product_acc);
        }
    }
}
