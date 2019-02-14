package com.fekrah.dolphin.server;

import com.fekrah.dolphin.helper.SharedHelper;
import com.fekrah.dolphin.models.Countries;
import com.fekrah.dolphin.models.FuckenResponse;
import com.fekrah.dolphin.models.Notification;
import com.fekrah.dolphin.models.OrderResponse;
import com.fekrah.dolphin.models.RegisterResponse;
import com.fekrah.dolphin.models.Specialize;
import com.fekrah.dolphin.models.TermsResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Apis {

    @FormUrlEncoded
    @POST("AppUser/Login")
    Call<RegisterResponse> logInClient(@Field("user_phone") String user_phone, @Field("user_pass") String user_pass);

    // register user client
    @Multipart
    @POST("AppUser/SignUp")
    Call<RegisterResponse> registerUser(
            @Part("user_type") int user_type,
            @Part("user_full_name") String user_full_name,
            @Part("user_pass") String user_pass,
            @Part("user_phone") String user_phone,
            @Part("user_email") String user_email,
            @Part("user_google_lat") String user_google_lat,
            @Part("user_google_long") String user_google_long,
            @Part("user_city") String user_city,
            @Part("user_token_id") String user_token_id,
            @Part("user_country") String user_country,
            @Part("user_address") String user_address,
            @Part("user_specialization_id_fk") int user_specialization_id_fk,
            @Part MultipartBody.Part image
    );

    // register user client
    @Multipart
    @POST("ApiClient/Resevation/48/8")
    Call<FuckenResponse> bookATechnical(
//            @Path("user_id") int user_id,
//            @Path("id_services") int id_services,
            @Part("order_type") int order_type,
            @Part("order_hours") int order_hours,
            @Part("order_details") String order_details,
            @Part("order_address") String order_address,
            @Part("order_date") String order_date,
            @Part("order_address_lat") String order_address_lat,
            @Part("order_address_long") String order_address_long
//            ,
//            @Part MultipartBody.Part order_image,
//            @Part MultipartBody.Part order_voice,
//            @Part MultipartBody.Part order_video
    );

    // get all cities
    @GET("AboutApp/Countries")
    Call<List<Countries>> getCountries();

    @GET("Api/Services")
    Call<List<Specialize>> getSpecializes();

    @GET("ApiClient/MyNotifications/{id}")
    Call<List<Notification>> getNotifications(@Path("id") String id);

    @GET("ApiClient/MyOrders/{id}")
    Call<List<OrderResponse>> getCurrentOrders(@Path("id") String id);

    @GET("ApiClient/NewOrders/{id}")
    Call<List<OrderResponse>> getNewtOrders(@Path("id") String id);

    @FormUrlEncoded
    @POST("AboutApp/ContactUs")
    Call<FuckenResponse> contactUs(@Field("name") String name, @Field("phone") String phone, @Field("message") String message);

    @GET("AboutApp/TermsAndConditions")
    Call<TermsResponse> geTerms();

    @FormUrlEncoded
    @POST("ApiClient/Confirm/{user_id}/{order_id}")
    Call<FuckenResponse> confirmOrder(@Path("user_id") String user_id, @Path("order_id") String order_id,@Field("confirm_type") int confirm_type);


    @FormUrlEncoded
    @POST("ApiClient/Confirm/{user_id}/{order_id}")
    Call<FuckenResponse> orderAnotherDate(@Path("user_id") String user_id,
                                          @Path("order_id") String order_id,
                                          @Field("confirm_type") int confirm_type,
                                          @Field("order_date") String order_date);

    @FormUrlEncoded
    @POST("ApiTechnical/Payment/{user_id}/{order_id}")
    Call<FuckenResponse> payment(
            @Path("user_id") String user_id,
            @Path("order_id") String order_id,
            @Field("client_id_fk") int client_id_fk,
            @Field("work_hours") int work_hours,
            @Field("hour_cost") int hour_cost,
            @Field("transfer_cost") int transfer_cost,
            @Field("total_cost") int total_cost,
            @Field("discount") int discount,
            @Field("payment_method") int payment_method

    );


}


//5cfe2040db3740178874044be7f6385d