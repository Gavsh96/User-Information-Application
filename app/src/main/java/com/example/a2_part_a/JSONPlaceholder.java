package com.example.a2_part_a;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONPlaceholder {
    @GET("users")
    Call<ArrayList<Person>> getPerson();

    @GET("posts")
    Call<ArrayList<Post>> getPost();
}
