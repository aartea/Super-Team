package co.aartea.baseballapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class AddPlayer extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
    private EditText position;

    private ArrayList newPlayer;

    private Button addPlayer;
    private Button cancelAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        firstName = (EditText) findViewById(R.id.first_name);
        lastName = (EditText) findViewById(R.id.last_name);
        position = (EditText) findViewById(R.id.player_position);

        addPlayer = (Button) findViewById(R.id.add);
        addPlayer.setOnClickListener(this);
        cancelAdd = (Button) findViewById(R.id.cancel_action);
        cancelAdd.setOnClickListener(this);


    }
}
