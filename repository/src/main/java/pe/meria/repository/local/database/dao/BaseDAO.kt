package pe.meria.repository.local.database.dao

import androidx.room.Delete
import androidx.room.Insert
import pe.meria.repository.local.database.entity.MovieEntity


abstract class BaseDAO<T> {

    @Insert
    abstract fun insertList(model : ArrayList<MovieEntity>) : Array<Long>

    @Delete
    protected abstract fun delete(model: T)

}