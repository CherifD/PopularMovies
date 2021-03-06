package com.cherifcodes.popularmovies.Utils;

import android.net.Uri;
import android.util.Log;

import com.cherifcodes.popularmovies.BuildConfig;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    //Define URL component strings
    public static final String BASE_URL_POPULAR = "https://api.themoviedb.org/3/movie/popular";
    public static final String BASE_URL_TOP_RATED = "https://api.themoviedb.org/3/movie/top_rated";
    private static final String MY_API_KEY = BuildConfig.API_KEY;
    private static final String KEY_FOR_MY_API_KEY = "api_key";
    private static final String LANGUAGE = "en-US";
    private static final String KEY_FOR_LANGUAGE = "language";

    //Define the based url for the movie poster to be fetched through Picasso
    public static final String POSTER_BASE_URL = "http://image.tmdb.org/t/p/w200";

    //Define HttpConnection settings
    private static final int READ_TIMEOUT = 10000;
    private static final int CONNECTION_TIMEOUT = 15000;
    private static final String HTTP_REQUEST_METHOD = "GET";

    /**
     * Builds a url needed to communicate with the theMovieDb database
     * @param baseUrlString the desired movie endpoint base url
     * @return the built URL
     */
    public static URL buildUrl(String baseUrlString) {

        //build the Uri from the strings
        Uri uri = Uri.parse(baseUrlString).buildUpon()
                .appendQueryParameter(KEY_FOR_MY_API_KEY, MY_API_KEY)
                .appendQueryParameter(KEY_FOR_LANGUAGE, LANGUAGE)
                .build();

        //build the URL from the Uri
        URL dbUrl = null;
        try {
            dbUrl = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return dbUrl;
    }


    /**
     * Fetches a JSON response using the specified URL
     * @param url the specified URL
     * @return the JSON response as a String
     */
    public static String getJsonResponse(URL url) {

        String jsonResponseString = null;
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        Scanner scanner = null;
        try {
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setReadTimeout(READ_TIMEOUT);
            urlConnection.setConnectTimeout(CONNECTION_TIMEOUT);
            urlConnection.setRequestMethod(HTTP_REQUEST_METHOD);
            urlConnection.connect();

            inputStream = urlConnection.getInputStream();
            scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");

            if (scanner.hasNext()) {
                jsonResponseString = scanner.next();
            }else
                Log.e(TAG, "Empty stream returned from Url: " + url);

        } catch (IOException e) {
            Log.e(TAG, "Problem retrieving JSON response from " + url + "\n", e);
        } finally {
            if (scanner != null) scanner.close();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
           if (urlConnection != null) urlConnection.disconnect();
        }

        return jsonResponseString;
    }
}
