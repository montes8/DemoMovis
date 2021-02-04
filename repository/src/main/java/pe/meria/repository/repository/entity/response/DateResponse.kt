package pe.meria.repository.repository.entity.response

import com.google.gson.annotations.SerializedName

class DateResponse (
    @SerializedName("maximum")
    var maximum : String?,
    @SerializedName("minimum")
    var minimum : String?
)