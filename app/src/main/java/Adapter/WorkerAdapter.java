package Adapter;

import android.app.Notification;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.e.contracterandworkerfinder.HirerBookDetailActivity;
import com.e.contracterandworkerfinder.R;
import com.e.contracterandworkerfinder.WorkerProfileActivity;

import java.io.InputStream;
import java.net.URL;
import java.util.List;


import DayJobApi.Api;
import Url.BaseUrl;
import channelcreation.ChannelCreate;
import model.EmployeeModel;
import model.FavouriteModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static Url.BaseUrl.Base_Url;
import static android.content.Context.MODE_PRIVATE;

public class WorkerAdapter extends RecyclerView.Adapter<WorkerAdapter.WorkerViewHolder> {
    private Context context;
    private List<EmployeeModel> employeelist;

    public WorkerAdapter(Context context, List<EmployeeModel> list) {
        this.context = context;
        this.employeelist = list;
    }

    private void StrictMode() {
        StrictMode.ThreadPolicy stict =new  StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(stict);
    }

    @NonNull
    @Override
    public WorkerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.workersview_layout,viewGroup,false);
        return  new WorkerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final WorkerViewHolder workerViewHolder, int i) {
        final String userid=employeelist.get(i).getUserId();
        final String employeeId=employeelist.get(i).getEmployeeId();
        final String firstname=employeelist.get(i).getFirstName();
        final String lastname=employeelist.get(i).getLastName();
        final String phone=employeelist.get(i).getPhone();
        final String state=employeelist.get(i).getState();
        final String city=employeelist.get(i).getCity();
        final String address=employeelist.get(i).getAddress();
        final String email=employeelist.get(i).getEmail();
        final String gender=employeelist.get(i).getGender();
        final  String skill=employeelist.get(i).getSkill();
        final  String experiance=employeelist.get(i).getExperiance();
        final  String job=employeelist.get(i).getJobCompleted();
        final  String language=employeelist.get(i).getLanguage();
        final String payment=employeelist.get(i).getPayment();
        final String working=employeelist.get(i).getWorking();
        final String cost=employeelist.get(i).getCost();
        final String available=employeelist.get(i).getAvailable();
        final String imagename=employeelist.get(i).getImage();
        EmployeeModel employeeModel=employeelist.get(i);
        String imgPath= Base_Url +"uploads/"+employeeModel.getImage();
        StrictMode();
        try {
            URL url = new URL(imgPath);
            workerViewHolder.image.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        workerViewHolder.Tvfirstname.setText(firstname+" "+employeeModel.getLastName());
        workerViewHolder.Tvskill.setText(skill+" "+employeeModel.getExperiance());
        workerViewHolder.Tvcost.setText(cost);
        workerViewHolder.Tvaddress.setText(city+", "+address);
        workerViewHolder.Tvjob.setText(job);

        workerViewHolder.Tvphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workerViewHolder.mp.start();
                Uri u=Uri.parse("tel:"+phone);
                Intent intent=new Intent(Intent.ACTION_DIAL,u);
                context.startActivity(intent);
            }
        });

        if (available.equals("1")) {
            workerViewHolder.Tvavail.setText("Available");
            workerViewHolder.Tvavail.setBackgroundColor(Color.parseColor("#43A047"));

        } else {

            workerViewHolder.Tvavail.setText("Unavailable");

            workerViewHolder.Tvavail.setBackgroundColor(Color.parseColor("#F44336"));
        }

        workerViewHolder.favimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workerViewHolder.mp.start();
                SharedPreferences sharedPreferences=context.getSharedPreferences("Users",MODE_PRIVATE);
               final String userIdd=sharedPreferences.getString("id","");
                final String image=sharedPreferences.getString("image","");
                workerViewHolder.builder.setMessage("Are You Sure?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
//                        finish();
                                FavouriteModel favouriteModel=new FavouriteModel(employeeId,userIdd,skill,experiance,job,
                                        language,payment,working,cost,available,imagename,firstname,lastname,phone,state,city,address,email,gender
                                );

                                Api api= BaseUrl.getInstance().create(Api.class);
                                Call<Void> voidCall=api.AddtoFavourite(favouriteModel);

                                voidCall.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        Toast.makeText(context, "Added to Favourite "+firstname+" "+lastname, Toast.LENGTH_SHORT).show();

                                        Notification notification=new NotificationCompat.Builder(context, ChannelCreate.Channel_1)
                                                .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                                                .setContentTitle("Day JoB")
                                                .setContentText("Added to Favourite List")
                                                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                                                .build();
                                        workerViewHolder.mp2.start();
                                        workerViewHolder.notificationManagerCompat.notify(1,notification);
                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                        Toast.makeText(context, "Error "+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                //create a dialog box;
                AlertDialog alert =workerViewHolder.builder.create();
                alert.setTitle("Add to Favourite");
                alert.show();

            }
        });


        workerViewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workerViewHolder.mp.start();
                Intent intent=new Intent(context, WorkerProfileActivity.class);
                intent.putExtra("userid",userid);
                intent.putExtra("eid",employeeId);
                intent.putExtra("firstname",firstname);
                intent.putExtra("lastname",lastname);
                intent.putExtra("phone",phone);
                intent.putExtra("state",state);
                intent.putExtra("city",city);
                intent.putExtra("address",address);
                intent.putExtra("email",email);
                intent.putExtra("gender",gender);
                intent.putExtra("skill",skill);
                intent.putExtra("imagename",imagename);
                intent.putExtra("experiance",experiance);
                intent.putExtra("job",job);
                intent.putExtra("language",language);
                intent.putExtra("payment",payment);
                intent.putExtra("working",working);
                intent.putExtra("cost",cost);
                intent.putExtra("available",available);
                context.startActivity(intent);
            }
        });

    }



    @Override
    public int getItemCount() {
        return  employeelist.size();
    }
    public class WorkerViewHolder extends RecyclerView.ViewHolder{
        private TextView Tvfirstname,Tvcost,Tvskill,Tvaddress, Tvjob,Tvavail;
        private ImageView image,Tvphone,favimage;
        AlertDialog.Builder builder;
        MediaPlayer mp,mp2;
        private NotificationManagerCompat notificationManagerCompat;

        public WorkerViewHolder(@NonNull View v) {
            super(v);

            notificationManagerCompat=NotificationManagerCompat.from(context);

            ChannelCreate channelCreate=new ChannelCreate(context);

            mp2=MediaPlayer.create(context,R.raw.notification);
            channelCreate.createChannel();
            mp= MediaPlayer.create(context,R.raw.buttonclick1);
            favimage = v.findViewById(R.id.favimage);
            image = v.findViewById(R.id.image);
            Tvavail = v.findViewById(R.id.Tvavail);
            Tvfirstname= v.findViewById(R.id.Tvfirstname);
            Tvphone= v.findViewById(R.id.Tvphone);
            Tvcost  = v.findViewById(R.id.Tvcost);
            Tvskill= v.findViewById(R.id.Tvskill);

            Tvaddress= v.findViewById(R.id.Tvaddress);
            builder=new AlertDialog.Builder(context);
            Tvjob= v.findViewById(R.id.Tvjob);
        }
    }
}
