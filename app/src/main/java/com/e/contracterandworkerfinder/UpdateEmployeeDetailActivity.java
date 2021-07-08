package com.e.contracterandworkerfinder;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
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

import java.util.List;

import DayJobApi.Api;
import Url.BaseUrl;
import model.EmployeeModel;
import model.UpdateEmployee;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateEmployeeDetailActivity extends AppCompatActivity {
    Spinner skill_spinner,experiance_spinner,payment_spinner,working_spinner;
    ImageView Imgback;
    EditText Etskilled,Etexperiance,Etjob,Etlanguage,Etpayment,Etworkinghrs,Etcost,Etavailable;
    Button Btnupdate,Btndelete;
    CheckBox edt_available;
    String id;
    TextView Etid;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_employee_detail);

        final MediaPlayer mp=MediaPlayer.create(this,R.raw.buttonclick1);
        Imgback=findViewById(R.id.Imgback);
        skill_spinner=(Spinner) findViewById(R.id.skill_spinner);
        experiance_spinner=(Spinner)findViewById(R.id.experiance_spinner);
        payment_spinner=(Spinner)findViewById(R.id.payment_spinner);
        working_spinner=(Spinner)findViewById(R.id.work_spinner);
        Etjob=findViewById(R.id.etjob);
        Etlanguage=findViewById(R.id.etlanguage);
        edt_available=(CheckBox) findViewById(R.id.edt_available);
        Etid=findViewById(R.id.Etid);
        Etcost=findViewById(R.id.etcost);
        Etavailable=findViewById(R.id.etavailable);
        Btnupdate=findViewById(R.id.BtnUpdate);
        Btndelete=findViewById(R.id.Btndelete);
        Etid.setVisibility(View.INVISIBLE);
        builder=new AlertDialog.Builder(this);
        Btnupdate.setOnClickListener(new View.OnClickListener() {
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
                    builder.setMessage("Are You Sure?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    finish();
                                    updateEmployee();
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
                    alert.setTitle("Update your Work Profile ");
                    alert.show();
                }
            }
        });

        Btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                builder.setMessage("Are You Sure?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
//                        finish();
                        deleteEmployee();
                    }
                })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                //create a dialog box;
                AlertDialog alert = builder.create();
                alert.setTitle("Delete your Work Profile ");
                alert.show();
            }
        });

// Create an ArrayAdapter using the string array and a default spinner layout
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.skill, R.layout.color_spinnerlayout);
        final ArrayAdapter<CharSequence> experiance_adapter = ArrayAdapter.createFromResource(this,
                R.array.experiance, R.layout.color_spinnerlayout);
        final ArrayAdapter<CharSequence> payment_adapter = ArrayAdapter.createFromResource(this,
                R.array.payment, R.layout.color_spinnerlayout);
        final ArrayAdapter<CharSequence> work_adapter = ArrayAdapter.createFromResource(this,
                R.array.workingday, R.layout.color_spinnerlayout);
//

// Specify the layout to use when the list of choices appears

        experiance_adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        payment_adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        work_adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
// Apply the adapter to the spinner
        skill_spinner.setAdapter(adapter);
        experiance_spinner.setAdapter(experiance_adapter);
        payment_spinner.setAdapter(payment_adapter);
        working_spinner.setAdapter(work_adapter);
        Imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.start();
                Intent intent=new Intent(UpdateEmployeeDetailActivity.this, DashboardEmployeeActivity.class);
                startActivity(intent);
            }
        });

        SharedPreferences sharedPreferences=getSharedPreferences("Users",MODE_PRIVATE);
        String id=sharedPreferences.getString("id","");


            if (id!="") {
                Api api = BaseUrl.getInstance().create(Api.class);
                Call<EmployeeModel> listCall = api.getEmployeeById(Integer.parseInt(id));

                listCall.enqueue(new Callback<EmployeeModel>() {
                    @Override
                    public void onResponse(Call<EmployeeModel> call, Response<EmployeeModel> response) {
                        if (response.body().getSkill()!=""){
                        String skills = response.body().getSkill();
                        String experiance = response.body().getExperiance();
                        String work = response.body().getJobCompleted();
                        String language = response.body().getLanguage();
                        String payment = response.body().getPayment();
                        String working = response.body().getWorking();
                        String cost = response.body().getCost();
                        String available = response.body().getAvailable();

                        final String empid = response.body().getEmployeeId();

                        if (skills != null) {
                            int spinnerskillposition = adapter.getPosition(skills);
                            skill_spinner.setSelection(spinnerskillposition);
                        }
                        if (experiance != null) {
                            int expposition = experiance_adapter.getPosition(experiance);
                            experiance_spinner.setSelection(expposition);
                        }
                        if (payment != null) {
                            int paymentposition = payment_adapter.getPosition(payment);
                            payment_spinner.setSelection(paymentposition);
                        }
                        if (working != null) {
                            int workingposition = adapter.getPosition(working);
                            working_spinner.setSelection(workingposition);
                        }
                        Etid.setText(empid);
                        Etlanguage.setText(response.body().getLanguage());
                        Etjob.setText(work);
                        Etcost.setText(cost);
                        if (available.equals("1")) {
                            edt_available.setChecked(true);
                        } else {
                            edt_available.setChecked(false);
                        }
                    }
                        else {
                            Toast.makeText(UpdateEmployeeDetailActivity.this, "No Work Profile", Toast.LENGTH_SHORT).show();
                       Intent intent=new Intent(UpdateEmployeeDetailActivity.this,WorkerActivity.class);
                       startActivity(intent);
                            return;

                        }
                    }

                    @Override
                    public void onFailure(Call<EmployeeModel> call, Throwable t) {
                        Toast.makeText(UpdateEmployeeDetailActivity.this, "Error" + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
    }

    private void deleteEmployee() {

        final String id=Etid.getText().toString();
        Api api=BaseUrl.getInstance().create(Api.class);
        Call<Void> voidCall=api.DeleteEmployee(Integer.parseInt(id));

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(UpdateEmployeeDetailActivity.this, "Deleted Successfully By "+ id, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(UpdateEmployeeDetailActivity.this,DashboardEmployeeActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdateEmployeeDetailActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void updateEmployee() {
        Api api=BaseUrl.getInstance().create(Api.class);
        String id=Etid.getText().toString();
        String skill=skill_spinner.getSelectedItem().toString();
        String exp=experiance_spinner.getSelectedItem().toString();
        String job=Etjob.getText().toString();
        String language=Etlanguage.getText().toString();
        String payment=payment_spinner.getSelectedItem().toString();
        String working=working_spinner.getSelectedItem().toString();
        String cost=Etcost.getText().toString();

        String available;
        if(edt_available.isChecked()){
            available="1";
        }else{
            available="0";
        }
        UpdateEmployee updateEmployee=new UpdateEmployee(skill,exp,job,language,payment,working,cost,available);

        Call<Void> voidCall=api.UpdateEmployee(Integer.parseInt(Etid.getText().toString()),updateEmployee);

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(UpdateEmployeeDetailActivity.this, "Code" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(UpdateEmployeeDetailActivity.this, "Work Detail Updated Successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = getIntent();
//                    finish();
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdateEmployeeDetailActivity.this, "Erorr"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
