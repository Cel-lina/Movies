package cyd.org.project1.views.MovieList;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cyd.org.project1.R;
import cyd.org.project1.common.APIDefinition;
import cyd.org.project1.model.Movie;
import cyd.org.project1.views.MovieDetailed.MoviesDetailedInformationActivity;

/**
 * Created by cars on 5/8/16.
 */
public class MoveListAdapter extends RecyclerView.Adapter<ImageViewHolder> {

  private List<Movie> movieList;
  private Context context;

  public MoveListAdapter() {
    movieList = new ArrayList<>(100);
    Log.d("debug", "initializing movie list adapter");

  }

  public void setMovieList(List<Movie> movieList) {
    this.movieList = movieList;
  }

  @Override
  public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_movies_image, null);
    context = parent.getContext();
    Log.d("debug", "inflating row");

    return new ImageViewHolder(layoutView);
  }

  @Override
  public void onBindViewHolder(ImageViewHolder holder, final int position) {
    Picasso.with(context).load(APIDefinition.IMAGE_ENDPOINT + movieList.get(position).getPoster_path()).fit().centerCrop().into(holder.movieImageView);
    holder.movieImageView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Log.d("debug", "click");
        Intent intent = new Intent(context, MoviesDetailedInformationActivity.class);
        intent.putExtra("cyd.org.project1.model.Movie", movieList.get(position));
        context.startActivity(intent);
      }
    });
  }


  @Override
  public int getItemCount() {
    return movieList.size();
  }


}

class ImageViewHolder extends RecyclerView.ViewHolder {

  public final ImageView movieImageView;

  public ImageViewHolder(View itemView) {
    super(itemView);
    movieImageView = (ImageView) itemView.findViewById(R.id.imageTextView);
  }
}