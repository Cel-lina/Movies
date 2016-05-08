package cyd.org.project1.views.MovieList;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import cyd.org.project1.R;
import cyd.org.project1.model.Movie;
import cyd.org.project1.presenter.MoviePresenter;
import cyd.org.project1.presenter.MoviePresenterImpl;
import cyd.org.project1.views.Settings.SettingsActivity;

public class MovieListActivity extends AppCompatActivity implements MoviePresenter.Listener, SharedPreferences.OnSharedPreferenceChangeListener {

  private GridLayoutManager gridLayoutManager;
  private RecyclerView recyclerView;
  private MoveListAdapter moveListAdapter;
  private MoviePresenter moviePresenter;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    moviePresenter = new MoviePresenterImpl(this);
    moviePresenter.getMovies(MoviePresenter.Criteria.POPULAR);
    PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);

    initializeList();
  }


  private void initializeList() {
    gridLayoutManager = new GridLayoutManager(this, 2);
    moveListAdapter = new MoveListAdapter();
    recyclerView = (RecyclerView) findViewById(R.id.listImages);
    recyclerView.setLayoutManager(gridLayoutManager);
    recyclerView.setAdapter(moveListAdapter);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
      startActivity(intent);
      return true;
    }

    if (item.getItemId() == android.R.id.home) {
      finish();  //return to caller
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onSuccess(List<Movie> movies) {
    moveListAdapter.setMovieList(movies);
    moveListAdapter.notifyDataSetChanged();
    Log.d("debug", "loading movies again");
  }


  @Override
  public void onError() {
    // TODO show an error
  }

  @Override
  public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
    Log.d("debug", "settings changed" + key);
    String criteria = sharedPreferences.getString(key, "0");
    MoviePresenter.Criteria finalCriteria = MoviePresenter.Criteria.POPULAR;
    switch (criteria) {
      case "0":
        finalCriteria = MoviePresenter.Criteria.POPULAR;
        break;
      case "1":
        finalCriteria = MoviePresenter.Criteria.TOP_RATED;
        break;
    }
    Log.d("debug", "criteria is " + finalCriteria.name());

    moviePresenter.getMovies(finalCriteria);


  }
}
