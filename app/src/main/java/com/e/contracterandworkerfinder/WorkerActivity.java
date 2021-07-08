package com.e.contracterandworkerfinder;

import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import DayJobApi.Api;
import Url.BaseUrl;
import channelcreation.ChannelCreate;
import model.EmployeeModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkerActivity extends AppCompatActivity {
    Spinner skill_spinner,experiance_spinner,payment_spinner,working_spinner;
    ImageView Imgback;
    EditText Etjob,Etlanguage,Etcost,Etavailable;
    Button Btnaddwork;
    CheckBox edt_available;
    private NotificationManagerCompat notificationManagerCompat;
    MediaPlayer mp,mp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker);

        notificationManagerCompat=NotificationManagerCompat.from(this);
        ChannelCreate channelCreate=new ChannelCreate(this);
        channelCreate.createChannel();
     mp=MediaPlayer.create(this,R.raw.buttonclick1);
        mp2=MediaPlayer.create(this,R.raw.notification);

        Imgback=findViewById(R.id.Imgbackwork);
       skill_spinner=(Spinner) findViewById(R.id.skill_spinner);
       experiance_spinner=(Spinner)findViewById(R.id.experiance_spinner);
       payment_spinner=(Spinner)findViewById(R.id.payment_spinner);
       working_spinner=(Spinner)findViewById(R.id.work_spinner);
        Etjob=findViewById(R.id.etjob);
        Etlanguage=findViewById(R.id.etlanguage);
        edt_available=(CheckBox) findViewById(R.id.edt_available);

        Etcost=findViewById(R.id.etcost);
        Etavailable=findViewById(R.id.etavailable);
        Btnaddwork=findViewById(R.id.Btnaddwork);

        Spinner spinner = (Spinner) findViewById(R.id.skill_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.skill, R.layout.color_spinnerlayout);
        ArrayAdapter<CharSequence> experiance_adapter = ArrayAdapter.createFromResource(this,
                R.array.experiance, R.layout.color_spinnerlayout);
        ArrayAdapter<CharSequence> payment_adapter = ArrayAdapter.createFromResource(this,
                R.array.payment, R.layout.color_spinnerlayout);
        ArrayAdapter<CharSequence> work_adapter = ArrayAdapter.createFromResource(this,
                R.array.workingday, R.layout.color_spinnerlayout);
//

// Specify the layout to use when the list of choices appears
        experiance_adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        payment_adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
                work_adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        experiance_spinner.setAdapter(experiance_adapter);
        payment_spinner.setAdapter(payment_adapter);
         working_spinner.setAdapter(work_adapter);
        Imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent=new Intent(WorkerActivity.this, DashboardEmployeeActivity.class);
                startActivity(intent);
            }
        });
        
        Btnaddwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();

                if (TextUtils.isEmpty(Etjob.getText().toString())){
                    Etjob.setError("Enter Your area of expertize");
                }
                else if (TextUtils.isEmpty(Etlanguage.getText().toString())){
                    Etlanguage.setError("Enter Language you know");
                }
                else if (TextUtils.isEmpty(Etcost.getText().toString())){
                    Etcost.setError("Enter cost per hr");
                }
                else {
                    addWork();
                }
            }
        });
        
        
    }

    private void addWork() {
        SharedPreferences sharedPreferences=getSharedPreferences("Users",MODE_PRIVATE);
        String id=sharedPreferences.getString("id","");
        final String firstname=sharedPreferences.getString("firstname","");
        final String lastname=sharedPreferences.getString("lastname","");
        String phone=sharedPreferences.getString("phone","");
        String state=sharedPreferences.getString("state","");
        String city=sharedPreferences.getString("city","");
        String address=sharedPreferences.getString("address","");
        String image=sharedPreferences.getString("image","");
        String email=sharedPreferences.getString("email","");
        String gender=sharedPreferences.getString("gender","");

        String skill=skill_spinner.getSelectedItem().toString();
        String experiance=experiance_spinner.getSelectedItem().toString();
        String job=Etjob.getText().toString();
        String lang=Etlanguage.getText().toString();
        String payment=payment_spinner.getSelectedItem().toString();
        String workingday=working_spinner.getSelectedItem().toString();
        String cost=Etcost.getText().toString();
        String available;
        if(edt_available.isChecked()){
             available="1";
        }else{
             available="0";
        }

        EmployeeModel employeeModel=new EmployeeModel(id,skill,experiance,job,lang,payment,workingday,cost,available,image,firstname,lastname,phone,state,city,address,email,gender);

        Api api= BaseUrl.getInstance().create(Api.class);
        Call<Void> addWorkCall=api.addWork(employeeModel);

        addWorkCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(WorkerActivity.this, "Code" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(WorkerActivity.this, "Work Detail Added Successfully by"+ firstname+" "+ lastname, Toast.LENGTH_SHORT).show();
//                    AlertDialog.Builder message=new AlertDialog.Builder(WorkerActivity.this);
//                    message.setTitle("Work Detail Added Successfully");
//                    message.setMessage("By "+ firstname+" "+ lastname);
//                    message.show();

                    Notification notification=new NotificationCompat.Builder(getApplicationContext(),ChannelCreate.Channel_1)
                            .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                            .setContentTitle("Day JoB")
                            .setContentText("Work Detail successfully Added")
                            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                            .build();
                    mp2.start();
                    notificationManagerCompat.notify(1,notification);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(WorkerActivity.this, "Erorr"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
