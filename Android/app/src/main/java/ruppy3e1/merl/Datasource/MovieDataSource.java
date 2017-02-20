package ruppy3e1.merl.Datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ruppy3e1.merl.Database.MovieSQLiteHelper;
import ruppy3e1.merl.Model.Movie;
import ruppy3e1.merl.Model.MovieAltID;
import ruppy3e1.merl.Model.MovieDate;
import ruppy3e1.merl.Model.MovieGenre;
import ruppy3e1.merl.Model.MovieHall;
import ruppy3e1.merl.Model.MovieShowTime;
import ruppy3e1.merl.Model.MovieURL;

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

    public void create(Movie movie){
        SQLiteDatabase database = open();

        database.beginTransaction();
        //implement detail
        ContentValues movieValues = new ContentValues();
        movieValues.put(MovieSQLiteHelper.COLUMN_MOVIE_NAME, movie.getmName());
        movieValues.put(MovieSQLiteHelper.COLUMN_MOVIE_ALT_NAME, movie.getmAltName());
        movieValues.put(MovieSQLiteHelper.COLUMN_MOVIE_RATING, movie.getmRating());
        movieValues.put(MovieSQLiteHelper.COLUMN_MOVIE_RUNTIME, movie.getmRunTime());
        movieValues.put(MovieSQLiteHelper.COLUMN_MOVIE_DESCRIPTION, movie.getmDescription());
        movieValues.put(MovieSQLiteHelper.COLUMN_MOVIE_IS_AIRED , movie.getAired());
        movieValues.put(MovieSQLiteHelper.COLUMN_MOVIE_IS_SHOWING , movie.getShowing());
        movieValues.put(MovieSQLiteHelper.COLUMN_MOVIE_IS_COMMING , movie.getComming());
        movieValues.put(MovieSQLiteHelper.COLUMN_MOVIES_TRAILER_LINK , movie.getmTrailerLink());
        movieValues.put(MovieSQLiteHelper.COLUMN_MOVIE_IMAGE_LINK , movie.getmImgLink());


        long movieID = database.insert(MovieSQLiteHelper.MOVIES_TABLE, null, movieValues);

        for (MovieAltID movieAltID : movie.getmMovieAltID()){
            ContentValues altIDValues = new ContentValues();
            altIDValues.put(MovieSQLiteHelper.COLUMN_MOVIE_LEGEND_ID, movieAltID.getLegendID());
            altIDValues.put(MovieSQLiteHelper.COLUMN_MOVIE_MAJOR_ID, movieAltID.getMajorID());
            altIDValues.put(MovieSQLiteHelper.COLUMN_MOVIE_PLATINUM_ID, movieAltID.getPlatinumID());


            database.insert(MovieSQLiteHelper.ALT_ID_TABLE, null, altIDValues);

        }

        for (MovieGenre movieGenre: movie.getmMovieGenre()){
            ContentValues genreValue = new ContentValues();
            genreValue.put(MovieSQLiteHelper.COLUMN_MOVIE_GENRE, movieGenre.getmGenreCode());

            database.insert(MovieSQLiteHelper.GENRES_TABLE, null , genreValue);

            //// TODO: 2/17/17  have not yet implement
        }


        for (MovieShowTime movieShowTime : movie.getmMovieShowTime()){

            ContentValues movieShowTimeValues = new ContentValues();
            movieShowTimeValues.put(MovieSQLiteHelper.COLUMN_MOVIE_LEGEND_SHOWTIME, movieShowTime.getLegendShowTime());
            movieShowTimeValues.put(MovieSQLiteHelper.COLUMN_MOVIE_MAJOR_SHOWTIME, movieShowTime.getMajorShowTime());
            movieShowTimeValues.put(MovieSQLiteHelper.COLUMN_MOVIE_PLATINUM_SHOWTIME, movieShowTime.getPlatinumShowTime());

            database.insert(MovieSQLiteHelper.MOVIE_SHOWTIMES_TABLE, null, movieShowTimeValues);

        }

        for (MovieHall movieHall : movie.getmMovieHall()){
            ContentValues movieHallValues = new ContentValues();
            movieHallValues.put(MovieSQLiteHelper.COLUMN_MOVIE_LEGEND_HALL, movieHall.getLegendHall());
            movieHallValues.put(MovieSQLiteHelper.COLUMN_MOVIE_MAJOR_HALL, movieHall.getMajorHall());
            movieHallValues.put(MovieSQLiteHelper.COLUMN_MOVIE_PLATINUM_HALL, movieHall.getPlatinumHall());

            database.insert(MovieSQLiteHelper.MOVIE_HALLS_TABLE, null, movieHallValues);
        }


        for (MovieDate movieDate : movie.getmMovieDate()){
            ContentValues movieDateValues = new ContentValues();
            movieDateValues.put(MovieSQLiteHelper.COLUMN_MOVIE_LEGEND_DATE, movieDate.getLegendDate());
            movieDateValues.put(MovieSQLiteHelper.COLUMN_MOVIE_MAJOR_DATE, movieDate.getMajorDate());
            movieDateValues.put(MovieSQLiteHelper.COLUMN_MOVIE_PLATINUM_DATE, movieDate.getPlatinumDate());

            database.insert(MovieSQLiteHelper.MOVIE_HALLS_TABLE, null, movieDateValues);

        }
        for (MovieURL movieURL : movie.getmMovieURL()){
            ContentValues movieURLValues = new ContentValues();

            movieURLValues.put(MovieSQLiteHelper.COLUMN_MOVIE_DETAIL_URL_LEGEND, movieURL.getLegendURL());
            movieURLValues.put(MovieSQLiteHelper.COLUMN_MOVIE_DETAIL_URL_MAJOR, movieURL.getMajorURL());
            movieURLValues.put(MovieSQLiteHelper.COLUMN_MOVIE_DETAIL_URL_PLATINUM, movieURL.getLegendURL());

            database.insert(MovieSQLiteHelper.MOVIE_DETAIL_URLS_TABLE,null,movieURLValues);

        }


        database.setTransactionSuccessful();
        database.endTransaction();
        close(database);

    }

    private SQLiteDatabase open(){
       return mMovieSQLiteHelper.getWritableDatabase();
    }


    private void close(SQLiteDatabase database){
        database.close();
    }

}
