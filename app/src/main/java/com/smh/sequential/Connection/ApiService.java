package com.smh.sequential.Connection;

import com.smh.sequential.Entity.Information;
import com.smh.sequential.Entity.Vocabulary;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface ApiService {

    @GET(ApiUrl.INFO)
    Call<Information[]> getInformation();

    @GET(ApiUrl.VOCABULARY+"/{list_name}/")
    Call<Vocabulary[]> getList(@Path("list_name") String name);


}
