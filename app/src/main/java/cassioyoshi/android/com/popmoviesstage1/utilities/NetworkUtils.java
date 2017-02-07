package cassioyoshi.android.com.popmoviesstage1.utilities;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by cassioimamura on 2/4/17.
 */

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    private static final String MOVIES_BASE_URL = "http://api.themoviedb.org/3/movie/popular";
    private static final String IMAGES_BASE_URL = "http://image.tmdb.org/t/p/w500/";
    private static final String API_KEY = "";

    /* The units we want our API to return */
    private static final String language = "en-US";





    public static URL buildUrl(String categoryChoose) {

        URL url = null;
        try{
            url = new URL(MOVIES_BASE_URL + "?api_key=" + API_KEY + "&language=" + language);
        }catch (MalformedURLException e) {
            Log.e(TAG, "Problem building the URL ", e);
        }

        Log.e(TAG, "this response is:" + url);


        return url;
    }


    public static URL buildImageUrl(String posterImage) {

        URL url = null;
        try {
            url = new URL(IMAGES_BASE_URL + posterImage);
        } catch (MalformedURLException e) {
            Log.e(TAG, "Problem building the URL ", e);
        }

        return url;
    }



    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}

