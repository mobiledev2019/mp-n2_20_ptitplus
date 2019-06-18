package com.example.btl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Locale;

public class TrangChuActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    TextView textNotificationItemCount;
    int mCartItemCount = 5;

    ListView lvTKB;
    ListView lvLoiNhac;
    TextView noTKB;
    ArrayList<KipHoc> listKipHoc;
    ArrayList<LoiNhac> listAllLoiNhac;
    ArrayList<LoiNhac> listLoiNhac;
    ArrayList<KipHoc> listKHToday;
    KipHocAdapter adapterKipHoc;
    LoiNhacAdapter adapterLoiNhac;

    String curentDateString;
    DateFormat df;
    private java.util.Calendar month;
    public GregorianCalendar cal_month;
    private GregorianCalendar selectedDate;
    DatabaseHelper db;

    TextView tvTKB;
    String noiDungTKB;
    RelativeLayout layoutLoad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        tvTKB = (TextView) findViewById(R.id.tvTKB);
        layoutLoad = (RelativeLayout) findViewById(R.id.loadingPanel);
        db = new DatabaseHelper(getApplicationContext());

        SharedPreferences sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);

        String name = sharedPreferences.getString("userName", "");

        new ReadJSONObject().execute("http://test1428.herokuapp.com/text_api?last+user+freeform+input="+name);

//        dataBase = new DataBase(this, "ptit.sqlite", null, 5);
//        Cursor numberTB = dataBase.GetData("SELECT * FROM ThongBao WHERE tinhTrang = 0");
//        mCartItemCount = numberTB.getCount();

        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        getSupportActionBar().setTitle("Trang chủ");
        mTitle.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);

        actionListener();

        lvLoiNhac = (ListView) findViewById(R.id.lvLoiNhacToday);
        noTKB = (TextView) findViewById(R.id.noTKB);

        listAllLoiNhac = new ArrayList<>();
        listLoiNhac = new ArrayList<>();



        cal_month = (GregorianCalendar) GregorianCalendar.getInstance();
        month = cal_month;
        selectedDate = (GregorianCalendar) cal_month.clone();
        df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        curentDateString = df.format(selectedDate.getTime());



        listAllLoiNhac = (ArrayList<LoiNhac>) db.getAllLoiNhac();
        for (int i=0; i<listLoiNhac.size(); i++) {
            if (listLoiNhac.get(i).getDate().equals(curentDateString)) {
                listLoiNhac.add(listAllLoiNhac.get(i));
            }

        }


        adapterLoiNhac = new LoiNhacAdapter(this, R.layout.item_list_loi_nhac, listLoiNhac);
        lvLoiNhac.setAdapter(adapterLoiNhac);

    }

    protected void actionListener() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.nav_trangchu :
                        menuItem.setChecked(true);
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_tintuc :
                        menuItem.setChecked(true);
                        Intent intent_tin_tuc = new Intent(TrangChuActivity.this, TinTucActivity.class);
                        startActivity(intent_tin_tuc);
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_diemthi :
                        menuItem.setChecked(true);
                        showMessage("Diem thi");
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_diemtichluy :
                        menuItem.setChecked(true);
                        Intent intent_diem = new Intent(TrangChuActivity.this, DiemActivity.class);
                        startActivity(intent_diem);
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_loinhac :
                        menuItem.setChecked(true);
                        Intent intent = new Intent(TrangChuActivity.this, LoiNhacCalendarActivity.class);
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
                        SharedPreferences sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
                        Intent i = new Intent(TrangChuActivity.this,MainActivity.class);
                        startActivity(i);
                        sharedPreferences.edit().putBoolean("logged", false).apply();
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
            case R.id.action_notification:
                mCartItemCount = 0;
                setupBadge();
                Intent intent_thongbao = new Intent(TrangChuActivity.this, ThongBaoActivity.class);
                startActivity(intent_thongbao);
                return true;
            case R.id.menuSetting:
                Intent intent = new Intent(TrangChuActivity.this, SettingActivity.class);
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

    private class ReadJSONObject extends AsyncTask<String, Void, String> {

        StringBuilder content = new StringBuilder();
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);

                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }

                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject jsonObject = new JSONObject(s);
                if(jsonObject.length()<2) {
                    noTKB.setText("Bạn không có lịch học hôm nay.");
                    layoutLoad.setVisibility(View.GONE);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }



        }
    }
}

