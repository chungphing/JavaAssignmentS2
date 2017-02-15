package ruppy3e1.merl.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chunpghing
 */

public class MovieDataSource {

    private Context mContext;
    private MovieSQLiteHelper mMovieSQLiteHelper;

    public MovieDataSource(Context context){
        mContext = context;
        mMovieSQLiteHelper = new MovieSQLiteHelper(context);
        SQLiteDatabase database = mMovieSQLiteHelper.getReadableDatabase();
        database.close();
    }
}
