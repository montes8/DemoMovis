package pe.meria.demovideos.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class BaseViewModel() : ViewModel() {
    val loadingLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<Exception>()

    fun execute(loading : Boolean = true,func: () -> Unit) {
        GlobalScope.launch {
            try {
                loadingLiveData.postValue(loading)
                withContext(Dispatchers.IO){func()}
                loadingLiveData.postValue(false)
            } catch (ex: Exception) {
                ex.printStackTrace()
                errorLiveData.postValue(ex)
                loadingLiveData.postValue(false)
            }
        }
    }

    fun executeRemoveLoading(func: () -> Unit) {
        GlobalScope.launch {
            try {
                withContext(Dispatchers.IO){func()}
            } catch (ex: Exception) {
                ex.printStackTrace()
                errorLiveData.postValue(ex)
            }
        }
    }

}