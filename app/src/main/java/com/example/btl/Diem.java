package com.example.btl;

public class Diem {
    private  int id;
    private String maMH;
    private String tenMH;
    private String diem;
    private String xepLoai;
    private int sotc;

    public Diem() {
    }

    public Diem(int id, String maMH, String tenMH, String diem, String xepLoai, int sotc) {
        this.id = id;
        this.maMH = maMH;
        this.tenMH = tenMH;
        this.diem = diem;
        this.xepLoai = xepLoai;
        this.sotc = sotc;
    }

    public Diem(String maMH, String tenMH, String diem, String xepLoai, int sotc) {
        this.maMH = maMH;
        this.tenMH = tenMH;
        this.diem = diem;
        this.xepLoai = xepLoai;
        this.sotc = sotc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public String getTenMH() {
        return tenMH;
    }

    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }

    public String getDiem() {
        return diem;
    }

    public void setDiem(String diem) {
        this.diem = diem;
    }

    public String getXepLoai() {
        return xepLoai;
    }

    public void setXepLoai(String xepLoai) {
        this.xepLoai = xepLoai;
    }

    public int getSotc() {
        return sotc;
    }

    public void setSotc(int sotc) {
        this.sotc = sotc;
    }
}
