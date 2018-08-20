package com.xingen.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.xingen.devicelibrary.DeviceHelper;

import java.util.regex.Pattern;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DeviceHelper.init(getApplicationContext());
        getImeiMessage();
    }

    private void getImeiMessage() {
        TextView textView = findViewById(R.id.main_imei_tv);
        String imei = DeviceHelper.getImei();
        boolean result = Pattern.matches("^[0-9]*$", imei);
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append(" 设备的Imei是 ：" );
        stringBuffer.append(imei);
        stringBuffer.append("\n");
        stringBuffer.append(" 是否是正确 ： ");
        stringBuffer.append(result);
        textView.setText(stringBuffer);
    }
}
