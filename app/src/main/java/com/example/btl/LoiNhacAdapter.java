package com.example.btl;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class LoiNhacAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    List<LoiNhac> listLoiNhac;

    public LoiNhacAdapter(Context context, int layout, List<LoiNhac> listLoiNhac) {
        this.context = context;
        this.layout = layout;
        this.listLoiNhac = listLoiNhac;
    }

    @Override
    public int getCount() {
        return listLoiNhac.size();
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

        TextView txtNoiDung = (TextView) convertView.findViewById(R.id.textLoiNhac);

        LoiNhac loiNhac = listLoiNhac.get(position);

        txtNoiDung.setText(loiNhac.getNoiDung());

        return convertView;
    }
}
