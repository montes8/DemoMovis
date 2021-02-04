package pe.meria.repository.repository.entity.response

import android.util.Log
import com.google.gson.annotations.SerializedName
import pe.meria.entity.Movie
import pe.meria.repository.repository.utils.EMPTY

class MovieResponse (
    @SerializedName("id")
    var id : Long?,
    @SerializedName("adult")
    var adult : Boolean?,
    @SerializedName("backdrop_path")
    var backdropPath : String?,
    @SerializedName("original_language")
    var originalLanguage : String?,
    @SerializedName("original_title")
    var originalTitle : String?,
    @SerializedName("overview")
    var overview : String?,
    @SerializedName("popularity")
    var popularity : Double?,
    @SerializedName("poster_path")
    var posterPath : String?,
    @SerializedName("release_date")
    var releaseDate : String?,
    @SerializedName("title")
    var title : String?,
    @SerializedName("video")
    var video : Boolean?,
    @SerializedName("vote_average")
    var voteAverage : Double?,
    @SerializedName("vote_count")
    var voteCount : Double?
){
    companion object{
        fun toMovie(data: MovieResponse):Movie{
            return Movie(data.id?:0,data.adult?:false,completeUrlImage(data.backdropPath?: EMPTY),data.originalLanguage?: EMPTY,
            data.originalTitle?: EMPTY,data.overview?: EMPTY,data.popularity?: 0.0,
                completeUrlImage(data.posterPath?: EMPTY),data.releaseDate?: EMPTY,data.title?: EMPTY,
                data.video?:false,data.voteAverage?:0.0,data.voteCount?:0.0
            )
        }

        private fun completeUrlImage(value : String):String{
            var url = EMPTY
            if (value.isNotEmpty()){
                url = "https://image.tmdb.org/t/p/w500$value"
            }
            return url
        }
    }
}