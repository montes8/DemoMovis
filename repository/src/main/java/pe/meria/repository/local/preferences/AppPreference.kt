package pe.meria.repository.local.preferences

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