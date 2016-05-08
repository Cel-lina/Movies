package cyd.org.project1.presenter;


import java.util.List;

import cyd.org.project1.model.Movie;

/**
 * Created by cars on 5/7/16.
 */
public interface MoviePresenter {
  enum Criteria {
    POPULAR, TOP_RATED
  }

  void getMovies(Criteria criteria);


  interface Listener {
    void onSuccess(List<Movie> movies);

    void onError();
  }

}


