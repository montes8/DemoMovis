package pe.meria.repository.local.preferences

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class PreferencesManager(context: Context, key :String) {


    private val encryptPreferences = EncryptedSharedPreferences.create(
        key,
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC),
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun setValue(key: String, value: String) {
        encryptPreferences
            .edit()
            .putString(key, value)
            .apply()
    }

    fun setValue(key: String, value: Int) {
        encryptPreferences
            .edit()
            .putInt(key, value)
            .apply()
    }

    fun setValue(key: String, value: Boolean) {
        encryptPreferences
            .edit()
            .putBoolean(key, value)
            .apply()
    }

    fun setValue(key: String, value: Float) {
        encryptPreferences
            .edit()
            .putFloat(key, value)
            .apply()
    }

    fun setValue(key: String, value: Long) {
        encryptPreferences
            .edit()
            .putLong(key, value)
            .apply()
    }

    fun getString(key: String): String {
        return encryptPreferences.getString(key, String()) ?: String()
    }


    fun getInt(key: String): Int {
        return encryptPreferences.getInt(key, 0)
    }

    fun getBoolean(key: String): Boolean {
        return encryptPreferences.getBoolean(key, false)
    }
}