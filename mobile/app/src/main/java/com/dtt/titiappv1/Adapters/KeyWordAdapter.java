package com.dtt.titiappv1.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dtt.titiappv1.Activities.SanPhamTheoTaiKhoanAct;
import com.dtt.titiappv1.R;
import com.dtt.titiappv1.Utilities.Constant;

import java.util.List;

public class KeyWordAdapter extends RecyclerView.Adapter<KeyWordAdapter.KeyWordHolder> {
    Context context;
    List<String> keyWordArr;

    public KeyWordAdapter(Context context, List<String> keyWordArr) {
        this.context = context;
        this.keyWordArr = keyWordArr;
    }

    @NonNull
    @Override
    public KeyWordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search,
                parent, false);

        return new KeyWordHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KeyWordHolder holder, int position) {
        final String keyWord = keyWordArr.get(position);
        holder.tvTuTimKiem.setText(keyWord);
        holder.lnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SanPhamTheoTaiKhoanAct.class);
                intent.putExtra(Constant.KEY_KIEU_SP, Constant.KEY_SP_TIM_KIEM);
                intent.putExtra(Constant.KEY_WORD, keyWord);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return keyWordArr.size();
    }

    public class KeyWordHolder extends RecyclerView.ViewHolder {
        public TextView tvTuTimKiem;
        public LinearLayout lnTimKiem;
        public KeyWordHolder(@NonNull View itemView) {
            super(itemView);
            tvTuTimKiem = itemView.findViewById(R.id.tv_tu_tim_kiem);
            lnTimKiem = itemView.findViewById(R.id.ln_tim_kiem);
        }
    }
}
