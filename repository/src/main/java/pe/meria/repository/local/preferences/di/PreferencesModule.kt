package pe.meria.repository.local.preferences.di

import org.koin.dsl.module
import pe.meria.repository.local.preferences.api.AppPreference
import pe.meria.repository.local.preferences.utils.ENCRYPTION_KEY
import pe.meria.repository.local.preferences.manager.PreferencesManager
import pe.meria.usecases.repository.preferences.AppRepositoryPreference

val preferencesModule = module {
    single {
        PreferencesManager(
            get(),
            getProperty(ENCRYPTION_KEY)
        )
    }
    single<AppRepositoryPreference> {
        AppPreference(
            get()
        )
    }
}