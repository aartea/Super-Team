package co.aartea.baseballapp.setup;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by aaron on 4/29/2016.
 */
public class DBAssetHelper extends SQLiteAssetHelper {

    //Single file; not a zip file; use .db!!
    private static final String DATABASE_NAME = "PLAYER_DB.db";
    private static final int DATABASE_VERSION = 1;

    public DBAssetHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        setForcedUpgrade();
    }
}
