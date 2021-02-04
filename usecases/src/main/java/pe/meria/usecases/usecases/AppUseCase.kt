package pe.meria.usecases.usecases

import android.content.Context
import pe.meria.entity.Movie
import pe.meria.usecases.repository.AppDataBase
import pe.meria.usecases.repository.AppRepositoryNetwork
import pe.meria.usecases.repository.AppRepositoryPreference
import pe.meria.usecases.utils.Utils

class AppUseCase(
    private var context: Context,
    private val appRepository: AppRepositoryNetwork,
    private val appRepositoryPreference: AppRepositoryPreference
    //private val AppDataBase: AppDataBase
) {

    fun getListMovie(): List<Movie> {
        var list : List<Movie> = ArrayList()
        if (Utils.isConnected(context)) {
            list = appRepository.getListMovie()
            //AppDataBase.saveListMovie(list)
        } else {
           // list =  AppDataBase.getListMovie()
        }
        return list
    }

    fun validateLogin() = appRepositoryPreference.getLogin()

    fun saveLogin() = appRepositoryPreference.saveLogin(true)

    fun logout() = appRepositoryPreference.saveLogin(false)

}