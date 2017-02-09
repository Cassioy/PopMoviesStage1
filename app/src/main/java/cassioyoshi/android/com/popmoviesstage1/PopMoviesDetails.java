package cassioyoshi.android.com.popmoviesstage1;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.net.URL;


/**
 * Created by cassioimamura on 2/4/17.
 */

public class PopMoviesDetails extends AppCompatActivity {

        public static final String EXTRA_NAME = "cheese_name";

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.details_pop_movies);

            URL u = (URL) getIntent().getSerializableExtra("backdropImage");
            String imageUrl = u.toString();
            String title = (String) getIntent().getSerializableExtra("title");

            final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            CollapsingToolbarLayout collapsingToolbar =
                    (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
            collapsingToolbar.setTitle(title);

            loadBackdrop(imageUrl);
        }

        private void loadBackdrop(String url) {
            final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
            Picasso.with(this).load(url).into(imageView);

        }

}



