package pe.meria.repository.repository.entity

import com.google.gson.annotations.SerializedName

data class CompleteErrorModel(
    @SerializedName("success") var success: Boolean = false,
    @SerializedName("error") var error: ErrorModel? = ErrorModel()
) {

}