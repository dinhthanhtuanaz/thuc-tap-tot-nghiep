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

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.SanPhamHolder> {
    public static final String KEY_ID_SAN_PHAM = "KEY_ID_SAN_PHAM";
    Context context;
    List<SanPham> arrSanPham;

    public SanPhamAdapter(Context context, List<SanPham> arrSanPham) {
        this.context = context;
        this.arrSanPham = arrSanPham;
    }

    public class SanPhamHolder extends RecyclerView.ViewHolder {
        private ImageView ivHinhSP;
        private TextView tvTenSP, tvGiaSP;
        private LinearLayout lnSanPham;
        public SanPhamHolder(@NonNull View itemView) {
            super(itemView);
            ivHinhSP = itemView.findViewById(R.id.iv_hinh_sp);
            tvTenSP = itemView.findViewById(R.id.tv_ten_sp);
            tvGiaSP = itemView.findViewById(R.id.tv_gia_sp);

            lnSanPham = itemView.findViewById(R.id.ln_san_pham);
        }
    }

    @NonNull
    @Override
    public SanPhamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent,
                                                        false);
        return new SanPhamHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamHolder holder, int position) {
        final SanPham sanPham = arrSanPham.get(position);

        Picasso.get().load(Server.IMAGES_URL + sanPham.getHinh()).
                placeholder(R.drawable.bg_placeholder).into(holder.ivHinhSP);
        holder.tvTenSP.setText(sanPham.getTen());
        holder.tvGiaSP.setText(SupportClassLogic.formatMoney(sanPham.getGia()));

        holder.lnSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChiTietSanPhamAct.class);
                intent.putExtra(KEY_ID_SAN_PHAM, sanPham.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrSanPham.size();
    }
}
