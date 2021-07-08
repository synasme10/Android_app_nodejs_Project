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
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.e.contracterandworkerfinder.HirerBookDetailActivity;
import com.e.contracterandworkerfinder.HirerFavouriteDetail;
import com.e.contracterandworkerfinder.R;
import com.e.contracterandworkerfinder.WorkerProfileActivity;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import DayJobApi.Api;
import Url.BaseUrl;
import broadcaste.BroadcastReceiverDayjob;
import channelcreation.ChannelCreate;
import model.EmployeeModel;
import model.FavouriteModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static Url.BaseUrl.Base_Url;
import static android.content.Context.MODE_PRIVATE;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder> {
    private Context context;
    private List<FavouriteModel> favouriteModelList;

    public FavouriteAdapter(Context context, List<FavouriteModel> favouriteModelList) {
        this.context = context;
        this.favouriteModelList = favouriteModelList;
    }

    private void StrictMode() {
        StrictMode.ThreadPolicy stict =new  StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(stict);
    }

    @NonNull
    @Override
    public FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_hirer_favourite_detail,viewGroup,false);
        return  new FavouriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FavouriteViewHolder favouriteViewHolder, int i) {
        final String favid=favouriteModelList.get(i).getFavId();
        final String userid=favouriteModelList.get(i).getUserId();
        final String employeeId=favouriteModelList.get(i).getEmployeeId();
        final String firstname=favouriteModelList.get(i).getFirstName();
        final String lastname=favouriteModelList.get(i).getLastName();
        final String phone=favouriteModelList.get(i).getPhone();
        final String state=favouriteModelList.get(i).getState();
        final String city=favouriteModelList.get(i).getCity();
        final String address=favouriteModelList.get(i).getAddress();
        final String email=favouriteModelList.get(i).getEmail();
        final String gender=favouriteModelList.get(i).getGender();
        final  String skill=favouriteModelList.get(i).getSkill();
        final  String experiance=favouriteModelList.get(i).getExperiance();
        final  String job=favouriteModelList.get(i).getJobCompleted();
        final  String language=favouriteModelList.get(i).getLanguage();
        final String payment=favouriteModelList.get(i).getPayment();
        final String working=favouriteModelList.get(i).getWorking();
        final String cost=favouriteModelList.get(i).getCost();
        final String available=favouriteModelList.get(i).getAvailable();
        final String imagename=favouriteModelList.get(i).getImage();

        String imgPath= Base_Url +"uploads/"+imagename;
        StrictMode();
        try {
            URL url = new URL(imgPath);
            favouriteViewHolder.Ivfavimage.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (available.equals("1")) {
            favouriteViewHolder.Ivfavavailable.setText("Available");
            favouriteViewHolder.Ivfavavailable.setBackgroundColor(Color.parseColor("#43A047"));

        } else {

            favouriteViewHolder.Ivfavavailable.setText("Unavailable");
            favouriteViewHolder.Ivfavavailable.setBackgroundColor(Color.parseColor("#F44336"));
        }
        favouriteViewHolder.tvfavname.setText(firstname+" "+lastname);

        favouriteViewHolder.Tvfavskill.setText(skill+" "+experiance);
        favouriteViewHolder.tvfavaddress.setText(city+", "+address);

        favouriteViewHolder.Tvfavjob.setText(job);
        favouriteViewHolder.favouriteid.setText(favid);
        favouriteViewHolder.Tvfavcost.setText(cost);

        favouriteViewHolder.Tvfavphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favouriteViewHolder.mp.start();
                Uri u=Uri.parse("tel:"+phone);
                Intent intent=new Intent(Intent.ACTION_DIAL,u);
                context.startActivity(intent);
            }
        });

        favouriteViewHolder.deletefav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favouriteViewHolder.mp.start();
                favouriteViewHolder.builder.setMessage("Are You Sure?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
//                        finish();
                                final String favid=favouriteViewHolder.favouriteid.getText().toString();

                                Api api= BaseUrl.getInstance().create(Api.class);
                                Call<Void> voidCall=api.DeleteFav(Integer.parseInt(favid));

                                voidCall.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        Toast.makeText(context, "Deleted Successfully By "+favid, Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(context, HirerFavouriteDetail.class);
                                        context.startActivity(intent);
                                        Notification notification=new NotificationCompat.Builder(context, ChannelCreate.Channel_1)
                                                .setSmallIcon(R.drawable.ic_notifications_active_black_24dp)
                                                .setContentTitle("Day JoB")
                                                .setContentText("Deleted Favourite")
                                                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                                                .build();
                                        favouriteViewHolder.mp2.start();
                                        favouriteViewHolder.notificationManagerCompat.notify(1,notification);
                                    }

                                    @Override
                                    public void onFailure(Call<Void> call, Throwable t) {
                                        Toast.makeText(context, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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
                AlertDialog alert =favouriteViewHolder.builder.create();
                alert.setTitle("Delete your Favourite ");
                alert.show();
            }
        });

        favouriteViewHolder.Ivfavimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favouriteViewHolder.mp.start();
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
        return favouriteModelList.size();
    }

    public class FavouriteViewHolder extends RecyclerView.ViewHolder{

        ImageView Ivfavimage,Tvfavphone;
        TextView tvfavname,Ivfavavailable,Tvfavskill,tvfavaddress,Tvfavjob,Tvfavcost,favouriteid;
        Button deletefav;
        AlertDialog.Builder builder;
        MediaPlayer mp;
        MediaPlayer mp2;
        Button Btnlogin;
        private NotificationManagerCompat notificationManagerCompat;
        BroadcastReceiverDayjob broadcastReceiver;



        public FavouriteViewHolder(@NonNull View itemView) {
            super(itemView);
            notificationManagerCompat=NotificationManagerCompat.from(context);

            ChannelCreate channelCreate=new ChannelCreate(context);
            channelCreate.createChannel();
            mp= MediaPlayer.create(context,R.raw.buttonclick1);
            mp2=MediaPlayer.create(context,R.raw.notification);
            Ivfavimage=itemView.findViewById(R.id.Ivfavimage);
            tvfavname=itemView.findViewById(R.id.tvfavname);
            Tvfavcost=itemView.findViewById(R.id.Tvfavcost);
            Ivfavavailable=itemView.findViewById(R.id.Ivfavavailable);
            Tvfavphone=itemView.findViewById(R.id.Tvfavphone);
            Tvfavskill=itemView.findViewById(R.id.Tvfavskill);
            tvfavaddress=itemView.findViewById(R.id.Tvfavaddress);
            Tvfavjob=itemView.findViewById(R.id.Tvfavjob);

            builder=new AlertDialog.Builder(context);
            deletefav=itemView.findViewById(R.id.deletefav);
            favouriteid=itemView.findViewById(R.id.favouriteid);

        }
    }
}
