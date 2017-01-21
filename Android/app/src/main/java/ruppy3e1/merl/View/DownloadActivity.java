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

import ruppy3e1.merl.R;
import ruppy3e1.merl.Util.ParseLegend;

public class DownloadActivity extends AppCompatActivity {


    private static final String TAG = DownloadActivity.class.getSimpleName();
    public static final String MAJOR_CINEPLEX_URL = "http://www.majorcineplex.com.kh/cinema/showtimes";
    public static final String PLAT_CINEPLEX_URL = "http://www.platinumcineplex.com.kh/phnom-penh/";

    public static final String LEGEND_SHOWTIME = "https://www.legend.com.kh/Payments/ShowTime.aspx";
    public static final String LEGEND_NOWSHOWING = "https://www.legend.com.kh/Browsing/Movies/NowShowing";

    private TextView mTitle;

    private ListView mMovieLv;
    private ArrayAdapter<String> arrayAdapter;

    private Document doc;
    private ArrayList<String> Movies;
    private ArrayList<String> Links;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        new RecieveData().execute();




    }

    class RecieveData extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... params) {

            try {
                doc = Jsoup.connect(LEGEND_SHOWTIME).get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            Movies = new ArrayList<>();


            Movies = ParseLegend.getMovies(doc);
            Links = ParseLegend.getImgLinks(doc);



            mTitle = (TextView) findViewById(R.id.title);


            mTitle.setText(ParseLegend.getTitle(doc));



            for (String m : Movies){
                Log.i(TAG, "onCreate: Movie Title: " + m);
            }
            for (String l : Links){
                Log.i(TAG, "onCreate: Image Links: " + l);
            }

            Log.i(TAG, "onCreate: title text = " + mTitle.getText());


            ParseLegend.getTimeTable(doc);


            super.onPostExecute(aVoid);
        }
    }

}
