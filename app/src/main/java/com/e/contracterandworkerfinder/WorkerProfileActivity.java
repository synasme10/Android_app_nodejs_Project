package com.e.contracterandworkerfinder;

import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;

import DayJobApi.Api;
import Url.BaseUrl;
import channelcreation.ChannelCreate;
import model.BookingModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static Url.BaseUrl.Base_Url;

public class WorkerProfileActivity extends AppCompatActivity {

    ImageView Ivimage,imagecall,imgbacks;
    TextView Tvname,Tvworkdetail,Tvjob,Tvlang,Tvphonenumber,Tvemail,Tvaddress;
    Button Btnpay,Btnhire;
    MediaPlayer mp,mp2;
    private NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_profile);

        notificationManagerCompat=NotificationManagerCompat.from(this);
        mp2=MediaPlayer.create(this,R.raw.notification);
        ChannelCreate channelCreate=new ChannelCreate(this);
        channelCreate.createChannel();
        Ivimage=findViewById(R.id.Ivimageprofile);
        Tvname=findViewById(R.id.Tvname);
        imagecall=findViewById(R.id.imagecall);
        Tvworkdetail=findViewById(R.id.Tvworkdetail);
        Tvjob=findViewById(R.id.Tvjob);
        Btnpay=findViewById(R.id.Btnpay);
        Tvlang=findViewById(R.id.Tvlang);
        Tvphonenumber=findViewById(R.id.Tvphonenumber);
        Tvemail=findViewById(R.id.Tvemails);
        Tvaddress=findViewById(R.id.Tvaddresses);

        Btnhire=findViewById(R.id.Btnhire);
        imgbacks=findViewById(R.id.Imgback);

         mp=MediaPlayer.create(this,R.raw.buttonclick1);
        imgbacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent=new Intent(WorkerProfileActivity.this,DashboardHireActivity.class);
                startActivity(intent);

            }
        });
        Btnhire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                HireEmployee();
            }
        });

        final String firstname=getIntent().getStringExtra("firstname");
        String lastname=getIntent().getStringExtra("lastname");
       final String phone=getIntent().getStringExtra("phone");
        String state=getIntent().getStringExtra("state");
        String city=getIntent().getStringExtra("city");
        String address=getIntent().getStringExtra("address");
        String email=getIntent().getStringExtra("email");
        String gender=getIntent().getStringExtra("gender");
        String skill=getIntent().getStringExtra("skill");
        String imagename=getIntent().getStringExtra("imagename");
        String experiance=getIntent().getStringExtra("experiance");
        String job=getIntent().getStringExtra("job");
        String language=getIntent().getStringExtra("language");
        String payment=getIntent().getStringExtra("payment");
        String working=getIntent().getStringExtra("working");
        String cost=getIntent().getStringExtra("cost");
        String available=getIntent().getStringExtra("available");

        if(getIntent() != null){

            Tvname.setText(firstname+" "+lastname);
            Tvworkdetail.setText("Rs."+cost+"/hr"+skill+ " for "+ experiance);
            Tvjob.setText(job);
            Btnpay.setText(working+", "+payment);
            Tvlang.setText(language);
            Tvaddress.setText(city+", "+address);
            Tvemail.setText(email);
            Tvphonenumber.setText(phone);

            imagecall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mp.start();
                    Uri u=Uri.parse("tel:"+phone);
                    Intent intent=new Intent(Intent.ACTION_DIAL,u);
                    startActivity(intent);
                }
            });

            StrictMode();
            String imgPath= Base_Url +"uploads/"+imagename;
            try {
                URL url = new URL(imgPath);
                Ivimage.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
            }
             catch (Exception e) {
                e.printStackTrace();
            }


        }


    }
    private void StrictMode() {
        StrictMode.ThreadPolicy stict =new  StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(stict);
    }
    private void HireEmployee() {
        final String userid=getIntent().getStringExtra("userid");
        final String Eid=getIntent().getStringExtra("eid");
        final String firstname=getIntent().getStringExtra("firstname");
        final String lastname=getIntent().getStringExtra("lastname");
        final String phone=getIntent().getStringExtra("phone");
        String state=getIntent().getStringExtra("state");
        String city=getIntent().getStringExtra("city");
        String address=getIntent().getStringExtra("address");
        String email=getIntent().getStringExtra("email");
        String gender=getIntent().getStringExtra("gender");
        String skill=getIntent().getStringExtra("skill");
        String imagename=getIntent().getStringExtra("imagename");
        String experiance=getIntent().getStringExtra("experiance");
        String job=getIntent().getStringExtra("job");
        String language=getIntent().getStringExtra("language");
        String payment=getIntent().getStringExtra("payment");
        String working=getIntent().getStringExtra("working");
        String cost=getIntent().getStringExtra("cost");
        String available=getIntent().getStringExtra("available");

        SharedPreferences sharedPreferences=getSharedPreferences("Users",MODE_PRIVATE);
        String hirerid=sharedPreferences.getString("id","");
        final String hirerfn=sharedPreferences.getString("firstname","");
        final String hirerln=sharedPreferences.getString("lastname","");
        String hirerphone=sharedPreferences.getString("phone","");
        String hirerstate=sharedPreferences.getString("state","");
        String hirercity=sharedPreferences.getString("city","");
        String hireraddress=sharedPreferences.getString("address","");
        String hirerimage=sharedPreferences.getString("image","");
        String hireremail=sharedPreferences.getString("email","");
        String hirergender=sharedPreferences.getString("gender","");

        String status="0";


        BookingModel bookingModel=new BookingModel(hirerid,hirerfn,hirerln,hirerphone,hirercity,hireraddress,hireremail,hirerimage
                ,Eid,userid,skill,experiance,job,language,payment,working,cost,available,imagename,firstname,lastname,phone,state,city,address,email,gender,status);
        Api api= BaseUrl.getInstance().create(Api.class);

        Call<Void> voidCall=api.BookEmployee(bookingModel);

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(WorkerProfileActivity.this, "Code" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(WorkerProfileActivity.this, "Booking Successfully "+ firstname+" "+ lastname, Toast.LENGTH_SHORT).show();


                    Notification notification=new NotificationCompat.Builder(getApplicationContext(),ChannelCreate.Channel_1)
                            .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                            .setContentTitle("Day JoB")
                            .setContentText("Booking Successfully")
                            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                            .build();
                    mp2.start();
                    notificationManagerCompat.notify(1,notification);

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(WorkerProfileActivity.this, "Erorr"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}

//        Intent intent=new Intent(WorkerProfileActivity.this, WorkerProfileActivity.class);
//        intent.putExtra("eid",Eid);
//        intent.putExtra("firstname",firstname);
//        intent.putExtra("lastname",lastname);
//        intent.putExtra("phone",phone);
//        intent.putExtra("state",state);
//        intent.putExtra("city",city);
//        intent.putExtra("address",address);
//        intent.putExtra("email",email);
//        intent.putExtra("gender",gender);
//        intent.putExtra("skill",skill);
//        intent.putExtra("imagename",imagename);
//        intent.putExtra("experiance",experiance);
//        intent.putExtra("job",job);
//        intent.putExtra("language",language);
//        intent.putExtra("payment",payment);
//        intent.putExtra("working",working);
//        intent.putExtra("cost",cost);
//        intent.putExtra("available",available);
//        startActivity(intent);