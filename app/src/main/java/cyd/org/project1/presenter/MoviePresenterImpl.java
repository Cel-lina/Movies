package cyd.org.project1.presenter;

import android.util.Log;

import java.util.Arrays;
import java.util.List;

import cyd.org.project1.BuildConfig;
import cyd.org.project1.common.APICommunication;
import cyd.org.project1.model.Movie;
import cyd.org.project1.model.ResponseMovies;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cars on 5/7/16.
 */
public class MoviePresenterImpl implements MoviePresenter {

  private final Listener listener;

  public MoviePresenterImpl(Listener listener) {
    this.listener = listener;
  }

  @Override
  public void getMovies(Criteria criteria) {
    Call<ResponseMovies> call;

    switch (criteria) {
      case POPULAR:
        call = APICommunication.getAPICommunication().getPopularMovies(BuildConfig.API_KEY);
        break;
      case TOP_RATED:
        call = APICommunication.getAPICommunication().getTopRatedMovies(BuildConfig.API_KEY);
        break;
      default:
        return;
    }

    call.enqueue(new Callback<ResponseMovies>() {
      @Override
      public void onResponse(Call<ResponseMovies> call, Response<ResponseMovies> response) {
        List<Movie> list = Arrays.asList(response.body().getResults());
        listener.onSuccess(list);
      }

      @Override
      public void onFailure(Call<ResponseMovies> call, Throwable t) {
        Log.d("error", t.getMessage());
        listener.onError();
      }
    });
  }


}
