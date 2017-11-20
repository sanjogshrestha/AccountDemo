package np.cnblabs.accountdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import np.cnblabs.accountdemo.adapter.CustomAdapter;

/**
 * Created by sanjogstha on 11/19/17.
 * Innovisto LLC
 * sanjogshrestha.nepal@gmail.com
 */

public class ListViewArrayAdapter extends AppCompatActivity {
    ListView listview;
    String[] foods = {"apple", "papaya", "mango" , "banana", "coconut", "grapes"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        listview = findViewById(R.id.listView);
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, MainActivity.foods);
        int[] drawable = {R.drawable.baseball, R.drawable.cricket, R.drawable.hockey, R.drawable.karate, R.drawable.soccer, R.drawable.tt};
        final CustomAdapter adapter = new CustomAdapter(this, foods, drawable);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListViewArrayAdapter.this,adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
