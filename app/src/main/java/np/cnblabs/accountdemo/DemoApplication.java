package np.cnblabs.accountdemo;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by sanjogstha on 11/26/17.
 * Innovisto LLC
 * sanjogshrestha.nepal@gmail.com
 */

public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Realm
        Realm.init(this);

        // The RealmConfiguration is created using the builder pattern.
        // The Realm file will be located in Context.getFilesDir() with name "myrealm.realm"
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("demoRealm")
                .schemaVersion(1)
                .build();

        // Use the config
        Realm.getInstance(config);
    }

    public static Realm getDefaultRealm(){
        return Realm.getDefaultInstance();
    }
}
