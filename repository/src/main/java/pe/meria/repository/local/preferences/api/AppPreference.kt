package pe.meria.repository.local.preferences.api

import pe.meria.repository.local.preferences.manager.PreferencesManager
import pe.meria.repository.local.preferences.utils.PREFERENCE_SESSION
import pe.meria.usecases.repository.preferences.AppRepositoryPreference

class AppPreference(private val sharedPreferenceManager: PreferencesManager) :
    AppRepositoryPreference {
    override fun getLogin(): Boolean {
        return sharedPreferenceManager.getBoolean(PREFERENCE_SESSION)
    }

    override fun saveLogin(value: Boolean) {
        sharedPreferenceManager.setValue(PREFERENCE_SESSION, value)
    }

}