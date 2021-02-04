package pe.meria.usecases.repository

import pe.meria.entity.Movie
import java.lang.Exception

interface AppDataBase {

    @Throws(Exception::class)
    fun getListMovie(): List<Movie>

    @Throws(Exception::class)
    fun saveListMovie(list : List<Movie>)


}