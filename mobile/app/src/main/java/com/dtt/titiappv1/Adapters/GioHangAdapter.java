package com.dtt.titiappv1.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dtt.titiappv1.Entities.GioHang;
import com.dtt.titiappv1.Event.ChangeStateGioHang;
import com.dtt.titiappv1.R;
import com.dtt.titiappv1.Utilities.Server;
import com.dtt.titiappv1.Utilities.SupportClassLogic;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.GioHangHolder>{
    public static final String KEY_GIO_HANG = "KEY_GIO_HANG";
    public static final String KEY_XOA_ITEM = "KEY_XOA_ITEM";
    private Context context;
    private List<GioHang> arrGioHang;
    //Khai báo changeStateGioHang
    private ChangeStateGioHang mChangeStateGioHang;
    public void changeStateGioHang(ChangeStateGioHang event){
        this.mChangeStateGioHang = event;
    }

    public GioHangAdapter(Context context, List<GioHang> arrGioHang) {
        this.context = context;
        this.arrGioHang = arrGioHang;
    }


    public class GioHangHolder extends RecyclerView.ViewHolder{
        private ImageView ivSanPham, ivDong, ivTang, ivGiam;
        private TextView tvTenSP, tvGiaSP;
        private Button btSoLuong;
        public GioHangHolder(@NonNull View itemView) {
            super(itemView);
            ivSanPham = itemView.findViewById(R.id.iv_san_pham);
            ivDong = itemView.findViewById(R.id.iv_dong);
            ivTang = itemView.findViewById(R.id.iv_tang);
            ivGiam = itemView.findViewById(R.id.iv_giam);
            tvTenSP = itemView.findViewById(R.id.tv_ten_sp);
            tvGiaSP = itemView.findViewById(R.id.tv_gia_sp);
            btSoLuong = itemView.findViewById(R.id.bt_so_luong);
        }
    }

    @NonNull
    @Override
    public GioHangHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new GioHangHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GioHangHolder holder, final int position) {
        final GioHang gioHang = arrGioHang.get(position);
        //Bind
        holder.tvTenSP.setText(gioHang.getTenSP());
        holder.tvGiaSP.setText(SupportClassLogic.formatMoney(gioHang.getGiaSP()));
        Picasso.get().load(Server.IMAGES_URL + gioHang.getHinhSP())
                .placeholder(R.drawable.bg_placeholder).into(holder.ivSanPham);
        holder.btSoLuong.setText(gioHang.getSoLuongSP() + "");
        holder.ivTang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyTangGiamSoLuongSanPham(holder, gioHang, position, 1);
            }
        });
        holder.ivGiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuLyTangGiamSoLuongSanPham(holder, gioHang, position, -1);
            }
        });
        holder.ivDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChangeStateGioHang.changeStateGioHang(KEY_XOA_ITEM, gioHang, position);
            }
        });
        //Xử lý Ẩn/Hiện Button Tăng/Giảm Số lượng sản phẩm
        xuLyAnHienNutTangGiamSoLuong(holder);

    }

    private void xuLyAnHienNutTangGiamSoLuong(GioHangHolder holder) {
        if(Integer.parseInt(holder.btSoLuong.getText().toString()) >= 5){
            holder.ivTang.setVisibility(View.INVISIBLE);
            holder.ivGiam.setVisibility(View.VISIBLE);
        } else{
            holder.ivTang.setVisibility(View.VISIBLE);
            holder.ivGiam.setVisibility(View.VISIBLE);
            if(Integer.parseInt(holder.btSoLuong.getText().toString()) <= 1 ){
                holder.ivGiam.setVisibility(View.INVISIBLE);
            }
        }
    }

    private void xuLyTangGiamSoLuongSanPham(GioHangHolder holder, GioHang gioHang, int viTri,int soLuong) {
        int soLuongHang = Integer.parseInt(holder.btSoLuong.getText().toString()) + soLuong;
        int giaMoiSP = gioHang.getGiaSP() / gioHang.getSoLuongSP();
        holder.btSoLuong.setText(soLuongHang + "");
        holder.tvGiaSP.setText(SupportClassLogic.formatMoney(soLuongHang * giaMoiSP));
        gioHang.setSoLuongSP(soLuongHang);
        gioHang.setGiaSP(soLuongHang * giaMoiSP);
        //Gửi dữ liệu qua bên GioHangAct để bên đó Xử lý cho phần Tổng tiền
        mChangeStateGioHang.changeStateGioHang(KEY_GIO_HANG, gioHang, viTri);

        xuLyAnHienNutTangGiamSoLuong(holder);
    }

    @Override
    public int getItemCount() {
        return arrGioHang.size();
    }
}
