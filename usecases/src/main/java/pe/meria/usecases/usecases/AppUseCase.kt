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

    fun getListMovie( page :Int): List<Movie> {
        return if (isConnected(context)){
            listMovie(page)
        }else{
            listMovieDataBase(page)
        }
    }

    private fun listMovie( page :Int):List<Movie>{
        val list = appRepository.getListMovie(page)
        if (page == 1){
            appDataBase.deleteTable()
        }
        appDataBase.saveListMovie(list as ArrayList<Movie>)
        return list
    }
    private fun listMovieDataBase(page :Int):List<Movie>{
        return appDataBase.getListMovie(page)
    }

    fun validateLogin() = appRepositoryPreference.getLogin()


    fun logout() = appRepositoryPreference.saveLogin(false)



}