package com.example.dailycareplus

import android.bluetooth.*
import android.content.*
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Toast


private val TAG = "gattClienCallback"

class DeviceControlActivity(private val context: Context?, private var bluetoothGatt: BluetoothGatt?) {
    private var device : BluetoothDevice? = null
    private val gattCallback : BluetoothGattCallback = object : BluetoothGattCallback(){
        override fun onConnectionStateChange(gatt: BluetoothGatt?, status: Int, newState: Int) {
            super.onConnectionStateChange(gatt, status, newState)
            when (newState) {
                BluetoothProfile.STATE_CONNECTED -> {
                    Log.i(TAG, "Connected to GATT server.")
                    Log.i(TAG, "Attempting to start service discovery: " +
                            bluetoothGatt?.discoverServices())
                }
                BluetoothProfile.STATE_DISCONNECTED -> {
                    Log.i(TAG, "Disconnected from GATT server.")
                    disconnectGattServer()
                }
            }

        }
        override fun onServicesDiscovered(gatt: BluetoothGatt?, status: Int) {
            super.onServicesDiscovered(gatt, status)
            when (status) {
                BluetoothGatt.GATT_SUCCESS -> {
                    Log.i(TAG, "Connected to GATT_SUCCESS.")
                    broadcastUpdate("Connected "+ device?.name)
                }
                else -> {
                    Log.w(TAG, "Device service discovery failed, status: $status")
                    broadcastUpdate("Fail Connect "+device?.name)
                }
            }
        }
        private fun broadcastUpdate(str: String) {
            val mHandler : Handler = object : Handler(Looper.getMainLooper()){
                override fun handleMessage(msg: Message) {
                    super.handleMessage(msg)
                    Toast.makeText(context,str,Toast.LENGTH_SHORT).show()
                }
            }
            mHandler.obtainMessage().sendToTarget()
        }
        private fun disconnectGattServer() {
            Log.d(TAG, "Closing Gatt connection")
            // disconnect and close the gatt
            if (bluetoothGatt != null) {
                bluetoothGatt?.disconnect()
                bluetoothGatt?.close()
                bluetoothGatt = null
            }
        }

        override fun onCharacteristicChanged(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic?
        ) {
            super.onCharacteristicChanged(gatt, characteristic)
            readCharacteristic(characteristic)
        }

        override fun onCharacteristicWrite(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic?,
            status: Int
        ) {
            super.onCharacteristicWrite(gatt, characteristic, status)
            if(status == BluetoothGatt.GATT_SUCCESS)
            {
                Log.d(TAG,"Characteristic written successfully")
            }
            else
            {
                Log.e(TAG,"Characteristic write unsuccessful,status:$status")
                disconnectGattServer()
            }
        }
        override fun onCharacteristicRead(
            gatt: BluetoothGatt?,
            characteristic: BluetoothGattCharacteristic,
            status: Int
        ) {
            super.onCharacteristicRead(gatt, characteristic, status)
            if (status == BluetoothGatt.GATT_SUCCESS) {
                Log.d(TAG, "Characteristic read successfully")
                readCharacteristic(characteristic)
            } else {
                Log.e(TAG, "Characteristic read unsuccessful, status: $status")
                // Trying to read from the Time Characteristic? It doesnt have the property or permissions
                // set to allow this. Normally this would be an error and you would want to:
                // disconnectGattServer();
            }
        }
        private fun readCharacteristic(characteristic: BluetoothGattCharacteristic) {

            val msg = characteristic.getStringValue(0)
            _txtRead = msg
            txtRead.set(_txtRead)
            Log.d(TAG, "read: $msg")
        }
    }

    fun connectGatt(device:BluetoothDevice):BluetoothGatt?{
        this.device = device

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            bluetoothGatt = device.connectGatt(context, false, gattCallback,
                BluetoothDevice.TRANSPORT_LE)
        }
        else {
            bluetoothGatt = device.connectGatt(context, false, gattCallback)
        }
        return bluetoothGatt
    }

}