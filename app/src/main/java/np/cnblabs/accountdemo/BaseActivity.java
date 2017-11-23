package np.cnblabs.accountdemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import np.cnblabs.accountdemo.webservice.ApiClient;
import np.cnblabs.accountdemo.webservice.WebService;

/**
 * Created by sanjogstha on 11/23/17.
 * Innovisto LLC
 * sanjogshrestha.nepal@gmail.com
 */

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    public WebService getWebService() {
        return ApiClient.getApiClient().create(WebService.class);
    }

    public void startActivity(Context context, Class to){
        startActivity(new Intent(context, to));
    }
}
