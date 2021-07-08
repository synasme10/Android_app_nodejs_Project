package com.e.contracterandworkerfinder;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import DayJobApi.Api;
import Url.BaseUrl;
import model.UpdateUser;
import model.UserModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateUserActivity extends AppCompatActivity {

    EditText Etfn,Etln,Etpn,etstate,etcitys,etaddresses,etuserid;
    Spinner gender_spinner;
    Button btnupdateregister;
    ImageView btnbackbutton,ivuserimage;
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        final MediaPlayer mp=MediaPlayer.create(this,R.raw.buttonclick1);
        Etfn=findViewById(R.id.Etfn);
        Etln=findViewById(R.id.Etln);
        Etpn=findViewById(R.id.Etpn);
        etstate=findViewById(R.id.etstate);
        etcitys=findViewById(R.id.etcitys);
        etaddresses=findViewById(R.id.etaddresses);
        gender_spinner=findViewById(R.id.genderss_spinner);
        btnupdateregister=findViewById(R.id.btnupdateregister);
        btnbackbutton=findViewById(R.id.Ivbackbutton);
        ivuserimage=findViewById(R.id.ivuserimage);
        etuserid=findViewById(R.id.etuserid);
        builder=new AlertDialog.Builder(this);
        final ArrayAdapter<CharSequence> gender_adapter = ArrayAdapter.createFromResource(this,
                R.array.genders, R.layout.color_spinnerlayout);
        gender_adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        gender_spinner.setAdapter(gender_adapter);

        SharedPreferences sharedPreferences=getSharedPreferences("Users",MODE_PRIVATE);
        String id=sharedPreferences.getString("id","");

        Api api= BaseUrl.getInstance().create(Api.class);

        Call<UserModel> listCall=api.getUserById(Integer.parseInt(id));
        listCall.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                String userid=response.body().getUserId();
                String firstname=response.body().getFirstName();
                String lastname=response.body().getLastName();
                String address=response.body().getAddress();
                String city=response.body().getCity();
                String state=response.body().getState();
                String phone=response.body().getPhone();
                String email=response.body().getEmail();
                String gender=response.body().getGender();
                String image_name=response.body().getImage();
                etuserid.setText(userid);
                Etfn.setText(firstname);
                Etln.setText(lastname);
                Etpn.setText(phone);
                etstate.setText(state);
                etcitys.setText(city);
                etaddresses.setText(address);
                if (gender != null) {

                    int spinnerskillposition = gender_adapter.getPosition(gender);
                    gender_spinner.setSelection(spinnerskillposition);
                }
                StrictMode();
                String imgPath=BaseUrl.Base_Url+"uploads/"+image_name;
                try {
                    URL url =new URL(imgPath);
                    ivuserimage.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
                }
                catch(Exception e ){
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Toast.makeText(UpdateUserActivity.this, "Error" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        btnupdateregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                if (TextUtils.isEmpty(Etfn.getText().toString())){
                    Etfn.setError("Enter First Name");
                }
                else if (TextUtils.isEmpty(Etln.getText().toString())){
                    Etln.setError("Enter Last Name");
                }
                else if (TextUtils.isEmpty(Etpn.getText().toString())){
                    Etpn.setError("Enter Phone Number");
                }
                else if (TextUtils.isEmpty(etstate.getText().toString())){
                    etstate.setError("Enter State Name");
                }
                else if (TextUtils.isEmpty(etcitys.getText().toString())){
                    etcitys.setError("Enter City Name");
                }
                else if (TextUtils.isEmpty(etaddresses.getText().toString())){
                    etaddresses.setError("Enter Address Name");
                }
                else {
                    builder.setMessage("Are You Sure?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
//                                finish();
                                    updateUser();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
//                                Intent intent = getIntent();
//                                finish();
//                                startActivity(intent);

                                }
                            });
                    //create a dialog box;
                    AlertDialog alert = builder.create();
                    alert.setTitle("Update your Profile ");
                    alert.show();
                }
            }
        });

        btnbackbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent=new Intent(UpdateUserActivity.this, DashboardEmployeeActivity.class);
                startActivity(intent);
            }
        });
    }


    private void StrictMode() {
        StrictMode.ThreadPolicy stict =new  StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(stict);
    }

    private void updateUser() {
        Api api=BaseUrl.getInstance().create(Api.class);

        String userid=etuserid.getText().toString();
        String firstname=Etfn.getText().toString();
        String lastname=Etln.getText().toString();
        String phone=Etpn.getText().toString();
        String state=etstate.getText().toString();
        String city=etcitys.getText().toString();
        String address=etaddresses.getText().toString();
        String gender=gender_spinner.getSelectedItem().toString();

        UpdateUser updateUser=new UpdateUser(firstname,lastname,phone,state,city,address,gender);

        Call<Void> voidCall=api.UpdateUser(Integer.parseInt(userid),updateUser);

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(UpdateUserActivity.this, "Code" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(UpdateUserActivity.this, "User Profile Updated Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdateUserActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
