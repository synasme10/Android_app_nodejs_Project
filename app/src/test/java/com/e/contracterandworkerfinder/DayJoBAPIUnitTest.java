package com.e.contracterandworkerfinder;

import org.junit.Test;

import java.io.IOException;

import DayJobApi.Api;
import Url.BaseUrl;
import model.BookingModel;
import model.EmployeeModel;
import model.LoginModel;
import model.LoginRegisterResponse;
import model.UpdateUser;
import model.UserModel;
import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DayJoBAPIUnitTest {

    Api api= BaseUrl.getInstance().create(Api.class);
    @Test
    public void testLogin() throws IOException
    {
        LoginModel loginModel=new LoginModel("sanam@gmail.com","sanam");


        Api api= BaseUrl.getInstance().create(Api.class);
        Call<LoginRegisterResponse> call=api.checkUser(loginModel);

        try
        {
            Response<LoginRegisterResponse> response=call.execute();
            LoginRegisterResponse loginRegisterResponse=response.body();
            assertTrue(response.isSuccessful() && loginRegisterResponse.getResult().getUsertype().equals("Employee"));

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }


    @Test
    public void testRegister() throws IOException
    {
        UserModel userModel=new UserModel("person_3.jpg-1562613416058.jpg","Umesh","Maharjan","9849248921","Provision No. 3",
                "Kathmandu","Dillibazar","Male","test@gmail.com","hello","Hirer");
        Api api= BaseUrl.getInstance().create(Api.class);
        Call<Void> call=api.Register(userModel);

        try
        {
            Response<Void> response=call.execute();
            assertTrue(response.isSuccessful());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Test
    public void testWorkRegister() throws IOException
    {
        EmployeeModel employeeModel=new EmployeeModel("5","Plumber","4 years","many","nepali",
                "check","7 days","500","1","person_3.jpg-1562613416058.jpg","Umesh","Maharjan","9849248921","Provision No. 3",
                "Kathmandu","Dillibazar","test@gmail.com","Male");
        Api api= BaseUrl.getInstance().create(Api.class);

        Call<Void> call=api.addWork(employeeModel);

        try
        {
            Response<Void> response=call.execute();
            assertTrue(response.isSuccessful());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Test
    public void testUserupdate() throws IOException
    {

        UpdateUser updateUser=new UpdateUser("Umesh","Maharjan","9849248921","Provision No. 3",
                "Kathmandu","Dillibazar","Male");

        Call<Void> call=api.UpdateUser(8,updateUser);

        try
        {
            Response<Void> response=call.execute();
            assertTrue(response.isSuccessful());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }


    @Test
    public void deleteWorkProfile() throws IOException
    {

        Call<Void> call=api.DeleteEmployee(2);

        try
        {
            Response<Void> response=call.execute();
            assertTrue(response.isSuccessful());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Test
    public void getEmployeeDetail() throws IOException
    {

        Call<UserModel> call=api.getUserById(32);

        try
        {
            Response<UserModel> response=call.execute();
            assertTrue(response.isSuccessful());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }




}