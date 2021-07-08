# stw300cem-final-project-synasme10
stw300cem-final-project-synasme10 created by GitHub Classroom

# Title
DayJOB- Worker Finder Application

# INTRODUCTION
DayJOB Application is smart collaboration application that organizes and simplifies work process.
It helps to provide solution focusing on the worker of unorganized sector such as mason, carpenters, plumber, painter, 
construction worker. The application will show the detail of worker such as their location, Phone number, worker skills,
price, etc. So, the hirer can hire them according to their skills. User can hire qualified worker temporarily or permanently. 
This application helps everyone to save time and collaborateefficiently. The application helps to find quality worker with the
touch of your finger.This android application give solution through which workers register themselves for a specific skill 
they have. Application makes easy to find the worker and hired them quickly. This application is medium for easiest and best 
way for employers to post job details and be hire fast. With the help of the information users, contractors or organized 
sectors can book the workers as per their requirement and hire them. 

# Justification
 Workers can create work Profile and post their profession details. After that Hirer can view workers profile and view all the 
 details of the worker and hire worker accordingly. Some hirer may want quality worker spending lots of wages while some hirer 
 may want to hire worker who wants to work for cheap amount. Then Worker will know who hired them they can either approved or 
 disapproved. Worker can update their availability as they can be hire by someone else already and be unavailable for some time.
 It saves both time.
 
 # AIMS AND OBJECTIVES
 
 # AIMS
1.	My project aim is to develop an android application through which contractor can hire the worker as per requirement
  easily and quickly.
2.	To provide user friendly GUI so user can easily interact with the application .
3.	The worker and contractor save their time and collaborate with each other easily.
4. To make life easy of both employee and hirer.

# OBJECTIVES
1. To perform feasibility analysis.
2. To identify the requirement, needs for the application to be completed.
3. To develop fully working android application which helps the contractor and worker.
4.	To develop fully tested application to be perform efficiently.
5.	To provide a perfect medium through which Hirer can hire the quality/skillful/rightful worker for specific work.


# FEATURES
1.	Information regarding worker skills, location, contact number, etc. are registered via workers.
2.	Hirer can search for particular worker easily.
3.	Worker can mark the price per Hr for their payment so that they can be hired and get satisfy with the payment.
4.	Users/Contractor can easily fill the vacant jobs and Workers can post their job vacancies and be hired fast.
5.	Project will help everyone to save time and collaborate easily.
6. Hirer can add Employee in their favourite list and hire them later.
7. User can updated their status like availability and hire approval.
8. User And Hire can call each other with click on phone icon and don't have to type the number everytime.

# Youtube Link
https://www.youtube.com/watch?v=U34u_mNBw_I&feature=youtu.be

# GitHub Link Api Backend Link
https://github.com/stw304cem/t2-backend-api-synasme10

# Rest Client 
An application program interface(Api) that uses HTTP requests to handle GET, PUT, POST and DELETE Data is all Rest Client.
They use GET to retrieve a resource; PUT to change the state of or update a resource, which can be an object, file or block; POST to create that resource ; and DELETE to remove it. A rest client can access and modify the rest resources. RESTful Client are applications capable to consume and operate with resources exposed by RESTful servers, under the same premises.


# Retrofit

A REST Client for Java and Android is known as Retrofit which is type-safe HTTP client. It make comparatively easy to retreive and upload JSON (or other structured updated) via REST based webservice. You need to configure converter for data serialization. Typically for JSON you use GSon. Every method of interface represents one possible API call. But it must have HTTP annotation (GET, POST, etc).
 Example:  
 @GET("v1/login")
 Call<List<LoginModel>> getUsers();
 
 @GET("v1/book/{user_id}")
    Call<List<BookingModel>> getBookById(@Path("user_id") int user_id);
 
# GET 
Get is used to request data from a specialized resource.

# POST
POST request method requests that a web server accepts the data enclosed in the body of the request message and most likely to store it. IT is used when file uploading (images) and submitting value from form.

# DELETE
Delete method deletes the specified resources. The DELETE method requests that the origin server delete the resource identified by the Request-URI. 

# PUT
The URI in a PUT request identifies the entity enclosed with the request. The HTTP PUT request method creates a new resource or replaces a representation of the target resource.



# Conclusion
Hence, DayJOB Application is smart collaboration application that organizes and simplifies work process for both workers and hirers. WOrker can registered their work profile and hirer can view and book the worker as per their requirement. I use Retrofit as it make comparatively easy to retreive and upload JSON (or other structured updated) via REST based webservice. Application uses CRUD operation to work completely. HTTP annotation POST, DELETE, PUT and GET request were written. 
Finally, I successfully create a android application that makes life of workers and hirers more advance, time saving and
easy.
