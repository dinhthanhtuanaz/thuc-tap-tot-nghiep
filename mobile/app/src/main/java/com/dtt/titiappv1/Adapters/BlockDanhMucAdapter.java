package com.dtt.titiappv1.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dtt.titiappv1.Entities.DanhMuc;
import com.dtt.titiappv1.Entities.SanPham;
import com.dtt.titiappv1.R;
import com.dtt.titiappv1.Utilities.Server;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

public class BlockDanhMucAdapter extends RecyclerView.Adapter<BlockDanhMucAdapter.BlockDanhMucHolder> {
    private Context context;
    private List<DanhMuc> arrDanhMuc;

    public BlockDanhMucAdapter(Context context, List<DanhMuc> arrDanhMuc) {
        this.context = context;
        this.arrDanhMuc = arrDanhMuc;
    }

    @NonNull
    @Override
    public BlockDanhMucHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category_block,
                parent, false);
        return new BlockDanhMucHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlockDanhMucHolder holder, int position) {
        DanhMuc danhMuc = arrDanhMuc.get(position);
        List<SanPham> arrSanPham = danhMuc.getArrSanPham();

        holder.tvTenDM.setText(danhMuc.getTenDanhMuc());
        Picasso.get().load(Server.IMAGES_URL + danhMuc.getHinhNen())
                .placeholder(R.drawable.bg_placeholder).into(holder.ivHinhNenDM);
        if(arrSanPham.size() < 5){
            holder.rvSanPham.setLayoutManager(new GridLayoutManager(context,
                    1, LinearLayoutManager.HORIZONTAL, false));
        } else {
            holder.rvSanPham.setLayoutManager(new GridLayoutManager(context,
                    2, LinearLayoutManager.HORIZONTAL, false));
        }

        SanPhamAdapter sanPhamAdapter = new SanPhamAdapter(context, arrSanPham);
        holder.rvSanPham.setAdapter(sanPhamAdapter);
    }

    @Override
    public int getItemCount() {
        return arrDanhMuc.size();
    }

    public class BlockDanhMucHolder extends RecyclerView.ViewHolder {
        public TextView tvTenDM;
        public ImageView ivHinhNenDM;
        public RecyclerView rvSanPham;
        public BlockDanhMucHolder(@NonNull View itemView) {
            super(itemView);
            tvTenDM = itemView.findViewById(R.id.tv_ten_dm);
            ivHinhNenDM = itemView.findViewById(R.id.iv_hinh_nen_dm);
            rvSanPham = itemView.findViewById(R.id.rv_san_pham);
        }
    }
}
