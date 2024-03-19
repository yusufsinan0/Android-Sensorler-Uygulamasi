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

public class PressureSensor extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor pressureSensor;
    private TextView pressureTextView;

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

        // Basınç sensörünü al
        pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);

        // Basınç değerlerini gösterecek TextView'i al
        pressureTextView = findViewById(R.id.textView2);

        if (pressureSensor == null) {
            Toast.makeText(getApplicationContext(),"Basunç sensörü bulunmamakta",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Sensör dinleyicisini kaydet
        sensorManager.registerListener(this, pressureSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Sensör dinleyicisini durdur
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Basınç sensöründen gelen değerler
        float pressureValue = event.values[0];

        // Basınç değerlerini TextView'e yaz
        pressureTextView.setText("Basınç Değeri: " + pressureValue + " hPa");
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Sensör doğruluğu değiştiğinde gerekli işlemleri yapabilirsiniz, ancak burada bir şey yapmıyoruz.
    }
}
