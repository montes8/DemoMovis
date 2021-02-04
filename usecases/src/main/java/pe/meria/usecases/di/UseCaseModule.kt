package pe.meria.usecases.di

import org.koin.dsl.module
import pe.meria.usecases.usecases.AppUseCase
import pe.meria.usecases.usecases.UserUseCase

val useCaseModule = module {
    single { AppUseCase(get(), get(),get(),get()) }
    single { UserUseCase(get()) }

}