package com.dtt.titiappv1.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dtt.titiappv1.Entities.SanPham;
import com.dtt.titiappv1.R;
import com.dtt.titiappv1.Utilities.Server;
import com.dtt.titiappv1.Utilities.SupportClassLogic;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SanPhamMoiAdapter extends RecyclerView.Adapter<SanPhamMoiAdapter.SanPhamHolder> {
    Context context;
    List<SanPham> arrSanPham;

    public SanPhamMoiAdapter(Context context, List<SanPham> arrSanPham) {
        this.context = context;
        this.arrSanPham = arrSanPham;
    }

    public class SanPhamHolder extends RecyclerView.ViewHolder {
        private ImageView ivHinhSP;
        private TextView tvTenSP, tvGiaSP;
        public SanPhamHolder(@NonNull View itemView) {
            super(itemView);
            ivHinhSP = itemView.findViewById(R.id.iv_hinh_sp);
            tvTenSP = itemView.findViewById(R.id.tv_ten_sp);
            tvGiaSP = itemView.findViewById(R.id.tv_gia_sp);
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
        SanPham sanPham = arrSanPham.get(position);

        Picasso.get().load(Server.IMAGES_URL + sanPham.getHinh()).
                placeholder(R.drawable.bg_placeholder).into(holder.ivHinhSP);
        holder.tvTenSP.setText(sanPham.getTen());
        holder.tvGiaSP.setText(SupportClassLogic.formatMoney(sanPham.getGia()));
    }

    @Override
    public int getItemCount() {
        return arrSanPham.size();
    }
}
