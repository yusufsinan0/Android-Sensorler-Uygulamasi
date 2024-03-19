package com.yusufsinan.a19mart;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TemperatureSensor extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor temperatureSensor;
    private TextView temperatureTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gyroscope);

        TextView textView1 = findViewById(R.id.textView1);
        TextView textView3 = findViewById(R.id.textView3);

        textView3.setText("");
        textView1.setText("");

        // Sensör yöneticisini al
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Sıcaklık sensörünü al
        temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_TEMPERATURE);

        // Sıcaklık değerlerini gösterecek TextView'i al
        temperatureTextView = findViewById(R.id.textView2);

        if (temperatureSensor == null) {
            Toast.makeText(getApplicationContext(),"Cihazda sensör bulunmamakta",Toast.LENGTH_LONG).show();
            temperatureTextView.setText("sensör bulunmamakta");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Sensör dinleyicisini kaydet
        sensorManager.registerListener(this, temperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Sensör dinleyicisini durdur
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Sıcaklık sensöründen gelen değerler
        float temperatureValue = event.values[0];

        // Sıcaklık değerlerini TextView'e yaz
        temperatureTextView.setText("Sıcaklık Değeri: " + temperatureValue + " °C");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Sensör doğruluğu değiştiğinde gerekli işlemleri yapabilirsiniz, ancak burada bir şey yapmıyoruz.
    }
}
