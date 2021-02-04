package pe.meria.usecases.usecases

import pe.meria.usecases.repository.AppRepositoryPreference
import pe.meria.usecases.utils.Utils.getLogin

class UserUseCase(private val appRepositoryPreference: AppRepositoryPreference) {

    fun login(user: String, pass: String): Boolean {
        if (getLogin(user,pass)){
            appRepositoryPreference.saveLogin(true)
        }else{
            appRepositoryPreference.saveLogin(false)
        }
        return getLogin(user,pass)
    }
}