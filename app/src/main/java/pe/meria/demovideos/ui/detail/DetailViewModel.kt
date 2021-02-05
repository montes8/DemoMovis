package pe.meria.demovideos.ui.detail


import androidx.lifecycle.MutableLiveData
import pe.meria.demovideos.ui.BaseViewModel

class DetailViewModel : BaseViewModel() {

    val successClickBack = MutableLiveData<Boolean>()

    fun onClickBack(){
        successClickBack.postValue(true)
    }
}