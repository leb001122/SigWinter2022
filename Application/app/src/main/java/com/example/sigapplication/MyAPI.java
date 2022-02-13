package com.example.sigapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MyAPI {

    @FormUrlEncoded
    @POST("appServer/post")
    Call<List<Disease>> postDisease(@Field("symptom") String symptom);



/*
    @PATCH("/posts/{pk}/")
    Call<PostItem> patch_posts(@Path("pk") int pk, @Body PostItem post);

    @DELETE("/posts/{pk}/")
    Call<PostItem> delete_posts(@Path("pk") int pk);
*/


}
