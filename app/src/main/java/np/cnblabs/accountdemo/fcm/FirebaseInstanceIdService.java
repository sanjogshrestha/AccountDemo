package np.cnblabs.accountdemo.fcm;

/**
 * Created by sanjogstha on 12/17/17.
 * Innovisto LLC
 * sanjogshrestha.nepal@gmail.com
 */

public class FirebaseInstanceIdService extends com.google.firebase.iid.FirebaseInstanceIdService{
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        //sendToServer(FirebaseInstanceId.getInstance().getToken());
    }

    private void sendToServer(String token) {
        //ToDo : future updates
    }
}
