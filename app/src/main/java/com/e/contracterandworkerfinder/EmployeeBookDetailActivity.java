package com.e.contracterandworkerfinder;

import android.content.Intent;
import android.content.SharedPreferences;
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
import Adapter.EmployeeBookAdapter;
import DayJobApi.Api;
import Url.BaseUrl;
import UserFragment.EmployeeBookDetail;
import model.BookingModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeBookDetailActivity extends AppCompatActivity {

    RecyclerView recyclerViews;
    ImageView Imgbacks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_book_recyclerview);

        recyclerViews=findViewById(R.id.recyclerViewer);
        Imgbacks=findViewById(R.id.Imgbacks);

        final MediaPlayer mp=MediaPlayer.create(this,R.raw.buttonclick1);
        getEmployeeBookingList();

        Imgbacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent=new Intent(EmployeeBookDetailActivity.this,DashboardEmployeeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getEmployeeBookingList() {


        SharedPreferences sharedPreferences=getSharedPreferences("Users",MODE_PRIVATE);
        String id=sharedPreferences.getString("id","");
        final String image=sharedPreferences.getString("image","");

        Api api = BaseUrl.getInstance().create(Api.class);

        Call<List<BookingModel>> bookingList=api.getBookByIdForEmployee(Integer.parseInt(id));
//        final Call<List<BookingModel>> bookingList = api.getBookByIdForEmployee(Integer.parseInt(id));
//       final Call<List<BookingModel>> bookingList = api.getAllBook();

        bookingList.enqueue(new Callback<List<BookingModel>>() {
            @Override
            public void onResponse(Call<List<BookingModel>> call, Response<List<BookingModel>> response) {
                if(! response.isSuccessful()){
                    Toast.makeText(EmployeeBookDetailActivity.this, "Code"+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<BookingModel> bookingModelList=response.body();

                EmployeeBookAdapter bookAdapter=new EmployeeBookAdapter(EmployeeBookDetailActivity.this,bookingModelList);
                recyclerViews.setAdapter(bookAdapter);
                recyclerViews.setLayoutManager(new LinearLayoutManager(EmployeeBookDetailActivity.this));
            }

            @Override
            public void onFailure(Call<List<BookingModel>> call, Throwable t) {
                Toast.makeText(EmployeeBookDetailActivity.this, "Error:"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
