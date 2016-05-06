package co.aartea.baseballapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class AddPlayer extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
    private EditText playerPosition;

    private ArrayList newPlayer;

    private Button addPlayer;
    private Button cancelAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        firstName = (EditText) findViewById(R.id.first_name);
        lastName = (EditText) findViewById(R.id.last_name);
        playerPosition = (EditText) findViewById(R.id.player_position);

        addPlayer = (Button) findViewById(R.id.add);
        addPlayer.setOnClickListener(this);
        cancelAdd = (Button) findViewById(R.id.cancel_action);
        cancelAdd.setOnClickListener(this);

        newPlayer = new ArrayList();
    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.cancel_action){
            finish();
        }
        else if(v.getId() == R.id.add){
            String getFirst = firstName.getText().toString();
            String getLast = lastName.getText().toString();
            String pos = playerPosition.getText().toString();

            Player player = new Player();
            player.setFname(getFirst);
            player.setLname(getLast);
            player.setPosition(pos);



        }
    }
}
