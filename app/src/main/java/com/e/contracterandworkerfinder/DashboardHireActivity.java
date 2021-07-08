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
import android.widget.Button;
import android.widget.Toast;

public class DashboardHireActivity extends AppCompatActivity {

    private float acelerationVal, acelerationLast,shake;
    private SensorManager sensorManager;
    CardView CvHirerdasboard,CvHirerdetail,Cvlogout,CvFav,CvHirerprofile,CvHirerupdate,CvLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_hire);


        sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor shakesensor = (Sensor) sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        acelerationVal =SensorManager.GRAVITY_EARTH;
        acelerationLast =SensorManager.GRAVITY_EARTH;
        shake = 0.00f;
        CvHirerdasboard=findViewById(R.id.CvHirerdasboard);
        final MediaPlayer mp=MediaPlayer.create(this,R.raw.buttonclick1);

        CvHirerdetail=findViewById(R.id.CvHirerdetail);
        CvHirerprofile=findViewById(R.id.CvHirerprofile);
        CvHirerupdate=findViewById(R.id.CvHirerupdate);
        CvFav=findViewById(R.id.CvFav);
        Cvlogout=findViewById(R.id.Cvlogout);
        CvLocation=findViewById(R.id.Cvlocations);

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
                    final  String tokens=sharedPreferences.getString("token","");

                    final String firstname=sharedPreferences.getString("firstname","");
                    final String lastname=sharedPreferences.getString("lastname","");

                    SharedPreferences sharedPreferences1=getSharedPreferences("Users",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences1.edit();
                    editor.putString("id","");
                    editor.putString("token","");
                    editor.putString("usertype","");
                    editor.commit();

                    Intent intent=new Intent(DashboardHireActivity.this,LoginActivity.class);
                    startActivity(intent);

                    Toast.makeText(DashboardHireActivity.this, "Thank You: "+firstname +" "+ lastname , Toast.LENGTH_SHORT).show();

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

        if(usertype.equals("Employee") || token.equals(" ") || Integer.parseInt(id)<0){
            Intent intent=new Intent(DashboardHireActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
        CvHirerdasboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent=new Intent(DashboardHireActivity.this,HireDashboardActivity.class);
                startActivity(intent);

            }
        });

        CvLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent=new Intent(DashboardHireActivity.this,Location.class);
                startActivity(intent);

            }
        });

        CvHirerdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent=new Intent(DashboardHireActivity.this,HirerBookDetailActivity.class);
                startActivity(intent);

            }
        });

        Cvlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                SharedPreferences sharedPreferences=getSharedPreferences("Users",MODE_PRIVATE);
                final  String tokens=sharedPreferences.getString("token","");

                final String firstname=sharedPreferences.getString("firstname","");
                final String lastname=sharedPreferences.getString("lastname","");

                SharedPreferences sharedPreferences1=getSharedPreferences("Users",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences1.edit();
                editor.putString("id","");
                editor.putString("token","");
                editor.putString("usertype","");
                editor.commit();

                Intent intent=new Intent(DashboardHireActivity.this,LoginActivity.class);
                startActivity(intent);

                Toast.makeText(DashboardHireActivity.this, "Thank You: "+firstname +" "+ lastname , Toast.LENGTH_SHORT).show();
            }
        });

        CvFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent=new Intent(DashboardHireActivity.this,HirerFavouriteDetail.class);
                startActivity(intent);

            }
        });

        CvHirerprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent=new Intent(DashboardHireActivity.this,HirerProfileViewActivity.class);
                startActivity(intent);

            }
        });
        CvHirerupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent=new Intent(DashboardHireActivity.this,HirerUpdateUserActivity.class);
                startActivity(intent);

            }
        });
    }
}
