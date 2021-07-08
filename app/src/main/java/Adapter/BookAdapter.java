package Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.e.contracterandworkerfinder.DashboardHireActivity;
import com.e.contracterandworkerfinder.EmployeeBookDetailActivity;
import com.e.contracterandworkerfinder.HirerBookDetailActivity;
import com.e.contracterandworkerfinder.R;
import com.e.contracterandworkerfinder.UpdateEmployeeDetailActivity;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import DayJobApi.Api;
import Url.BaseUrl;
import model.BookingModel;
import model.EmployeeModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static Url.BaseUrl.Base_Url;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private Context context;
    private List<BookingModel> bookinglist;

    public BookAdapter(Context context, List<BookingModel> list) {
        this.context = context;
        this.bookinglist = list;
    }

    private void StrictMode() {
        StrictMode.ThreadPolicy stict =new  StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(stict);
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_hirer_book_detail,viewGroup,false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BookViewHolder bookViewHolder, int i) {
        final String bookid=bookinglist.get(i).getBookId();
        final String hirerid=bookinglist.get(i).getHirerid();
        final String hirefirstname=bookinglist.get(i).getHirerfirstName();
        final String hirerlastname=bookinglist.get(i).getHirerlastName();
        final String hirerphone=bookinglist.get(i).getHirerphone();
        final String hirercity=bookinglist.get(i).getHirercity();
        final String hireraddress=bookinglist.get(i).getHireraddress();
        final String hireremail=bookinglist.get(i).getHireremail();
        final String hirerimage=bookinglist.get(i).getHirerimage();
        final String userid=bookinglist.get(i).getUserId();
        final String employeeId=bookinglist.get(i).getEmployeeId();
        final String firstname=bookinglist.get(i).getFirstName();
        final String lastname=bookinglist.get(i).getLastName();
        final String phone=bookinglist.get(i).getPhone();
        final String state=bookinglist.get(i).getState();
        final String city=bookinglist.get(i).getCity();
        final String address=bookinglist.get(i).getAddress();
        final String email=bookinglist.get(i).getEmail();
        final String gender=bookinglist.get(i).getGender();
        final  String skill=bookinglist.get(i).getSkill();
        final  String experiance=bookinglist.get(i).getExperiance();
        final  String job=bookinglist.get(i).getJobCompleted();
        final  String language=bookinglist.get(i).getLanguage();
        final String payment=bookinglist.get(i).getPayment();
        final String working=bookinglist.get(i).getWorking();
        final String cost=bookinglist.get(i).getCost();
        final String available=bookinglist.get(i).getAvailable();
        final String imagename=bookinglist.get(i).getImage();
        final String status=bookinglist.get(i).getStatus();

        BookingModel bookingModel=bookinglist.get(i);
        String imgPath= Base_Url +"uploads/"+bookingModel.getImage();
        StrictMode();
        try {
            URL url = new URL(imgPath);
            bookViewHolder.employeeimage.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        bookViewHolder.bookid.setText(bookid);
        bookViewHolder.tvfirstname.setText(firstname+" "+lastname);
        bookViewHolder.tvskill.setText(skill+" "+experiance);
        bookViewHolder.tvcost.setText(cost);
        bookViewHolder.tvaddress.setText(city+", "+address);
        bookViewHolder.tvjob.setText(job);
        bookViewHolder.Tvavailable.setText(available);

        if(available.equals("1"))
        {
            bookViewHolder.Tvavailable.setText("Available");
        bookViewHolder.Tvavailable.setBackgroundColor(Color.parseColor("#43A047"));
        }
        else
        {
            bookViewHolder.Tvavailable.setText("Unavailable");
            bookViewHolder.Tvavailable.setBackgroundColor(Color.parseColor("#F44336"));
        }

        if(status.equals("1"))
        {
            bookViewHolder.Tvstatus.setText("Approved");
//            bookViewHolder.Tvstatus.setBackgroundColor(Color.GREEN);
                    bookViewHolder.Tvstatus.setBackgroundColor(Color.parseColor("#43A047"));
        }
        else
        {
            bookViewHolder.Tvstatus.setText("Not Approved");
            bookViewHolder.Tvstatus.setBackgroundColor(Color.parseColor("#F44336"));
        }


        bookViewHolder.tvphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookViewHolder.mp.start();
                Uri u=Uri.parse("tel:"+phone);
                Intent intent=new Intent(Intent.ACTION_DIAL,u);
                context.startActivity(intent);
            }
        });

        bookViewHolder.deleteBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookViewHolder.mp.start();
                bookViewHolder.builder.setMessage("Are You Sure?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
//                        finish();
                                final String bookid=bookViewHolder.bookid.getText().toString();

                                Api api= BaseUrl.getInstance().create(Api.class);
                                Call<Void> voidCall=api.DeleteBook(Integer.parseInt(bookid));

                                voidCall.enqueue(new Callback<Void>() {
                                    @Override
                                    public void onResponse(Call<Void> call, Response<Void> response) {
                                        Toast.makeText(context, "Deleted Successfully By "+bookid, Toast.LENGTH_SHORT).show();
                                        Intent intent=new Intent(context, HirerBookDetailActivity.class);
                                        context.startActivity(intent);
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
                AlertDialog alert =bookViewHolder.builder.create();
                alert.setTitle("Delete your Work Profile ");
                alert.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return bookinglist.size();
    }


    public class BookViewHolder extends RecyclerView.ViewHolder{

        ImageView employeeimage,tvphone,Imgback;
        TextView tvfirstname,tvcost,tvskill,tvaddress,tvjob,Tvavailable,Tvstatus,bookid;
        Button deleteBook;
        AlertDialog.Builder builder;
        final MediaPlayer mp;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            employeeimage = itemView.findViewById(R.id.employeeimage);

             mp=MediaPlayer.create(context,R.raw.buttonclick1);
            tvphone= itemView.findViewById(R.id.tvphone);
            tvfirstname= itemView.findViewById(R.id.tvfirstname);
            tvcost  = itemView.findViewById(R.id.tvcost);
            tvskill= itemView.findViewById(R.id.tvskill);
            deleteBook=itemView.findViewById(R.id.deleteBook);
            tvaddress= itemView.findViewById(R.id.tvaddress);
            bookid=itemView.findViewById(R.id.bookid);
            tvjob= itemView.findViewById(R.id.tvjob);
            builder=new AlertDialog.Builder(context);
            Tvavailable= itemView.findViewById(R.id.Tvavailable);
            Tvstatus= itemView.findViewById(R.id.Tvstatus);

        }
    }
}
