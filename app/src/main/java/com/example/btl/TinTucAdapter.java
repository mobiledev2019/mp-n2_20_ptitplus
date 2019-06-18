package com.example.btl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TinTucAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<TinTuc> tinTucList;

    public TinTucAdapter(Context context, int layout, List<TinTuc> tinTucList) {
        this.context = context;
        this.layout = layout;
        this.tinTucList = tinTucList;
    }

    @Override
    public int getCount() {
        return tinTucList.size();
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

        TextView txtTieuDe = (TextView) convertView.findViewById(R.id.textViewTinTuc);
        ImageView hinhAnh = (ImageView) convertView.findViewById(R.id.imageViewTinTuc);


        TinTuc tinTuc = tinTucList.get(position);

        txtTieuDe.setText(tinTuc.getTieuDe());
        hinhAnh.setImageResource(tinTuc.getHinhAnh());

        return convertView;
    }
}
