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
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import co.aartea.baseballapp.setup.DBAssetHelper;

public class MainActivity extends AppCompatActivity {
    private CursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCursorAdapter = null;

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

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {

            //Implicit Intent needs to handle our query!
            String query = intent.getStringExtra(SearchManager.QUERY);

            //Cursor needs access to cursor in our SQL helper class.
            Cursor cursor = PlayerSQLiteHelper.getInstance(MainActivity.this).getPlayerList(query);
            DatabaseUtils.dumpCursor(cursor);

            ListView playerListView = (ListView)findViewById(R.id.list_view);

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
            }else{
                mCursorAdapter.swapCursor(cursor);
            }
        }
    }
}