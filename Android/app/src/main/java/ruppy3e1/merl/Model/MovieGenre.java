package ruppy3e1.merl.Model;

import java.io.Serializable;
import java.util.Vector;

/**
 * Created by chunpghing
 */

public class MovieGenre implements Serializable{

    private int mId = -1;
    private int mGenreCode;

    public static final int ACTION = 1;
    public static final int ADVENTURE = 2;
    public static final int ANIMATION = 3;
    public static final int BIOGRAPHY = 4;
    public static final int COMEDY = 5;
    public static final int CRIME = 6;
    public static final int  DOCUMENTARY = 7;
    public static final int DRAMA = 8;
    public static final int FAMILY = 9;
    public static final int FANTASY = 10;
    public static final int FILM_NOIR = 11;
    public static final int HISTORY = 12;
    public static final int HORROR = 13;
    public static final int MUSIC = 14;
    public static final int MUSICAL = 15;
    public static final int MYSTERY = 16;
    public static final int ROMANCE = 17;
    public static final int SCI_FI = 18;
    public static final int SPORT = 19;
    public static final int THRILLER = 20;
    public static final int WAR = 21;
    public static final int WESTERN = 22;
    public static final int ANIME = 23;
    public static final int OTHER = 24;

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getmGenreCode() {
        return mGenreCode;
    }

    public void setmGenreCode(int mGenreCode) {
        this.mGenreCode = mGenreCode;
    }

    public MovieGenre(int id, int code){

        mId = id;
        mGenreCode = code;


    }


}
