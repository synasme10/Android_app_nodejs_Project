package com.e.contracterandworkerfinder;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import Adapter.BookAdapter;
import DayJobApi.Api;
import Url.BaseUrl;
import model.BookingModel;
import model.EmployeeModel;
import model.UserModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HirerBookDetailActivity extends AppCompatActivity {

    RecyclerView recyclerViews;
    ImageView imgbacks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hirerbook_recyclerview);

        recyclerViews=findViewById(R.id.recyclerViews);
        imgbacks=findViewById(R.id.Imgback);

        final MediaPlayer mp=MediaPlayer.create(this,R.raw.buttonclick1);
        imgbacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent=new Intent(HirerBookDetailActivity.this,DashboardHireActivity.class);
                startActivity(intent);

            }
        });
        getBookinglist();
    }

    private void getBookinglist() {

        SharedPreferences sharedPreferences=getSharedPreferences("Users",MODE_PRIVATE);
        String id=sharedPreferences.getString("id","");
        final String image=sharedPreferences.getString("image","");

        Api api = BaseUrl.getInstance().create(Api.class);

       final Call<List<BookingModel>> bookingList = api.getBookById(Integer.parseInt(id));
//       final Call<List<BookingModel>> bookingList = api.getAllBook();

        bookingList.enqueue(new Callback<List<BookingModel>>() {
            @Override
            public void onResponse(Call<List<BookingModel>> call, Response<List<BookingModel>> response) {
                if(! response.isSuccessful()){
                    Toast.makeText(HirerBookDetailActivity.this, "Code"+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

             List<BookingModel> bookingModelList=response.body();

                BookAdapter bookAdapter=new BookAdapter(HirerBookDetailActivity.this,bookingModelList);
                recyclerViews.setAdapter(bookAdapter);
                recyclerViews.setLayoutManager(new LinearLayoutManager(HirerBookDetailActivity.this));
            }

            @Override
            public void onFailure(Call<List<BookingModel>> call, Throwable t) {
                Toast.makeText(HirerBookDetailActivity.this, "Error:"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
