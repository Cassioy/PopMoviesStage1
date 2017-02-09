package cassioyoshi.android.com.popmoviesstage1;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.URL;


/**
 * Created by cassioimamura on 2/4/17.
 */

public class PopMoviesDetails extends AppCompatActivity {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.details_pop_movies);

            URL u = (URL) getIntent().getSerializableExtra("backdropImage");
            URL t = (URL) getIntent().getSerializableExtra("posterImage");
            String imageUrl = u.toString();
            String thumbnailUrl = t.toString();
            String title = (String) getIntent().getSerializableExtra("title");
            String synopsis = (String) getIntent().getSerializableExtra("plotSynopsis");
            String released = (String) getIntent().getSerializableExtra( "releaseDate" );
            String votes = (String) getIntent().getSerializableExtra( "voteAvg" );

            final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            CollapsingToolbarLayout collapsingToolbar =
                    (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
            collapsingToolbar.setTitle(title);

            TextView overview = (TextView) findViewById( R.id.plot_synopsis);
            overview.setText(synopsis);

            TextView releaseDate = (TextView) findViewById( R.id.release_date);
            releaseDate.setText(released);


            ImageView thumb = (ImageView) findViewById( R.id.movie_info_thumbnail );
            Picasso.with(this).load(thumbnailUrl).into(thumb);


            loadBackdrop(imageUrl);
        }

        private void loadBackdrop(String url) {
            final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
            Picasso.with(this).load(url).into(imageView);

        }

}



