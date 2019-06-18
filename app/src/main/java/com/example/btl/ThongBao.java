package com.example.btl;

public class ThongBao {
    int id;
    private String noiDung;
    private String loaiTB;
    private int tinhTrang;
    private String date;

    public ThongBao() {
    }

    public ThongBao(int id, String noiDung, String loaiTB, int tinhTrang, String date) {
        this.id = id;
        this.noiDung = noiDung;
        this.loaiTB = loaiTB;
        this.tinhTrang = tinhTrang;
        this.date = date;
    }

    public ThongBao(String noiDung, String loaiTB, int tinhTrang, String date) {
        this.noiDung = noiDung;
        this.loaiTB = loaiTB;
        this.tinhTrang = tinhTrang;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getLoaiTB() {
        return loaiTB;
    }

    public void setLoaiTB(String loaiTB) {
        this.loaiTB = loaiTB;
    }

    public int getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(int tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
