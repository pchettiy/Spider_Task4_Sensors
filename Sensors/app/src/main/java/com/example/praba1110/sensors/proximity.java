package com.example.praba1110.sensors;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class proximity extends ActionBarActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor;
    RelativeLayout layout;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity);
        layout= (RelativeLayout) findViewById(R.id.Rlayout);
        text= (TextView) findViewById(R.id.textView2);
        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        sensor=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_proximity, menu);
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

    //Change background color on wave
    @Override
    public void onSensorChanged(SensorEvent event) {
    if(event.values[0]==0)
    {
        layout.setBackgroundColor(Color.BLACK);
        text.setTextColor(Color.WHITE);
    }
        else
    {
        layout.setBackgroundColor(Color.WHITE);
        text.setTextColor(Color.BLACK);
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
                sensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}

