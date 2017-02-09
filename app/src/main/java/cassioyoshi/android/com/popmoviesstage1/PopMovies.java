package cassioyoshi.android.com.popmoviesstage1;

import android.os.Parcelable;
import android.os.Parcel;

import java.net.URL;

/**
 * Created by cassioimamura on 2/4/17.
 */

public class PopMovies implements Parcelable{

    URL posterSource;
    URL backdropSource;
    String mPoster_path;

    String mTitle;
    String mReleaseDate;
    String mVoteAvg;
    String mPlotSynopsis;

  //  public PopMovies(int movieThumbnail){this.mMovieThumbnail = movieThumbnail;}

  //  public PopMovies(String poster_path){this.mPoster_path = poster_path;}

    public PopMovies(URL posterImage, URL backdropImage, String title, String overview, String voteAvg, String releaseDate) {
        this.posterSource = posterImage;
        this.backdropSource = backdropImage;
        this.mTitle = title;
        this.mPlotSynopsis = overview;
        this.mVoteAvg = voteAvg;
        this.mReleaseDate = releaseDate;

    }

    private PopMovies(Parcel in) {
        mTitle = in.readString();
        mPlotSynopsis = in.readString();
        mVoteAvg = in.readString();
        mReleaseDate = in.readString();
        posterSource = in.readParcelable(URL.class.getClassLoader());
        backdropSource = in.readParcelable(URL.class.getClassLoader());
    }



    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(mTitle);
        out.writeString( mPlotSynopsis );
        out.writeString( mVoteAvg );
        out.writeString( mReleaseDate );
        out.writeValue( posterSource );
        out.writeValue( backdropSource );
    }

    public static final Parcelable.Creator<PopMovies> CREATOR
            = new Parcelable.Creator<PopMovies>() {

        public PopMovies createFromParcel(Parcel in) {
            return new PopMovies(in);
        }

        public PopMovies[] newArray(int size) {
            return new PopMovies[size];
        }
    };

    private PopMovies() {
    }


}



    /** public PopMovies(int movieThumbnail, String title, String releaseDate, String voteAvg, String plotSynopsis) {
         mMovieThumbnail = movieThumbnail;
         mTitle = title;
         mReleaseDate = releaseDate;
         mVoteAvg = voteAvg;
         mPlotSynopsis = plotSynopsis;

     } **/

