package com.example.meteorology.data.remote

import com.example.meteorology.data.Constants.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient() {

    object RemoteApi {

        private val okHttpClient = OkHttpClient.Builder().addInterceptor { chain ->
            val originalRequest = chain.request()
            val originalHttpUrl = originalRequest.url()
            val newUrl = originalHttpUrl
                .newBuilder()
                .addQueryParameter("apiKey", "91e3668bab7d284442e663999b8e9978")
                .build()

            val newRequest = originalRequest.newBuilder().url(newUrl).build()
            chain.proceed(newRequest)
        }

        val retrofitClient = Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

}
