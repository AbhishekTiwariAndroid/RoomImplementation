package com.example.roomimplementation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.roomimplementation.DbUtils.LocalDB;
import com.example.roomimplementation.DbUtils.User;

public class MainActivity extends AppCompatActivity {

    private EditText edittextusername;
    private EditText edittextpassword;
    private Button btnlogin;
    private Button btnsignup;

    private final String CREDENTIAL_SHARED_PREF = "our_shared_pref";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edittextusername = findViewById(R.id.edittext_username);
        edittextpassword = findViewById(R.id.edittext_password);
        btnlogin = findViewById(R.id.btn_login);
        btnsignup = findViewById(R.id.btn_signup);

        btnsignup.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);

            }
        });


        btnlogin.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
/*                SharedPreferences credentials = getSharedPreferences(CREDENTIAL_SHARED_PREF, Context.MODE_PRIVATE);
                String strUsername = credentials.getString("Username", null);
                String strPassword = credentials.getString("Password", null);*/

                final String username_from_ed = edittextusername.getText( ).toString( );
                final String password_from_ed = edittextpassword.getText( ).toString( );

                new Thread(new Runnable( ) {
                    @Override
                    public void run() {
                        LocalDB dbInstance = RoomImplementation.getmInstance( ).getDbInstance( );
                        User user = dbInstance.userDao( ).getUserByUsername(username_from_ed);

                        if (user != null && user.getUsername( ) != null && username_from_ed != null && user.getUsername( ).equalsIgnoreCase(username_from_ed)) {
                            if (user.getPassword( ) != null && password_from_ed != null && user.getPassword( ).equalsIgnoreCase(password_from_ed)) {
                                runOnUiThread(new Runnable( ) {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show( );
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable( ) {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show( );
                                    }
                                });
                            }

                        } else {
                            runOnUiThread(new Runnable( ) {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show( );
                                }
                            });
                        }
                    }
                }).start( );
            }
            });
        }
}

