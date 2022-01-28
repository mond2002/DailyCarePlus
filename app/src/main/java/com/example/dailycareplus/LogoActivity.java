package com.example.dailycareplus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LogoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        moveMain(3);
}

    private void moveMain(int sec)
    {
     new Handler().postDelayed(new Runnable()
     {
         @Override
         public void run()
             {
                 Intent intent = new Intent(getApplicationContext(),MainActivity.class);

             startActivity(intent);

             finish();
            }
        }, 1000*sec);
    }
 }
