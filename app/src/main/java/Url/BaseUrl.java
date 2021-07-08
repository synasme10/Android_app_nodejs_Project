package Url;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseUrl {

//    public static final String Base_Url="http://10.0.2.2:3000/";
//    public static final String Base_Url="http://192.168.1.20:3000/";
    public static final String Base_Url="http://192.168.1.69:3000/";
    public static String Cookie="";

    public static Retrofit getInstance(){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(Base_Url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
