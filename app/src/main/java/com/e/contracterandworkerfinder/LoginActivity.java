package com.e.contracterandworkerfinder;

import android.app.Notification;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import DayJobApi.Api;
import broadcaste.BroadcastReceiverDayjob;
import channelcreation.ChannelCreate;
import model.LoginRegisterResponse;
import model.LoginModel;
import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import Url.BaseUrl;

public class LoginActivity extends AppCompatActivity {


EditText Etusername,Etpassword;;
TextView Tvforgot,Tvcreateaccount;
    MediaPlayer mp,mp2;
    Button Btnlogin;
    private NotificationManagerCompat notificationManagerCompat;
    BroadcastReceiverDayjob broadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        notificationManagerCompat=NotificationManagerCompat.from(this);
        broadcastReceiver=new BroadcastReceiverDayjob(this);
        ChannelCreate channelCreate=new ChannelCreate(this);
        channelCreate.createChannel();
        Etusername=findViewById(R.id.Etusername);
        Etpassword=findViewById(R.id.Etpassword);
        Tvforgot=findViewById(R.id.Tvforgot);
        Tvcreateaccount=findViewById(R.id.Tvcreateaccount);
        Btnlogin=findViewById(R.id.Btnlogin);
         mp=MediaPlayer.create(this,R.raw.buttonclick1);
        mp2=MediaPlayer.create(this,R.raw.notification);

        SharedPreferences sharedPreferences = getSharedPreferences("Users",MODE_PRIVATE);
        String token = sharedPreferences.getString("token","");
        String uType = sharedPreferences.getString("usertype","");

        if (token.isEmpty()){
//            Toast.makeText(this, "token caina", Toast.LENGTH_SHORT).show();
        }
        else if(!token.isEmpty() && uType.equals("Hirer")){
//            Toast.makeText(this, "token ca", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(LoginActivity.this,DashboardHireActivity.class);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(LoginActivity.this, DashboardEmployeeActivity.class);
            startActivity(intent);
        }

        Btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();

                if(TextUtils.isEmpty(Etusername.getText().toString())){
                    Etusername.setError("Enter email");
                    return;
                }
                else if(TextUtils.isEmpty(Etpassword.getText().toString())){
                    Etpassword.setError("Enter Password");
                    return;
                }

                else {
                    auth();
                }

            }
        });
        Tvcreateaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });


    }


    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter intentFilter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadcastReceiver,intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();

        unregisterReceiver(broadcastReceiver);
    }
    private void auth(){


        Api api=BaseUrl.getInstance().create(Api.class);
        final String username=Etusername.getText().toString();
        final String password=Etpassword.getText().toString();

        LoginModel loginModel=new LoginModel(username,password);

        Call<LoginRegisterResponse> call=api.checkUser(loginModel);

        call.enqueue(new Callback<LoginRegisterResponse>() {
            @Override
            public void onResponse(Call<LoginRegisterResponse> call, Response<LoginRegisterResponse> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"Incorrect",Toast.LENGTH_LONG).show();
                return;
                }
                else{
                    if (response.body().getResult().getUsertype().equals("Hirer")){
                        Toast.makeText(LoginActivity.this, "Hirer login", Toast.LENGTH_SHORT).show();

                        String id=response.body().getResult().getUserId();
                        String firstname=response.body().getResult().getFirstName();
                        String lastname=response.body().getResult().getLastName();
                        String phone=response.body().getResult().getPhone();
                        String state=response.body().getResult().getState();
                        String city=response.body().getResult().getCity();
                        String address=response.body().getResult().getAddress();
                        String email=response.body().getResult().getEmail();
                        String gender=response.body().getResult().getGender();
                        String image=response.body().getResult().getImage();
                        String usertype=response.body().getResult().getUsertype();
                        String token=response.body().getToken();

                        SharedPreferences sharedPreferences=getSharedPreferences("Users",MODE_PRIVATE);
                        SharedPreferences.Editor value=sharedPreferences.edit();

                        value.putString("token",token);
                        value.putString("id",id);
                        value.putString("firstname",firstname);
                        value.putString("lastname",lastname);
                        value.putString("phone",phone);
                        value.putString("state",state);
                        value.putString("city",city);
                        value.putString("address",address);
                        value.putString("image",image);
                        value.putString("email",email);
                        value.putString("usertype",usertype);
                        value.putString("gender",gender);

                        value.commit();

                        Intent intent=new Intent(LoginActivity.this, DashboardHireActivity.class);
                        startActivity(intent);
                        Notification notification=new NotificationCompat.Builder(getApplicationContext(),ChannelCreate.Channel_1)
                                .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                                .setContentTitle("Day JoB")
                                .setContentText("Login Successfull as Hirer")
                                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                                .build();
                        mp2.start();
                        notificationManagerCompat.notify(1,notification);

                    }
                    else if (response.body().getResult().getUsertype().equals("Employee")){
                        Toast.makeText(LoginActivity.this, "Employee login", Toast.LENGTH_SHORT).show();

                        String id=response.body().getResult().getUserId();
                        String firstname=response.body().getResult().getFirstName();
                        String lastname=response.body().getResult().getLastName();
                        String phone=response.body().getResult().getPhone();
                        String state=response.body().getResult().getState();
                        String city=response.body().getResult().getCity();
                        String address=response.body().getResult().getAddress();
                        String email=response.body().getResult().getEmail();
                        String gender=response.body().getResult().getGender();
                        String image=response.body().getResult().getImage();
                        String usertype=response.body().getResult().getUsertype();
                        String token=response.body().getToken();


                        SharedPreferences sharedPreferences=getSharedPreferences("Users",MODE_PRIVATE);
                        SharedPreferences.Editor value=sharedPreferences.edit();


                        value.putString("id",id);
                        value.putString("firstname",firstname);
                        value.putString("lastname",lastname);
                        value.putString("phone",phone);
                        value.putString("state",state);
                        value.putString("city",city);
                        value.putString("address",address);
                        value.putString("image",image);
                        value.putString("email",email);
                        value.putString("usertype",usertype);
                        value.putString("gender",gender);
                        value.putString("token",token);
                        value.commit();

                        Notification notification=new NotificationCompat.Builder(getApplicationContext(),ChannelCreate.Channel_1)
                                .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                                .setContentTitle("Day JoB")
                                .setContentText("Login Successfull as Employee")
                                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                                .build();
                        mp2.start();
                        notificationManagerCompat.notify(1,notification);

//                        if (Integer.parseInt(id)>0 && token.equals("")){
//                            Toast.makeText(LoginActivity.this, "No Value", Toast.LENGTH_SHORT).show();
//                        }
//                        else {
                            Intent intent = new Intent(LoginActivity.this, DashboardEmployeeActivity.class);
                            startActivity(intent);


                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Invalid login and password", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginRegisterResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
