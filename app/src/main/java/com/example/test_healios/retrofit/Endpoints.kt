package com.example.test_healios.retrofit

import com.example.test_healios.retrofit.model.SingleComment
import com.example.test_healios.retrofit.model.SinglePost
import com.example.test_healios.retrofit.model.SingleUser
import retrofit2.Call
import retrofit2.http.GET

interface Endpoints {
    @GET("users")
    fun getUsersList(): Call<List<SingleUser>>

    @GET("posts")
    fun getPostsList(): Call<List<SinglePost>>

    @GET("comments")
    fun getCommentsList(): Call<List<SingleComment>>
}