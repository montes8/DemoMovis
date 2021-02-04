package pe.meria.repository.repository.entity.response

import com.google.gson.annotations.SerializedName
import pe.meria.entity.Movie

class BaseResponse (
    @SerializedName("total_pages")
    var totalPages :Int?,
    @SerializedName("total_results")
    var totalResults :Int?,
    @SerializedName("page")
    var page :Int?,
    @SerializedName("dates")
   var dates : DateResponse,
    @SerializedName("results")
    var results : List<MovieResponse>
){
    companion object{
        fun toListMovie(response :BaseResponse ):List<Movie>{
            val list : ArrayList<Movie> = ArrayList()
            for (item in response.results){
                list.add(MovieResponse.toMovie(item))
            }
            return list
        }


    }
}