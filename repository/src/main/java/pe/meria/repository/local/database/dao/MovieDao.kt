package pe.meria.repository.local.database.dao

import androidx.room.*
import pe.meria.repository.local.database.entity.MovieEntity

@Dao
interface MovieDao {

    @Query("select * from MovieEntity LIMIT :pageSize OFFSET :pageIndex")
    fun listMovie(pageSize : Int,pageIndex: Int): List<MovieEntity>

    @Insert
    fun insertListMovie(plato : ArrayList<MovieEntity>) : Array<Long>

    @Query("DELETE FROM MovieEntity")
    fun cleanTable(): Int
}