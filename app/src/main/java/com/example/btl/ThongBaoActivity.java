package com.example.btl;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Locale;

public class ThongBaoActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    ListView lvThongBao;
    ArrayList<ThongBao> listThongBao;
    ThongBaoAdapter adapter;
    ArrayList<LoiNhac> listLoiNhac;
    DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_bao);

        db = new DatabaseHelper(getApplicationContext());

        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        getSupportActionBar().setTitle("Thông báo");
        mTitle.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        actionListener();

        lvThongBao = (ListView) findViewById(R.id.lvThongBao);
        listLoiNhac = new ArrayList<>();


//
        adapter = new ThongBaoAdapter(ThongBaoActivity.this, R.layout.item_list_thong_bao, listThongBao );
        lvThongBao.setAdapter(adapter);

        


    }

    protected void actionListener() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.nav_trangchu :
                        menuItem.setChecked(true);
                        showMessage("Trang chu");
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_tintuc :
                        menuItem.setChecked(true);
                        Intent intent_tintuc = new Intent(ThongBaoActivity.this, TinTucActivity.class);
                        startActivity(intent_tintuc);
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_diemthi :
                        menuItem.setChecked(true);
                        showMessage("Diem thi");
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_diemtichluy :
                        menuItem.setChecked(true);
                        showMessage("Diem tich luy");
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_loinhac :
                        menuItem.setChecked(true);
                        Intent intent = new Intent(ThongBaoActivity.this, LoiNhacCalendarActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_bando :
                        menuItem.setChecked(true);
                        showMessage("Ban do");
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_dangxuat :
                        menuItem.setChecked(true);
                        showMessage("Dang xuat");
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_tkb :
                        menuItem.setChecked(true);
                        showMessage("Thoi khoa bieu");
                        drawerLayout.closeDrawers();
                        return true;
                }
                return false;
            }
        });
    }
    protected void showMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

}
