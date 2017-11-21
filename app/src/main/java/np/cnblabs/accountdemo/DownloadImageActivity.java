package np.cnblabs.accountdemo;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by sanjogstha on 11/21/17.
 * Innovisto LLC
 * sanjogshrestha.nepal@gmail.com
 */

public class DownloadImageActivity extends AppCompatActivity{
    String url = "https://pbs.twimg.com/profile_images/613291161017450501/r_E4MaVU.jpg";
    ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_image);
        imageView = findViewById(R.id.imageView);
    }

    public void downloadImage(View view) {
        new DownloadAsync().execute(url);
    }

    public class DownloadAsync extends AsyncTask<String, Void, Bitmap>{
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(DownloadImageActivity.this);
            progressDialog.setTitle("Bazzinga");
            progressDialog.setMessage("Downloading");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap = null;
            InputStream inputStream = null;

            try {
                inputStream = new URL(strings[0]).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(inputStream!=null){
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            progressDialog.dismiss();
            if(bitmap == null){
                Toast.makeText(DownloadImageActivity.this, "No image found", Toast.LENGTH_SHORT).show();
                return;
            }
            imageView.setImageBitmap(bitmap);
        }
    }
}
