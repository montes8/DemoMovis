package pe.meria.demovideos.ui.signing

import android.content.Context
import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import pe.meria.demovideos.R
import pe.meria.demovideos.component.editNormal.TCEditNormalText
import pe.meria.demovideos.component.editPasword.TCEditPasswordText
import pe.meria.demovideos.ui.BaseViewModel
import pe.meria.demovideos.utils.AppUtils.showErrorToast
import pe.meria.usecases.usecases.UserUseCase

class SigningViewModel(private val userUseCase: UserUseCase,private val context: Context) : BaseViewModel() {


    lateinit var editUser : TCEditNormalText
    lateinit var editPass : TCEditPasswordText
    val successLoginLiveData = MutableLiveData<Boolean>()

    fun setEditUse(userEdit : TCEditNormalText, passEdit: TCEditPasswordText){
        editUser = userEdit
        editPass = passEdit
        editPass.setUp(false)
    }


    fun login (){
        if (validateData(editUser,editPass)){
            execute(true) {
                val valid = userUseCase.login(editUser.textInput, editPass.textInput)
                successLoginLiveData.postValue(valid)
            }
        }
    }

    private fun validateData(userName:TCEditNormalText, password: TCEditPasswordText): Boolean {
        if (TextUtils.isEmpty(userName.textInput)) {
           // showErrorToast(context.resources.getString(R.string.error_empty_user),context)
            userName.errorMessage = context.resources.getString(R.string.error_empty_user)
            return false
        }
        if (TextUtils.isEmpty(password.textInput)) {
            //showErrorToast(context.resources.getString(R.string.error_empty_password),context)
            password.errorMessage = context.resources.getString(R.string.error_empty_password)
            return false
        }
        userName.hideError
        password.hideError
        return true
    }

}