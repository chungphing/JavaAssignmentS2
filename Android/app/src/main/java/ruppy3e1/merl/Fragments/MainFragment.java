package ruppy3e1.merl.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ruppy3e1.merl.Database.MovieSQLiteHelper;
import ruppy3e1.merl.R;
import ruppy3e1.merl.View.DownloadActivity;

/**
 * Created by chunpghing
 */

public class MainFragment extends Fragment {

    public static final String TAG = "Main Fragment";

    public static final String PREFERENCES = "PREFERENCES";
    public static final String PREF_DATABASE_VERSION = "DATABASE_VERSION";
    private static final String PREF_STATUS = "STATUS";
    private int dbVersion;
    private int status;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        checkDatabaseVersion();

    }

    private void checkDatabaseVersion() {
        SharedPreferences pref = getActivity().getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);


        dbVersion = pref.getInt(PREF_DATABASE_VERSION, 0);
        if (dbVersion != 0){
            status = pref.getInt(PREF_STATUS, 0);

            if (status == 0){

                //data is not yet update or initialize
                //now switching to download activity


                Intent intent = new Intent(getActivity(), DownloadActivity.class);
                //Bundle bundle = new Bundle();



                startActivity(intent);
                getActivity().finish();
            }
            else if (status == 1){
                initDataset();
            }
        }



    }

    private void initDataset() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);


        getActivity().setTitle("Hello world");


        Toast.makeText(getActivity(), "Toasting from fragment", Toast.LENGTH_SHORT).show();
        return view;
    }


    public static MainFragment newInstance(){


        return new MainFragment();
    }
}
