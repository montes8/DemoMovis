package pe.meria.repository.local.database.dao

import androidx.room.*
import pe.meria.repository.local.database.entity.MovieEntity

@Dao
abstract class MovieDao :BaseDAO<MovieEntity>(){

    @Query("select * from MovieEntity LIMIT :pageSize OFFSET :pageIndex")
    abstract fun listMovie(pageSize : Int,pageIndex: Int): List<MovieEntity>

    @Query("DELETE FROM MovieEntity")
    abstract fun deleteTable(): Int


    @Transaction
    open fun  insertListMovie(model : ArrayList<MovieEntity>) : Array<Long>{
        return insertList(model)
    }

}