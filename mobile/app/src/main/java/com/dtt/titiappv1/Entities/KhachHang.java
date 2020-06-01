package com.dtt.titiappv1.Entities;

import java.util.Date;

public class KhachHang {
    int id;
    String soDienThoai, email, matKhau, hoTen;
    boolean gioiTinh, nhanThongBao;
    Date ngaySinh, ngayTao;

    public KhachHang(int id, String soDienThoai, String email, String matKhau, String hoTen,
                     boolean gioiTinh, boolean nhanThongBao, Date ngaySinh, Date ngayTao) {
        this.id = id;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.matKhau = matKhau;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.nhanThongBao = nhanThongBao;
        this.ngaySinh = ngaySinh;
        this.ngayTao = ngayTao;
    }

    public int getId() {
        return id;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public boolean isNhanThongBao() {
        return nhanThongBao;
    }

    public void setNhanThongBao(boolean nhanThongBao) {
        this.nhanThongBao = nhanThongBao;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }
}
