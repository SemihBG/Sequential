package com.semihbkgr.sequential.android.connection;

import com.semihbkgr.sequential.android.entity.Information;
import com.semihbkgr.sequential.android.entity.Vocabulary;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

interface ApiService {

    @GET(ApiUrl.INFO)
    Call<Information[]> getInformation();

    @GET(ApiUrl.VOCABULARY+"/{list_name}/")
    Call<Vocabulary[]> getList(@Path("list_name") String name);


}
