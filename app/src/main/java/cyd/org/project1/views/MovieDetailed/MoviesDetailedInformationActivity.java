package cyd.org.project1.views.MovieDetailed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import cyd.org.project1.R;
import cyd.org.project1.common.APIDefinition;
import cyd.org.project1.model.Movie;

public class MoviesDetailedInformationActivity extends AppCompatActivity {

  private ImageView imageView;
  private TextView overviewTextView;
  private TextView releaseDate;
  private TextView voteAverage;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_movies_detailed_information);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    Bundle b = getIntent().getExtras();
    Movie movie = b.getParcelable("cyd.org.project1.model.Movie");
    if (movie != null) {
      initializeFields(movie);
    }
  }

  private void initializeFields(Movie movie) {
    getSupportActionBar().setTitle(movie.getOriginal_title());
    imageView = (ImageView) findViewById(R.id.thumbnail_film);
    overviewTextView = (TextView) findViewById(R.id.description_film);
    releaseDate = (TextView) findViewById(R.id.release_date);
    voteAverage = (TextView) findViewById(R.id.vote_average);
    overviewTextView.setText(movie.getOverview());
    releaseDate.setText(movie.getRelease_date());

    voteAverage.setText(Float.toString(movie.getVote_average()));
    Picasso.with(this.getApplicationContext()).load(APIDefinition.IMAGE_ENDPOINT + movie.getPoster_path()).fit().centerCrop().into(imageView);
  }


}
