package com.example.kirill.lab2_login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    public final String name="Admin";
    public final String pass="Admin";
    EditText userName;
    EditText password;
    TextView attempts;
    Button login;
    int number = 3;
    int counter = 0;
    boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = (EditText)findViewById(R.id.idUserName);
        password = (EditText)findViewById(R.id.idPassword);
        attempts = (TextView)findViewById(R.id.idAttempts);
        login = (Button)findViewById(R.id.idLogin);
        attempts.setText("attempts:"+number);
    }

    public void onLoginButtonClick(View view){
        counter++;
        if(isDifferentSymbol(password.getText().toString())&& flag==true){
            number = 5;
            flag = false;
        }else{
            if(flag) {
                number = 3;
                flag = false;
            }
        }
        if(counter < number) {
            if (userName.getText().toString().equals(name) && password.getText().toString().equals(pass)) {
                attempts.setText("Welcome " + name + "!");
            } else {
                attempts.setText("attempts:"+(number-counter));
            }
        }else{
            attempts.setText("attempts:"+(number-counter));
            finish();
        }
    }
    public boolean isDifferentSymbol(String password) {
        int errors = 0;
        int counter=0;
        if (Math.abs(pass.length() - password.length())<=1) {
            if(pass.length()<password.length()){

                for(int i=0;i<pass.length();i++){

                    if(pass.charAt(i)!=password.charAt(i)){

                        if(pass.charAt(i)!=password.charAt(i+1)){

                            return false;

                        }

                    }

                }

            }else {
                for (int i=0;i<password.length(); i++){
                    if(password.charAt(i)!=pass.charAt(i)){

                        if(password.charAt(i)!=pass.charAt(i+1)){
                            errors++;
                        }

                    }
                }

            }

        }else {
            return false;
        }
        return errors<=1;
    }
}
