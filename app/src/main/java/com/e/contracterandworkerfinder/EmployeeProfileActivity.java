package com.e.contracterandworkerfinder;

import android.content.Intent;
import android.content.SharedPreferences;

import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.InputStream;
import java.net.URL;

import DayJobApi.Api;
import Url.BaseUrl;
import model.EmployeeModel;
import model.UserModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static Url.BaseUrl.Base_Url;

public class EmployeeProfileActivity extends AppCompatActivity {

    ImageView Ivimages,Imgbackbut;
    TextView Tvname,Tvphonenumber,Tvaddresses,Tvemails,Tvgender,Tvworkdetails,Tvjob,Btnpayment,Tvlanguages;
    Button Btnworkprof,BtnUpdateWorkprofile,btnupdateprofile;
    MediaPlayer mp;

    private SensorManager sensorManager;
    private SensorManager tvGyro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_profile);

        setTitle("Gyroscope Sensor");


        sensorGyro();

          mp=MediaPlayer.create(this,R.raw.buttonclick1);
        Ivimages=findViewById(R.id.Ivemployeeprofile);
        Btnworkprof=findViewById(R.id.Btnworkprof);
        Imgbackbut=findViewById(R.id.Imgbackbut);
        Tvname=findViewById(R.id.Tvnames);
        Tvphonenumber=findViewById(R.id.Tvphonenumbers);
        Tvemails=findViewById(R.id.Tvemailes);
        Tvaddresses=findViewById(R.id.Tvaddresses);
        Tvgender=findViewById(R.id.Tvgens);
        Tvworkdetails=findViewById(R.id.Tvworks);
        Tvjob=findViewById(R.id.Tvjobcomp);
        btnupdateprofile=findViewById(R.id.btnupdateprofile);
        Btnpayment=findViewById(R.id.Btnpayment);
        BtnUpdateWorkprofile=findViewById(R.id.BtnUpdateWorkprofile);
        Tvlanguages=findViewById(R.id.Tvlanguages);

        displayUserProfile();

        btnupdateprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent=new Intent(EmployeeProfileActivity.this,UpdateUserActivity.class);
                startActivity(intent);
            }
        });
        Imgbackbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent=new Intent(EmployeeProfileActivity.this,DashboardEmployeeActivity.class);
                startActivity(intent);
            }
        });
        BtnUpdateWorkprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent=new Intent(EmployeeProfileActivity.this,UpdateEmployeeDetailActivity.class);
                startActivity(intent);
            }
        });

    }

    private void sensorGyro() {
        sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor=sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        SensorEventListener gyrolistener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[1]<0){
                    Intent intent=new Intent(EmployeeProfileActivity.this,UpdateUserActivity.class);
                    startActivity(intent);
                }
                else if(event.values[1]>0){
                    Intent intent=new Intent(EmployeeProfileActivity.this,UpdateEmployeeDetailActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        if (sensor !=null){
            sensorManager.registerListener(gyrolistener,sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else{
            Toast.makeText(this, "No sensor Found", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayUserProfile() {
        SharedPreferences sharedPreferences=getSharedPreferences("Users",MODE_PRIVATE);
        String id=sharedPreferences.getString("id","");
       final String image=sharedPreferences.getString("image","");

        Api api = BaseUrl.getInstance().create(Api.class);
        Call<UserModel> listCall = api.getUserById(Integer.parseInt(id));

        listCall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                String firstname=response.body().getFirstName();
                String lastname=response.body().getLastName();
                String address=response.body().getAddress();
                String city=response.body().getCity();
                String state=response.body().getState();
                String phone=response.body().getPhone();
                String email=response.body().getEmail();
                String gender=response.body().getGender();
                String image_name=response.body().getImage();



                Tvname.setText(firstname+" "+lastname);
                Tvphonenumber.setText(phone);
                Tvaddresses.setText(city+", "+address);
                Tvemails.setText(email);
                Tvgender.setText(gender);
                StrictMode();
                String imgPath= Base_Url +"uploads/"+image_name;
                try {
                    URL url = new URL(imgPath);
                    Ivimages.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
//

                Btnworkprof.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mp.start();
                        Employeeworkprofile();
                    }
                });


            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(EmployeeProfileActivity.this, "Error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void StrictMode() {
        StrictMode.ThreadPolicy stict =new  StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(stict);
    }
    private void Employeeworkprofile() {

        SharedPreferences sharedPreferences=getSharedPreferences("Users",MODE_PRIVATE);
        String id=sharedPreferences.getString("id","");

        Api api = BaseUrl.getInstance().create(Api.class);
        Call<EmployeeModel> listCall = api.getEmployeeById(Integer.parseInt(id));

        listCall.enqueue(new Callback<EmployeeModel>() {
            @Override
            public void onResponse(Call<EmployeeModel> call, Response<EmployeeModel> response) {
                if (response.body().getSkill()!=null) {
                    String imagesname=response.body().getImage();
                    String skills = response.body().getSkill();
                    String experiance = response.body().getExperiance();
                    String work = response.body().getJobCompleted();
                    String language = response.body().getLanguage();
                    String payment = response.body().getPayment();
                    String working = response.body().getWorking();
                    String cost = response.body().getCost();
                    String available = response.body().getAvailable();

                    Tvworkdetails.setText("Rs."+cost+"/hr "+skills+ " for "+ experiance);
                    Tvjob.setText(work);
                    Tvlanguages.setText("Speaks: "+language);
                    Btnpayment.setVisibility(View.VISIBLE);
                    BtnUpdateWorkprofile.setVisibility(View.VISIBLE);
                    Btnpayment.setText(working+", "+payment);
                    String paths= Base_Url+"uploads/"+imagesname;

                }
            }

            @Override
            public void onFailure(Call<EmployeeModel> call, Throwable t) {
                Toast.makeText(EmployeeProfileActivity.this, "Erorr"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
