package com.ismailhakkiaydin.movies.data.remote

import com.ismailhakkiaydin.movies.model.detail.MovieDetailResponse
import com.ismailhakkiaydin.movies.model.movie.MovieResponse
import com.ismailhakkiaydin.movies.model.search.MovieSearchResponse
import com.ismailhakkiaydin.movies.model.videos.MovieVideoResponse
import com.ismailhakkiaydin.movies.util.Constant
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private val api = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(getOkhttpClient())
        .build()
        .create(ApiService::class.java)

    fun getTopRatedMovies(): Single<MovieResponse>{
        return api.getTopRatedMovies()
    }

    fun getPopularMovies(): Single<MovieResponse>{
        return api.getPopularMovies()
    }

    fun getUpcomingMovies(): Single<MovieResponse>{
        return api.getUpComingMovies()
    }

    fun getMovieDetails(movieId:Int): Single<MovieDetailResponse>{
        return api.getMovieDetails(movieId)
    }

    fun getMovieTrailers(movieId: Int): Single<MovieVideoResponse>{
        return api.getMovieVideos(movieId)
    }

    fun getSearchMovies(search:String):Single<MovieSearchResponse>{
        return api.getSearchMovies(search)
    }

    private fun getOkhttpClient():OkHttpClient{
        val client = OkHttpClient.Builder()
        client.addInterceptor(RequestInterceptor())
        return client.build()
    }
}