package com.dtt.titiappv1.Entities;

public class SanPham {
    private int id;
    private String ten;
    private int gia;
    private String hinh, moTa, thongSoChiTiet, albumHinh;
    private int idDanhMuc;

    public SanPham() {
    }

    public SanPham(int id, String ten, int gia, String hinh, String moTa,
                   String thongSoChiTiet, String albumHinh, int idDanhMuc) {
        this.id = id;
        this.ten = ten;
        this.gia = gia;
        this.hinh = hinh;
        this.moTa = moTa;
        this.thongSoChiTiet = thongSoChiTiet;
        this.albumHinh = albumHinh;
        this.idDanhMuc = idDanhMuc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getThongSoChiTiet() {
        return thongSoChiTiet;
    }

    public void setThongSoChiTiet(String thongSoChiTiet) {
        this.thongSoChiTiet = thongSoChiTiet;
    }

    public String getAlbumHinh() {
        return albumHinh;
    }

    public void setAlbumHinh(String albumHinh) {
        this.albumHinh = albumHinh;
    }

    public int getIdDanhMuc() {
        return idDanhMuc;
    }

    public void setIdDanhMuc(int idDanhMuc) {
        this.idDanhMuc = idDanhMuc;
    }
}
