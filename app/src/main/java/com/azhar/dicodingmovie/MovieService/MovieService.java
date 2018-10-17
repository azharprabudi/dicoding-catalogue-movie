package com.azhar.dicodingmovie.MovieService;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.azhar.dicodingmovie.Adapter.MovieArrayAdapter;
import com.azhar.dicodingmovie.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MovieService extends AsyncTask<String, Void, String> {

    final Activity sourceClass;
    final ListView listView;
    final String MovieAPIURL = "https://api.themoviedb.org/3/search/movie?";
    final String QUERY_API_KEY = "api_key";
    final String API_KEY = "c609b9a96ce2ff4c4834c4ac4cdb800a";
    final String QUERY_SEARCH = "query";

    public MovieService(Activity sourceClass, ListView listView) {
        this.sourceClass = sourceClass;
        this.listView = listView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        String results = null;
        try {
            Uri uriBuilder = Uri.parse(MovieAPIURL)
                    .buildUpon()
                    .appendQueryParameter(QUERY_API_KEY, API_KEY)
                    .appendQueryParameter(QUERY_SEARCH, strings[0])
                    .build();
            URL url = new URL(uriBuilder.toString());
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            /* getting input stream*/
            InputStream resultsStream = httpURLConnection.getInputStream();

            /* parse input stream to buffered */
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resultsStream));
            results = bufferedReader.readLine();
            bufferedReader.close();
        } catch(IOException e) {
            e.printStackTrace();
            Toast.makeText(sourceClass, e.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            return results;
        }
    }

    @Override
    protected void onPostExecute(String resultsStr) {
        super.onPostExecute(resultsStr);
        /* resultsStr when data is not empty, then render list movie */
        if (!resultsStr.isEmpty()) {
            try {
                JSONObject resultsJson = new JSONObject(resultsStr);
                JSONArray resultsArray= resultsJson.getJSONArray("results");
                ArrayList<MovieModel> movieList = new ArrayList<MovieModel>();

                /* append data to new movie list */
                for (int i = 0; i < resultsArray.length(); i++) {
                    /* get json movie depend on index */
                    JSONObject movieJson = resultsArray.getJSONObject(i);
                    /* parse to movie model */
                    MovieModel movie = new MovieModel(movieJson.getString("title"), movieJson.getString("overview"), movieJson.getString("poster_path"), movieJson.getString("release_date"));
                    /* append to new movie list */
                    movieList.add(movie);
                }

                MovieArrayAdapter movieArrayAdapter = new MovieArrayAdapter(sourceClass, R.layout.movie_item, movieList);
                listView.setAdapter(movieArrayAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(sourceClass, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
