package com.example.dailycareplus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DPassMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dpass_main);

        TextView textView = findViewById(R.id.btkakaoLogin);

    }

    public void TestMove(View view)
    {
        Intent intent = new Intent(this,btSettingMainMenu.class);
        startActivity(intent);
    }
}