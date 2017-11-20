package np.cnblabs.accountdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import np.cnblabs.accountdemo.R;

/**
 * Created by sanjogstha on 11/19/17.
 * Innovisto LLC
 * sanjogshrestha.nepal@gmail.com
 */

public class CustomAdapter extends ArrayAdapter {

    private Context context;
    private String[] data;
    private int[] drawable;

    public CustomAdapter(@NonNull Context context, @NonNull String[] data, int[] drawable) {
        super(context, R.layout.custom_adapter_view);
        this.context = context;
        this.data = data;
        this.drawable = drawable;
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(layoutInflater != null) {
            convertView = layoutInflater.inflate(R.layout.custom_adapter_view, parent, false);
            ViewHolder holder = new ViewHolder();
            holder.title = convertView.findViewById(R.id.title);
            holder.imageView = convertView.findViewById(R.id.imageView);
            holder.title.setText(data[position]);
            holder.imageView.setImageDrawable(ContextCompat.getDrawable(context, drawable[position]));
        }
        return convertView;
    }


    @Override
    public int getCount() {
        return data.length;
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return data[position];
    }

    private class ViewHolder{
        TextView title;
        ImageView imageView;
    }
}
