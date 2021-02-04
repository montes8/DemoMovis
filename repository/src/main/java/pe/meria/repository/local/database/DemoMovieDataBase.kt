package pe.meria.repository.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import pe.meria.repository.local.database.dao.MovieDao
import pe.meria.repository.local.database.entity.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class DemoMovieDataBase : RoomDatabase() {
    abstract val movieDao: MovieDao
}