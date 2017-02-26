package ruppy3e1.merl.View;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import ruppy3e1.merl.Datasource.MovieDataSource;
import ruppy3e1.merl.Fragments.MainFragment;
import ruppy3e1.merl.R;

public class MainActivity extends AppCompatActivity {

    private static final int CONTENT_VIEW_ID = 10101010;
    private static final String MAIN_FRAGMENT_TAG = "mainfagment_tag";


    public static final String PREFERENCES = "PREFERENCES";
    public static final String PREF_DATABASE_VERSION = "DATABASE_VERSION";
    private static final String PREF_STATUS = "STATUS";
    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onResume() {
        super.onResume();

        MovieDataSource dataSource = new MovieDataSource(this.getApplicationContext());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkDatabaseVersion();


        setContentView(R.layout.activity_main);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.placeholder);

        if (savedInstanceState == null) {
            MainFragment mainFragment = new MainFragment();

            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.placeholder, mainFragment, MAIN_FRAGMENT_TAG);
            ft.commit();
        }


    }

    private void checkDatabaseVersion() {
        String timeStamp = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
        SharedPreferences pref = this.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);


        int savedDate = pref.getInt(PREF_DATABASE_VERSION, 0);
        boolean status = pref.getBoolean(PREF_STATUS, false);
        if ((Integer.parseInt(timeStamp) > savedDate) && !status){

            this.deleteDatabase("movies.db");
            Intent intent = new Intent(this, DownloadActivity.class);
            //Bundle bundle = new Bundle();


            Log.i(TAG, "checkDatabaseVersion: finished. try to open fragment");
            startActivity(intent);
            finish();
        }else {
            Log.i(TAG, "checkDatabaseVersion: database already exist lauching mainfragment");
        }




    }
}
