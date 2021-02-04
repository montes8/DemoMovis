package  pe.meria.repository.repository

import pe.meria.repository.repository.entity.response.BaseResponse
import retrofit2.Call
import retrofit2.http.*


interface ServiceApi {
    @GET("3/movie/upcoming")
    fun getListMovie(@Query("page")page : String,
                     @Query("api_key")api_key : String): Call<BaseResponse>
}