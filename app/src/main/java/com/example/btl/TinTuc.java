package com.example.btl;

public class TinTuc {
    private String tieuDe;
    private int hinhAnh;

    public TinTuc(String tieuDe, int hinhAnh) {
        this.tieuDe = tieuDe;
        this.hinhAnh = hinhAnh;
    }
    public TinTuc() {
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public int getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(int hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}
