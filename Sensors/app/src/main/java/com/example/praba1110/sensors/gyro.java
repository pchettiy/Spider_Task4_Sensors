package com.example.praba1110.sensors;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;


public class gyro extends ActionBarActivity implements SensorEventListener{
    TextView RX,RY,RZ;
    private SensorManager sensormanager;
    private Sensor sensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyro);
        sensormanager=(SensorManager)getSystemService(SENSOR_SERVICE);
        sensor=sensormanager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        RX= (TextView) findViewById(R.id.RX);
        RY= (TextView) findViewById(R.id.RY);
        RZ= (TextView) findViewById(R.id.RZ);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gyro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Display changes in rotation values
    @Override
    public void onSensorChanged(SensorEvent event) {
        String A= String.valueOf(event.values[0]);
        String B= String.valueOf(event.values[1]);
        String C= String.valueOf(event.values[2]);
        RX.setText("Rate of rotation(x) = "+A+" rad/s");
        RY.setText("Rate of rotation(y) = "+B+" rad/s");
        RZ.setText("Rate of rotation(z) = "+C+" rad/s");
    }



    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        //do nothing
    }
    @Override
    protected void onResume() {
        super.onResume();
        // register this class as a listener
        sensormanager.registerListener(this,
                sensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        sensormanager.unregisterListener(this);
    }
}
