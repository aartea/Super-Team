package co.aartea.baseballapp;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        addPlayer.setOnClickListener((View.OnClickListener) this);
        cancelAdd = (Button) findViewById(R.id.cancel_action);
        cancelAdd.setOnClickListener((View.OnClickListener) this);

        newPlayer = new ArrayList();
    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.cancel_action){
            //Ends our activity
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

            insertPlayer(player);

        }
        //Ends our activity
        finish();
    }

    public void insertPlayer(Player player){

        //Create our db object
        PlayerSQLiteHelper helpMe = new PlayerSQLiteHelper(this);

        //Insert values using writable db
        SQLiteDatabase db = helpMe.getWritableDatabase();

        //Receive our values from our class and pass them through here!
        ContentValues cv = new ContentValues();

        cv.put(PlayerSQLiteHelper.COL_FIRST_NAME, player.getFname());
        cv.put(PlayerSQLiteHelper.COL_LAST_NAME, player.getLname());
        cv.put(PlayerSQLiteHelper.COL_POSITION, player.getPosition());

        long insertColumn = db.insert(PlayerSQLiteHelper.PLAYERS_TABLE_NAME, null, cv);
        db.close();
        Toast.makeText(AddPlayer.this, "Insert into columnID "+ insertColumn, Toast.LENGTH_SHORT).show();
    }
}
