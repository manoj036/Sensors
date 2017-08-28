package com.example.manoj.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements SensorEventListener {

    GraphView graphView;

    private List<Float> ld,ad,md,pd;
    private SensorManager sensorManager;
    int a,b,c,d;
    private LineGraphSeries<DataPoint> prox_data,light_data,accel_data,magnet_data;
    private Sensor light_sensor,accel_sensor,magn_sensor,prox_sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ld = new ArrayList<Float>();
        md = new ArrayList<Float>();
        pd = new ArrayList<Float>();
        ad = new ArrayList<Float>();
        graphView = (GraphView)findViewById(R.id.graph);
        setSupportActionBar(toolbar);

        graphView.getViewport().setScalable(true);
        graphView.getViewport().setScrollable(true);
        graphView.getViewport().setMinX(0);
        graphView.getViewport().setMaxX(100);
        graphView.getLegendRenderer().setVisible(true);
        graphView.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graphView.getLegendRenderer().setWidth(300);
        accel_data=new LineGraphSeries<>();
        prox_data=new LineGraphSeries<>();
        magnet_data=new LineGraphSeries<>();
        light_data=new LineGraphSeries<>();
        graphView.addSeries(accel_data);
        accel_data.setTitle("Accelerometer Data");
        graphView.addSeries(magnet_data);
        magnet_data.setTitle("Magnetometer Data");
        graphView.addSeries(prox_data);
        prox_data.setTitle("Proximity Data");
        graphView.addSeries(light_data);
        light_data.setTitle("LUX Data");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        light_sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        prox_sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        accel_sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magn_sensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        if (light_sensor!= null){
            // Success! There's a magnetometer.
            sensorManager.registerListener((SensorEventListener) this,light_sensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            // Failure! No magnetometer.
        }
        if (prox_sensor != null){
            sensorManager.registerListener((SensorEventListener) this,prox_sensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {

        }
        if (accel_sensor != null){
            sensorManager.registerListener((SensorEventListener) this,accel_sensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
        }
        if (magn_sensor != null){
            sensorManager.registerListener((SensorEventListener) this,magn_sensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
        }
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        float value;
        Sensor sensor = event.sensor;
        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            value= (float) Math.sqrt(event.values[0]*event.values[0]+event.values[1]*event.values[1]+event.values[2]);
           ad.add(value);
            accel_data.appendData(new DataPoint(a,b),true,1000);
            a=a+1;
        }
        else if (sensor.getType() == Sensor.TYPE_LIGHT){
            ld.add(event.values[0]);
            light_data.appendData(new DataPoint(a,b),true,1000);
            a=a+1;
        }
        else if (sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            value= (float) Math.sqrt(event.values[0]*event.values[0]+event.values[1]*event.values[1]+event.values[2]);
            md.add(value);
            magnet_data.appendData(new DataPoint(a,b),true,1000);
            a=a+1;
        }
        else if (sensor.getType() == Sensor.TYPE_PROXIMITY){
            pd.add(event.values[0]);
            prox_data.appendData(new DataPoint(a,b),true,1000);
            a=a+1;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
