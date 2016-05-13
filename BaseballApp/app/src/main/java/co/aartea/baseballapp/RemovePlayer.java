package co.aartea.baseballapp;


import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class RemovePlayer extends AppCompatActivity {
//    Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
    private Button cancel = (Button) findViewById(R.id.cancel);
    public RemovePlayer() {
        // Required empty public constructor
    }

    //Potential requirement of factory

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_remove_player, container, false);
    }

}



