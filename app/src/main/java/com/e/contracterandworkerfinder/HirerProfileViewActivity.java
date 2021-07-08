package com.e.contracterandworkerfinder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.StrictMode;
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
import model.UserModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static Url.BaseUrl.Base_Url;

public class HirerProfileViewActivity extends AppCompatActivity {

    ImageView Ivimages,imgbacks;
    TextView Tvname,Tvphonenumber,Tvaddresses,Tvemails,Tvgender,Tvworkdetails,Tvjob,Btnpayment,Tvlanguages;
    Button btnupdateprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hirer_profile_view);

        Ivimages=findViewById(R.id.Ivhirerprofile);

        Tvname=findViewById(R.id.Tvhirernames);
        Tvphonenumber=findViewById(R.id.Tvhirerphonenumbers);
        Tvemails=findViewById(R.id.Tvhireremailes);
        Tvaddresses=findViewById(R.id.Tvhireraddresses);
        Tvgender=findViewById(R.id.Tvhirergens);

        btnupdateprofile=findViewById(R.id.btnhirerupdateprofile);

        imgbacks=findViewById(R.id.Imgback);

        final MediaPlayer mp=MediaPlayer.create(this,R.raw.buttonclick1);
        imgbacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent=new Intent(HirerProfileViewActivity.this,DashboardHireActivity.class);
                startActivity(intent);

            }
        });
        displayUserProfile();


        btnupdateprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent=new Intent(HirerProfileViewActivity.this,HirerUpdateUserActivity.class);
                startActivity(intent);
            }
        });
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




            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(HirerProfileViewActivity.this, "Error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void StrictMode() {
        StrictMode.ThreadPolicy stict =new  StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(stict);
    }
}
