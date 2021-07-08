package UserFragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.e.contracterandworkerfinder.R;

public class EmployeeProfile extends Fragment {
//
//    ImageView Ivprofileimage;
//    TextView Tvname,Tvphonenumber,Tvaddresses,Tvemails;
//    Button Btnworkprof;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_employee_profile,container,false);

//        Ivprofileimage=findViewById(R.id.Ivimageprofile);
//        Btnworkprof=view.findViewById(R.id.Btnworkprof);
//        Tvname=view.findViewById(R.id.Tvnames);
//        Tvphonenumber=view.findViewById(R.id.Tvphonenumbers);
//        Tvemails=view.findViewById(R.id.Tvemailes);
//        Tvaddresses=view.findViewById(R.id.Tvaddresses);
//
//        displayUserProfile();


    }

    //    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//                             @Nullable Bundle savedInstanceState) {
//        View view= inflater.inflate(R.layout.activity_employee_profile,container,false);
//
//        Ivprofileimage=view.findViewById(R.id.Ivimageprofile);
//        Btnworkprof=view.findViewById(R.id.Btnworkprof);
//        Tvname=view.findViewById(R.id.Tvnames);
//        Tvphonenumber=view.findViewById(R.id.Tvphonenumbers);
//        Tvemails=view.findViewById(R.id.Tvemailes);
//        Tvaddresses=view.findViewById(R.id.Tvaddresses);
//
//        displayUserProfile();
//        return view;
//    }

    private void displayUserProfile() {
//        SharedPreferences sharedPreferences=this.getActivity().getSharedPreferences("Users",MODE_PRIVATE);
//        String id=sharedPreferences.getString("id","");
//
//        Api api = BaseUrl.getInstance().create(Api.class);
//        Call<UserModel> listCall = api.getUserById(Integer.parseInt(id));
//
//        listCall.enqueue(new Callback<UserModel>() {
//            @Override
//            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
//                String firstname=response.body().getFirstName();
//                String lastname=response.body().getLastName();
//                String address=response.body().getAddress();
//                String city=response.body().getCity();
//                String state=response.body().getState();
//                String phone=response.body().getPhone();
//                String email=response.body().getEmail();
//                String gender=response.body().getGender();
//                String image=response.body().getImage();
//
//
//                String imgPath= Base_Url +"uploads/"+image;
//                try {
//                    URL url = new URL(imgPath);
//                    Ivprofileimage.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
//                }
//                catch (Exception e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<UserModel> call, Throwable t) {
//                Toast.makeText(getView().getContext(), "Error" + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
