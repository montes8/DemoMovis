package pe.meria.repository.local.database.api

import android.util.Log
import pe.meria.entity.Movie
import pe.meria.repository.local.database.dao.MovieDao
import pe.meria.repository.local.database.entity.MovieEntity
import pe.meria.repository.repository.exception.NetworkExceptionConnection
import pe.meria.repository.repository.utils.errorConnection
import pe.meria.usecases.repository.database.AppDataBase

class AppRepositoryDataBase(private val movieDao: MovieDao) : AppDataBase {

    override fun getListMovie(page :Int): List<Movie> {
        var pagination = page-1
        Log.d("paginationOne","$pagination")
        if (pagination >0){
            pagination =pagination* 20
            Log.d("paginationTwo","$pagination")
        }
        Log.d("paginationThree","$pagination")
        val list =  MovieEntity.toListMovies(movieDao.listMovie(20,pagination))
        return if (list.isNotEmpty()){
            list
        }else{
            if (page ==1){
                throw NetworkExceptionConnection(10,errorConnection,errorConnection)
            }else{
                list
            }
        }
    }

    override fun saveListMovie(list: ArrayList<Movie>) {
         movieDao.insertListMovie(MovieEntity.toListMoviesEntity(list))
    }

    override fun deleteTable() {
        movieDao.cleanTable()
    }
}