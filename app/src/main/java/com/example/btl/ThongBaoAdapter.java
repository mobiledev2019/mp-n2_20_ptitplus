package com.example.btl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ThongBaoAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    List<ThongBao> listThongBao;

    public ThongBaoAdapter(Context context, int layout, List<ThongBao> listThongBao) {
        this.context = context;
        this.layout = layout;
        this.listThongBao = listThongBao;
    }

    @Override
    public int getCount() {
        return listThongBao.size();
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

        TextView tvThongBao = (TextView) convertView.findViewById(R.id.tvThongBao);
        TextView tvThongBaoDate = (TextView) convertView.findViewById(R.id.tvThongBaoDate);

        ThongBao tb = listThongBao.get(position);


        tvThongBao.setText(tb.getNoiDung());
        tvThongBaoDate.setText(tb.getDate());

        setEventView(convertView, position);

        return convertView;
    }

    public void setEventView(View v,int pos){
        if (listThongBao.get(pos).getTinhTrang() == 0) {
            v.setBackgroundResource(R.drawable.custom_tb_item);
        }
    }
}
