package com.dtt.titiappv1.Event;

import com.dtt.titiappv1.Entities.GioHang;

public interface ChangeStateGioHang {
    void changeStateGioHang(String tag, GioHang gioHang, int position);
}
