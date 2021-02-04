package pe.meria.usecases.usecases

import android.content.Context
import pe.meria.entity.Movie
import pe.meria.usecases.repository.database.AppDataBase
import pe.meria.usecases.repository.network.AppRepositoryNetwork
import pe.meria.usecases.repository.preferences.AppRepositoryPreference
import pe.meria.usecases.utils.Utils.isConnected

class AppUseCase(private val context: Context,
    private val appRepository: AppRepositoryNetwork,
    private val appRepositoryPreference: AppRepositoryPreference,
    private val appDataBase: AppDataBase
) {

    fun getListMovie(): List<Movie> {
        return if (isConnected(context)){
            listMovie()
        }else{
            listMovieDataBase()
        }
    }

    private fun listMovie():List<Movie>{
        val list = appRepository.getListMovie()
        appDataBase.saveListMovie(list as ArrayList<Movie>)
        return list
    }
    private fun listMovieDataBase():List<Movie>{
        return appDataBase.getListMovie()
    }

    fun validateLogin() = appRepositoryPreference.getLogin()


    fun logout() = appRepositoryPreference.saveLogin(false)



}