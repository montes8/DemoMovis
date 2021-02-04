package pe.meria.usecases.di

import org.koin.dsl.module
import pe.meria.usecases.usecases.AppUseCase

val useCaseModule = module {
    single { AppUseCase(get(), get(),get()) }
}