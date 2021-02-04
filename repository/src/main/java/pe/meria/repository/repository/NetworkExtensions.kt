package pe.meria.repository.repository

import com.google.gson.Gson
import okhttp3.ResponseBody
import pe.meria.repository.repository.entity.CompleteErrorModel
import retrofit2.Response


fun ResponseBody?.toCompleteErrorModel() : CompleteErrorModel? {
    return this?.let {
        return Gson().fromJson(it.string(), CompleteErrorModel::class.java)
    } ?: CompleteErrorModel()
}

fun <T> Response<T>.validateBody() : T {
    this.body()?.let {
        return it
    } ?: throw NullPointerException()
}