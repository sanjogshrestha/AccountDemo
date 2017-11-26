package np.cnblabs.accountdemo.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sanjogstha on 11/26/17.
 * Innovisto LLC
 * sanjogshrestha.nepal@gmail.com
 */

public class RealmModelData extends RealmObject {
    public static String EMAIL_ID = "email";
    public static String NAME = "name";

    @PrimaryKey private String email;
    private String name;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
