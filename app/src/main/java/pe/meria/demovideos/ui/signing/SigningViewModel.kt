package pe.meria.demovideos.ui.signing

import androidx.lifecycle.MutableLiveData
import pe.meria.demovideos.ui.BaseViewModel
import pe.meria.entity.Movie
import pe.meria.usecases.usecases.AppUseCase

class SigningViewModel(private val appUseCase: AppUseCase) : BaseViewModel() {

    val movieListLiveData = MutableLiveData<List<Movie>>()

    fun getListMovie(){
        execute{
           val response = appUseCase.getListMovie()
            movieListLiveData.postValue(response)

        }
    }

}