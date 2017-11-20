package np.cnblabs.accountdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import np.cnblabs.accountdemo.adapter.CustomRecyclerAdapter;
import np.cnblabs.accountdemo.model.Movie;

/**
 * Created by sanjogstha on 11/20/17.
 * Innovisto LLC
 * sanjogshrestha.nepal@gmail.com
 */

public class RecyclerActivity extends AppCompatActivity{
    RecyclerView recyclerView;
    CustomRecyclerAdapter mAdapter;
    LinearLayout emptyLayout;
    List<Movie> movieList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);

        recyclerView = findViewById(R.id.recyclerView);
        emptyLayout = findViewById(R.id.emptyLayout);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        mAdapter = new CustomRecyclerAdapter(movieList);
        recyclerView.setAdapter(mAdapter);

        getMovieList();

        mAdapter.setOnClick(new CustomRecyclerAdapter.onClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(RecyclerActivity.this, movieList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getMovieList() {
        Movie movie = new Movie("Mad Max: Fury Road", "Action & Adventure", "2015");
        movieList.add(movie);

        movie = new Movie("Fury", "Action & Adventure", "2015");
        movieList.add(movie);
    }

    public void add(View view) {
        Movie movie = new Movie("Titanic", "Romantic", "1992");
        movieList.add(movie);
        if(movieList.size() > 0) {
            emptyLayout.setVisibility(View.GONE);
        }
        mAdapter.notifyItemInserted(movieList.size());
    }

    public void remove(View view) {
        movieList.remove(movieList.size()-1);
        mAdapter.notifyItemRemoved(movieList.size());

        if(movieList.isEmpty())
            emptyLayout.setVisibility(View.VISIBLE);
    }
}
