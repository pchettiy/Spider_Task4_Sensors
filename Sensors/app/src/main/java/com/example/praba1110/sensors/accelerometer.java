package com.example.praba1110.sensors;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class accelerometer extends Activity implements SensorEventListener {

    private SensorManager sensorManager;
    private long lastUpdate;
    MediaPlayer mp;
    TextView text,AX,AY,AZ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        text= (TextView) findViewById(R.id.textView);
        AX= (TextView) findViewById(R.id.AX);
        AY= (TextView) findViewById(R.id.AY);
        AZ= (TextView) findViewById(R.id.AZ);
        AX.setTextColor(Color.BLACK);
        AY.setTextColor(Color.BLACK);
        AZ.setTextColor(Color.BLACK);
        text.setTextColor(Color.RED);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lastUpdate = System.currentTimeMillis();
        mp=MediaPlayer.create(this, R.raw.whip);       //Initializing whip sound
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event);
        }

    }

    private void getAccelerometer(SensorEvent event) {
        float[] values = event.values;
        // Movement
        float x = values[0];
        float y = values[1];
        float z = values[2];
        AX.setText("A(x) = "+values[0]);
        AY.setText("A(y) = "+values[1]);
        AZ.setText("A(z) = "+values[2]);
        float accelationSquareRoot = (x * x + y * y + z * z)
                / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
        long actualTime = event.timestamp;
        if (accelationSquareRoot >= 2) //
        {
            if (actualTime - lastUpdate < 200) {
                return;
            }
            lastUpdate = actualTime;
            Toast.makeText(this, "Device shook!", Toast.LENGTH_LONG).show();
            mp.start();     //play whip sound if shook

        }

    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //do nothing
    }
    @Override
    protected void onResume() {
        super.onResume();
        // register this class as a listener
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}



