package cassioyoshi.android.com.popmoviesstage1;

import java.net.URL;

/**
 * Created by cassioimamura on 2/4/17.
 */

public class PopMovies {
    int mMovieThumbnail;
    URL imageSource;
    String mPoster_path;

    String mTitle;
    String mReleaseDate;
    String mVoteAvg;
    String mPlotSynopsis;

  //  public PopMovies(int movieThumbnail){this.mMovieThumbnail = movieThumbnail;}

    public PopMovies(String poster_path){
        this.mPoster_path = poster_path;
    }

    public PopMovies(URL url) { this.imageSource = url; }




    /** public PopMovies(int movieThumbnail, String title, String releaseDate, String voteAvg, String plotSynopsis) {
         mMovieThumbnail = movieThumbnail;
         mTitle = title;
         mReleaseDate = releaseDate;
         mVoteAvg = voteAvg;
         mPlotSynopsis = plotSynopsis;

     } **/

}
