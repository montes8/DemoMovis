package pe.meria.demovideos.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pe.meria.demovideos.ui.AppViewModel
import pe.meria.demovideos.ui.home.HomeViewModel


val viewModelsModule = module {
    viewModel { AppViewModel(get()) }
    viewModel { HomeViewModel(get()) }

}
