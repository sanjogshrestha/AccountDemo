package np.cnblabs.accountdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import np.cnblabs.accountdemo.R;
import np.cnblabs.accountdemo.RecyclerActivity;
import np.cnblabs.accountdemo.adapter.CustomRecyclerAdapter;
import np.cnblabs.accountdemo.model.Movie;

/**
 * Created by sanjogstha on 12/12/17.
 * Innovisto LLC
 * sanjogshrestha.nepal@gmail.com
 */

public class HomeFragment extends Fragment{

    RecyclerView recyclerView;
    CustomRecyclerAdapter mAdapter;
    List<Movie> movieList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;

    public static HomeFragment newInstance(){
        return new HomeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));

        mAdapter = new CustomRecyclerAdapter(movieList);
        recyclerView.setAdapter(mAdapter);

        movieList = RecyclerActivity.getMovieList(movieList);

        mAdapter.setOnClick(new CustomRecyclerAdapter.onClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getActivity(), movieList.get(position).getTitle(), Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}
