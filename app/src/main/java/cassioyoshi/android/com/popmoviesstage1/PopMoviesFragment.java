package cassioyoshi.android.com.popmoviesstage1;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
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

    private String location;
    private PopMoviesAdapter adapter;
    private PopMoviesAdapter popMoviesAdapter;
    private static final String LOG_TAG = PopMoviesFragment.class.getSimpleName();
    private List<PopMovies> moviesArrayList;
    private GridView moviesGrid;


    public PopMoviesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        new FetchMoviesTask().execute();

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        moviesGrid = (GridView) rootView.findViewById(R.id.movies_grid);

        moviesGrid.setAdapter(popMoviesAdapter);


        return rootView;
    }

    public class FetchMoviesTask extends AsyncTask<Void, Void, List<PopMovies>> {

        @Override
        protected void onPreExecute() {
            Toast.makeText( getActivity(),"Welcome To Popular Video App!", Toast.LENGTH_LONG ).show();

        }

        @Override
        protected ArrayList<PopMovies> doInBackground(Void... params) {

            URL movieRequestUrl = NetworkUtils.buildUrl(location);

            Log.e(LOG_TAG, "this url is: " + movieRequestUrl);

            try {
                String jsonMovieResponse = NetworkUtils
                        .getResponseFromHttpUrl(movieRequestUrl);

                ArrayList<PopMovies> simpleJsonMovieData = MovieJsonUtils
                        .getSimpleMovieStringsFromJson(PopMoviesFragment.this, jsonMovieResponse);

                updateData(simpleJsonMovieData);

                adapter = new PopMoviesAdapter(getContext(), moviesArrayList);

                return simpleJsonMovieData;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<PopMovies> simpleJsonMovieData) {

                if (simpleJsonMovieData != null && !simpleJsonMovieData.isEmpty()) {

                    moviesGrid.invalidateViews();
                    moviesGrid.setAdapter(adapter);


                }

        }

    }

    public void updateData(List<PopMovies> movieList){
        moviesArrayList = movieList;
    }

}



