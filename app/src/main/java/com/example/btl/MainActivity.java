package com.example.btl;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    Button btDN;
    EditText edtUsername;
    EditText edtPass;

    SharedPreferences sharedPreferences;

    private Toolbar toolbar;

    String curentDateString;
    DateFormat df;
    String userName;
    String pass;
    private java.util.Calendar month;
    public GregorianCalendar cal_month;
    private GregorianCalendar selectedDate;
    private boolean dnthanhcong = false;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        getSupportActionBar().setTitle("Đăng nhập");
        mTitle.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        edtUsername = (EditText) findViewById(R.id.editTextUsername);
        edtPass = (EditText) findViewById(R.id.editTextPass);
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        btDN = (Button) findViewById(R.id.buttonDN);


        if(sharedPreferences.getBoolean("logged",false)){
            goToActivity();
        }
        btDN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = edtUsername.getText().toString();
                pass = edtPass.getText().toString();
                progressBar.setVisibility(View.VISIBLE);
                new ReadJSONObject().execute("http://test1428.herokuapp.com/text_api_point_report?username="+userName+"&password="+ pass);
            }
        });
    }
    public void goToActivity(){
        Intent i = new Intent(MainActivity.this,DiemActivity.class);
        startActivity(i);
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
            if(s.length()>2) {
                dnthanhcong = true;
            }
            if(dnthanhcong) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String userName = edtUsername.getText().toString();
                String pass = edtPass.getText().toString();
                editor.putString("userName", userName);
                editor.putString("password", pass);
                goToActivity();
                dnthanhcong = false;
                progressBar.setVisibility(View.GONE);
                editor.putBoolean("logged", true).apply();
            } else {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Tài khoản của bạn không đúng!", Toast.LENGTH_LONG).show();
            }
            super.onPostExecute(s);
        }
    }
}
