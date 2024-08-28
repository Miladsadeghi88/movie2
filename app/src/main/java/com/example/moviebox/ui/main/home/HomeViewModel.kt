package com.example.moviebox.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviebox.api.RetrofitClient
import com.example.moviebox.model.popularMovies.PopularMoviesModel
import com.example.moviebox.model.popularSeries.PopularSeriesModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel:ViewModel() {
    private val _movies = MutableLiveData<PopularMoviesModel>()
    val movies: LiveData<PopularMoviesModel> get()=_movies
    private val _series = MutableLiveData<PopularSeriesModel>()
    val  series : LiveData<PopularSeriesModel> get() = _series




    private val _error = MutableLiveData<String>()
    val error : LiveData<String> get() = _error

    fun getPopularMovies(){
        RetrofitClient.retrofitService.popularMoviesInMain().enqueue(object :
            Callback<PopularMoviesModel> {
            override fun onResponse(
                call: Call<PopularMoviesModel>,
                response: Response<PopularMoviesModel>
            ) {
                if (response.isSuccessful){
                    _movies.postValue(response.body())
                } else {
                    _error.postValue(response.message())
                }

            }

            override fun onFailure(call: Call<PopularMoviesModel>, t: Throwable) {
                _error.postValue(t.message)
            }

        })


    }
    fun getPopularSeries(){
        RetrofitClient.retrofitService.popularSeriesInMain().enqueue(object :Callback<PopularSeriesModel>{
            override fun onResponse(
                call: Call<PopularSeriesModel>,
                response: Response<PopularSeriesModel>
            ) {
                if (response.isSuccessful){
                    _series.postValue(response.body())
                }
                else {
                    _error.postValue(response.message())
                }
            }

            override fun onFailure(call: Call<PopularSeriesModel>, t: Throwable) {
                _error.postValue(t.message)
            }

        })
    }


}