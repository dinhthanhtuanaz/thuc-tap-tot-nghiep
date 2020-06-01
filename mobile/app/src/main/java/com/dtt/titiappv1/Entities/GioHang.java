package com.dtt.titiappv1.Entities;

public class GioHang {
    private int idSP;
    private String tenSP, hinhSP;
    private int soLuongSP, giaSP;

    public GioHang(){

    }

    public GioHang(int idSP, String tenSP, String hinhSP, int soLuongSP, int giaSP) {
        this.idSP = idSP;
        this.tenSP = tenSP;
        this.hinhSP = hinhSP;
        this.soLuongSP = soLuongSP;
        this.giaSP = giaSP;
    }

    public int getIdSP() {
        return idSP;
    }

    public void setIdSP(int idSP) {
        this.idSP = idSP;
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

    public int getSoLuongSP() {
        return soLuongSP;
    }

    public void setSoLuongSP(int soLuongSP) {
        this.soLuongSP = soLuongSP;
    }

    public int getGiaSP() {
        return giaSP;
    }

    public void setGiaSP(int giaSP) {
        this.giaSP = giaSP;
    }

    @Override
    public String toString() {
        return "GioHang{" +
                "idSP=" + idSP +
                ", tenSP='" + tenSP + '\'' +
                ", hinhSP='" + hinhSP + '\'' +
                ", soLuongSP=" + soLuongSP +
                ", giaSP=" + giaSP +
                '}';
    }
}
