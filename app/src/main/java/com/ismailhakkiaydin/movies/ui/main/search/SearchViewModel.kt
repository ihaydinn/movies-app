package com.ismailhakkiaydin.movies.ui.main.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ismailhakkiaydin.movies.data.remote.ApiClient
import com.ismailhakkiaydin.movies.model.search.MovieSearchResponse
import com.ismailhakkiaydin.movies.model.search.MovieSearchResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class SearchViewModel(application: Application) : AndroidViewModel(application) {

    private val apiClient = ApiClient()
    private val disposable = CompositeDisposable()

    val searchMovies = MutableLiveData<List<MovieSearchResult>>()

    fun getSearchMovies(search: String) {
        disposable.add(
            apiClient.getSearchMovies(search)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<MovieSearchResponse>() {
                    override fun onSuccess(t: MovieSearchResponse) {
                        searchMovies.value = t.results!!
                    }

                    override fun onError(e: Throwable) {

                    }

                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}