package com.dtt.titiappv1.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.dtt.titiappv1.Fragments.DangKyFrg;
import com.dtt.titiappv1.Fragments.DangNhapFrg;

public class PagerDangNhapAdapter extends FragmentPagerAdapter {
    public PagerDangNhapAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new DangNhapFrg();
        } else if(position == 1){
            return new DangKyFrg();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0){
            return "Đăng nhập";
        } else if(position == 1){
            return "Đăng ký";
        }
        return null;
    }
}
