package com.example.dailycareplus

import android.content.Intent
import android.graphics.Color.TRANSPARENT
import android.graphics.Color.parseColor
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import org.w3c.dom.Text
import java.util.*

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

    var userTemp : String = "36.5"

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


        //온도 lED 인디케이터 색상 변화
        /*
        var tempint : Int = userTemp.toInt()
        if(tempint<30.0) {
            tempLedInfo.setBackgroundColor(getColor(R.color.IndicatorG))
        }else if(tempint<37.0){
            tempLedInfo.setBackgroundColor(getColor(R.color.IndicatorO))
        }else if(tempint<37.5){
            tempLedInfo.setBackgroundColor(getColor(R.color.IndicatorR))
        }

         */





    }

    override fun onStart() {
        super.onStart()

        onResume()
    }

    override fun onResume() {
        super.onResume()


        tempText.text = temp+"°"+"C"

        Handler(Looper.getMainLooper()).postDelayed({
            getextra()
        }, 500)

        /*
        if (temp != null) {
            Toast.makeText(this, temp, Toast.LENGTH_SHORT).show()
     }

         */
    }

    override fun onPause() {
        super.onPause()

        onResume()
    }

    fun getextra()
    {
        intent = Intent(this,DeviceControlActivity::class.java)
        val temp=intent.getStringExtra("temp")

        userTemp=temp.toString()

        onResume()
    }


}