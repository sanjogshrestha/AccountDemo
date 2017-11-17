package np.cnblabs.accountdemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by sanjogstha on 11/16/17.
 * Innovisto LLC
 * sanjogshrestha.nepal@gmail.com
 */

public class SignUp extends AppCompatActivity{

    TextInputLayout fName_textInputLayout, lName_textInputLayout, email_textInputLayout, password_textInputLayout, cPassword_textInputLayout;
    EditText fName_editText, lName_editText, email_editText, password_editText, cPassword_editText;

    Toolbar mToolbar;
    RadioGroup gender_radioGroup;
    RadioButton male_radioBtn, female_radioBtn;
    CheckBox tc_checkBox;

    Button signupBtn;
    private String fName, lName, email, password, gender;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fName_textInputLayout = findViewById(R.id.fName_textInputLayout);
        lName_textInputLayout = findViewById(R.id.lName_textInputLayout);
        email_textInputLayout = findViewById(R.id.email_textInputLayout);
        password_textInputLayout = findViewById(R.id.password_textInputLayout);
        cPassword_textInputLayout = findViewById(R.id.cPassword_textInputLayout);

        fName_editText = findViewById(R.id.fName_editText);
        lName_editText = findViewById(R.id.lName_editText);
        email_editText = findViewById(R.id.email_editText);
        password_editText = findViewById(R.id.password_editText);
        cPassword_editText = findViewById(R.id.cPassword_editText);

        gender_radioGroup = findViewById(R.id.gender_radioGroup);

        male_radioBtn = findViewById(R.id.male_radioBtn);
        female_radioBtn = findViewById(R.id.female_radioBtn);

        tc_checkBox = findViewById(R.id.tc_checkBox);

        signupBtn = findViewById(R.id.signupBtn);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });
    }

    private void validate() {
        if(!validateFirstName()) return;
        if(!validateSecondName()) return;
        if(!validateEmail()) return;
        if(!validatePassword()) return;
        if(!validateGender()) return;
        if(!validateTnC()) return;
        System.out.println("Data=>" + "Name = " + fName + " " + lName + " , Email=" + email +
                " , Gender = " + gender + " , Password =" + password);
        Toast.makeText(this, "Hurray", Toast.LENGTH_SHORT).show();

        startActivity(new Intent(this, Login.class));
        finish();
    }

    private boolean validatePassword() {
        password = password_editText.getText().toString();
        String confirmPassword = cPassword_editText.getText().toString();
        if(password.isEmpty()){
            password_textInputLayout.setErrorEnabled(true);
            password_textInputLayout.setError(getString(R.string.empty_field));
            return false;
        }else{
            password_textInputLayout.setErrorEnabled(false);
            if(confirmPassword.isEmpty()){
                cPassword_textInputLayout.setErrorEnabled(true);
                cPassword_textInputLayout.setError(getString(R.string.empty_field));
                return false;
            }else{
                cPassword_textInputLayout.setErrorEnabled(false);
                if(!password.equals(confirmPassword)){
                    Toast.makeText(this, R.string.password_mismatch, Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
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

    private boolean validateTnC() {
        if(tc_checkBox.isChecked())
            return true;

        Toast.makeText(this, R.string.terms_must_message, Toast.LENGTH_SHORT).show();
        return false;
    }

    private boolean validateGender() {
        int id = gender_radioGroup.getCheckedRadioButtonId();
        Toast toastMsg = Toast.makeText(this, R.string.select_gender, Toast.LENGTH_SHORT);
        if(id == -1) {
            toastMsg.show();
            return false; /*No value is selected*/
        }
        RadioButton radioButton = findViewById(id);
        gender = radioButton.getText().toString();
        if(gender.isEmpty()) {
            toastMsg.show();
            return false;
        }

        return true;
    }

    private boolean validateFirstName() {
        fName = fName_editText.getText().toString();
        if(fName.isEmpty()){
            fName_textInputLayout.setError(getString(R.string.empty_field));
            fName_textInputLayout.setErrorEnabled(true);
            return false;
        }else{
            fName_textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateSecondName() {
        lName = lName_editText.getText().toString();
        if(lName.isEmpty()){
            lName_textInputLayout.setError(getString(R.string.empty_field));
            lName_textInputLayout.setErrorEnabled(true);
            return false;
        }else{
            lName_textInputLayout.setErrorEnabled(false);
        }
        return true;
    }
}
