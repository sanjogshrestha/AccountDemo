package np.cnblabs.accountdemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import np.cnblabs.accountdemo.R;
import np.cnblabs.accountdemo.model.Movie;

/**
 * Created by sanjogstha on 11/20/17.
 * Innovisto LLC
 * sanjogshrestha.nepal@gmail.com
 */

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder> {
    private List<Movie> movieList;
    onClickListener listener;

    public CustomRecyclerAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Movie movie = movieList.get(position);
        holder.title.setText(movie.getTitle());
        holder.category.setText(movie.getCategory());
        holder.year.setText(movie.getYear());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, category, year;
        ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titleTV);
            category = itemView.findViewById(R.id.categoryTV);
            year = itemView.findViewById(R.id.yearTV);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClick(view, getAdapterPosition());
                }
            });
        }
    }

    public void setOnClick(onClickListener listener){
        this.listener = listener;
    }

    public interface onClickListener{
        void onClick(View view, int position);
    }
}
