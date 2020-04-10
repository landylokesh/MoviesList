package com.nucleus.movieslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindSearchBoxEvent()
    }

    private fun bindSearchBoxEvent() {
        searchBox.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val moviesList: Call<MoviesListModel> = MoviesListService.getMovies(query)
                moviesList.enqueue(object : Callback<MoviesListModel>{
                    override fun onFailure(call: Call<MoviesListModel>, t: Throwable) {

                    }

                    override fun onResponse(call: Call<MoviesListModel>, response: Response<MoviesListModel>) {
                        response.body()?.let {
                            result.text =  it.results.toString()
                        }
                    }
                })
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }
}


/*
    API Key (v3 auth)
    f2be8d684e732a8d76d2070b7f894bc6

    Example API Request
    https://api.themoviedb.org/3/movie/550?api_key=f2be8d684e732a8d76d2070b7f894bc6

    API Read Access Token (v4 auth)
    eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmMmJlOGQ2ODRlNzMyYThkNzZkMjA3MGI3Zjg5NGJjNiIsInN1YiI6IjVlOTA3YmU5MzdiM2E5MDAxNTg5ZmY2YSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.BpCrdMpI6oXdkJtBMY4QoIIKnCwjjrYgl9iNmncNLKQ

 */
