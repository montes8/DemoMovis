package pe.meria.repository.local.preferences

import org.koin.dsl.module
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