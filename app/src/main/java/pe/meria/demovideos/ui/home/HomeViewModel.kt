package pe.meria.demovideos.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import pe.meria.demovideos.ui.BaseViewModel
import pe.meria.entity.Movie
import pe.meria.usecases.usecases.AppUseCase

class HomeViewModel(private val appUseCase: AppUseCase) : BaseViewModel() {

    val movieListLiveData = MutableLiveData<List<Movie>>()

    fun getListMovie(loading: Boolean, page :Int){
        execute(loading){
           val response = appUseCase.getListMovie(page)
            Log.d("listMovie","$response")
            Log.d("listMovieCount","${response.size}")
            movieListLiveData.postValue(response) }
    }

    fun logout(){
        appUseCase.logout()
    }

}