package cassioyoshi.android.com.popmoviesstage1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.net.URL;
import java.util.List;

/**
 * Created by cassioimamura on 2/4/17.
 */

public class PopMoviesAdapter extends ArrayAdapter<PopMovies> implements Serializable{

    private static final String LOG_TAG = PopMoviesAdapter.class.getSimpleName();
    private static URL imageUrl;
    private static Context con;



    /** This is a custom constructor **/

    public PopMoviesAdapter(Context context, List<PopMovies> popMoviesList){

        super(context, 0, popMoviesList);
        con = context;


    }

    /**
     * Provides a view for an AdapterView (GridView)
     *
     * @param position    The AdapterView position that is requesting a view
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        final PopMovies popMovies = getItem(position);
        String imageUrl = popMovies.posterSource.toString();
        final String backDropUrl = popMovies.backdropSource.toString();
        final String title = popMovies.mTitle;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.pop_movies_item_grid, parent, false);
        }


        ImageView iconView = (ImageView) convertView.findViewById(R.id.thumbnail_image);
        Picasso.with(getContext()).load(imageUrl).into(iconView);
        iconView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailsIntent = new Intent(con,PopMoviesDetails.class);
                detailsIntent.putExtra("backdropImage", popMovies.backdropSource);
                detailsIntent.putExtra( "posterImage", popMovies.posterSource );
                detailsIntent.putExtra( "title", popMovies.mTitle );
                detailsIntent.putExtra( "plotSynopsis", popMovies.mPlotSynopsis);
                detailsIntent.putExtra( "releaseDate", popMovies.mReleaseDate );
                detailsIntent.putExtra( "voteAvg", popMovies.mVoteAvg );
                con.startActivity(detailsIntent);
            }
        } );

        return convertView;

    }






}

