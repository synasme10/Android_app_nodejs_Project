package DayJobApi;

import java.util.List;
import java.util.Map;

import model.BookingModel;
import model.EmployeeModel;
import model.FavouriteModel;
import model.ImageResponse;
import model.LoginRegisterResponse;
import model.LoginModel;
import model.UpdateBookingModel;
import model.UpdateEmployee;
import model.UpdateUser;
import model.UserModel;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Api {

    @POST("v1/login")
    Call<LoginRegisterResponse> checkUser(@Body LoginModel loginModel);


    @Multipart
    @POST("uploads")
    Call<ImageResponse> uploadImage( @Part MultipartBody.Part img );


    // 1. Using Class
    @POST("/v1/userstest")
    Call<Void> Register( @Body UserModel usermodel);

    // 2. Using @Field if attributes are small
//    @FormUrlEncoded
//    @POST("/v1/users")
//    Call<Void> Register( @Field("name") String name, @Field("password") String desc);

    // 3. Using @FieldMap
    @FormUrlEncoded
    @POST("/v1/users")
    Call<Void> Register(@FieldMap Map<String,String> map);


    //emptest
    @POST("v1/employeedetail")
    Call<Void> addWork(@Body EmployeeModel employeeModel);

    //getting all employeedetail so that hirer can view the detail
    @GET("v1/usersdetail")
    Call<List<EmployeeModel>> getAllEmployee();

    @GET("v1/viewoneemployee/{user_id}")
    Call<EmployeeModel> getEmployeeById(@Path("user_id") int userid);

    @PUT("v1/editworkdetail/{employee_id}")
    Call<Void> UpdateEmployee(@Path("employee_id") int empid,@Body UpdateEmployee updateEmployee);

    @DELETE("v1/deleteworkdetail/{employee_id}")
    Call<Void> DeleteEmployee(@Path("employee_id") int empid);

    @POST("v1/employeebook")
    Call<Void> BookEmployee(@Body BookingModel bookingModel);

    @GET("v1/vieweachusers/{userid}")
    Call<UserModel> getUserById(@Path("userid") int userid);

    @PUT("v1/editmydetail/{user_id}")
    Call<Void> UpdateUser(@Path("user_id") int empid,@Body UpdateUser updateUser);


    @GET("v1/book/{user_id}")
    Call<List<BookingModel>> getBookById(@Path("user_id") int user_id);

    @DELETE("v1/deletebook/{book_id}")
    Call<Void> DeleteBook(@Path("book_id") int book_id);

    @GET("/v1/employeebookdetail/{user_id}")
    Call<List<BookingModel>> getBookByIdForEmployee(@Path("user_id") int user_id);

    @PUT("/v1/editbookstatus/{bookid}")
    Call<Void> UpdateBook(@Path("bookid") int bookid,@Body UpdateBookingModel updateBookingModel);


    @POST("v1/addtofav")
    Call<Void> AddtoFavourite(@Body FavouriteModel favouriteModel);


    @GET(" v1/favourite/{user_id}")
    Call<List<FavouriteModel>> getFavourite(@Path("user_id") int user_id);


    @DELETE("v1/deletefavourite/{fav_id}")
    Call<Void> DeleteFav(@Path("fav_id") int fav_id);
}
