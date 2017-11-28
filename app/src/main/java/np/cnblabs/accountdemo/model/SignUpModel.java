package np.cnblabs.accountdemo.model;

/**
 * Created by sanjogstha on 11/28/17.
 * Innovisto LLC
 * sanjogshrestha.nepal@gmail.com
 */

public class SignUpModel {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String gender;

    public SignUpModel(){}

    public SignUpModel(String email, String firstName,
                       String lastName, String password,
                       String gender){
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
