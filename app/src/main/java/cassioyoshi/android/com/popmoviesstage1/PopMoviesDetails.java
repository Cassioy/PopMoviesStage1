package cassioyoshi.android.com.popmoviesstage1;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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

            Drawable red = getResources().getDrawable(R.drawable.circle_3);
            Drawable yellow = getResources().getDrawable(R.drawable.circle_2);
            Drawable green = getResources().getDrawable(R.drawable.circle);


            Button rating = (Button) findViewById( R.id.rating_button );
            double votedAvg = Double.parseDouble(votes);
            if (votedAvg < 4){
                rating.setBackground(red);
                rating.setText(votes);
            }else if(votedAvg < 7){
                rating.setBackground(yellow);
                rating.setText(votes);
            }else if(votedAvg >= 7){
                rating.setBackground(green);
                rating.setText(votes);
            }


            ImageView thumb = (ImageView) findViewById( R.id.movie_info_thumbnail );
            Picasso.with(this).load(thumbnailUrl).into(thumb);

            final ImageButton fab = (ImageButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           fab.setImageResource( fab.isSelected() ? R.drawable.ic_grade_white_48px : R.drawable.ic_grade_yellow_48px );
                                           fab.setSelected( !fab.isSelected() );

                                       }

                                   });



            loadBackdrop(imageUrl);
        }

        private void loadBackdrop(String url) {
            final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
            Picasso.with(this).load(url).into(imageView);

        }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            // API 5+ solution
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();

        PopMoviesDetails.this.overridePendingTransition(R.anim.trans_right_in,
                R.anim.trans_right_out);
    }

}



