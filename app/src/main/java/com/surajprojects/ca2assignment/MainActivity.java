package com.surajprojects.ca2assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button signup;
    EditText name, email, pass, conf_pass, phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signup = findViewById(R.id.btn_signup);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        pass = findViewById(R.id.pass);
        conf_pass = findViewById(R.id.conf_pass);

        pass.setTransformationMethod(new MyPasswordTransformationMethod());
        conf_pass.setTransformationMethod(new MyPasswordTransformationMethod());

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(name.getText().toString().isEmpty() || email.getText().toString().isEmpty() || phone.getText().toString().isEmpty()
                        || pass.getText().toString().isEmpty() || conf_pass.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), R.string.all_fields, Toast.LENGTH_LONG).show();
                }
                else if(!(phone.getText().toString().length() == 10)) {
                    Toast.makeText(getApplicationContext(), R.string.phone_num, Toast.LENGTH_LONG).show();
                }
                else if(!pass.getText().toString().equals(conf_pass.getText().toString())){
                    Toast.makeText(getApplicationContext(), R.string.pass_match, Toast.LENGTH_LONG).show();
                }
                else{
                    Intent intent = new Intent(MainActivity.this, WelcomePage.class);
                    intent.putExtra("Name", name.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }
}

class MyPasswordTransformationMethod extends PasswordTransformationMethod {

    @Override
    public CharSequence getTransformation(CharSequence source, View view) {
        return new PasswordCharSequence(source);
    }

    private class PasswordCharSequence implements CharSequence {

        private CharSequence mSource;

        public PasswordCharSequence(CharSequence source) {
            mSource = source;
        }

        public char charAt(int index) {
            return '*';
        }

        public int length() {
            return mSource.length();
        }

        public CharSequence subSequence(int start, int end) {
            return mSource.subSequence(start, end); // Return default
        }
    }
}