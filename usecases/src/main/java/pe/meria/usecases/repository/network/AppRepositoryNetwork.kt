package pe.meria.usecases.repository.network

import pe.meria.entity.Movie
import java.lang.Exception

interface AppRepositoryNetwork {

    @Throws(Exception::class)
    fun getListMovie(): List<Movie>


}