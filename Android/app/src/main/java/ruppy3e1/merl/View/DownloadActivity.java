package ruppy3e1.merl.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.LoginFilter;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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



    public static final String PREFERENCES = "PREFERENCES";
    public static final String PREF_DATABASE_VERSION = "DATABASE_VERSION";
    private static final String PREF_STATUS = "STATUS";

    private static final String TAG = DownloadActivity.class.getSimpleName();

    public static final String LEGEND_URL = "https://www.legend.com.kh/Browsing/Movies/NowShowing";
    private int Stage = 0;

    private static final String LEGEND_COMING_SOON = "https://www.legend.com.kh/Browsing/Movies/ComingSoon";

    //TODO: HAVEN'T IMPLEMENTED YET


    public static final String LEGEND_NOWSHOWING = "https://www.legend.com.kh/Browsing/Movies/NowShowing";

    public static final String MAJOR_CINEPLEX_URL = "http://www.majorcineplex.com.kh/cinema/showtimes";
    public static final String PLAT_CINEPLEX_URL = "http://www.platinumcineplex.com.kh/phnom-penh/";


    private TextView mTitle;

    private ListView mMovieLv;
    private ArrayAdapter<String> arrayAdapter;

    private Document doc;
    private MovieDataSource dataSource;
    private int count = 0;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);


        mTitle = (TextView) findViewById(R.id.title);
        dataSource = new MovieDataSource(this);


        Log.i(TAG, "onCreate: in download activity");


        if (isNetworkAvailable()){


            progressBar = (ProgressBar) findViewById(R.id.progess);
            progressBar.setMax(10);
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(0);
            mTitle.setText("Downloading...");
            new RecieveDataLegend().execute();
        }else {
            mTitle.setText("No Internet Connection");
        }







    }


    class RecieveDataLegend extends AsyncTask<Integer, Integer, String>{
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);


        }

        @Override
        protected String doInBackground(Integer... params) {

            String name = "";
            String altName = "";
            String description = "";
            String rating ="";
            String runtime = "";
            String img = "";
            String trailer_url = "";





            try {

                    doc = Jsoup.connect(LEGEND_URL).get();


            Elements list_item = doc.select("#movies-list > div.list-item");

                Stage = 1;



                publishProgress(1);

            for (Element element : list_item){



                ArrayList<MovieGenre> genre = new ArrayList<>();
                ArrayList<MovieHall> movieHall = new ArrayList<>();
                ArrayList<MovieDate> movieDate = new ArrayList<>();
                ArrayList<MovieShowTime> movieShowTime = new ArrayList<>();
                ArrayList<MovieAltID> movieAltID = new ArrayList<>();
                ArrayList<MovieURL> movieURLs = new ArrayList<>();

                name = element.select("div.item-details > div > div > a > h3").text();
                img ="https:" +  element.select("div.image-outer > a > img").attr("src");
                String legendUrl = "https://www.legend.com.kh" + element.select("div.item-details > div > div > a").attr("href");


                Document detail = null;
                try{
                    detail = Jsoup.connect(legendUrl).get();
                }catch (IOException e){
                    e.printStackTrace();
                }

                runtime = detail.select("body > div.wrapper > section.content > article > div > div > div.description-box > dl > dd:nth-child(4)").text();
                if (!detail.select("body > div.wrapper > section.content > article > div > div > div.description-box > dl > dt:nth-child(3)").text().equals("Run Time:")){
                    runtime = "";
                }
                rating = detail.select("body > div.wrapper > section.content > article > div > div > div.description-box > dl > dd:nth-child(2)").text();
                description = detail.select("body > div.wrapper > section.content > article > div > div > div.description-box > p").text();
                trailer_url = detail.select("#trailer").attr("href");


                Elements alltime = detail.select("#show-times > div.film-list > div.film-item").not("div.future");

                MovieAltID mMovieAltID = new MovieAltID();
                // MovieShowTime legendShowTime = new MovieShowTime();
                MovieGenre movieGenre = new MovieGenre();
                MovieURL movieURL = new MovieURL();
                movieURL.setLegendURL(legendUrl);
                //movie genre implement here

                movieURL.setLegendURL(legendUrl);
                movieGenre.setmGenreCode(MovieGenre.toCode(detail.select("body > div.wrapper > section.content > article > div > div > div.description-box > dl > dd:nth-child(6)").text()));
                //movie date implement here



                //hall need to at later because it stored at another page separately
                movieAltID.add(mMovieAltID);
                genre.add(movieGenre);
                movieURLs.add(movieURL);
                for (Element el : alltime){
//                    MovieHall mMovieHall = new MovieHall();
//                    MovieDate mMovieDate = new MovieDate();

                    //legendShowTime.setLegendShowTime(el.select("div > div.film-header > a > h3").text() + "/" + el.select(" div > div.session > h4.session-date").text() +"/"+  el.select(" div > div.session > h4.session-time > a > time").text()  );
                    Elements sessions = el.select("div > div.session");
                    for (Element s : sessions){
                        MovieShowTime mMovieShowTime = new MovieShowTime();
                        Log.i(TAG, "doInBackground: " + el.select("div > div.film-header > a > h3").text() + "~" + s.select(" h4.session-date").text() + "~" + s.select("div.session-times > a > time").text());


                        mMovieShowTime.setLegendShowTime(el.select("div > div.film-header > a > h3").text() + "~" + s.select(" h4.session-date").text() + "~" + s.select("div.session-times > a > time").text());
                        movieShowTime.add(mMovieShowTime);
                    }




                }







            count++;




                Movie movie = new Movie(-1,name, altName, description, false, true, false,rating , runtime , trailer_url, img, movieAltID, movieHall, genre, movieDate, movieShowTime, movieURLs);
                dataSource.create(movie);
            }
                publishProgress(5);


        } catch (IOException e) {
            e.printStackTrace();
                Log.i(TAG, "doInBackground: error");
        }


        try{

            //download coming soon
            Document doc2 = Jsoup.connect(LEGEND_COMING_SOON).get();



            Elements list_item = doc2.select("#movies-list > div.list-item");
            Stage = 2;


            publishProgress(6);
            for (Element element : list_item){


                ArrayList<MovieGenre> genre = new ArrayList<>();
                ArrayList<MovieHall> movieHall = new ArrayList<>();
                ArrayList<MovieDate> movieDate = new ArrayList<>();
                ArrayList<MovieShowTime> movieShowTime = new ArrayList<>();
                ArrayList<MovieAltID> movieAltID = new ArrayList<>();
                ArrayList<MovieURL> movieURLs = new ArrayList<>();

                name = element.select("div.item-details > div > div > a > h3").text();
                img ="https:" +  element.select("div.image-outer > a > img").attr("src");
                String legendUrl = "https://www.legend.com.kh" + element.select("div.item-details > div > div > a").attr("href");



                Document detail = null;
                try{
                    detail = Jsoup.connect( legendUrl).get();
                }catch (IOException e){
                    e.printStackTrace();

                    Log.i(TAG, "doInBackground: error can't connect");
                }

                runtime = detail.select("body > div.wrapper > section.content > article > div > div > div.description-box > dl > dd:nth-child(4)").text();
                if (!detail.select("body > div.wrapper > section.content > article > div > div > div.description-box > dl > dt:nth-child(3)").text().equals("Run Time:")){
                    runtime = "";
                }

                    rating = detail.select("body > div.wrapper > section.content > article > div > div > div.description-box > dl > dd:nth-child(2)").text();
                description = detail.select("body > div.wrapper > section.content > article > div > div > div.description-box > p").text();
                trailer_url = detail.select("#trailer").attr("href");

//body > div.wrapper > section.content > article > div > div > div.description-box > dl > dd:nth-child(2)




                Elements alltime = detail.select("#show-times > div.film-list > div.film-item");
                MovieAltID mMovieAltID = new MovieAltID();
               // MovieShowTime legendShowTime = new MovieShowTime();
                MovieGenre movieGenre = new MovieGenre();
                MovieURL movieURL = new MovieURL();
                movieURL.setLegendURL(legendUrl);
                //movie genre implement here

                movieURL.setLegendURL(legendUrl);
                movieGenre.setmGenreCode(MovieGenre.toCode(detail.select("body > div.wrapper > section.content > article > div > div > div.description-box > dl > dd:nth-child(6)").text()));
                //movie date implement here



                //hall need to at later because it stored at another page separately
                movieAltID.add(mMovieAltID);
                genre.add(movieGenre);
                movieURLs.add(movieURL);
//                for (Element el : alltime){
//                    MovieHall mMovieHall = new MovieHall();
//                    MovieDate mMovieDate = new MovieDate();
//
//                    //legendShowTime.setLegendShowTime(el.select("div > div.film-header > a > h3").text() + "/" + el.select(" div > div.session > h4.session-date").text() +"/"+  el.select(" div > div.session > h4.session-time > a > time").text()  );
//                    MovieShowTime mMovieShowTime = new MovieShowTime();
//                    //#show-times > div.film-list > div:nth-child(1) > div > div.film-header > a > h3
//
//                    mMovieShowTime.setLegendShowTime(alltime.select("div > div.film-header > a > h3").first().text() + "~" + alltime.select("div > div:nth-child(2) > h4").first().text() + "~" + alltime.select("div > a > time").first().text());
//                    Log.i(TAG, "doInBackground: " + alltime.select("div > div.film-header > a > h3").first().text() + "~" + alltime.select("div > div:nth-child(2) > h4").first().text() + "~" + alltime.select("div > a > time").first().text());
//                    mMovieDate.setLegendDate(detail.select("#show-times > div.film-list > div:nth-child(1) > div > div:nth-child(2) > h4").text());
//
//
//
//                    //date only exit for comming soon entry
//
//                    //movie showtime implement here
//                    movieDate.add(mMovieDate);
//
//                    movieShowTime.add(mMovieShowTime);
//                    movieHall.add(mMovieHall);
//
//
//
//                }


                publishProgress(9);

                count++;


                Movie movie = new Movie(-1, name, altName, description, false, false, true,rating , runtime , trailer_url, img, movieAltID, movieHall, genre, movieDate, movieShowTime, movieURLs);
                dataSource.create(movie);
                publishProgress(10);

            }

        }catch (IOException e){
            e.printStackTrace();
            Log.i(TAG, "doInBackground: error");

        }
            return null;



        }

        @Override
        protected void onPostExecute(String aVoid) {


            Log.i(TAG, "onPostExecute: finished downloading and complete " + count + " loops");

            String timeStamp = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());

            SharedPreferences.Editor editor = getSharedPreferences(PREFERENCES,MODE_PRIVATE).edit();
            editor.putBoolean(PREF_STATUS, true);
            editor.putInt(PREF_DATABASE_VERSION,Integer.parseInt(timeStamp));
            editor.apply();


            Intent intent = new Intent(DownloadActivity.this, Launcher.class);
            startActivity(intent);
            finish();

            super.onPostExecute(aVoid);
        }
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
