package com.example.btl;

public class KipHoc {
    private int id;
    private String date;
    private String tenMH;
    private String tenGV;
    private String time;
    private String diaDiem;

    public KipHoc() {
    }

    public KipHoc(int id, String date, String tenMH, String tenGV, String time, String diaDiem) {
        this.id = id;
        this.date = date;
        this.tenMH = tenMH;
        this.tenGV = tenGV;
        this.time = time;
        this.diaDiem = diaDiem;
    }

    public KipHoc(String date, String tenMH, String tenGV, String time, String diaDiem) {
        this.date = date;
        this.tenMH = tenMH;
        this.tenGV = tenGV;
        this.time = time;
        this.diaDiem = diaDiem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTenMH() {
        return tenMH;
    }

    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }

    public String getTenGV() {
        return tenGV;
    }

    public void setTenGV(String tenGV) {
        this.tenGV = tenGV;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(String diaDiem) {
        this.diaDiem = diaDiem;
    }
}
