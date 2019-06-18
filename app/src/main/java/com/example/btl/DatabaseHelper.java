package com.example.btl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.btl.Diem;
import com.example.btl.KipHoc;
import com.example.btl.LoiNhac;
import com.example.btl.ThongBao;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String LOG = "DatabaseHelper";

    private static final String DATABASE_NAME = "myPtit";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_DIEM = "diem_tich_luy";
    private static final String TABLE_KIPHOC = "kip_hoc";
    private static final String TABLE_LOINHAC = "loi_nhac";
    private static final String TABLE_THONGBAO = "thong_bao";

    private static final String CREATE_TABLE_DIEM = "CREATE TABLE IF NOT EXISTS "
            + TABLE_DIEM + "(id INTEGER PRIMARY KEY AUTOINCREMENT, maMH VARCHAR(50), tenMH VARCHAR(200), diem VARCHAR(15), xepLoai VARCHAR(2))";

    private static final String CREATE_TABLE_KIPHOC = "CREATE TABLE IF NOT EXISTS "
            + TABLE_KIPHOC + "(id INTEGER PRIMARY KEY AUTOINCREMENT, date VARCHAR(50), tenMH VARCHAR(200), tenGV VARCHAR(100), thoiGian VARCHAR(20), phongHoc VARCHAR(50))";

    private static final String CREATE_TABLE_LOINHAC = "CREATE TABLE IF NOT EXISTS "
            + TABLE_LOINHAC + "(id INTEGER PRIMARY KEY AUTOINCREMENT, noiDung VARCHAR(500), date VARCHAR(50), trangThai INTEGER)";

    private static final String CREATE_TABLE_THONGBAO = "CREATE TABLE IF NOT EXISTS "
            + TABLE_THONGBAO + "(id INTEGER PRIMARY KEY AUTOINCREMENT, noiDung VARCHAR(500), loaiTB VARCHAR(50), tinhTrang INTEGER, date VARCHAR(50))";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DIEM);
        db.execSQL(CREATE_TABLE_KIPHOC);
        db.execSQL(CREATE_TABLE_LOINHAC);
        db.execSQL(CREATE_TABLE_THONGBAO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DIEM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KIPHOC);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOINHAC);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_THONGBAO);

        onCreate(db);
    }

    //CRUD Diem
    public long createDiem (Diem diem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("maMH", diem.getMaMH());
        values.put("tenMH", diem.getTenMH());
        values.put("diem", diem.getDiem());
        values.put("xepLoai", diem.getXepLoai());

        // insert row
        long diem_id = db.insert(TABLE_DIEM, null, values);

        return diem_id;
    }
    public Diem getDiem(int diem_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_DIEM + " WHERE id = " + diem_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Diem diem = new Diem();
        diem.setId(c.getInt(c.getColumnIndex("id")));
        diem.setMaMH(c.getString(c.getColumnIndex("maMH")));
        diem.setTenMH(c.getString(c.getColumnIndex("tenMH")));
        diem.setDiem(c.getString(c.getColumnIndex("diem")));
        diem.setXepLoai(c.getString(c.getColumnIndex("xepLoai")));
        return diem;
    }
    public List<Diem> getAllDiem() {
        List<Diem> diems = new ArrayList<Diem>();
        String selectQuery = "SELECT  * FROM " + TABLE_DIEM;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Diem diem = new Diem();
                diem.setId(c.getInt(c.getColumnIndex("id")));
                diem.setMaMH(c.getString(c.getColumnIndex("maMH")));
                diem.setTenMH(c.getString(c.getColumnIndex("tenMH")));
                diem.setDiem(c.getString(c.getColumnIndex("diem")));
                diem.setXepLoai(c.getString(c.getColumnIndex("xepLoai")));

                // adding to todo list
                diems.add(diem);
            } while (c.moveToNext());
        }

        return diems;
    }
    public int updateDiem(Diem diem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("maMH", diem.getMaMH());
        values.put("tenMH", diem.getTenMH());
        values.put("diem", diem.getDiem());
        values.put("xepLoai", diem.getXepLoai());

        // updating row
        return db.update(TABLE_DIEM, values, "id = ?",
                new String[] { String.valueOf(diem.getId()) });
    }
    public void deleteDiem(int diem_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DIEM, "id = ?",
                new String[] { String.valueOf(diem_id) });
    }


    //CRUD Kiphoc
    public long createKipHoc (KipHoc kipHoc) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("date", kipHoc.getDate());
        values.put("tenMH", kipHoc.getTenMH());
        values.put("tenGV", kipHoc.getTenGV());
        values.put("thoiGian", kipHoc.getTime());
        values.put("phongHoc", kipHoc.getDiaDiem());

        // insert row
        long kipHoc_id = db.insert(TABLE_DIEM, null, values);

        return kipHoc_id;
    }
    public KipHoc getKipHoc (int kipHoc_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_KIPHOC + " WHERE id = " + kipHoc_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        KipHoc kh = new KipHoc();
        kh.setId(c.getInt(c.getColumnIndex("id")));
        kh.setDate(c.getString(c.getColumnIndex("date")));
        kh.setTenMH(c.getString(c.getColumnIndex("tenMH")));
        kh.setTenGV(c.getString(c.getColumnIndex("tenGV")));
        kh.setTime(c.getString(c.getColumnIndex("thoiGian")));
        kh.setDiaDiem(c.getString(c.getColumnIndex("phongHoc")));
        return kh;
    }
    public List<KipHoc> getAllKipHocByDate(String toDay) {
        List<KipHoc> kipHocs = new ArrayList<KipHoc>();
        String selectQuery = "SELECT  * FROM " + TABLE_KIPHOC + " WHERE date = " + toDay;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                KipHoc kh = new KipHoc();
                kh.setId(c.getInt(c.getColumnIndex("id")));
                kh.setDate(c.getString(c.getColumnIndex("date")));
                kh.setTenMH(c.getString(c.getColumnIndex("tenMH")));
                kh.setTenGV(c.getString(c.getColumnIndex("tenGV")));
                kh.setTime(c.getString(c.getColumnIndex("thoiGian")));
                kh.setDiaDiem(c.getString(c.getColumnIndex("phongHoc")));

                // adding to todo list
                kipHocs.add(kh);
            } while (c.moveToNext());
        }

        return kipHocs;
    }
    public int updateKipHoc(KipHoc kipHoc) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("date", kipHoc.getDate());
        values.put("tenMH", kipHoc.getTenMH());
        values.put("tenGV", kipHoc.getTenGV());
        values.put("thoiGian", kipHoc.getTime());
        values.put("phongHoc", kipHoc.getDiaDiem());

        // updating row
        return db.update(TABLE_KIPHOC, values, "id = ?",
                new String[] { String.valueOf(kipHoc.getId()) });
    }
    public void deleteKipHoc(int kipHoc_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_KIPHOC, "id = ?",
                new String[] { String.valueOf(kipHoc_id) });
    }

    //CRUD LoiNhac
    public long createLoiNhac (LoiNhac loiNhac) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("noiDung", loiNhac.getNoiDung());
        values.put("date", loiNhac.getDate());
        values.put("trangThai", loiNhac.getTrangthai());

        // insert row
        long loinhac_id = db.insert(TABLE_LOINHAC, null, values);

        return loinhac_id;
    }
    public LoiNhac getLoiNhac (int loiNhac_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_LOINHAC + " WHERE id = " + loiNhac_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        LoiNhac ln = new LoiNhac();
        ln.setId(c.getInt(c.getColumnIndex("id")));
        ln.setNoiDung(c.getString(c.getColumnIndex("noiDung")));
        ln.setDate(c.getString(c.getColumnIndex("date")));
        ln.setTrangthai(c.getInt(c.getColumnIndex("trangThai")));
        return ln;
    }
    public List<LoiNhac> getAllLoiNhacByDate(String toDay) {
        List<LoiNhac> loiNhacs = new ArrayList<LoiNhac>();
        String selectQuery = "SELECT  * FROM " + TABLE_LOINHAC + " WHERE date = " + toDay;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                LoiNhac ln = new LoiNhac();
                ln.setId(c.getInt(c.getColumnIndex("id")));
                ln.setNoiDung(c.getString(c.getColumnIndex("noiDung")));
                ln.setDate(c.getString(c.getColumnIndex("date")));
                ln.setTrangthai(c.getInt(c.getColumnIndex("trangThai")));

                // adding to todo list
                loiNhacs.add(ln);
            } while (c.moveToNext());
        }

        return loiNhacs;
    }
    public int updateLoiNhac(LoiNhac loiNhac) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("noiDung", loiNhac.getNoiDung());
        values.put("date", loiNhac.getDate());
        values.put("trangThai", loiNhac.getTrangthai());

        // updating row
        return db.update(TABLE_LOINHAC, values, "id = ?",
                new String[] { String.valueOf(loiNhac.getId()) });
    }
    public void deleteLoiNhac(int loiNhac_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LOINHAC, "id = ?",
                new String[] { String.valueOf(loiNhac_id) });
    }

    //CRUD ThongBao
    public long createThongBao (ThongBao thongBao) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("noiDung", thongBao.getNoiDung());
        values.put("loaiTB", thongBao.getLoaiTB());
        values.put("tinhTrang", thongBao.getTinhTrang());
        values.put("date", thongBao.getDate());

        // insert row
        long thongBao_id = db.insert(TABLE_THONGBAO, null, values);

        return thongBao_id;
    }
    public ThongBao getThongBao (int thongBao_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_THONGBAO + " WHERE id = " + thongBao_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        ThongBao tb = new ThongBao();
        tb.setId(c.getInt(c.getColumnIndex("id")));
        tb.setNoiDung(c.getString(c.getColumnIndex("noiDung")));
        tb.setLoaiTB(c.getString(c.getColumnIndex("loaiTB")));
        tb.setTinhTrang(c.getInt(c.getColumnIndex("tinhTrang")));
        tb.setDate(c.getString(c.getColumnIndex("date")));
        return tb;
    }
    public List<ThongBao> getAllThongBaoByDate(String toDay) {
        List<ThongBao> thongBaos = new ArrayList<ThongBao>();
        String selectQuery = "SELECT  * FROM " + TABLE_THONGBAO + " WHERE date = " + toDay;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                ThongBao tb = new ThongBao();
                tb.setId(c.getInt(c.getColumnIndex("id")));
                tb.setNoiDung(c.getString(c.getColumnIndex("noiDung")));
                tb.setLoaiTB(c.getString(c.getColumnIndex("loaiTB")));
                tb.setTinhTrang(c.getInt(c.getColumnIndex("tinhTrang")));
                tb.setDate(c.getString(c.getColumnIndex("date")));

                // adding
                thongBaos.add(tb);
            } while (c.moveToNext());
        }

        return thongBaos;
    }
}
