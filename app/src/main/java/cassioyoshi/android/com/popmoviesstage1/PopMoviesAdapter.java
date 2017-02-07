package cassioyoshi.android.com.popmoviesstage1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.List;

/**
 * Created by cassioimamura on 2/4/17.
 */

public class PopMoviesAdapter extends ArrayAdapter<PopMovies>{

    private static final String LOG_TAG = PopMoviesAdapter.class.getSimpleName();
    private static URL imageUrl;



    /** This is a custom constructor **/

    public PopMoviesAdapter(Context context, List<PopMovies> popMoviesList){

        super(context, 0, popMoviesList);


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
    public View getView(int position, View convertView, ViewGroup parent){
        PopMovies popMovies = getItem(position);
        String imageUrl = popMovies.imageSource.toString();

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.pop_movies_item_grid, parent, false);
        }

        ImageView iconView = (ImageView) convertView.findViewById(R.id.thumbnail_image);
        Picasso.with(getContext()).load(imageUrl).into(iconView);

        return convertView;

    }


}

