package com.yusufsinan.a19mart;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class Accelerometer extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor accelerometer;

    private TextView xValue, yValue, zValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gyroscope);

        xValue = (TextView) findViewById(R.id.textView1);
        yValue = (TextView) findViewById(R.id.textView2);
        zValue = (TextView) findViewById(R.id.textView3);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (sensorManager != null) {
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }

        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            xValue.setText("Ivmeölçer sensörü bulunamadı!");
            yValue.setText("Ivmeölçer sensörü bulunamadı!");
            zValue.setText("Ivmeölçer sensörü bulunamadı!");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        xValue.setText("X: " + x);
        yValue.setText("Y: " + y);
        zValue.setText("Z: " + z);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Bu metodun kullanılmasına gerek yok, ama uygulama içinde bulunmalı
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Uygulama durdurulduğunda sensorun dinlemesinin durdurulması
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Uygulama tekrar çalıştırıldığında sensorun dinlenmeye başlaması
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
}
