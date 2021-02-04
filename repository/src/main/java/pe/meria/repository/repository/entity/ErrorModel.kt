package pe.meria.repository.repository.entity

import com.google.gson.annotations.SerializedName
import pe.meria.repository.repository.utils.EMPTY
import pe.meria.repository.repository.utils.ErrorResponse
import pe.meria.repository.repository.utils.defaultCode

data class ErrorModel(
    @SerializedName("errorCode") var code: Int? = defaultCode,
    @SerializedName("errorMessage") var message: String? = EMPTY,
    @SerializedName("errorMessageDetail") var detail: String? = EMPTY
) {
    fun getException(): Exception {
        return ErrorResponse.returnException(this.code, this.message, this.detail)
    }
}