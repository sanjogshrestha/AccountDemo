package np.cnblabs.accountdemo.utils;

import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by sanjogstha on 12/12/17.
 * Innovisto LLC
 * sanjogshrestha.nepal@gmail.com
 */

public class Util {

    /** Check if this device has a camera */
    public static boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

}
