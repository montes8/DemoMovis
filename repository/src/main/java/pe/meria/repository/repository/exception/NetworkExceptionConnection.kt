package pe.meria.repository.repository.exception

import pe.meria.repository.repository.utils.defaultCode
import pe.meria.repository.repository.utils.generalErrorMessage
import java.lang.Exception

class NetworkExceptionConnection(
    var errorCode          : Int?    = defaultCode,
    var errorMessage       : String? = generalErrorMessage,
    var errorMessageDetail : String? = generalErrorMessage
) : Exception(errorMessageDetail)