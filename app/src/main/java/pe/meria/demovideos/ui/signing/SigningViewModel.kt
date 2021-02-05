package pe.meria.demovideos.ui.signing

import android.content.Context
import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import pe.meria.demovideos.R
import pe.meria.demovideos.ui.BaseViewModel
import pe.meria.demovideos.utils.AppUtils.showErrorToast
import pe.meria.demovideos.utils.EMPTY
import pe.meria.usecases.usecases.UserUseCase

class SigningViewModel(private val userUseCase: UserUseCase,private val context: Context) : BaseViewModel() {


    val userName = MutableLiveData<String>(EMPTY)
    val password = MutableLiveData<String>(EMPTY)
    val successLoginLiveData = MutableLiveData<Boolean>()

    fun clickLogin() {
        if (validateData()){
            execute(true) {
                val valid = userUseCase.login(userName.value?: EMPTY, password.value?: EMPTY)
                successLoginLiveData.postValue(valid)
            }
        }
    }

    private fun validateData(): Boolean {
        if (TextUtils.isEmpty(userName.value)) {
            showErrorToast(context.resources.getString(R.string.error_empty_user),context)
            return false
        }
        if (TextUtils.isEmpty(password.value)) {
            showErrorToast(context.resources.getString(R.string.error_empty_password),context)
            return false
        }
        return true
    }
}