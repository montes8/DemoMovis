package pe.meria.repository.local.database.dao

import androidx.room.*
import pe.meria.repository.local.database.entity.MovieEntity

@Dao
interface MovieDao {

    @Query("select * from MovieEntity")
    fun listMovie(): List<MovieEntity>

    @Insert
    fun insertListMovie(plato : ArrayList<MovieEntity>) : Array<Long>

    @Query("DELETE FROM MovieEntity")
    fun cleanTable(): Int
}