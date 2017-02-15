package ruppy3e1.merl.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ruppy3e1.merl.R;

/**
 * Created by chunpghing
 */

public class MainFragment extends Fragment {

    public static final String TAG = "Main Fragment";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


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
