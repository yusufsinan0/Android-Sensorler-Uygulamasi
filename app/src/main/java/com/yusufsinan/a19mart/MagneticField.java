package com.yusufsinan.a19mart;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class MagneticField extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor magneticFieldSensor;
    private TextView magneticXValue, magneticYValue, magneticZValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gyroscope);

        // TextView'lerin tanımlanması
        magneticXValue = findViewById(R.id.textView1);
        magneticYValue = findViewById(R.id.textView2);
        magneticZValue = findViewById(R.id.textView3);

        // SensorManager oluşturulması
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Manyetik alan sensörünün alınması
        if (sensorManager != null) {
            magneticFieldSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        }

        // Manyetik alan sensörünün kullanılabilir olup olmadığının kontrolü
        if (magneticFieldSensor != null) {
            sensorManager.registerListener(this, magneticFieldSensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            magneticXValue.setText("Magnetic field sensor not found!");
            magneticYValue.setText("Magnetic field sensor not found!");
            magneticZValue.setText("Magnetic field sensor not found!");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Manyetik alan verilerinin alınması
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        // TextView'lerde gösterilmesi
        magneticXValue.setText("X: " + x);
        magneticYValue.setText("Y: " + y);
        magneticZValue.setText("Z: " + z);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Bu metodun kullanılmasına gerek yok, ama uygulama içinde bulunmalı
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
        if (magneticFieldSensor != null) {
            sensorManager.registerListener(this, magneticFieldSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
}
