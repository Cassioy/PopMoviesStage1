package cassioyoshi.android.com.popmoviesstage1;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


public class PopMoviesMainActivity extends AppCompatActivity {

    private static final String SORT_MOVIE_POPULAR = "popular";
    private static final String SORT_MOVIE_TOP_RATED = "top_rated";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_movies_main);

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.popular);
        menuItem.setIntent(createSharePopIntent());

        MenuItem menuItem2 = menu.findItem(R.id.topRated);
        menuItem2.setIntent(createShareTopIntent());

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.popular:
                addNewFrag( "popular" );

                return true;

            case R.id.topRated:
                addNewFrag( "top_rated" );

                return true;

        }


        return super.onOptionsItemSelected(item);
    }

    private Intent createSharePopIntent() {
        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText(SORT_MOVIE_POPULAR)
                .getIntent();
        return shareIntent;
    }

    private Intent createShareTopIntent() {
        Intent share1Intent = ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText(SORT_MOVIE_TOP_RATED)
                .getIntent();
        return share1Intent;
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
        PopMoviesMainActivity.this.overridePendingTransition(R.anim.trans_right_in,
                R.anim.trans_right_out);
    }


    public void addNewFrag(String value){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        PopMoviesFragment fragment = new PopMoviesFragment();
        fragment.temp = value;
        fragmentTransaction.replace(R.id.frag_parent, fragment);
        fragmentTransaction.commit();

    }

}

