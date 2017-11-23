package np.cnblabs.accountdemo;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import np.cnblabs.accountdemo.model.ImdbModel;
import np.cnblabs.accountdemo.model.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sanjogstha on 11/23/17.
 * Innovisto LLC
 * sanjogshrestha.nepal@gmail.com
 */

public class RetrofitActivity extends BaseActivity {
    ListView listView;

    ArrayAdapter<String> arrayAdapter;
    List<String> modelList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        listView = findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, modelList);
    }

    public void fetchAPI(View view) {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();

       getWebService().getMovieList().enqueue(new Callback<ImdbModel>() {
           @Override
           public void onResponse(@NonNull Call<ImdbModel> call, @NonNull Response<ImdbModel> response) {
               progressDialog.dismiss();
               ImdbModel imdbModel = response.body();
               if(imdbModel == null) return;
               for (Result result : imdbModel.getResults()) {
                   modelList.add(result.getTitle());
               }
               listView.setAdapter(arrayAdapter);
           }

           @Override
           public void onFailure(@NonNull Call<ImdbModel> call, @NonNull Throwable t) {
               progressDialog.dismiss();
           }
       });
    }
}
