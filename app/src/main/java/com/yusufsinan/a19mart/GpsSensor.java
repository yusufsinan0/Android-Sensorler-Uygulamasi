package com.yusufsinan.a19mart;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class GpsSensor extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor rotationVectorSensor;
    private TextView rotationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gyroscope);

        TextView textView1 = findViewById(R.id.textView1);
        TextView textView3 = findViewById(R.id.textView3);

        textView3.setText("");
        textView1.setText("");

        rotationTextView = findViewById(R.id.textView2);

        // Sensör yöneticisini al
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // Dönme vektörü sensörünü al
        rotationVectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        if (rotationVectorSensor == null) {
            // Cihaz bu sensörü desteklemiyorsa uygun bir mesaj göster
            rotationTextView.setText("Dönme vektörü sensörü bulunamadı");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Sensör dinleyicisini kaydet
        sensorManager.registerListener(this, rotationVectorSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Sensör dinleyicisini durdur
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Dönme vektörü sensöründen gelen değerler
        float[] rotationMatrix = new float[9];
        SensorManager.getRotationMatrixFromVector(rotationMatrix, event.values);
        float[] orientationValues = new float[3];
        SensorManager.getOrientation(rotationMatrix, orientationValues);

        // Radyan cinsinden dönme açısını dereceye çevir
        float rotationDegrees = (float) Math.toDegrees(orientationValues[0]);

        // Dönme açısını TextView'e yaz
        rotationTextView.setText("Dönme Açısı: " + rotationDegrees);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Sensör doğruluğu değiştiğinde gerekli işlemleri yapabilirsiniz, ancak burada bir şey yapmıyoruz.
    }
}
