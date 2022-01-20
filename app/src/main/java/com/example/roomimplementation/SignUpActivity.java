package com.example.roomimplementation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.roomimplementation.DbUtils.LocalDB;
import com.example.roomimplementation.DbUtils.User;

public class SignUpActivity extends AppCompatActivity {
    private EditText edittextusername;
    private EditText edittextpwd;
    private EditText edittextconfirmpwd;
    private Button btncreateuser;

    private final String CREDENTIAL_SHARED_PREF = "our_shared_pref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edittextusername = findViewById(R.id.edittext_username);
        edittextpwd = findViewById(R.id.edittext_password);
        edittextconfirmpwd = findViewById(R.id.edittext_confirm_password);
        btncreateuser = findViewById(R.id.btn_create_user);

        btncreateuser.setOnClickListener(new View.OnClickListener( ) {
            @Override
            public void onClick(View view) {
                String strPassword = edittextpwd.getText().toString();
                String strConfirmPassword = edittextconfirmpwd.getText().toString();
                String strUsername = edittextusername.getText().toString();

                if (strPassword != null && strConfirmPassword != null && strPassword.equalsIgnoreCase(strConfirmPassword)) {
/*                    SharedPreferences credentials = getSharedPreferences(CREDENTIAL_SHARED_PREF, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = credentials.edit();
                    editor.putString("Password", strPassword);
                    editor.putString("Username", strUsername);
                    editor.commit(); */

                    new Thread(new Runnable( ) {
                        @Override
                        public void run() {
                            User user = new User ();
                            user.setUsername(strUsername);
                            user.setPassword(strPassword);
                            LocalDB dbInstance = RoomImplementation.getmInstance().getDbInstance();
                            dbInstance.userDao().createuser(user);

                        }
                    }).start();



                    SignUpActivity.this.finish();
                }
            }
        });

    }
}