package com.nucleus.movieslist

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDBService{
    @GET("search/movie?api_key=f2be8d684e732a8d76d2070b7f894bc6&language=en-US&page=1&include_adult=false")
    fun getMovies(@Query("query") query:String): Call<MoviesListModel>
}
var MListRFit: Retrofit = Retrofit.Builder()
    .baseUrl("https://api.themoviedb.org/3/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

var MoviesListService: TMDBService = MListRFit.create(TMDBService::class.java)

