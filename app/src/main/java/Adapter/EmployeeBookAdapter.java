package Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.e.contracterandworkerfinder.EmployeeBookDetailActivity;
import com.e.contracterandworkerfinder.HirerBookDetailActivity;
import com.e.contracterandworkerfinder.R;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import DayJobApi.Api;
import Url.BaseUrl;
import model.BookingModel;
import model.UpdateBookingModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static Url.BaseUrl.Base_Url;

public class EmployeeBookAdapter extends RecyclerView.Adapter<EmployeeBookAdapter.EmployeeBookViewHolder> {
    private Context context;
    private List<BookingModel> bookinglist;

    public EmployeeBookAdapter(Context context, List<BookingModel> bookinglist) {
        this.context = context;
        this.bookinglist = bookinglist;
    }


    private void StrictMode() {
        StrictMode.ThreadPolicy stict =new  StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(stict);
    }
    @NonNull
    @Override
    public EmployeeBookViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_employee_book_detail,viewGroup,false);
        return new EmployeeBookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final EmployeeBookViewHolder employeeBookViewHolder, int i) {
        final String bookid=bookinglist.get(i).getBookId();
        final String hirerid=bookinglist.get(i).getHirerid();
        final String hirerfirstname=bookinglist.get(i).getHirerfirstName();
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
        String imgPath= Base_Url +"uploads/"+bookingModel.getHirerimage();
//        StrictMode();
//        try {
//            URL url = new URL(imgPath);
//            employeeBookViewHolder.hirerimage.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        StrictMode();
        try {
            URL url=new URL(imgPath);
            employeeBookViewHolder.hirerimage.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        employeeBookViewHolder.employeebookid.setText(bookid);
        employeeBookViewHolder.tvhirerfirstname.setText(hirerfirstname+" "+hirerlastname);
        employeeBookViewHolder.Tvhireremailes.setText(hireremail);
        employeeBookViewHolder.Tvhireraddresses.setText(hirercity+" "+ hireraddress);
        employeeBookViewHolder.Tvavailablestore.setText(available);
        employeeBookViewHolder.Tvstatusstore.setText(status);
        employeeBookViewHolder.employeebookid.setText(bookid);



        employeeBookViewHolder.tvhirerphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri u=Uri.parse("tel:"+hirerphone);
                Intent intent=new Intent(Intent.ACTION_DIAL,u);
                context.startActivity(intent);
            }
        });

        if (available.equals("1")) {
            employeeBookViewHolder.edt_bookavailable.setChecked(true);
            employeeBookViewHolder.Tvemployeeavailable.setText("Available");
            employeeBookViewHolder.Tvemployeeavailable.setBackgroundColor(Color.parseColor("#43A047"));

        } else {
            employeeBookViewHolder.edt_bookavailable.setChecked(false);
            employeeBookViewHolder.Tvemployeeavailable.setText("Unavailable");

            employeeBookViewHolder.Tvemployeeavailable.setBackgroundColor(Color.parseColor("#F44336"));
        }

        if (status.equals("1")) {
            employeeBookViewHolder.edt_bookstatus.setChecked(true);
            employeeBookViewHolder.Tvemployeestatus.setText("Approved");

            employeeBookViewHolder.Tvemployeestatus.setBackgroundColor(Color.parseColor("#43A047"));
        } else {
            employeeBookViewHolder.edt_bookstatus.setChecked(false);
            employeeBookViewHolder.Tvemployeestatus.setText("Not Approved");
            employeeBookViewHolder.Tvemployeestatus.setBackgroundColor(Color.parseColor("#F44336"));
        }
        employeeBookViewHolder.updateBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                employeeBookViewHolder.mp.start();
                Api api= BaseUrl.getInstance().create(Api.class);
                final String bookid=employeeBookViewHolder.employeebookid.getText().toString();
                String availables=employeeBookViewHolder.Tvavailablestore.getText().toString();
                String statuss=employeeBookViewHolder.Tvstatusstore.getText().toString();

                String available;
                if(employeeBookViewHolder.edt_bookavailable.isChecked()){
                    available="1";
                }else{
                    available="0";
                }

                String status;
                if(employeeBookViewHolder.edt_bookstatus.isChecked()){
                    status="1";
                }else{
                    status="0";
                }

                UpdateBookingModel updateBookingModel=new UpdateBookingModel(available,status);
                Call<Void> voidCall=api.UpdateBook(Integer.parseInt(bookid),updateBookingModel);

                voidCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        Toast.makeText(context, "Updated Successfully By "+bookid, Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(context, EmployeeBookDetailActivity.class);
                        context.startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(context, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return bookinglist.size();
    }

    public class EmployeeBookViewHolder extends RecyclerView.ViewHolder{
        ImageView hirerimage,tvhirerphone;
        TextView tvhirerfirstname,Tvhireremailes,Tvhireraddresses,Tvemployeeavailable,Tvemployeestatus,employeebookid,Tvavailablestore,Tvstatusstore;
        Button updateBook;
        AlertDialog.Builder builder;
        CheckBox edt_bookavailable,edt_bookstatus;
        MediaPlayer mp;

        public EmployeeBookViewHolder(@NonNull View itemView) {
            super(itemView);

            mp= MediaPlayer.create(context,R.raw.buttonclick1);
            hirerimage = itemView.findViewById(R.id.hirerimage);
            tvhirerphone= itemView.findViewById(R.id.tvhirerphone);
            tvhirerfirstname= itemView.findViewById(R.id.tvhirerfirstname);
            Tvhireremailes  = itemView.findViewById(R.id.Tvhireremailes);

            updateBook=itemView.findViewById(R.id.updateBook);
            Tvhireraddresses= itemView.findViewById(R.id.Tvhireraddresses);
            employeebookid=itemView.findViewById(R.id.employeebookid);
            Tvavailablestore=itemView.findViewById(R.id.Tvavailablestore);
            edt_bookavailable=itemView.findViewById(R.id.edt_bookavailable);
            edt_bookstatus=itemView.findViewById(R.id.edt_bookstatus);
            Tvstatusstore=itemView.findViewById(R.id.Tvstatusstore);


            builder=new AlertDialog.Builder(context);
            Tvemployeeavailable= itemView.findViewById(R.id.Tvemployeeavailable);
            Tvemployeestatus= itemView.findViewById(R.id.Tvemployeestatus);
        }
    }
}
