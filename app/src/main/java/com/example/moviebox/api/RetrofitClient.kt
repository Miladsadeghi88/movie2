package com.example.moviebox.api
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder: Request.Builder = original.newBuilder()
                .header("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5NDc1MTBmZmRmOTJhMWZhMmY4MGJhYTZlYzEzY2MzNiIsIm5iZiI6MTcyNDY3MDI2Ni45Mzg5NzUsInN1YiI6IjY0MDA5MDhmOTY1M2Y2MDA4NTNkOTc3OCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.5YqcUfaSapxOTrxjAWstvVixtRGqQOYG5rPPVC-XEBY")
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }
        .build()

    val retrofitService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
