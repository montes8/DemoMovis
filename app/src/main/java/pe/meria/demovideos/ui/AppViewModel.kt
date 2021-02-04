package pe.meria.demovideos.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import pe.meria.entity.Movie
import pe.meria.usecases.usecases.AppUseCase

class AppViewModel(private val appUseCase: AppUseCase) : BaseViewModel() {
    val movieListLiveData = MutableLiveData<List<Movie>>()
    val successLoginLiveData = MutableLiveData<Boolean>()



    fun getLogin(){
        executeRemoveLoading {
            //val response = appUseCase.validateLogin()
            successLoginLiveData.postValue(false)
        }
    }

}