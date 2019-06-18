package com.example.btl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
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

public class ThoiKhoaBieuActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    TextView textNotificationItemCount;
    int mCartItemCount;
    TextView txtTKB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thoi_khoa_bieu);

//        dataBase = new DataBase(this, "ptit.sqlite", null, 2);
//        Cursor numberTB = dataBase.GetData("SELECT * FROM ThongBao WHERE tinhTrang = 0");
//        mCartItemCount = numberTB.getCount();

        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        getSupportActionBar().setTitle("Thời khoá biểu");
        mTitle.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);

        actionListener();

        txtTKB = (TextView) findViewById(R.id.txtTKBDay);
        SharedPreferences sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);

        String name = sharedPreferences.getString("userName", "");
        Toast.makeText(ThoiKhoaBieuActivity.this, name, Toast.LENGTH_LONG ).show();


        String url = "http://test1428.herokuapp.com/text_api?last+user+freeform+input=B15DCCN194";

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("messages");
                            JSONObject obTKB = jsonArray.getJSONObject(0);
                            String txt = obTKB.getString("text");
                            txtTKB.setText(txt);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ThoiKhoaBieuActivity.this, "LOI", Toast.LENGTH_LONG).show();
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }

    protected void actionListener() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.nav_trangchu :
                        menuItem.setChecked(true);
                        Intent intent_trangChu = new Intent(ThoiKhoaBieuActivity.this, TrangChuActivity.class);
                        startActivity(intent_trangChu);
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_tintuc :
                        menuItem.setChecked(true);
                        Intent intent_tin_tuc = new Intent(ThoiKhoaBieuActivity.this, TinTucActivity.class);
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
                        Intent intent_diem = new Intent(ThoiKhoaBieuActivity.this, DiemActivity.class);
                        startActivity(intent_diem);
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_loinhac :
                        menuItem.setChecked(true);
                        Intent intent = new Intent(ThoiKhoaBieuActivity.this, LoiNhacCalendarActivity.class);
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
            case R.id.action_notification:
                mCartItemCount = 0;
                setupBadge();
                Intent intent_thongbao = new Intent(ThoiKhoaBieuActivity.this, ThongBaoActivity.class);
                startActivity(intent_thongbao);
                return true;
            case R.id.menuSetting:
                Intent intent = new Intent(ThoiKhoaBieuActivity.this, SettingActivity.class);
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
}
