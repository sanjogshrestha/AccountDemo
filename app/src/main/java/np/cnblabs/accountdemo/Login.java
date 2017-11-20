package np.cnblabs.accountdemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    TextInputLayout email_textInputLayout, password_textInputLayout;
    EditText email_editText, password_editText;
    private String password, email;

    public static final String IS_LOGIN = "is_login";
    public static final String EMAIL = "email";
    public static final String MY_PREFERENCE = "myPreference";
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferences = this.getSharedPreferences(MY_PREFERENCE, Context.MODE_PRIVATE);

        if(preferences.contains(IS_LOGIN)){
            boolean isLogin = preferences.getBoolean(IS_LOGIN, false);
            String loggedEmail = preferences.getString(EMAIL, null);
            if(isLogin) {
                if(loggedEmail != null) {
                    startActivity(new Intent(this, MainActivity.class)
                            .putExtra("EMAIL", loggedEmail));
                    finish();
                }
            }
        }

        email_textInputLayout = findViewById(R.id.email_textInputLayout);
        password_textInputLayout = findViewById(R.id.password_textInputLayout);

        email_editText = findViewById(R.id.email_editText);
        password_editText = findViewById(R.id.password_editText);
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

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(EMAIL , email);
        editor.apply();

        Toast.makeText(this, " You can now login but we have no interface to take you", Toast.LENGTH_SHORT).show();

        Intent mainIntent = new Intent(this, MainActivity.class);
        mainIntent.putExtra("EMAIL", email);
        startActivity(mainIntent);
    }
}
