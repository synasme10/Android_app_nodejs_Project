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
import Adapter.FavouriteAdapter;
import DayJobApi.Api;
import Url.BaseUrl;
import model.BookingModel;
import model.FavouriteModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HirerFavouriteDetail extends AppCompatActivity {

    RecyclerView favrecyclerViews;
    ImageView imgbacks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_recyclerview);

        favrecyclerViews=findViewById(R.id.favrecyclerViews);
        imgbacks=findViewById(R.id.Imgback);

        final MediaPlayer mp=MediaPlayer.create(this,R.raw.buttonclick1);
        imgbacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent=new Intent(HirerFavouriteDetail.this,DashboardHireActivity.class);
                startActivity(intent);

            }
        });
        getFavouriteList();
    }

    private void getFavouriteList() {
        SharedPreferences sharedPreferences=getSharedPreferences("Users",MODE_PRIVATE);
        String id=sharedPreferences.getString("id","");
        final String image=sharedPreferences.getString("image","");

        Api api = BaseUrl.getInstance().create(Api.class);

        final Call<List<FavouriteModel>> favouriteList=api.getFavourite(Integer.parseInt(id));

        favouriteList.enqueue(new Callback<List<FavouriteModel>>() {
            @Override
            public void onResponse(Call<List<FavouriteModel>> call, Response<List<FavouriteModel>> response) {
                if(! response.isSuccessful()){
                    Toast.makeText(HirerFavouriteDetail.this, "Code"+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<FavouriteModel> favouriteModelList=response.body();

                FavouriteAdapter favouriteAdapter=new FavouriteAdapter(HirerFavouriteDetail.this,favouriteModelList);
                favrecyclerViews.setAdapter(favouriteAdapter);
                favrecyclerViews.setLayoutManager(new LinearLayoutManager(HirerFavouriteDetail.this));
            }

            @Override
            public void onFailure(Call<List<FavouriteModel>> call, Throwable t) {
                Toast.makeText(HirerFavouriteDetail.this, "Error:"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
