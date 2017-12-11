package np.cnblabs.accountdemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by sanjogstha on 11/19/17.
 * Innovisto LLC
 * sanjogshrestha.nepal@gmail.com
 */

public class SpinnerActivity extends BaseActivity {
    String[] foods = {"apple", "papaya", "mango" , "banana", "coconut", "grapes"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner);

        /*Start of Spinner*/
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.custom_view, foods);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(SpinnerActivity.this,
                        adapterView.getItemAtPosition(i).toString(),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });
        /*End Of Spinner*/
    }
}
