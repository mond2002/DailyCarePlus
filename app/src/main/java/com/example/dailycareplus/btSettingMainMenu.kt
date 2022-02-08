package com.example.dailycareplus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class btSettingMainMenu : AppCompatActivity() {

    //연결 버튼 호출
    lateinit var btConStart: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bt_setting_main_menu)


        btConStart=findViewById(R.id.btConStart)


        btConStart.setOnClickListener(){

            //bt가 활성화 되면 다음 엑티비티로 이동.
            if(bt==true) {
                //다음 Activity 이동
                var intent: Intent = Intent(this, MainMenuActivity::class.java)
                startActivity(intent)
            }else{
            }
        }


    }
}