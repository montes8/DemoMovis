package pe.meria.usecases.repository

import java.lang.Exception

interface AppRepositoryPreference {

    @Throws(Exception::class)
    fun getLogin(): Boolean

    @Throws(Exception::class)
    fun saveLogin(value: Boolean)

}