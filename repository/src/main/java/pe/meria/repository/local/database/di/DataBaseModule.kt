package pe.meria.repository.local.database.di


import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module
import pe.meria.repository.local.database.DemoMovieDataBase
import pe.meria.repository.local.database.api.AppRepositoryDataBase
import pe.meria.usecases.repository.database.AppDataBase


val databaseModule: Module = module {
    single {
        Room.databaseBuilder(androidContext(), DemoMovieDataBase::class.java, "movie-db")
            .build()
    }
    single { get<DemoMovieDataBase>().movieDao }
    single<AppDataBase> { AppRepositoryDataBase(get()) }

}