package com.azhar.dicodingmovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhar.dicodingmovie.MovieService.MovieModel;
import com.bumptech.glide.Glide;

public class MovieDetail extends AppCompatActivity {

    static final String DATA_DETAILS = "DATA_DETAILS";

    private ImageView imageView;
    private TextView title, overview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        imageView = (ImageView)findViewById(R.id.banner_image);
        title = (TextView)findViewById(R.id.title);
        overview = (TextView)findViewById(R.id.overview);

        MovieModel details = (MovieModel) getIntent().getExtras().getSerializable(MovieDetail.DATA_DETAILS);
        if (details != null) {
            Glide.with(this).load("http://image.tmdb.org/t/p/w500/" + details.getPosterPath()).into(imageView);
            title.setText(details.getTitle());
            overview.setText(details.getOverview());
        }

    }
}
