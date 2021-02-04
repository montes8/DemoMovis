package pe.meria.entity

import java.io.Serializable

data class Movie  (
    var id : Long,
    var adult : Boolean,
    var originalLanguage : String,
    var originalTitle : String,
    var overview : String,
    var popularity : Double,
    var posterPath : String,
    var releaseDate : String,
    var title : String,
    var video : Boolean,
    var voteAverage : Double,
    var voteCount : Double
):Serializable