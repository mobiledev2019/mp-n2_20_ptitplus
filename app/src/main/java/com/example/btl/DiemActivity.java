package com.example.btl;

import android.app.Activity;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DiemActivity extends AppCompatActivity {

    ArrayList<Diem> listDiem;
    DiemAdapter adapter;
    ListView lvDiem;

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    TextView textNotificationItemCount;
    int mCartItemCount = 5;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diem);
        String username = getSharedPreferences("dataLogin", MODE_PRIVATE).getString("userName", "");
        String password = getSharedPreferences("dataLogin", MODE_PRIVATE).getString("password", "");
        new ReadJSONObject().execute("http://test1428.herokuapp.com/text_api_point_report?username=" + username + "&password=" + password);

        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        getSupportActionBar().setTitle("Điểm tích luỹ");
        mTitle.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);

        actionListener();

//        listDiem = new ArrayList<>();
//        listDiem.add(new Diem("INT1919", "Cau truc du lieu va giai thuat", "8.0", "B+"));
//        listDiem.add(new Diem("INT1920", "Giai tich", "8.5", "A"));
//        listDiem.add(new Diem("INT2019", "Vat ly 3", "9.0", "A+"));
//        listDiem.add(new Diem("ABS1415", "Tieng anh A.11", "6.0", "C"));
//        listDiem.add(new Diem("ABS2627", "Mac Lenin", "6.5", "C+"));
//        listDiem.add(new Diem("ABS5647", "Tieng anh A.21", "7.0", "B"));
//
//        lvDiem = (ListView) findViewById(R.id.lvDiem);
//
//        adapter = new DiemAdapter(DiemActivity.this, R.layout.item_diem, listDiem);
//        lvDiem.setAdapter(adapter);
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
                        Intent intent_tintuc = new Intent(DiemActivity.this, TinTucActivity.class);
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
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.nav_loinhac :
                        menuItem.setChecked(true);
                        Intent intent = new Intent(DiemActivity.this, LoiNhacCalendarActivity.class);
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
                        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("logged", false).apply();
                        Intent intentLogout = new Intent(DiemActivity.this, MainActivity.class);
                        startActivity(intentLogout);
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
                Intent intent_thongbao = new Intent(DiemActivity.this, ThongBaoActivity.class);
                startActivity(intent_thongbao);
                return true;
            case R.id.menuSetting:
                Intent intent = new Intent(DiemActivity.this, SettingActivity.class);
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
//            Toast.makeText(DiemActivity.this, s, Toast.LENGTH_LONG ).show();
            listDiem = new ArrayList<>();
//            listDiem.add(new Diem("INT1919", "Cau truc du lieu va giai thuat", "8.0", "B+"));
            JSONArray j = null;
            try {
                j = new JSONArray(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            for(int i = j.length() - 1; i >= 0; i--) {
                JSONObject item = null;
                try {
                    item = (JSONObject) j.get(i);
                    JSONArray item_data = (JSONArray) item.get("data");
                    for(int i2 = 0; i2 < item_data.length(); i2++){
                        JSONArray item_detail = (JSONArray)item_data.get(i2);
                        listDiem.add(new Diem(item_detail.getString(1), item_detail.getString(2), item_detail.getString(15), item_detail.getString(16)));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            lvDiem = (ListView) findViewById(R.id.lvDiem);

            adapter = new DiemAdapter(DiemActivity.this, R.layout.item_diem, listDiem);
            lvDiem.setAdapter(adapter);
        }
    }
}
