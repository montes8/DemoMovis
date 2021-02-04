package pe.meria.repository.repository.entity.response

import com.google.gson.annotations.SerializedName
import pe.meria.entity.Movie
import pe.meria.repository.repository.utils.EMPTY

class MovieResponse (
    @SerializedName("id")
    var id : Long?,
    @SerializedName("adult")
    var adult : Boolean?,
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
            return Movie(data.id?:0,data.adult?:false,data.originalLanguage?: EMPTY,
            data.originalTitle?: EMPTY,data.overview?: EMPTY,data.popularity?: 0.0,
                data.posterPath?: EMPTY,data.releaseDate?: EMPTY,data.title?: EMPTY,
                data.video?:false,data.voteAverage?:0.0,data.voteCount?:0.0
            )
        }
    }
}