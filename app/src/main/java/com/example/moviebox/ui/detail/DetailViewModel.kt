package com.example.moviebox.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviebox.api.RetrofitClient
import com.example.moviebox.model.detailMovie.DetailMovieModel
import com.example.moviebox.model.detailSeries.DetailSeries
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {
    private  val _detailMovie = MutableLiveData<DetailMovieModel>()
    val detailMovie:LiveData<DetailMovieModel> get() = _detailMovie
    private val _detailSeries = MutableLiveData<DetailSeries>()
    val detailSeries : LiveData<DetailSeries> get () = _detailSeries


    private val _errorD = MutableLiveData<String>()
    val errorD :LiveData<String> get() =_errorD



    fun getDetailMovie(id : Int){
        RetrofitClient.retrofitService.detailMovie(id).enqueue(object:Callback<DetailMovieModel>{
            override fun onResponse(
                call: Call<DetailMovieModel>,
                response: Response<DetailMovieModel>
            ) {
               if (response.isSuccessful){
                   _detailMovie.postValue(response.body())
               }
                else {
                   _errorD.postValue(response.message())
                }
            }

            override fun onFailure(call: Call<DetailMovieModel>, t: Throwable) {
                _errorD.postValue(t.message)
            }

        })

    }
    fun getDetailSeries(id: Int){
        RetrofitClient.retrofitService.detailSeries(id).enqueue(object :Callback<DetailSeries>{
            override fun onResponse(call: Call<DetailSeries>, response: Response<DetailSeries>) {
              if (response.isSuccessful){
                  _detailSeries.postValue(response.body())
              }
                else{
                    _errorD.postValue(response.message())
                }
            }

            override fun onFailure(call: Call<DetailSeries>, t: Throwable) {
                _errorD.postValue(t.message)
            }

        })
    }
}