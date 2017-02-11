package cassioyoshi.android.com.popmoviesstage1;

import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cassioyoshi.android.com.popmoviesstage1.utilities.MovieJsonUtils;
import cassioyoshi.android.com.popmoviesstage1.utilities.NetworkUtils;

/**
 * Created by cassioimamura on 2/4/17.
 */

public class PopMoviesFragment extends Fragment {

    private static final String LOG_TAG = PopMoviesFragment.class.getSimpleName();

    private PopMoviesAdapter adapter;
    private List<PopMovies> moviesArrayList;
    private GridView moviesGrid;
    private String category_chooser;

    public static String temp;
    public Context mContext;


    public PopMoviesFragment() {
        setRetainInstance(true);  //Hear put this line

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate( R.layout.fragment_main, container, false );

        if (temp == null) {
            category_chooser = "top_rated";
        }
        if (temp == "top_rated") {
            category_chooser = temp;
        }
        if (temp == "popular") {
            category_chooser = temp;
        }

        mContext = rootView.getContext();
        new FetchMoviesTask().execute( category_chooser );


        moviesGrid = (GridView) rootView.findViewById( R.id.movies_grid );

        moviesGrid.setAdapter( adapter );

        return rootView;
    }

    public class FetchMoviesTask extends AsyncTask<String, Void, List<PopMovies>> {


        @Override
        protected void onPreExecute() {

            isInternetOn();

        }

        @Override
        protected ArrayList<PopMovies> doInBackground(String... params) {

            String choose_category = params[0];
            URL movieRequestUrl = NetworkUtils.buildUrl( choose_category );
                try {
                    String jsonMovieResponse = NetworkUtils
                            .getResponseFromHttpUrl( movieRequestUrl );

                    ArrayList<PopMovies> simpleJsonMovieData = MovieJsonUtils
                            .getSimpleMovieStringsFromJson( PopMoviesFragment.this, jsonMovieResponse );

                    updateData( simpleJsonMovieData );

                    adapter = new PopMoviesAdapter( mContext, moviesArrayList );

                    return simpleJsonMovieData;

                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
        }

        @Override
        protected void onPostExecute(List<PopMovies> simpleJsonMovieData) {

            if (simpleJsonMovieData != null && !simpleJsonMovieData.isEmpty()) {

                hideProgressBar();

                moviesGrid.invalidateViews();
                moviesGrid.setAdapter( adapter );

            } else {
                Toast.makeText( mContext, "Could not Retrieve Information", Toast.LENGTH_SHORT ).show();
            }
        }

    }

    public void updateData(List<PopMovies> movieList) {
        moviesArrayList = movieList;
    }

    public void hideProgressBar() {

        ProgressBar progress = (ProgressBar) getActivity().findViewById( R.id.progressBarFetch );
        progress.setVisibility( View.GONE );

    }


    public void getTemp(String t) {
        temp = t;
    }


    public final boolean isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager)getActivity().getSystemService(getActivity().CONNECTIVITY_SERVICE );

        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {

            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {

            Toast.makeText(getActivity(), "Not Connected, please verify your Internet Connection", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }

}





