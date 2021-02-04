package pe.meria.repository.local.preferences

import org.koin.dsl.module
import pe.meria.usecases.repository.AppRepositoryPreference

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