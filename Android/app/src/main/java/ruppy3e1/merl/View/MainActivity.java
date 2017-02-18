package ruppy3e1.merl.View;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import ruppy3e1.merl.Datasource.MovieDataSource;
import ruppy3e1.merl.Fragments.MainFragment;
import ruppy3e1.merl.R;

public class MainActivity extends AppCompatActivity {

    private static final int CONTENT_VIEW_ID = 10101010;
    private static final String MAIN_FRAGMENT_TAG = "mainfagment_tag";

    @Override
    protected void onResume() {
        super.onResume();

        MovieDataSource dataSource = new MovieDataSource(this.getApplicationContext());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.placeholder);

        if (savedInstanceState == null) {
            MainFragment mainFragment = new MainFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(mainFragment, MAIN_FRAGMENT_TAG);
            ft.commit();
        }


    }
}
