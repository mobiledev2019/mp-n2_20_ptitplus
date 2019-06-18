package com.example.btl;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class LoiNhacActivity extends AppCompatActivity {

    private Toolbar toolbar;
    ListView lvLoiNhac;
    ArrayList<LoiNhac> arrayLoiNhac;
    ArrayList<LoiNhac> arrayLNAdd;
    LoiNhacAdapter adapter;
    Button btnaddLoiNhac;
    String dateSelected;
    EditText edtNoiDungLoiNhac;
    TextView txtNothing;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loi_nhac);

        btnaddLoiNhac = (Button) findViewById(R.id.btAddNotif);

        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        getSupportActionBar().setTitle("Lời nhắc");
        mTitle.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);



        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");

        lvLoiNhac = (ListView) findViewById(R.id.lvLoiNhac);
        arrayLoiNhac = new ArrayList<>();
        arrayLNAdd = new ArrayList<>();

        arrayLoiNhac = bundle.getParcelableArrayList("arraylist");
        dateSelected = bundle.getString("date");

        TextView showDate = (TextView) findViewById(R.id.showDate);
        showDate.setText(dateSelected);

        btnaddLoiNhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAddLN();

            }
        });

        adapter = new LoiNhacAdapter(LoiNhacActivity.this, R.layout.item_list_loi_nhac, arrayLoiNhac);
        lvLoiNhac.setAdapter(adapter);
        lvLoiNhac.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                PopupMenu popupMenu = new PopupMenu(LoiNhacActivity.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu());
                popupMenu.show();

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.itemSua:
                                final Dialog dialog = new Dialog(LoiNhacActivity.this);
                                dialog.setContentView(R.layout.custom_dialog);
                                edtNoiDungLoiNhac = (EditText) dialog.findViewById(R.id.edtNoiDung);
                                edtNoiDungLoiNhac.setText(arrayLoiNhac.get(position).getNoiDung());

                                Button btnadd = (Button) dialog.findViewById(R.id.btnadd);
                                Button btncancle = (Button) dialog.findViewById(R.id.btncancle);
                                btnadd.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        String noiDungLoiNhac = edtNoiDungLoiNhac.getText().toString();
                                        arrayLoiNhac.get(position).setNoiDung(noiDungLoiNhac);
                                        adapter.notifyDataSetChanged();
                                        dialog.dismiss();

                                    }
                                });
                                btncancle.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                    }
                                });

                                dialog.show();
                                break;
                            case R.id.itemXoa:

                                arrayLoiNhac.remove(position);
                                adapter.notifyDataSetChanged();
                                break;
                        }
                        return false;
                    }
                });

                return false;
            }
        });
        if (arrayLoiNhac.size()>0) {
        }
        else {
            txtNothing = (TextView) findViewById(R.id.txtnothing);
            txtNothing.setText("Bạn không có lời nhắc nào. Để lại lời nhắc!");
            txtNothing.setVisibility(View.VISIBLE);
        }



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("listAdd", arrayLNAdd);
                intent.putExtra("dataAdd", bundle);
                setResult(RESULT_OK, intent);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void DialogAddLN() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);
        edtNoiDungLoiNhac = (EditText) dialog.findViewById(R.id.edtNoiDung);
        Button btnadd = (Button) dialog.findViewById(R.id.btnadd);
        Button btncancle = (Button) dialog.findViewById(R.id.btncancle);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noiDungLoiNhac = edtNoiDungLoiNhac.getText().toString();
                LoiNhac ln = new LoiNhac(noiDungLoiNhac, dateSelected, 0);
                arrayLoiNhac.add(ln);
                arrayLNAdd.add(ln);
                adapter.notifyDataSetChanged();
                dialog.dismiss();

            }
        });
        btncancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
}
