package com.example.test_healios.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {
    private var retrofit: Retrofit? = null
    private val BASE_URL = "http://jsonplaceholder.typicode.com/"

    val client: Endpoints
        get() = getClient(BASE_URL).create(Endpoints::class.java)

    fun getClient(baseUrl: String): Retrofit {
        if(retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}

