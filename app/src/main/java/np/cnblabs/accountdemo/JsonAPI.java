package np.cnblabs.accountdemo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import np.cnblabs.accountdemo.model.Post;
import np.cnblabs.accountdemo.model.TreeHouseModel;

/**
 * Created by sanjogstha on 11/21/17.
 * Innovisto LLC
 * sanjogshrestha.nepal@gmail.com
 */

public class JsonAPI extends AppCompatActivity{
    String url = "http://blog.teamtreehouse.com/api/get_recent_summary/";
    ListView listView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        listView = findViewById(R.id.listView);

        new FetchAPI().execute(url);
    }

    public class FetchAPI extends AsyncTask<String, Void, String>{
        ProgressDialog progressDialog = new ProgressDialog(JsonAPI.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setTitle("Fetching");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                URL mUrl = new URL(strings[0]);
                HttpURLConnection httpConnection = (HttpURLConnection) mUrl.openConnection();
                httpConnection.setRequestMethod("GET");
                httpConnection.connect();

                int responseCode = httpConnection.getResponseCode();

                if(responseCode == HttpURLConnection.HTTP_OK){
                    BufferedReader br = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                    br.close();
                    return sb.toString();
                }else
                    return null;

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String string) {
            progressDialog.dismiss();
            Gson gson = new Gson();
            TreeHouseModel treeHouseModel = gson.fromJson(string, TreeHouseModel.class);
            List<String> modelList = new ArrayList<>();
            for (Post post : treeHouseModel.getPosts()) {
                modelList.add(post.getAuthor());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(JsonAPI.this, android.R.layout.simple_list_item_1, modelList);
            listView.setAdapter(adapter);
        }
    }

}
