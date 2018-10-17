package com.azhar.dicodingmovie.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.azhar.dicodingmovie.MovieService.MovieModel;
import com.azhar.dicodingmovie.R;
import com.bumptech.glide.Glide;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

public class MovieArrayAdapter extends ArrayAdapter {
    private List<MovieModel> movieList;
    private int resource;
    private Context context;

    public MovieArrayAdapter(@NonNull Context context, int resource, List<MovieModel> movieList) {
        super(context, resource, movieList);
        this.context = context;
        this.resource = resource;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /* get val */
        MovieModel movieModel = movieList.get(position);

        /* set curr view*/
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);

        /* component detail */
        ImageView bannerImage = view.findViewById(R.id.poster);
        Glide.with(context).load("http://image.tmdb.org/t/p/w500/" + movieModel.getPosterPath()).into(bannerImage);

        TextView title = view.findViewById(R.id.title);
        title.setText(movieModel.getTitle());

        TextView overview = view.findViewById(R.id.overview);
        overview.setText(movieModel.getOverview().length() >= 150? movieModel.getOverview().substring(0, 150) + "..." : movieModel.getOverview() + "...");

        TextView releaseDate = view.findViewById(R.id.releasedate);
        releaseDate.setText(movieModel.getReleaseDate());

        return view;
    }

    @NonNull
    @Override
    public Object getItem(int position) {
        return movieList.get(position);
    }
}
