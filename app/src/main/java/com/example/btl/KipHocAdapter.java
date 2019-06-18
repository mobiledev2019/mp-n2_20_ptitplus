package com.example.btl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class KipHocAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    List<KipHoc> listKipHoc;

    public KipHocAdapter(Context context, int layout, List<KipHoc> listKipHoc) {
        this.context = context;
        this.layout = layout;
        this.listKipHoc = listKipHoc;
    }

    @Override
    public int getCount() {
        return listKipHoc.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView = inflater.inflate(layout, null);

        TextView tvThoiGian = (TextView) convertView.findViewById(R.id.tvThoiGianHoc);
        TextView tenMH = (TextView) convertView.findViewById(R.id.tvTenMonHoc);
        TextView tvGiaoVien = (TextView) convertView.findViewById(R.id.tvgiangVien);
        TextView tvPhongHoc = (TextView) convertView.findViewById(R.id.tvPhongHoc);

        KipHoc kipHoc = listKipHoc.get(position);

        tvThoiGian.setText(kipHoc.getTime());
        tenMH.setText(kipHoc.getTenMH());
        tvGiaoVien.setText(kipHoc.getTenGV());
        tvPhongHoc.setText(kipHoc.getDiaDiem());

        return convertView;
    }
}
