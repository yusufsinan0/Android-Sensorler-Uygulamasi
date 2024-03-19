package com.yusufsinan.a19mart;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GravitySensor extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor gravitySensor;

    private TextView xValue, yValue, zValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gyroscope);

        // TextView'lerin tanımlanması
        xValue = (TextView) findViewById(R.id.textView1);
        yValue = (TextView) findViewById(R.id.textView2);
        zValue = (TextView) findViewById(R.id.textView3);
        // Sensör yöneticisini al
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Yerçekimi sensörünü al
        gravitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);

        if (gravitySensor == null) {
            // Cihaz bu sensörü desteklemiyorsa uygun bir mesaj göster
            // veya alternatif bir yol sunabilirsiniz.
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Sensör dinleyicisini kaydet
        sensorManager.registerListener(this, gravitySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Sensör dinleyicisini durdur
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Yerçekimi sensöründen gelen değerler
        float gravityX = event.values[0];
        float gravityY = event.values[1];
        float gravityZ = event.values[2];

        // TextView'lerde gösterilmesi
        xValue.setText("X: " + gravityX);
        yValue.setText("Y: " + gravityY);
        zValue.setText("Z: " + gravityZ);

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Sensör doğruluğu değiştiğinde gerekli işlemleri yapabilirsiniz, ancak burada bir şey yapmıyoruz.
    }
}
