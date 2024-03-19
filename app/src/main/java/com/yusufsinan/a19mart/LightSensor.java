package com.yusufsinan.a19mart;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class LightSensor extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor lightSensor;
    private TextView lightValueTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gyroscope);

        lightValueTextView = findViewById(R.id.textView2);
        TextView textView1 = findViewById(R.id.textView1);
        TextView textView3 = findViewById(R.id.textView3);

        textView3.setText("");
        textView1.setText("");

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        // Işık sensörünün kullanılabilir olup olmadığının kontrolü
        if (lightSensor != null) {
            sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            lightValueTextView.setText("Light sensor not found!");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Işık değerinin alınması
        float lightValue = event.values[0];

        // TextView'lerde gösterilmesi
        lightValueTextView.setText("Işık değeri : " + lightValue + " lux");
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
        if (lightSensor != null) {
            sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }
}
