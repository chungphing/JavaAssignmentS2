package test.test.test;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private Document Doc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new RecieveData().execute();

    }



    class RecieveData extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            try {
                Doc = Jsoup.connect("http://www.cookinglight.com/").get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            Elements elements = Doc.select("#block-system-main > div > div > div > div.panel-pane.pane-entity-field.pane-node-field-hp-150x150-touts > div > div");

            for (Element element : elements){
                Element title = element.select("aside > div > h2 > a > div > div").first();
                Element url = element.select("aside > div > a").first();
                Element image = element.select(" aside > div > a > div > div > div > div > picture > source:nth-child(4)").first();

                Log.i(TAG, "onPostExecute: title :" + title.text());
                Log.i(TAG, "onPostExecute: url :" + url.attr("href"));
                Log.i(TAG, "onPostExecute: image link :" + image.attr("data-srcset"));
            }


            super.onPostExecute(aVoid);
        }
    }
}
