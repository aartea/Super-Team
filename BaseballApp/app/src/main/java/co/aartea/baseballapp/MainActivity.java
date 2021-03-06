package co.aartea.baseballapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import co.aartea.baseballapp.setup.DBAssetHelper;

public class MainActivity extends AppCompatActivity {
    private CursorAdapter mCursorAdapter;
    private ListView playerListView;
    private Cursor cursor;

    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCursorAdapter = null;

        playerListView = (ListView)findViewById(R.id.list_view);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        //Read database that contains our pre-populated info.
        DBAssetHelper dbAssetHelper = new DBAssetHelper(MainActivity.this);
        dbAssetHelper.getReadableDatabase();

        //Main method will contain our handleIntent method and passes getIntent() as a parameter.
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    //Will inflate our menu's search functionality
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        // Borrowed code from GitHub repo: "Associate searchable configuration with the SearchView"
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    //Method handles our implicit intent
    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {

            //Implicit Intent needs to handle our query!
            String query = intent.getStringExtra(SearchManager.QUERY);
            Toast.makeText(MainActivity.this,"Searching for "+query,Toast.LENGTH_SHORT).show();

            //Cursor needs access to cursor in our SQL helper class.
            cursor = PlayerSQLiteHelper.getInstance(MainActivity.this).getPlayerList(query);
            DatabaseUtils.dumpCursor(cursor);

            playerListView = (ListView)findViewById(R.id.list_view);

            if(mCursorAdapter == null) {
                mCursorAdapter = new SimpleCursorAdapter(
                        MainActivity.this,
                        android.R.layout.simple_list_item_1,
                        cursor,
                        new String[]{PlayerSQLiteHelper.COL_FIRST_NAME},
                        new int[]{android.R.id.text1},
                        0
                );
                playerListView.setAdapter(mCursorAdapter);

                //onItemClickListener that will handle detail view when listview item is clicked
                playerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent =  new Intent(MainActivity.this,DetailActivity.class);
                        cursor.moveToPosition(position);
                        intent.putExtra("id",cursor.getInt(cursor.getColumnIndex(PlayerSQLiteHelper.COL_ID)));
                        startActivity(intent);
                    }
                });
            }else{
                mCursorAdapter.swapCursor(cursor);
            }
        }
    }

    //1. Use a dialog box for user to input stuff; or 2. use an activity
    //Nvm. Dialog box not used for this purpose; create xml layout file.

    public void click_add(View view) {
        Intent i = new Intent(MainActivity.this, AddPlayer.class);
        startActivity(i);
    }
}