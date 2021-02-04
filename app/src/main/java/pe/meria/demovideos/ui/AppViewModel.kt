package pe.meria.demovideos.ui

import androidx.lifecycle.MutableLiveData
import pe.meria.usecases.usecases.AppUseCase

class AppViewModel(private val appUseCase: AppUseCase) : BaseViewModel() {

    val successLoginLiveData = MutableLiveData<Boolean>()

    fun validateLogin(){
        executeRemoveLoading {
            val response = appUseCase.validateLogin()
            successLoginLiveData.postValue(response)
        }
    }

}