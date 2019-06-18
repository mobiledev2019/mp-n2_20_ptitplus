package com.example.btl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class DiemAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    List<Diem> listDiem;

    public DiemAdapter(Context context, int layout, List<Diem> listDiem) {
        this.context = context;
        this.layout = layout;
        this.listDiem = listDiem;
    }

    @Override
    public int getCount() {
        return listDiem.size();
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

        TextView tvMaMH = (TextView) convertView.findViewById(R.id.tvMaMonHoc);
        TextView tenMH = (TextView) convertView.findViewById(R.id.tvTenMH);
        TextView tvdiem = (TextView) convertView.findViewById(R.id.tvDiem);
        TextView xepLoai = (TextView) convertView.findViewById(R.id.tvXepLoai);
        TextView soTC = (TextView)  convertView.findViewById(R.id.tvSoTC);

        Diem diem = listDiem.get(position);

        tvMaMH.setText(diem.getMaMH());
        tenMH.setText(diem.getTenMH());
        tvdiem.setText(diem.getDiem());
        xepLoai.setText(diem.getXepLoai());
        soTC.setText(diem.getSotc()+"");

        return convertView;
    }
}
