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

    GraphView graphView1,graphView2,graphView3,graphView4;

    private List<Float> ld,ad,md,pd;
    private SensorManager sensorManager;
    int a,b,c,d;
    float d1,d2,d3,d4;
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
        graphView1 = (GraphView)findViewById(R.id.graph1);
        graphView2 = (GraphView)findViewById(R.id.graph2);
        graphView3 = (GraphView)findViewById(R.id.graph3);
        graphView4 = (GraphView)findViewById(R.id.graph4);

        setSupportActionBar(toolbar);

        graphView1.getViewport().setScalable(true);
        graphView1.getViewport().setScrollable(true);
        graphView1.getViewport().setMinX(0);
        graphView1.getViewport().setMaxX(100);
        graphView1.getLegendRenderer().setVisible(true);
        graphView1.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graphView1.getLegendRenderer().setWidth(300);
        graphView2.getViewport().setScalable(true);
        graphView2.getViewport().setScrollable(true);
        graphView2.getViewport().setMinX(0);
        graphView2.getViewport().setMaxX(100);
        graphView2.getLegendRenderer().setVisible(true);
        graphView2.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graphView2.getLegendRenderer().setWidth(300);
        graphView3.getViewport().setScalable(true);
        graphView3.getViewport().setScrollable(true);
        graphView3.getViewport().setMinX(0);
        graphView3.getViewport().setMaxX(100);
        graphView3.getLegendRenderer().setVisible(true);
        graphView3.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graphView3.getLegendRenderer().setWidth(300);
        graphView4.getViewport().setScalable(true);
        graphView4.getViewport().setScrollable(true);
        graphView4.getViewport().setMinX(0);
        graphView4.getViewport().setMaxX(100);
        graphView4.getLegendRenderer().setVisible(true);
        graphView4.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graphView4.getLegendRenderer().setWidth(300);

        accel_data=new LineGraphSeries<>();
        prox_data=new LineGraphSeries<>();
        magnet_data=new LineGraphSeries<>();
        light_data=new LineGraphSeries<>();
        graphView1.addSeries(accel_data);
        accel_data.setTitle("Accelerometer Data");
        graphView2.addSeries(magnet_data);
        magnet_data.setTitle("Magnetometer Data");
        graphView3.addSeries(prox_data);
        prox_data.setTitle("Proximity Data");
        graphView4.addSeries(light_data);
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
        a=a+1;
        Sensor sensor = event.sensor;
        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            d1= (float) Math.sqrt(event.values[0]*event.values[0]+event.values[1]*event.values[1]+event.values[2]);
            ad.add(d1);
            accel_data.appendData(new DataPoint(a,d1),true,1000);
            light_data.appendData(new DataPoint(a,d2),true,1000);
            magnet_data.appendData(new DataPoint(a,d3),true,1000);
            prox_data.appendData(new DataPoint(a,d4),true,1000);
        }
        else if (sensor.getType() == Sensor.TYPE_LIGHT){
            ld.add(event.values[0]);
            d2=event.values[0];
            accel_data.appendData(new DataPoint(a,d1),true,1000);
            light_data.appendData(new DataPoint(a,d2),true,1000);
            magnet_data.appendData(new DataPoint(a,d3),true,1000);
            prox_data.appendData(new DataPoint(a,d4),true,1000);
        }
        else if (sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            d3= (float) Math.sqrt(event.values[0]*event.values[0]+event.values[1]*event.values[1]+event.values[2]*event.values[2]);
            md.add(d3);
            accel_data.appendData(new DataPoint(a,d1),true,1000);
            light_data.appendData(new DataPoint(a,d2),true,1000);
            magnet_data.appendData(new DataPoint(a,d3),true,1000);
            prox_data.appendData(new DataPoint(a,d4),true,1000);
        }
        else if (sensor.getType() == Sensor.TYPE_PROXIMITY){
            d4=event.values[0];
            pd.add(event.values[0]);
            accel_data.appendData(new DataPoint(a,d1),true,1000);
            light_data.appendData(new DataPoint(a,d2),true,1000);
            magnet_data.appendData(new DataPoint(a,d3),true,1000);
            prox_data.appendData(new DataPoint(a,d4),true,1000);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
