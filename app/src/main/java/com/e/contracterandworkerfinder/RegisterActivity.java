package com.e.contracterandworkerfinder;

import android.Manifest;
import android.app.Dialog;
import android.app.Notification;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import DayJobApi.Api;
import Url.BaseUrl;
import channelcreation.ChannelCreate;
import model.ImageResponse;
import model.UserModel;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;

public class RegisterActivity extends AppCompatActivity {

    String imagePath;
    Dialog myDialog;
    EditText Etemail, Etaddress, Etphone, Etstate,Etcity,Etgender,Etusertype, Etfirst, Etlast, Etusername, Etpassword,Etcpw;
    TextView Tvlogin;
    Button Btnregister;
    ImageView Ivimage,IvImageView;
    Spinner gender_spinner,usertype_spinner;
    private String imageName;
    MediaPlayer mp,mp2,mp3;
    Api api;
    private NotificationManagerCompat notificationManagerCompat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ChannelCreate channelCreate=new ChannelCreate(this);
        channelCreate.createChannel();
         mp=MediaPlayer.create(this,R.raw.buttonclick1);
         mp2=MediaPlayer.create(this,R.raw.buttonclick2);
         mp3=MediaPlayer.create(this,R.raw.notification);
        api = BaseUrl.getInstance().create(Api.class);

        notificationManagerCompat=NotificationManagerCompat.from(this);
        myDialog = new Dialog(this);
        Etemail = findViewById(R.id.Etemails);
        Etstate=findViewById(R.id.Etstates);
        Etcity=findViewById(R.id.Etcitys);
        usertype_spinner=(Spinner)findViewById(R.id.usertype_spinner);
        gender_spinner=(Spinner)findViewById(R.id.gender_spinner);
        Etaddress = findViewById(R.id.Etaddresses);
        Etphone = findViewById(R.id.Etphonenumber);
        Etfirst = findViewById(R.id.Etfirstname);
        Etusername = findViewById(R.id.Etusername);
        Etpassword = findViewById(R.id.Etpassword);
        Etlast = findViewById(R.id.Etlastname);
        Tvlogin = findViewById(R.id.Tvlogin);
        Etcpw=findViewById(R.id.Etcpw);
        Btnregister = findViewById(R.id.Btnregister);
        IvImageView=findViewById(R.id.IvImageview);

// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> gender_adapter = ArrayAdapter.createFromResource(this,
                R.array.genders, R.layout.color_spinnerlayout);

        ArrayAdapter<CharSequence> usertype_adapter = ArrayAdapter.createFromResource(this,
                R.array.usertype, R.layout.color_spinnerlayout);
// Specify the layout to use when the list of choices appears
        gender_adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        usertype_adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
// Apply the adapter to the spinner
        gender_spinner.setAdapter(gender_adapter);
        usertype_spinner.setAdapter(usertype_adapter);


        Btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();

                if (TextUtils.isEmpty(Etfirst.getText().toString())){
                    Etfirst.setError("Enter First Name");
                }
                else if (TextUtils.isEmpty(Etlast.getText().toString())){
                    Etlast.setError("Enter Last Name");
                }
                else if (TextUtils.isEmpty(Etphone.getText().toString())){
                    Etphone.setError("Enter Phone Number");
                }
                else if (TextUtils.isEmpty(Etstate.getText().toString())){
                    Etstate.setError("Enter State Name");
                }
                else if (TextUtils.isEmpty(Etcity.getText().toString())){
                    Etcity.setError("Enter City Name");
                }
                else if (TextUtils.isEmpty(Etaddress.getText().toString())){
                    Etaddress.setError("Enter Address Name");
                }
                else if (TextUtils.isEmpty(Etemail.getText().toString())){
                    Etemail.setError("Enter Email Name");
                }
                else if (TextUtils.isEmpty(Etpassword.getText().toString())){
                    Etpassword.setError("Enter Password");
                }

                else if (TextUtils.isEmpty(Etcpw.getText().toString())){
                    Etcpw.setError("Enter Confirm Password ");
                }
                else if(Etpassword.getText().toString().length()<6)
                {
                    Etpassword.setError("Enter minimum 6 character password ");
                }
                else if(Etpassword.getText().toString().equals(Etcpw.getText().toString())){
                    Save();
                }
                else {

                    Etpassword.setText("");
                    Etcpw.setText("");
                    Etpassword.setError("Password and Comfirm Password didn't match");
                }
            }
        });

        Tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void ShowPopup(View v) {
        TextView Tvclose;
        ImageView Ivcamera;
        ImageView Ivimage;

        myDialog.setContentView(R.layout.camerapopup);

        Tvclose = (TextView) myDialog.findViewById(R.id.Tvclose);
        Ivcamera = (ImageView) myDialog.findViewById(R.id.Ivcamera);
        Ivimage = myDialog.findViewById(R.id.Ivimage);

//        checkPermission();
        Ivcamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
//                OpenCamera();
                myDialog.dismiss();
                mp2.start();
            }
        });
        Ivimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                BrowseUserImage();
                myDialog.dismiss();
                mp2.start();

            }
        });
        Tvclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

//    private void checkPermission() {
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(this,new String[]
//                    {Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
//
//        }
//    }

//    private void OpenCamera() {
//        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(intent,1);
//
//        }
//    }



    private void BrowseUserImage() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "Please select an image ", Toast.LENGTH_SHORT).show();
            }
        }
        Uri uri = data.getData();
        imagePath = getRealPathFromUri(uri);
        previewImage(imagePath);
        //after geting image patha display image in image view
    }

    //this function will return the image patha
    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(), uri, projection, null,
                null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }


    private void previewImage(String imagePath) {
        File imgFile = new File(imagePath);
        if (imgFile.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            IvImageView.setImageBitmap(myBitmap);

        }
    }

        private void StrictMode() {
            android.os.StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            android.os.StrictMode.setThreadPolicy(policy);
        }

    private void SaveImageOnly() {

        File file = new File(imagePath);

        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestBody);

        Api api = BaseUrl.getInstance().create(Api.class);
        Call<ImageResponse> responseBodyCall = api.uploadImage(body);

        StrictMode();

        try {
            Response<ImageResponse> imageResponseResponse = responseBodyCall.execute();
            // After saving an image, retrieve the current name of the image
            imageName = imageResponseResponse.body().getFilename();
        } catch (IOException e) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }


    private void Save() {
        SaveImageOnly();
        String firstname = Etfirst.getText().toString();
        String lastname = Etlast.getText().toString();
        String phone = Etphone.getText().toString();
        String state = Etstate.getText().toString();
        String city = Etcity.getText().toString();
        String address = Etaddress.getText().toString();
        String gender = gender_spinner.getSelectedItem().toString();
        String email = Etemail.getText().toString();
        String password = Etpassword.getText().toString();
        String usertype = usertype_spinner.getSelectedItem().toString();


        UserModel userModel=new UserModel(imageName,firstname,lastname,phone,state,city,address,gender,email,password,usertype);
//        UserModel userModel=new UserModel(imageName,firstname,lastname,"sfs","sdfs","sdfssf","sdfsfs","sdfsf","sdfs","sdfs","sdfsfs");
        Api api = BaseUrl.getInstance().create(Api.class);
        Call<Void> heroesCall = api.Register(userModel);

        heroesCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(RegisterActivity.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();


                Notification notification=new NotificationCompat.Builder(getApplicationContext(),ChannelCreate.Channel_1)
                        .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                        .setContentTitle("Day JoB")
                        .setContentText("Resistered Successfull")
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .build();
                mp3.start();
                notificationManagerCompat.notify(1,notification);
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "My error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Open backggg

    }



}
