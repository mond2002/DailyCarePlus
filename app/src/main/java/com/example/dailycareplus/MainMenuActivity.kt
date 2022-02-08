package com.example.dailycareplus

import android.graphics.Color.TRANSPARENT
import android.graphics.Color.parseColor
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import org.w3c.dom.Text

class MainMenuActivity : AppCompatActivity() {

    // 네트워크 iP
    lateinit var btAdd : TextView
    // 사용자 이름
    lateinit var userName : TextView
    // 접종 정보
    lateinit var vaccineInfo : TextView
    // 접종 날짜
    lateinit var vaccineDayInfo : TextView
    // Dpass 연결 정보
    lateinit var dpassInfo : TextView
    // 체온 정보
    lateinit var tempText : TextView
    // 익일 감염자 수
    lateinit var impeInfo : TextView
    // 마지막 방문 장소
    lateinit var locaInfo : TextView
    // 체온 인디케이터
    lateinit var tempLedInfo : View

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)

        //각 UI 호출----------------------------------------------------------------------------------
        btAdd = findViewById(R.id.btAdd)
        userName = findViewById(R.id.UserName)
        vaccineInfo = findViewById(R.id.vaccineInfo)
        vaccineDayInfo = findViewById(R.id.vaccineDayInfo)
        dpassInfo = findViewById(R.id.dpassInfo)
        tempText = findViewById(R.id.tempText)
        impeInfo = findViewById(R.id.impeInfo)
        locaInfo = findViewById(R.id.locaInfo)
        tempLedInfo = findViewById(R.id.tempLedInfo)

        //체온 부분-----------------------------------------------------------------------------------
        //usetTemp = 실시간 온도 정보
        tempText.text = usetTemp

        //온도 lED 인디케이터 색상 변화
        if(userTemp=<30.0) {
            tempLedInfo.setBackgroundColor(getColor(R.color.IndicatorG))
        }else if(userTemp=<37.0){
            tempLedInfo.setBackgroundColor(getColor(R.color.IndicatorO))
        }else if(userTemp=<37.5){
            tempLedInfo.setBackgroundColor(getColor(R.color.IndicatorR))
        }



    }
}