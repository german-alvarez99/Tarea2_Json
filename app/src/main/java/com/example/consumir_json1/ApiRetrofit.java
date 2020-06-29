package com.example.consumir_json1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ApiRetrofit {

    String Url="https://api-uat.kushkipagos.com/";
    @Headers({"Public-Merchant-Id:84e1d0de1fbf437e9779fd6a52a9ca18"})
    @GET("transfer-subscriptions/v1/bankList")
    Call<List<Banco>> getBankList();
}
