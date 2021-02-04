package pe.meria.demovideos.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pe.meria.demovideos.ui.AppViewModel
import pe.meria.demovideos.ui.detail.DetailViewModel
import pe.meria.demovideos.ui.home.HomeViewModel
import pe.meria.demovideos.ui.signing.SigningViewModel


val viewModelsModule = module {
    viewModel { AppViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel() }
    viewModel { SigningViewModel(get(),get()) }

}
