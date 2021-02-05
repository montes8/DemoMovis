package pe.meria.usecases.repository.database

import pe.meria.entity.Movie
import java.lang.Exception

interface AppDataBase {

    @Throws(Exception::class)
    fun getListMovie(page :Int): List<Movie>

    @Throws(Exception::class)
    fun saveListMovie(list : ArrayList<Movie>)

    @Throws(Exception::class)
    fun deleteTable()


}