package np.cnblabs.accountdemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import np.cnblabs.accountdemo.R;
import np.cnblabs.accountdemo.realm.RealmModelData;

/**
 * Created by sanjogstha on 11/26/17.
 * Innovisto LLC
 * sanjogshrestha.nepal@gmail.com
 */

public class CustomRealmAdapter extends ArrayAdapter{
    private List<RealmModelData> realmModelDataList;
    private Context context;

    public CustomRealmAdapter(List<RealmModelData> realmModelDataList,
                              Context context) {
        super(context, R.layout.custom_realm_view);
        this.realmModelDataList = realmModelDataList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return realmModelDataList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater =
                (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(layoutInflater != null) {
            RealmModelData realmModelData = realmModelDataList.get(position);
            convertView = layoutInflater.inflate(R.layout.custom_realm_view, parent,
                    false);
            CustomRealmAdapter.ViewHolder holder = new CustomRealmAdapter.ViewHolder();
            holder.name = convertView.findViewById(R.id.nameTV);
            holder.email = convertView.findViewById(R.id.emailTV);
            holder.name.setText(realmModelData.getName());
            holder.email.setText(realmModelData.getEmail());
        }
        return convertView;
    }

    public class ViewHolder{
        TextView email, name;
    }
}
