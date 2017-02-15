package ruppy3e1.merl.Model;

import java.util.ArrayList;

/**
 * Created by chunpghing
 */

public class Movie {

    private String mName;
    private ArrayList<String> mAltName;

    private String mlegendId;
    private String mMajorId;
    private String mRating;
    private String mRunTime;
    private ArrayList<String> mGenre;
    private String mDescription;

    private String mLegendShows;
    private String mMajorShows;

    private String mLegendShowsHall;
    private String mMajorShowsHall;


    private int[] mLegendDate;
    private int[] mMajorDate;

    private Boolean isAired;
    private Boolean isShowing;
    private Boolean isComming;


    private String mTrailerLink;
    private String mImgLink;

    private String mDetailUrl;


    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public ArrayList<String> getmAltName() {
        return mAltName;
    }

    public void setmAltName(ArrayList<String> mAltName) {
        this.mAltName = mAltName;
    }

    public String getMlegendId() {
        return mlegendId;
    }

    public void setMlegendId(String mlegendId) {
        this.mlegendId = mlegendId;
    }

    public String getmMajorId() {
        return mMajorId;
    }

    public void setmMajorId(String mMajorId) {
        this.mMajorId = mMajorId;
    }

    public String getmRating() {
        return mRating;
    }

    public void setmRating(String mRating) {
        this.mRating = mRating;
    }

    public String getmRunTime() {
        return mRunTime;
    }

    public void setmRunTime(String mRunTime) {
        this.mRunTime = mRunTime;
    }

    public ArrayList<String> getmGenre() {
        return mGenre;
    }

    public void setmGenre(ArrayList<String> mGenre) {
        this.mGenre = mGenre;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getmLegendShows() {
        return mLegendShows;
    }

    public void setmLegendShows(String mLegendShows) {
        this.mLegendShows = mLegendShows;
    }

    public String getmMajorShows() {
        return mMajorShows;
    }

    public void setmMajorShows(String mMajorShows) {
        this.mMajorShows = mMajorShows;
    }

    public String getmLegendShowsHall() {
        return mLegendShowsHall;
    }

    public void setmLegendShowsHall(String mLegendShowsHall) {
        this.mLegendShowsHall = mLegendShowsHall;
    }

    public String getmMajorShowsHall() {
        return mMajorShowsHall;
    }

    public void setmMajorShowsHall(String mMajorShowsHall) {
        this.mMajorShowsHall = mMajorShowsHall;
    }

    public int[] getmLegendDate() {
        return mLegendDate;
    }

    public void setmLegendDate(int[] mLegendDate) {
        this.mLegendDate = mLegendDate;
    }

    public int[] getmMajorDate() {
        return mMajorDate;
    }

    public void setmMajorDate(int[] mMajorDate) {
        this.mMajorDate = mMajorDate;
    }

    public Boolean getAired() {
        return isAired;
    }

    public void setAired(Boolean aired) {
        isAired = aired;
    }

    public Boolean getShowing() {
        return isShowing;
    }

    public void setShowing(Boolean showing) {
        isShowing = showing;
    }

    public Boolean getComming() {
        return isComming;
    }

    public void setComming(Boolean comming) {
        isComming = comming;
    }

    public String getmTrailerLink() {
        return mTrailerLink;
    }

    public void setmTrailerLink(String mTrailerLink) {
        this.mTrailerLink = mTrailerLink;
    }

    public String getmImgLink() {
        return mImgLink;
    }

    public void setmImgLink(String mImgLink) {
        this.mImgLink = mImgLink;
    }

    public String getmDetailUrl() {
        return mDetailUrl;
    }

    public void setmDetailUrl(String mDetailUrl) {
        this.mDetailUrl = mDetailUrl;
    }
}
