package com.example.btl;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class LoiNhac implements Parcelable {

    int id;
    private String noiDung;
    private String date;
    private int trangthai;

    public LoiNhac() {
    }

    public LoiNhac(int id, String noiDung, String date, int trangthai) {
        this.id = id;
        this.noiDung = noiDung;
        this.date = date;
        this.trangthai = trangthai;
    }

    public LoiNhac(String noiDung, String date, int trangthai) {
        this.noiDung = noiDung;
        this.date = date;
        this.trangthai = trangthai;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }


    protected LoiNhac(Parcel in) {
        id = in.readInt();
        noiDung = in.readString();
        date = in.readString();
        trangthai = in.readInt();
    }

    public static final Creator<LoiNhac> CREATOR = new Creator<LoiNhac>() {
        @Override
        public LoiNhac createFromParcel(Parcel in) {
            return new LoiNhac(in);
        }

        @Override
        public LoiNhac[] newArray(int size) {
            return new LoiNhac[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(noiDung);
        dest.writeString(date);
        dest.writeInt(trangthai);
    }
}
