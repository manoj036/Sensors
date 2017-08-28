package com.example.manoj.sensors;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor light_sensor,accel_sensor,magn_sensor,prox_sensor;
    private TextView light,accel,magn,prox;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        light = (TextView)findViewById(R.id.light);
        prox = (TextView)findViewById(R.id.proximity);
        accel = (TextView)findViewById(R.id.accelerometer);
        magn = (TextView)findViewById(R.id.magnetic);
        button= (Button)findViewById(R.id.fab);
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        light_sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        prox_sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        accel_sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magn_sensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        if (light_sensor!= null){
            // Success! There's a magnetometer.
            sensorManager.registerListener(this,light_sensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            // Failure! No magnetometer.
        }
        if (prox_sensor != null){
            sensorManager.registerListener(this,prox_sensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
        }
        if (accel_sensor != null){
            sensorManager.registerListener(this,accel_sensor,SensorManager.SENSOR_DELAY_NORMAL);
           }
        else {
        }
        if (magn_sensor != null){
            sensorManager.registerListener(this,magn_sensor,SensorManager.SENSOR_DELAY_NORMAL);
         }
        else {
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,SecondActivity.class);
                startActivity(i);
            }
        });
    }



    @Override
    public void onSensorChanged(SensorEvent event){
        float value;
        Sensor sensor = event.sensor;
        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            value= (float) Math.sqrt(event.values[0]*event.values[0]+event.values[1]*event.values[1]+event.values[2]);
            accel.setText("Accelerometer :"+value);
        }
        else if (sensor.getType() == Sensor.TYPE_LIGHT){
            light.setText("LUX :"+event.values[0]);
        }
        else if (sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            value= (float) Math.sqrt(event.values[0]*event.values[0]+event.values[1]*event.values[1]+event.values[2]);
            magn.setText("Accelerometer :"+value);
        }
        else if (sensor.getType() == Sensor.TYPE_PROXIMITY){
            prox.setText("Proximity :"+event.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}

