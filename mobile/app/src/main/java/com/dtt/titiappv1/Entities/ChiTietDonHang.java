package com.dtt.titiappv1.Entities;

public class ChiTietDonHang {
    private int idSP, idDH;
    private String tenSP, hinhSP;
    private int soLuong, gia;

    public ChiTietDonHang(){

    }

    public ChiTietDonHang(int idSP, int idDH, String tenSP, String hinhSP, int soLuong, int gia) {
        this.idSP = idSP;
        this.idDH = idDH;
        this.tenSP = tenSP;
        this.hinhSP = hinhSP;
        this.soLuong = soLuong;
        this.gia = gia;
    }

    public int getIdSP() {
        return idSP;
    }

    public void setIdSP(int idSP) {
        this.idSP = idSP;
    }

    public int getIdDH() {
        return idDH;
    }

    public void setIdDH(int idDH) {
        this.idDH = idDH;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getHinhSP() {
        return hinhSP;
    }

    public void setHinhSP(String hinhSP) {
        this.hinhSP = hinhSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }
}
