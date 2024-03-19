package com.yusufsinan.a19mart;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class Proximity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor proximitySensor;
    private TextView proximityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gyroscope);

        TextView textView1 = findViewById(R.id.textView2);
        TextView textView3 = findViewById(R.id.textView3);

        textView3.setText("");
        textView1.setText("");

        // Sensör yöneticisini al
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Yakınlık sensörünü al
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        // Yakınlık değerlerini gösterecek TextView'i al
        proximityTextView = findViewById(R.id.textView1);

        if (proximitySensor == null) {
            // Cihaz bu sensörü desteklemiyorsa uygun bir mesaj göster
            proximityTextView.setText("Yakınlık sensörü bulunamadı");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Sensör dinleyicisini kaydet
        sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Sensör dinleyicisini durdur
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Yakınlık sensöründen gelen değerler
        float proximityValue = event.values[0];

        // Yakınlık değerlerini TextView'e yaz
        proximityTextView.setText("Yakınlık Değeri: " + proximityValue);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Sensör doğruluğu değiştiğinde gerekli işlemleri yapabilirsiniz, ancak burada bir şey yapmıyoruz.
    }
}
