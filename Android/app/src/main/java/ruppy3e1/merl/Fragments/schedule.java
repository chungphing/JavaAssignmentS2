package ruppy3e1.merl.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ruppy3e1.merl.Datasource.MovieDataSource;
import ruppy3e1.merl.Model.Movie;
import ruppy3e1.merl.MoviesAdapter;
import ruppy3e1.merl.R;

/**
 * Created by chunpghing
 */

public class schedule extends Fragment {


    public interface OnMovieSelectedInterface {
        void onListMovieSelected(int index);
    }



    public static final String TAG = "Main Fragment";

    public static final String PREFERENCES = "PREFERENCES";
    public static final String PREF_DATABASE_VERSION = "DATABASE_VERSION";
    private static final String PREF_STATUS = "STATUS";
    private int dbVersion;
    private boolean status;
    private MoviesAdapter adapter;
    private ArrayList<Movie> movies;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        initDataset();

    }


    @Override
    public void onResume() {
        super.onResume();



    }

    private void initDataset() {


        MovieDataSource dataSource = new MovieDataSource(this.getActivity());
         movies = new ArrayList<>();
        movies = dataSource.readName();
        Log.i(TAG, "initDataset: movie size " + movies.size());

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        MainFragment.OnMovieSelectedInterface listener = (MainFragment.OnMovieSelectedInterface)getActivity();

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        adapter = new MoviesAdapter(getActivity(), movies, listener);

            RecyclerView rvMovies = (RecyclerView) view.findViewById(R.id.rvMovies);

            rvMovies.setAdapter(adapter);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);

        rvMovies.setLayoutManager(layoutManager);

        getActivity().setTitle("SCHEDULE");


        return view;
    }


    public static schedule newInstance(){


        return new schedule();
    }
}
