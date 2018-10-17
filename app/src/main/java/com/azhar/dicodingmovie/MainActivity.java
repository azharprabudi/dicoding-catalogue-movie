package com.azhar.dicodingmovie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.azhar.dicodingmovie.MovieService.MovieModel;
import com.azhar.dicodingmovie.MovieService.MovieService;



public class MainActivity extends AppCompatActivity {

    private EditText inputSearch;
    private Button buttonSearch;
    private RequestQueue mRequestQueue;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText inputSearch = (EditText) findViewById(R.id.input_search);
        Button buttonSearch = (Button) findViewById(R.id.btn_search);
        final ListView listView  = (ListView) findViewById(R.id.list_movie);

        mRequestQueue = Volley.newRequestQueue(this);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* get input field value */
                final String search = inputSearch.getText().toString();
                if (!search.isEmpty()) {
                    MovieService mMovieService = new MovieService(MainActivity.this, listView);
                    mMovieService.execute(search);
                } else {
                    inputSearch.setError("Search Can't Be Empty");
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), MovieDetail.class);
                intent.putExtra(MovieDetail.DATA_DETAILS, (MovieModel)parent.getItemAtPosition(position));
                startActivity(intent);
            }
        });
    }

}
