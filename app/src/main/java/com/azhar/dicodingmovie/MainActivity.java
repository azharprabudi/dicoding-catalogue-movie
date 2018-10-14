package com.azhar.dicodingmovie;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    final String API_KEY = "eb297cd5ea1f54df9c812341b5ce75e5";

    private EditText inputSearch;
    private Button buttonSearch;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText inputSearch = (EditText) findViewById(R.id.input_search);
        inputSearch.setText("HELO");
        Button buttonSearch = (Button) findViewById(R.id.btn_search);
        mRequestQueue = Volley.newRequestQueue(this);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String search = inputSearch.getText().toString();
                getCatalogueMovies(search);
            }
        });

    }

    private void getCatalogueMovies(final String search) {
        final String apiUrl = "https://api.themoviedb.org/3/search/movie?";

        /* create url link */
        Uri builtUri = Uri.parse(apiUrl).buildUpon().appendQueryParameter("api_key", API_KEY).appendQueryParameter("query", search).build();
        URL URL_API = new URL(builtUri.toString());

//        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_API, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                mRequestQueue.stop();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//                mRequestQueue.stop();
//            }
//        });
//        mRequestQueue.add(stringRequest);
    }

}
