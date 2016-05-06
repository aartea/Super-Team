package co.aartea.baseballapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by aaron on 4/29/2016.
 */
public class PlayerSQLiteHelper extends SQLiteOpenHelper{

    //Database profile

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PLAYER_DB";
    public static final String PLAYERS_TABLE_NAME = "player_table";

    //Column names
    public static final String COL_ID = "_id";
    public static final String COL_FIRST_NAME = "first_name";
    public static final String COL_LAST_NAME = "last_name";
    public static final String COL_AGE= "age";
    public static final String COL_TEAM = "team";
    public static final String COL_POSITION = "position";
    public static final String COL_RBI = "rbi";
    public static final String COL_BA = "ba";

    //Array of our database columns

    public static final String[] PLAYERS_COLUMNS = {COL_ID, COL_FIRST_NAME,COL_LAST_NAME,COL_AGE,COL_TEAM, COL_POSITION, COL_RBI, COL_BA};

    //Create our table as a string parameter

    private static final String CREATE_PLAYERS_TABLE = "CREATE TABLE "+PLAYERS_TABLE_NAME +
            "("+
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_FIRST_NAME+" TEXT, "+
            COL_LAST_NAME+" TEXT, "+
            COL_AGE+" INTEGER, "+
            COL_TEAM+" TEXT, "+
            COL_POSITION+" TEXT, "+
            COL_RBI+ " INTEGER, " +
            COL_BA+ " REAL )";

    //Class constructor
    private static PlayerSQLiteHelper instance;
    //Our singleton
    public static PlayerSQLiteHelper getInstance(Context context){
        if(instance == null){
            instance = new PlayerSQLiteHelper(context);
        }
        return instance;
    }

    private PlayerSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //End class constructor

    //onCreate method for database
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_PLAYERS_TABLE);
    }

    //onUpgrade method for database; drops a table if one exists and revamps it with new data
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PLAYERS_TABLE_NAME );
        this.onCreate(db);
    }

    //Passes parameter type string of our query
    public Cursor getPlayerList(String query){

        SQLiteDatabase db = this.getReadableDatabase();

        //Cursor will go and locate your query among column values and it will be returned by this method.

        Cursor cursor = db.query(PLAYERS_TABLE_NAME, // a. table
                PLAYERS_COLUMNS, // b. column names
                COL_FIRST_NAME + " LIKE ? OR " + COL_LAST_NAME + " LIKE ? OR " + COL_POSITION + " LIKE ? ", // c. selections
                new String[]{"%" + query + "%", "%" + query + "%", "%" + query + "%"}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit


        return cursor;
    }

    public String getDescriptionById(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(PLAYERS_TABLE_NAME,
                new String[]{COL_FIRST_NAME},
                COL_ID+" = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);

        if(cursor.moveToFirst()){
            return cursor.getString(cursor.getColumnIndex(COL_FIRST_NAME));
        } else {
            return "No Description Found";
        }
    }
}