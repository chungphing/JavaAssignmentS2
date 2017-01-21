package ruppy3e1.merl.Util;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import ruppy3e1.merl.R;
import ruppy3e1.merl.View.DownloadActivity;

/**
 * Created by chungphing
 */


/********************************
 *
 *  NOTE: LEGEND WEBSITE REMOVE POST AFTER THE MOVIES IS PLAYING SO AT NIGHT METHOD WILL RESULT IN 0 MOVIES... DON'T PANIC
 *
 *******************************/
public class ParseLegend {




    private static final String TAG = ParseLegend.class.getSimpleName();





    public static ArrayList<String> getMovies(Document doc){
            ArrayList<String> Movies = new ArrayList<>();


            Elements spanName = doc.select("div.st_container");
            Elements spanName2 = spanName.select("div.sp_sessions_title");
            Elements text = spanName2.select("a");


            for (Element t : text){


                Movies.add(t.text());

            }

        return Movies;
    }


    public static ArrayList<String> getImgLinks(Document doc){
         ArrayList<String> ImgLinks = new ArrayList<>();


            Elements spanName = doc.select("div.st_container");
            Elements spanName2 = spanName.select("div.sp_sessions_title");
            Elements text = spanName2.select("a");

            Elements imgName2 = spanName.select("div.sp_sessions_img");
            Elements imgSpan = imgName2.select("a");

            Elements img = imgSpan.select("img");

        
            for (Element i : img){

                ImgLinks.add(i.attr("src"));

            }

        return ImgLinks;
    }

    public static String getTitle(Document doc){
            return doc.title();
    }



    public static void getTimeTable(Document doc){


        Elements post = doc.select("div.st_container");
        Elements timetables = post.select("div.sp_sessions_time");


        //TODO: get time tables



    }

    public static void getParentalRating(Document doc){

        Elements post = doc.select("div.st_container");
        Elements rating = post.select("div.bx-rtg > ul > li.rte-m > img");
        String stringRating = rating.attr("alt");
        //TODO: may need to get by ID
    }

    public static void getLegendID(Document doc){


        Elements post = doc.select("div.st_container");
        Elements links = post.select("sp_sessions_title > a");
        String stringRating = links.attr("href");


        String LegendID = stringRating.substring(stringRating.lastIndexOf('/') + 1);

    }


    public static void getRuntime(Document doc){


        Elements post = doc.select("div.st_container");
        Elements runtime = post.select("div.bx-rtg > ul > li.vle-t > span");
        String stringRuntime = runtime.text();
    }


}