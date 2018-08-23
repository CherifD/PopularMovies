package com.cherifcodes.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable{

    private String mOriginalTitle;
    private String mPosterString;
    private String mReleaseDate;
    private String mOverview;
    private double mVoteAverage;

    public Movie(String originalTitle, String posterString, String releaseDate,
                 String mOverview, double voteAverage) {
        this.mOriginalTitle = originalTitle;
        this.mPosterString = posterString;
        this.mReleaseDate = releaseDate;
        this.mOverview = mOverview;
        this.mVoteAverage = voteAverage;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public String getPosterString() {
        return mPosterString;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getOverview() {
        return mOverview;
    }

    public double getVoteAverage() {
        return mVoteAverage;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "\nmOriginalTitle='" + mOriginalTitle + '\'' +
                ", \nmPosterString='" + mPosterString + '\'' +
                ", \nmReleaseDate='" + mReleaseDate + '\'' +
                ", \nmOverview='" + mOverview + '\'' +
                ", \nmVoteAverage=" + mVoteAverage +
                '}';
    }

    public Movie(Parcel in) {
        this.mOriginalTitle = in.readString();
        this.mOverview = in.readString();
        this.mPosterString = in.readString();
        this.mReleaseDate = in.readString();
        this.mVoteAverage = in.readDouble();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mOriginalTitle);
        parcel.writeString(this.mOverview);
        parcel.writeString(this.mPosterString);
        parcel.writeString(this.mReleaseDate);
        parcel.writeDouble(this.mVoteAverage);
    }
}
