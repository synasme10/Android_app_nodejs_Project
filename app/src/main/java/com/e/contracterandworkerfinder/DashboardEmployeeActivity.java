package com.e.contracterandworkerfinder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

public class DashboardEmployeeActivity extends AppCompatActivity {
    private float acelerationVal, acelerationLast,shake;
    private SensorManager sensorManager;
    CardView CvEmployeedasboard,Cvprofile,Cvemployeebook,Cvupdatework,Cvupprof,CvLogout,CvLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_employee);

        sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor shakesensor = (Sensor) sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        acelerationVal =SensorManager.GRAVITY_EARTH;
        acelerationLast =SensorManager.GRAVITY_EARTH;
        shake = 0.00f;
        CvEmployeedasboard=findViewById(R.id.CvEmployeedasboard);
        final MediaPlayer mp=MediaPlayer.create(this,R.raw.buttonclick1);

        Cvprofile=findViewById(R.id.Cvprofile);
        Cvemployeebook=findViewById(R.id.Cvemployeebook);
        CvLocation=findViewById(R.id.Cvlocation);
        Cvupprof=findViewById(R.id.Cvupprof);
        Cvupdatework=findViewById(R.id.Cvupdatework);
        CvLogout=findViewById(R.id.CvLogout);

        SensorEventListener shakeEventListener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

                float x = event.values[0];
                float y =event.values[1];
                float z =event.values[2];

                acelerationLast = acelerationVal;
                acelerationVal =(float) Math.sqrt((double)(x*x+y*y+z*z));
                float delta = acelerationVal - acelerationLast;
                shake = shake * 0.9f + delta;

                if (shake >20){

                    SharedPreferences sharedPreferences=getSharedPreferences("Users",MODE_PRIVATE);

                    final String firstname=sharedPreferences.getString("firstname","");
                    final String lastname=sharedPreferences.getString("lastname","");

                    SharedPreferences sharedPreferences1=getSharedPreferences("Users",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences1.edit();
                    editor.putString("id","");
                    editor.putString("token","");
                    editor.putString("usertype","");
                    editor.commit();

                    Intent intent=new Intent(DashboardEmployeeActivity.this,LoginActivity.class);
                    startActivity(intent);

                    Toast.makeText(DashboardEmployeeActivity.this, "Thank You: "+firstname +" "+ lastname , Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        if (shakesensor!=null){
            sensorManager.registerListener(shakeEventListener, shakesensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            Toast.makeText(this, "No Sensor Found",Toast.LENGTH_SHORT).show();
        }


        SharedPreferences sharedPreferences=getSharedPreferences("Users",MODE_PRIVATE);

        final String usertype=sharedPreferences.getString("usertype","");
        final String id=sharedPreferences.getString("id","");
        final String token=sharedPreferences.getString("token","");

        if(usertype.equals("Hirer") || token.equals(" ") || Integer.parseInt(id)<0){
            Intent intent=new Intent(DashboardEmployeeActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }


        CvEmployeedasboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent=new Intent(DashboardEmployeeActivity.this,WorkerActivity.class);
                startActivity(intent);

            }
        });

        Cvprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent=new Intent(DashboardEmployeeActivity.this,EmployeeProfileActivity.class);
                startActivity(intent);

            }
        });

        Cvemployeebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent=new Intent(DashboardEmployeeActivity.this, EmployeeBookDetailActivity.class);
                startActivity(intent);

            }
        });

        Cvupprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent=new Intent(DashboardEmployeeActivity.this, UpdateUserActivity.class);
                startActivity(intent);

            }
        });

        CvLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();

                Intent intent=new Intent(DashboardEmployeeActivity.this,Location.class);
                startActivity(intent);

            }
        });

        Cvupdatework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();

                Intent intent=new Intent(DashboardEmployeeActivity.this,UpdateEmployeeDetailActivity.class);
                startActivity(intent);

            }
        });

        CvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                SharedPreferences sharedPreferences=getSharedPreferences("Users",MODE_PRIVATE);

                final String firstname=sharedPreferences.getString("firstname","");
                final String lastname=sharedPreferences.getString("lastname","");

                SharedPreferences sharedPreferences1=getSharedPreferences("Users",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences1.edit();
                editor.putString("id","");
                editor.putString("token","");
                editor.putString("usertype","");
                editor.commit();

                Intent intent=new Intent(DashboardEmployeeActivity.this,LoginActivity.class);
                startActivity(intent);

                Toast.makeText(DashboardEmployeeActivity.this, "Thank You: "+firstname +" "+ lastname , Toast.LENGTH_SHORT).show();

            }
        });
    }
}
