package com.example.acer.musicapp2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.button.MaterialButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {


   EditText e1, e2;
    Button loginbtn;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        textView=findViewById(R.id.link);
        String text="Don't have an account? Sign Up";

        SpannableString ss=new SpannableString(text);

        ClickableSpan clickableSpan=new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login.this,Signup.class);
                startActivity(intent);
                overridePendingTransition(R.anim.hold,R.anim.fade_in);
            }
        };

        ss.setSpan(clickableSpan,23,30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());



        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
        boolean log = sharedPreferences.getBoolean("Is logged in", false);
        if (log) {
            Intent intent = new Intent(login.this, MainPage.class);
            startActivity(intent);
        }

        e1= findViewById(R.id.et1);
        e2= findViewById(R.id.et2);

        loginbtn = findViewById(R.id.loginbtn);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String uname = e1.getText().toString();
                String pwd = e2.getText().toString();

                if (uname.isEmpty()) {

                    Toast.makeText(login.this, "Username Field Is Empty", Toast.LENGTH_SHORT).show();
                } else if (pwd.isEmpty()) {
                    Toast.makeText(login.this, "Password Field Is Empty", Toast.LENGTH_SHORT).show();
                } else {

                    SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putString("Username", uname);
                    editor.putString("Password", pwd);
                    editor.putBoolean("Is logged in", true);

                    editor.commit();
                    Toast.makeText(login.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(login.this, MainPage.class);
                    startActivity(intent);
                }
            }
        });
    }

   /* public void textlink (View view){
        Intent intent=new Intent(login.this,Signup.class);
        startActivity(intent);
        overridePendingTransition(R.anim.hold,R.anim.fade_in);
    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.hold,R.anim.fade_out);
    }
}