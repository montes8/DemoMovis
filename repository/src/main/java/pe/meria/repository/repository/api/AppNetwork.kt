package pe.meria.repository.repository.api

import pe.meria.entity.Movie
import pe.meria.repository.repository.ServiceApi
import pe.meria.repository.repository.entity.response.BaseResponse
import pe.meria.repository.repository.toCompleteErrorModel
import pe.meria.repository.repository.validateBody
import pe.meria.usecases.repository.AppRepositoryNetwork

class AppNetwork(private val apiConfig: ServiceApi) : AppRepositoryNetwork {


    @Throws(Exception::class)
    override fun getListMovie(): List<Movie> {
        val callResponse =
            apiConfig.getListMovie("1","")
        val response = callResponse.execute()
        if (!response.isSuccessful) {
            throw response.errorBody().toCompleteErrorModel()?.error?.getException() ?: Exception()
        }
        return BaseResponse.toListMovie(response.validateBody())
    }


}