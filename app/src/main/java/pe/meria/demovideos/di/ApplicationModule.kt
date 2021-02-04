package pe.meria.demovideos.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pe.meria.demovideos.ui.AppViewModel


val viewModelsModule = module {
    viewModel { AppViewModel(get()) }

}
