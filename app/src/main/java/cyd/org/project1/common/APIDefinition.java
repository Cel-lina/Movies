package cyd.org.project1.common;

import cyd.org.project1.model.ResponseMovies;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by cars on 5/7/16.
 */
public interface APIDefinition {

  String IMAGE_ENDPOINT = "http://image.tmdb.org/t/p/w185";


  @GET("3/movie/popular")
  Call<ResponseMovies> getPopularMovies(@Query("api_key") String apiKey);

  @GET("3/movie/top_rated")
  Call<ResponseMovies> getTopRatedMovies(@Query("api_key") String apiKey);


}
