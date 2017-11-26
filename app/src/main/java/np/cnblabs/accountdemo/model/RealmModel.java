package np.cnblabs.accountdemo.model;

/**
 * Created by sanjogstha on 11/26/17.
 * Innovisto LLC
 * sanjogshrestha.nepal@gmail.com
 */

public class RealmModel {
    private String name, email;

    public RealmModel(String name, String email){
        this.email = email;
        this.name = name;
    }

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
