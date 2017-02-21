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

import ruppy3e1.merl.Database.MovieSQLiteHelper;
import ruppy3e1.merl.Datasource.MovieDataSource;
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
    private MovieDataSource dataSource;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);



        dataSource = new MovieDataSource(this);


        Log.i(TAG, "onCreate: in download activity");

        new RecieveDataLegend().execute();






    }


    class RecieveDataLegend extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... params) {

            try {
               // doc = null;

                    doc = Jsoup.connect(LEGEND_URL).get();
//
//                    doc = Jsoup.connect(MAJOR_CINEPLEX_URL).get();
//
//                }
//                if (count == 2){
//                    doc = Jsoup.connect(PLAT_CINEPLEX_URL).get();
//
//                }


            String name = "";
            String altName = "";
            String description = "";
            String rating ="";
            String runtime = "";
            String img = "";
            String trailer_url = "";


            Boolean isAired = false, isComming = false, isShowing = false;

                Log.i(TAG, "doInBackground: count " + count);

            ArrayList<MovieGenre> genre = new ArrayList<>();
            ArrayList<MovieHall> movieHall = new ArrayList<>();
            ArrayList<MovieDate> movieDate = new ArrayList<>();
            ArrayList<MovieShowTime> movieShowTime = new ArrayList<>();
            ArrayList<MovieAltID> movieAltID = new ArrayList<>();
            ArrayList<MovieURL> movieURLs = new ArrayList<>();

            boolean hasMore =false;


            //if (count == 0){




            Elements list_item = doc.select("#movies-list > div");


            for (Element element : list_item){
                Log.i(TAG, "doInBackground: im in the elements");
                //div.item-details > div > div > a

                //div.item-details > div > div > a > h3

                //div.image-outer > a > img

                name = element.select("div.item-details > div > div > a > h3").text();
                img = element.select("div.image-outer > a > img").attr("src");
                String legendUrl = element.select("div.item-details > div > div > a").attr("href");


                if (!legendUrl.equals("")) hasMore = true;


                Document detail = null;
                try{
                    detail = Jsoup.connect("https://www.legend.com.kh" + legendUrl).get();

                }catch (IOException e){
                    e.printStackTrace();
                    Log.i(TAG, "doInBackground: error");
                }

                runtime = detail.select("body > div.wrapper > section.content > article > div > div > div.description-box > dl > dd:nth-child(4)").text();
                rating = detail.select("body > div.wrapper > section.content > article > div > div > div.description-box > dl > dd:nth-child(2)").text();
                description = detail.select("body > div.wrapper > section.content > article > div > div > div.description-box > p").text();
                trailer_url = detail.select("#trailer").attr("href");

                hasMore = false;
                while (hasMore){




                    //body > div.wrapper > section.content > article > div > div > div.description-box > dl > dd:nth-child(2)

                    //body > div.wrapper > section.content > article > div > div > div.description-box > dl > dd:nth-child(4)


                    //body > div.wrapper > section.content > article > div > div > div.description-box > dl > dd:nth-child(6)

                    MovieAltID mMovieAltID = new MovieAltID();
                    MovieDate mMovieDate = new MovieDate();
                    MovieHall mMovieHall = new MovieHall();
                    MovieShowTime mMovieShowTime = new MovieShowTime();
                    MovieGenre movieGenre = new MovieGenre();
                    MovieURL movieURL = new MovieURL();




                    movieURL.setLegendURL(legendUrl);



                    movieGenre.setmGenreCode(MovieGenre.toCode(detail.select("body > div.wrapper > section.content > article > div > div > div.description-box > dl > dd:nth-child(6)").text()));

                    //download data for various table here




                    movieAltID.add(mMovieAltID);
                    genre.add(movieGenre);
                    movieDate.add(mMovieDate);
                    movieShowTime.add(mMovieShowTime);
                    movieURLs.add(movieURL);
                    movieHall.add(mMovieHall);

                }
//            }
//            if (count == 1){
//
//            }
//            if (count == 2){
//
//            }



                Log.i(TAG, "doInBackground: " + "name: " +  name+ " alt name: "+ altName +" description: "+ description+ " rating: " + rating + " image: "+ img +" trailer:  "+ trailer_url);


                Movie movie = new Movie(name, altName, description, isAired, isShowing, isComming,rating , runtime , trailer_url, img, movieAltID, movieHall, genre, movieDate, movieShowTime, movieURLs);
            dataSource.create(movie);
            }


        } catch (IOException e) {
            e.printStackTrace();
                Log.i(TAG, "doInBackground: error");
        }
            return null;



        }

        @Override
        protected void onPostExecute(Void aVoid) {


            super.onPostExecute(aVoid);
        }
    }



}
