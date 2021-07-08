package com.e.contracterandworkerfinder;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import Adapter.WorkerAdapter;
import DayJobApi.Api;
import Url.BaseUrl;
import model.EmployeeModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HireDashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ImageView imgbacks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_dashboard);

        recyclerView=findViewById(R.id.recyclerView);
        imgbacks=findViewById(R.id.Imgback);

        final MediaPlayer mp=MediaPlayer.create(this,R.raw.buttonclick1);
        imgbacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent=new Intent(HireDashboardActivity.this,DashboardHireActivity.class);
                startActivity(intent);

            }
        });

        getAllEmployee();
    }

    private void getAllEmployee() {
        Api api= BaseUrl.getInstance().create(Api.class);

        final Call<List<EmployeeModel>> employeeList=api.getAllEmployee();

        employeeList.enqueue(new Callback<List<EmployeeModel>>() {
            @Override
            public void onResponse(Call<List<EmployeeModel>> call, Response<List<EmployeeModel>> response) {
                if(! response.isSuccessful()){
                    Toast.makeText(HireDashboardActivity.this, "Code"+response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<EmployeeModel> employeeModels=response.body();

                WorkerAdapter workerAdapter=new WorkerAdapter(HireDashboardActivity.this,employeeModels);
                recyclerView.setAdapter(workerAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(HireDashboardActivity.this));

            }

            @Override
            public void onFailure(Call<List<EmployeeModel>> call, Throwable t) {
                Toast.makeText(HireDashboardActivity.this, "Error:"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}