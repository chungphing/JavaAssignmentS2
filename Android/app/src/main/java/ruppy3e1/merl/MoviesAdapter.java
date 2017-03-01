package ruppy3e1.merl;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import ruppy3e1.merl.Fragments.MainFragment;
import ruppy3e1.merl.Model.Movie;

import static android.content.ContentValues.TAG;

/**
 * Created by chunpghing
 */


public class MoviesAdapter extends  RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private final MainFragment.OnMovieSelectedInterface mListener;
    private ArrayList<Movie> mMovies;
    private Context mContext;

    public MoviesAdapter(Context context, ArrayList<Movie> movies, MainFragment.OnMovieSelectedInterface listener){
        mMovies = movies;
        mContext = context;
        mListener = listener;
    }

    private Context getContext(){
        return mContext;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent,false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bindMovie(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class  MovieViewHolder extends  RecyclerView.ViewHolder
        implements View.OnClickListener{

        public TextView mName;
        public ImageView mImage;
        private int mIndex;
        public MovieViewHolder(View itemView) {
            super(itemView);

            mName = (TextView) itemView.findViewById(R.id.movie_name);
            mImage = (ImageView) itemView.findViewById(R.id.image_poster);

            itemView.setOnClickListener(this);
        }
        public void bindMovie(Movie movie){
            mIndex = movie.getmId();
            mName.setText(movie.getmName());
            Picasso.with(mContext).load(movie.getmImgLink()).placeholder(R.drawable.place_holder).error(R.drawable.placeholder_error).into(mImage);

        }

        @Override
        public void onClick(View v) {
            mListener.onListMovieSelected(mIndex);
        }
    }



}



//
//public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder>
//    implements View.OnClickListener
//{
//    @Override
//    public MoviesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        Context context = parent.getContext();
//        LayoutInflater inflater = LayoutInflater.from(context);
//
//        View movieView = inflater.inflate(R.layout.movie_item, parent, false);
//
//        ViewHolder viewHolder = new ViewHolder(movieView);
//
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder holder, int position) {
//        Movie movie = mMovies.get(position);
//
//        TextView textView = holder.nameTextView;
//        textView.setText(movie.getmName());
//
//
//        ImageView imageView = holder.imageView;
//
//        Picasso.with(mContext).load(movie.getmImgLink()).placeholder(R.drawable.place_holder).error(R.drawable.placeholder_error).into(imageView);
//        //imageView.setBackgroundResource(movie.getmImgLink());
//
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//
//        return mMovies.size();
//
//    }
//
//    @Override
//    public void onClick(View v) {
//        Log.i(TAG, "onClick: " + );
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder{
//        public TextView nameTextView;
//        public ImageView imageView;
//
//
//        public ViewHolder(View itemView){
//            super(itemView);
//
//            nameTextView = (TextView) itemView.findViewById(R.id.movie_name);
//            imageView = (ImageView) itemView.findViewById(R.id.image_poster);
//
//        }
//    }
//
//    private ArrayList<Movie> mMovies;
//
//    private Context mContext;
//
//    public MoviesAdapter(Context context, ArrayList<Movie> movies){
//        mMovies = movies;
//        mContext = context;
//    }
//
//    private Context getContext(){
//        return mContext;
//    }
//}
