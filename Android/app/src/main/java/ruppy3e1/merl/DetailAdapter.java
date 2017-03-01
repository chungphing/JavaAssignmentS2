package ruppy3e1.merl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import ruppy3e1.merl.Fragments.MainFragment;
import ruppy3e1.merl.Model.Movie;
import ruppy3e1.merl.Model.MovieDate;
import ruppy3e1.merl.Model.MovieHall;
import ruppy3e1.merl.Model.MovieShowTime;

/**
 * Created by chunpghing
 */


public class DetailAdapter extends  RecyclerView.Adapter<DetailAdapter.MovieViewHolder> {
    private ArrayList<MovieShowTime> movieShowTimes;


    private Context mContext;

    public DetailAdapter(Context context, ArrayList<MovieShowTime> showTimes){
        movieShowTimes = showTimes;
    }

    private Context getContext(){
        return mContext;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_item, parent,false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.bindMovie(movieShowTimes.get(position));
    }

    @Override
    public int getItemCount() {


        return movieShowTimes.size();
    }

    public class  MovieViewHolder extends  RecyclerView.ViewHolder{

        public TextView theater;
        public TextView date;
        public TextView time;

        private int mIndex;
        public MovieViewHolder(View itemView) {
            super(itemView);

            theater = (TextView) itemView.findViewById(R.id.theater);
            date = (TextView) itemView.findViewById(R.id.date);

            time = (TextView) itemView.findViewById(R.id.time);



        }
        public void bindMovie(MovieShowTime showTime){
            String[] showtime = showTime.getLegendShowTime().split("~");
            String strtheater = showtime[0];
            String strdate = showtime[1];
            String strtime = showtime[2];

            theater.setText(strtheater);
            date.setText(strdate);
            time.setText(strtime);

//            mIndex = movie.getmId();
//            mName.setText(movie.getmName());
//            Picasso.with(mContext).load(movie.getmImgLink()).placeholder(R.drawable.place_holder).error(R.drawable.placeholder_error).into(mImage);

        }

    }



}