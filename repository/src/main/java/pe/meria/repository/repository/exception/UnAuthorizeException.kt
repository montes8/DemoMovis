package pe.meria.repository.repository.exception

import java.lang.Exception

data class UnAuthorizeException(var errorSesionMsg: String) : Exception(errorSesionMsg)