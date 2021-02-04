package pe.meria.demovideos.application

import android.app.Application
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import pe.meria.demovideos.BuildConfig
import pe.meria.demovideos.R
import pe.meria.demovideos.di.viewModelsModule
import pe.meria.repository.local.preferences.ENCRYPTION_KEY
import pe.meria.repository.local.preferences.preferencesModule
import pe.meria.repository.repository.di.networkModule
import pe.meria.repository.repository.utils.NAME_BASE_URL
import pe.meria.usecases.di.useCaseModule


class DemoMovieApplication : Application() {

    override fun onCreate() {
        super.onCreate()
       startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@DemoMovieApplication)
            modules(
                listOf(
                    viewModelsModule, useCaseModule,
                    preferencesModule,networkModule
                )
            )
           getKoin().setProperty(NAME_BASE_URL,
               BuildConfig.BASE_URL
           )
           getKoin().setProperty(ENCRYPTION_KEY, getString(R.string.encryption_key))
        }
    }
}