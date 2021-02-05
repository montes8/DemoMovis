package pe.meria.repository.local.database.api

import pe.meria.entity.Movie
import pe.meria.repository.local.database.dao.MovieDao
import pe.meria.repository.local.database.entity.MovieEntity
import pe.meria.repository.repository.exception.NetworkExceptionConnection
import pe.meria.repository.repository.utils.errorConnection
import pe.meria.usecases.repository.database.AppDataBase

class AppRepositoryDataBase(private val movieDao: MovieDao) : AppDataBase {

    override fun getListMovie(): List<Movie> {
        val list =  MovieEntity.toListMovies(movieDao.listMovie())
        if (list.isNotEmpty()){
            return list
        }else{
            throw NetworkExceptionConnection(10,errorConnection,errorConnection)
        }
    }

    override fun saveListMovie(list: ArrayList<Movie>) {
         movieDao.insertListMovie(MovieEntity.toListMoviesEntity(list))
    }

    override fun deleteTable() {
        movieDao.cleanTable()
    }
}