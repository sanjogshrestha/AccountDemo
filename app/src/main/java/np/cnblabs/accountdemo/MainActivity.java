package np.cnblabs.accountdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by sanjogstha on 11/19/17.
 * Innovisto LLC
 * sanjogshrestha.nepal@gmail.com
 */

public class MainActivity extends BaseActivity {
    String[] foods = {"apple", "papaya", "mango" , "banana", "coconut", "grapes"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView titleTV = findViewById(R.id.titleTV);
        Spinner spinner = findViewById(R.id.spinner);

        Intent intent = getIntent();
        String email = intent.getStringExtra("EMAIL");
        titleTV.setText(email);

        /*Start of Spinner*/
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.custom_view, foods);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
        /*End Of Spinner*/
    }

    public void showList(View view) {
        startActivity(this, ListViewArrayAdapter.class);
    }

    public void showRecyclerView(View view) {
        startActivity(this, RecyclerActivity.class);
    }

    public void downloadImage(View view) {
        startActivity(new Intent(this, DownloadImageActivity.class));
    }

    public void apiCall(View view) {
        startActivity(new Intent(this, JsonAPI.class));
    }

    public void showImage(View view) {
        startActivity(new Intent(this, PicassoImage.class));
    }

    public void callRetrofit(View view) {
        startActivity(new Intent(this, RetrofitActivity.class));
    }

    public void callRealm(View view) {
        startActivity(this, RealmActivity.class);
    }
}
