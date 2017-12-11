package np.cnblabs.accountdemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import np.cnblabs.accountdemo.realm.SignUpModelData;
import np.cnblabs.accountdemo.realm.UserModelData;

public class Login extends AppCompatActivity {
    TextInputLayout email_textInputLayout, password_textInputLayout;
    EditText email_editText, password_editText;
    private String password, email;

    public static final String IS_LOGIN = "is_login";
    public static final String EMAIL = "email";
    public static final String MY_PREFERENCE = "myPreference";
    SharedPreferences preferences;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        realm = DemoApplication.getDefaultRealm();
        preferences = this.getSharedPreferences(MY_PREFERENCE, Context.MODE_PRIVATE);

        UserModelData userModelData = realm.where(UserModelData.class).findFirst();
        if(userModelData != null && userModelData.isValid()){
            startActivity(new Intent(this, NavigationActivity.class));
            finish();
        }

        /*if(preferences.contains(IS_LOGIN)){
            boolean isLogin = preferences.getBoolean(IS_LOGIN, false);
            String loggedEmail = preferences.getString(EMAIL, null);
            if(isLogin) {
                if(loggedEmail != null) {
                    startActivity(new Intent(this, MainActivity.class)
                            .putExtra("EMAIL", loggedEmail));
                    finish();
                }
            }
        }*/

        email_textInputLayout = findViewById(R.id.email_textInputLayout);
        password_textInputLayout = findViewById(R.id.password_textInputLayout);

        email_editText = findViewById(R.id.email_editText);
        password_editText = findViewById(R.id.password_editText);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(realm != null) realm.close();
    }

    public void signup(View view) {
        startActivity(new Intent(this, SignUp.class));
    }

    private boolean validatePassword() {
        password = password_editText.getText().toString();
        if(password.isEmpty()){
            password_textInputLayout.setErrorEnabled(true);
            password_textInputLayout.setError(getString(R.string.empty_field));
            return false;
        }else{
            password_textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateEmail() {
        email = email_editText.getText().toString();
        if(email.isEmpty()){
            email_textInputLayout.setErrorEnabled(true);
            email_textInputLayout.setError(getString(R.string.empty_field));
            return false;
        }else {
            if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                email_textInputLayout.setErrorEnabled(true);
                email_textInputLayout.setError(getString(R.string.invalid_email));
                return false;
            }
            email_textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    public void validate(View view) {
        if(!validateEmail()) return;
        if(!validatePassword()) return;

        final SignUpModelData signUpModelData = realm.where(SignUpModelData.class)
                .equalTo(SignUpModelData.EMAIL_ID, email)
                .equalTo(SignUpModelData.PASSWORD, password)
                .findFirst();

        if(signUpModelData == null){
            Toast.makeText(this, "Unknown user", Toast.LENGTH_SHORT).show();
            return;
        }

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                UserModelData userModelData = realm.createObject(UserModelData.class, email);
                userModelData.setFirstName(signUpModelData.getFirstName());
                userModelData.setLastName(signUpModelData.getLastName());
                userModelData.setGender(signUpModelData.getGender());
                userModelData.setPassword(signUpModelData.getPassword());
            }
        });

        /*SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(EMAIL , email);
        editor.apply();*/

        //Toast.makeText(this, " You can now login but we have no interface to take you", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(this, NavigationActivity.class));
    }
}
