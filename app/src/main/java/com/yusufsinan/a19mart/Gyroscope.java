package com.yusufsinan.a19mart;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Gyroscope extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor gyroscopeSensor;
    private TextView gyroXValue, gyroYValue, gyroZValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gyroscope);

        // TextView'lerin tanımlanması
        gyroXValue = findViewById(R.id.textView1);
        gyroYValue = findViewById(R.id.textView2);
        gyroZValue = findViewById(R.id.textView3);

        // SensorManager oluşturulması
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Jiroskop sensörünün alınması
        if (sensorManager != null) {
            gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        }

        // Jiroskop sensörünün kullanılabilir olup olmadığının kontrolü
        if (gyroscopeSensor != null) {
            sensorManager.registerListener(this, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            gyroXValue.setText("Gyroscope sensor not found!");
            gyroYValue.setText("Gyroscope sensor not found!");
            gyroZValue.setText("Gyroscope sensor not found!");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Jiroskop verilerinin alınması
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        // TextView'lerde gösterilmesi
        gyroXValue.setText("X: " + x);
        gyroYValue.setText("Y: " + y);
        gyroZValue.setText("Z: " + z);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    @Override
    protected void onPause() {
        super.onPause();
        // Uygulama durdurulduğunda sensör dinlemesinin durdurulması
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Uygulama tekrar çalıştırıldığında sensör dinlemesinin başlatılması
        if (gyroscopeSensor != null) {
            sensorManager.registerListener(this, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
