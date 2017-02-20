package ruppy3e1.merl.View;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.LoginFilter;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import ruppy3e1.merl.Model.Movie;
import ruppy3e1.merl.Model.MovieAltID;
import ruppy3e1.merl.Model.MovieDate;
import ruppy3e1.merl.Model.MovieGenre;
import ruppy3e1.merl.Model.MovieHall;
import ruppy3e1.merl.Model.MovieShowTime;
import ruppy3e1.merl.Model.MovieURL;
import ruppy3e1.merl.R;
import ruppy3e1.merl.Util.ParseLegend;

public class DownloadActivity extends AppCompatActivity {


    private static final String TAG = DownloadActivity.class.getSimpleName();
    public static final String MAJOR_CINEPLEX_URL = "http://www.majorcineplex.com.kh/cinema/showtimes";
    public static final String PLAT_CINEPLEX_URL = "http://www.platinumcineplex.com.kh/phnom-penh/";

    public static final String LEGEND_URL = "https://www.legend.com.kh/Browsing/Movies/NowShowing";
    public static final String LEGEND_NOWSHOWING = "https://www.legend.com.kh/Browsing/Movies/NowShowing";

    private TextView mTitle;

    private ListView mMovieLv;
    private ArrayAdapter<String> arrayAdapter;

    private Document doc;

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);


        for (int i = 0; i < 3; i++){

            new RecieveDataLegend().execute();
            count++;

        }





    }


    class RecieveDataLegend extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... params) {

            try {
                doc = null;
                if (count ==0)
                {
                    doc = Jsoup.connect(LEGEND_URL).get();
                }
                if (count == 1){
                    doc = Jsoup.connect(MAJOR_CINEPLEX_URL).get();

                }
                if (count == 2){
                    doc = Jsoup.connect(PLAT_CINEPLEX_URL).get();

                }


            String name = "";
            String altName = "";
            String description = "";
            String rating ="";
            String runtime = "";
            String img = "";
            String trailer_url = "";


            Boolean isAired = false, isComming = false, isShowing = false;


            ArrayList<MovieGenre> genre = new ArrayList<>();
            ArrayList<MovieHall> movieHall = new ArrayList<>();
            ArrayList<MovieDate> movieDate = new ArrayList<>();
            ArrayList<MovieShowTime> movieShowTime = new ArrayList<>();
            ArrayList<MovieAltID> movieAltID = new ArrayList<>();
            ArrayList<MovieURL> movieURLs = new ArrayList<>();




            if (count == 0){

            }
            if (count == 1){

            }
            if (count == 2){

            }


            MovieAltID mMovieAltID = new MovieAltID();
            MovieDate mMovieDate = new MovieDate();
            MovieHall mMovieHall = new MovieHall();
            MovieShowTime mMovieShowTime = new MovieShowTime();
            MovieGenre movieGenre = new MovieGenre();
            MovieURL movieURL = new MovieURL();




            Movie movie = new Movie(name, altName, description, isAired, isShowing, isComming,rating , runtime , trailer_url, img, movieAltID, movieHall, genre, movieDate, movieShowTime, movieURLs);

        } catch (IOException e) {
            e.printStackTrace();
        }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {


            super.onPostExecute(aVoid);
        }
    }

}
