package com.sodelhinews.network

import com.sodelhinews.model.NewsArticle
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{

    @GET("top-headlines")
    fun getNewsByCountry(@Query("country") country: String?, @Query("apiKey")apiKey :String): Call<NewsArticle>

    @GET("top-headlines")
    fun getNewsByCountryAndCategory(@Query("country") country: String?, @Query("category") category : String, @Query("apiKey")apiKey:String): Call<NewsArticle>

}