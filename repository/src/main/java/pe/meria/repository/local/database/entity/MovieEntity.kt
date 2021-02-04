package pe.meria.repository.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import pe.meria.entity.Movie
import pe.meria.repository.repository.utils.EMPTY

@Entity
class MovieEntity (
    @PrimaryKey
    var id : Long?,
    var adult : Boolean?,
    var backdropPath : String?,
    var originalLanguage : String?,
    var originalTitle : String?,
    var overview : String?,
    var popularity : Double?,
    var posterPath : String?,
    var releaseDate : String?,
    var title : String?,
    var video : Boolean?,
    var voteAverage : Double?,
    var voteCount : Double?
){
    companion object{
        fun toListMovies(response : List<MovieEntity>):List<Movie>{
            val list : ArrayList<Movie> = ArrayList()
            for (data in response){
                list.add(toMovie(data))

            }
            return list

        }

        private fun toMovie(movie : MovieEntity):Movie{
            return Movie(movie.id?:0,movie.adult?:false,movie.backdropPath?: EMPTY,
            movie.originalLanguage?: EMPTY,movie.originalTitle?: EMPTY,
            movie.overview?: EMPTY,movie.popularity?:0.0,movie.posterPath?: EMPTY,
            movie.releaseDate?: EMPTY,movie.title?: EMPTY,movie.video?:false,
            movie.voteAverage?:0.0, movie.voteCount?:0.0)
        }

        fun toListMoviesEntity(response : ArrayList<Movie>):ArrayList<MovieEntity>{
            val list : ArrayList<MovieEntity> = ArrayList()
            for (data in response){
                list.add(toMovieEntity(data))

            }
            return list

        }

        private fun toMovieEntity(movie : Movie):MovieEntity{
            return MovieEntity(movie.id,movie.adult,movie.backdropPath,
                movie.originalLanguage,movie.originalTitle,
                movie.overview,movie.popularity,movie.posterPath,
                movie.releaseDate,movie.title,movie.video,
                movie.voteAverage, movie.voteCount)
        }
    }
}