package com.example.optusdemo.module

import com.example.optusdemo.utils.ApiInterface
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/*
* Creates the instance of Retrofit for API call and provides method to call the API
 */

class ApiModule {

    private var retrofit: Retrofit? = null
    private val baseUrl = "https://jsonplaceholder.typicode.com"

    // function returns instance of retrofit
    private fun provideApiRetrofit(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(provideOkHttpClient())
                .build()
        }
        return retrofit
    }

    // function returns the instance of OkHttpClient
    private fun provideOkHttpClient(): OkHttpClient? {
        val builder = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(provideHttpLoggingInterceptor())
        builder.networkInterceptors().add(Interceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            requestBuilder.header("Content-Type", "application/json")
            chain.proceed(requestBuilder.build())
        })
        return builder.build()
    }

    // function returns the instance of HttpLoggingInterceptor
    private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor? {
        val logging = HttpLoggingInterceptor()
        // set your desired log level
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    // Function returns the instance of retrofit
    fun provideAllApi(): ApiInterface? {
        return provideApiRetrofit()!!.create(ApiInterface::class.java)
    }

}