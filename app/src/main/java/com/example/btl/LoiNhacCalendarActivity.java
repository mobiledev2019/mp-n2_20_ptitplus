package com.example.btl;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class LoiNhacCalendarActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    TextView textNotificationItemCount;
    int mCartItemCount;

    public GregorianCalendar cal_month, cal_month_copy;
    private LoiNhacAdapterGrid loiNhacAdapterGrid;
    private TextView tv_month;
    ArrayList<LoiNhac> arrayLoiNhac;
    int REQUEST_CODE_EDIT = 123;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loi_nhac_calendar);

        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        getSupportActionBar().setTitle("Lời nhắc");
        mTitle.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        actionListener();

        db = new DatabaseHelper(getApplicationContext());
        arrayLoiNhac = new ArrayList<>();

        arrayLoiNhac = (ArrayList<LoiNhac>) db.getAllLoiNhac();

//        dataBase = new DataBase(this, "ptit.sqlite", null, 2);
//
//        Cursor dataLoiNhac = dataBase.GetData("SELECT * FROM LoiNhac");
//        Cursor numberTB = dataBase.GetData("SELECT * FROM ThongBao WHERE tinhTrang = 0");
//        mCartItemCount = numberTB.getCount();
//
//        while (dataLoiNhac.moveToNext()){
//            int id = dataLoiNhac.getInt(0);
//            String noiDung = dataLoiNhac.getString(1);
//            String date = dataLoiNhac.getString(2);
//            int trangThai = dataLoiNhac.getInt(3);
//            arrayLoiNhac.add(new LoiNhac(id, noiDung, date, trangThai));
//        }


        cal_month = (GregorianCalendar) GregorianCalendar.getInstance();
        cal_month_copy = (GregorianCalendar) cal_month.clone();
        loiNhacAdapterGrid = new LoiNhacAdapterGrid(this, cal_month, arrayLoiNhac);

        tv_month = (TextView) findViewById(R.id.tv_month);
        tv_month.setText(android.text.format.DateFormat.format("MMMM yyyy", cal_month));


        ImageButton previous = (ImageButton) findViewById(R.id.ib_prev);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cal_month.get(GregorianCalendar.MONTH) == 4&&cal_month.get(GregorianCalendar.YEAR)==2017) {
                    //cal_month.set((cal_month.get(GregorianCalendar.YEAR) - 1), cal_month.getActualMaximum(GregorianCalendar.MONTH), 1);
                    Toast.makeText(LoiNhacCalendarActivity.this, "Event Detail is available for current session only.", Toast.LENGTH_SHORT).show();
                }
                else {
                    setPreviousMonth();
                    refreshCalendar();
                }


            }
        });
        ImageButton next = (ImageButton) findViewById(R.id.Ib_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cal_month.get(GregorianCalendar.MONTH) == 5&&cal_month.get(GregorianCalendar.YEAR)==2018) {
                    //cal_month.set((cal_month.get(GregorianCalendar.YEAR) + 1), cal_month.getActualMinimum(GregorianCalendar.MONTH), 1);
                    Toast.makeText(LoiNhacCalendarActivity.this, "Event Detail is available for current session only.", Toast.LENGTH_SHORT).show();
                }
                else {
                    setNextMonth();
                    refreshCalendar();
                }
            }
        });

        GridView gridview = (GridView) findViewById(R.id.gv_calendar);
        gridview.setAdapter(loiNhacAdapterGrid);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<LoiNhac> arrayLoiNhacDate = new ArrayList<>();
                int len = arrayLoiNhac.size();
                String selectedGridDate = loiNhacAdapterGrid.day_string.get(position);

                for (int i = 0; i < len; i++) {
                    if (arrayLoiNhac.get(i).getDate().equals(selectedGridDate)) {
                        LoiNhac itemLoiNhac = arrayLoiNhac.get(i);
                        arrayLoiNhacDate.add(itemLoiNhac);
                    }
                }
                Intent intent = new Intent(LoiNhacCalendarActivity.this, LoiNhacActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("arraylist", arrayLoiNhacDate);
                bundle.putString("date", selectedGridDate);

                //gui mang loi nhac sang cho LoiNhacActivity de hien thi detail
                intent.putExtra("data", bundle);
                startActivityForResult(intent, REQUEST_CODE_EDIT);


            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_EDIT && resultCode == RESULT_OK && data !=null) {
            Bundle bundle = data.getBundleExtra("dataAdd");
            ArrayList<LoiNhac> arrayadd = new ArrayList<>();
            arrayadd = bundle.getParcelableArrayList("listAdd");
            arrayLoiNhac.clear();
            for (int i=0; i<arrayadd.size(); i++){
                LoiNhac ln = arrayadd.get(i);
                arrayLoiNhac.add(ln);
            }
            loiNhacAdapterGrid.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    protected void setNextMonth() {
        if (cal_month.get(GregorianCalendar.MONTH) == cal_month.getActualMaximum(GregorianCalendar.MONTH)) {
            cal_month.set((cal_month.get(GregorianCalendar.YEAR) + 1), cal_month.getActualMinimum(GregorianCalendar.MONTH), 1);
        } else {
            cal_month.set(GregorianCalendar.MONTH,
                    cal_month.get(GregorianCalendar.MONTH) + 1);
        }
    }

    protected void setPreviousMonth() {
        if (cal_month.get(GregorianCalendar.MONTH) == cal_month.getActualMinimum(GregorianCalendar.MONTH)) {
            cal_month.set((cal_month.get(GregorianCalendar.YEAR) - 1), cal_month.getActualMaximum(GregorianCalendar.MONTH), 1);
        } else {
            cal_month.set(GregorianCalendar.MONTH, cal_month.get(GregorianCalendar.MONTH) - 1);
        }
    }

    public void refreshCalendar() {
        loiNhacAdapterGrid.refreshDays();
        loiNhacAdapterGrid.notifyDataSetChanged();
        tv_month.setText(android.text.format.DateFormat.format("MMMM yyyy", cal_month));
    }

    protected void actionListener() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.nav_trangchu :
                        menuItem.setChecked(true);
                        Intent intent_trangChu = new Intent(LoiNhacCalendarActivity.this, TrangChuActivity.class);
                        startActivity(intent_trangChu);
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_tintuc :
                        menuItem.setChecked(true);
                        Intent intent = new Intent(LoiNhacCalendarActivity.this, TinTucActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_diemthi :
                        menuItem.setChecked(true);
                        showMessage("Diem thi");
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_diemtichluy :
                        menuItem.setChecked(true);
                        Intent intent_diem = new Intent(LoiNhacCalendarActivity.this, DiemActivity.class);
                        startActivity(intent_diem);
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_loinhac :
                        menuItem.setChecked(true);
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
                        Intent intent_tkb = new Intent(LoiNhacCalendarActivity.this, ThoiKhoaBieuActivity.class);
                        startActivity(intent_tkb);
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
            case R.id.action_notification:
                mCartItemCount = 0;
                setupBadge();
                Intent intent_thongbao = new Intent(LoiNhacCalendarActivity.this, ThongBaoActivity.class);
                startActivity(intent_thongbao);
                return true;
            case R.id.menuSetting:
                Intent intent = new Intent(LoiNhacCalendarActivity.this, SettingActivity.class);
                startActivity(intent);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);

        final MenuItem menuItem = menu.findItem(R.id.action_notification);

        View actionView = MenuItemCompat.getActionView(menuItem);
        textNotificationItemCount = (TextView) actionView.findViewById(R.id.notification_badge);

        setupBadge();

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOptionsItemSelected(menuItem);
            }
        });

        return true;
    }


    private void setupBadge() {

        if (textNotificationItemCount != null) {
            if (mCartItemCount == 0) {
                if (textNotificationItemCount.getVisibility() != View.GONE) {
                    textNotificationItemCount.setVisibility(View.GONE);
                }
            } else {
                textNotificationItemCount.setText(String.valueOf(Math.min(mCartItemCount, 99)));
                if (textNotificationItemCount.getVisibility() != View.VISIBLE) {
                    textNotificationItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    protected void onRestart() {
//        arrayLoiNhac = new ArrayList<>();
//        Cursor dataLoiNhac = dataBase.GetData("SELECT * FROM LoiNhac");
//
//        while (dataLoiNhac.moveToNext()){
//            int id = dataLoiNhac.getInt(0);
//            String noiDung = dataLoiNhac.getString(1);
//            String date = dataLoiNhac.getString(2);
//            int trangThai = dataLoiNhac.getInt(3);
//            arrayLoiNhac.add(new LoiNhac(id, noiDung, date, trangThai));
//        }
//        loiNhacAdapterGrid.notifyDataSetChanged();
        super.onRestart();
    }
}
