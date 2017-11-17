package np.cnblabs.accountdemo;

import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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

        Toast.makeText(this, " You can now login but we have no interface to take you", Toast.LENGTH_SHORT).show();
    }
}
