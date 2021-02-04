package pe.meria.repository.repository.entity

import com.google.gson.annotations.SerializedName
import pe.meria.repository.repository.utils.ErrorResponse

data class CompleteErrorModel(
    @SerializedName("status_code") var code: Int = 0,
    @SerializedName("success") var success: Boolean = false,
    @SerializedName("status_message") var message: String = "Error"
) {
    fun getException(): Exception {
        return ErrorResponse.returnException(this.code, this.message, this.message)
    }
}