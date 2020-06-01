package com.dtt.titiappv1.Entities;

import java.util.List;

public class DanhMuc{
    private int id;
    private String tenDanhMuc, hinhNen, moTa;

    //Bổ sung thêm thuộc tính để phục vụ cho Home
    private List<SanPham> arrSanPham;

    public DanhMuc(){

    }

    public DanhMuc(int id, String tenDanhMuc, String hinhNen, String moTa) {
        this.id = id;
        this.tenDanhMuc = tenDanhMuc;
        this.hinhNen = hinhNen;
        this.moTa = moTa;
    }

    public List<SanPham> getArrSanPham() {
        return arrSanPham;
    }

    public void setArrSanPham(List<SanPham> arrSanPham) {
        this.arrSanPham = arrSanPham;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    public String getHinhNen() {
        return hinhNen;
    }

    public void setHinhNen(String hinhNen) {
        this.hinhNen = hinhNen;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public String toString() {
        return "DanhMuc{" +
                "id=" + id +
                ", tenDanhMuc='" + tenDanhMuc + '\'' +
                ", hinhNen='" + hinhNen + '\'' +
                ", moTa='" + moTa + '\'' +
                '}';
    }
}