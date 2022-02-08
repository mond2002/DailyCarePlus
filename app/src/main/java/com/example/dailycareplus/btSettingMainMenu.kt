package com.example.dailycareplus

import android.annotation.SuppressLint
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

class btSettingMainMenu : AppCompatActivity() {

    //연결 버튼 호출
    lateinit var btConStart: View

    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult -> if (result.resultCode == Activity.RESULT_OK) {
        val intent = result.data
        }
    }

    val btm: BluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager;
    val bta: BluetoothAdapter = btm.getAdapter();
    @SuppressLint("MissingPermission")
    val pairedDevices: Set<BluetoothDevice>? = bta.bondedDevices;

    val REQUEST_ENABLE_BT = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bt_setting_main_menu)


        btConStart = findViewById(R.id.btConStart)

        if(bta?.isEnabled == false)
        {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startForResult.launch(enableBtIntent,REQUEST_ENABLE_BT)
        }

        val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result: ActivityResult -> if (result.resultCode == Activity.RESULT_OK) {
                    val intent = result.data
                }
        }



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