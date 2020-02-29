package com.baashaa.retrofitexample

import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface WikiApiService {

    @GET("api.php")
    fun hitCountCheck(@Query("action") action: String,
                      @Query("format") format: String,
                      @Query("list") list: String,
                      @Query("srsearch") srsearch: String):
            Observable<Result>


    companion object {
        fun create(): WikiApiService {

            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            val okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor) // same for .addInterceptor(...)

                .build()

            val retrofit = Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl("https://en.wikipedia.org/w/")
                .build()

            return retrofit.create(WikiApiService::class.java)
        }
    }


}
