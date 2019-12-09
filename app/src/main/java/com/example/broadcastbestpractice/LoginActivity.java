package com.example.broadcastbestpractice;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {

    private EditText mAccountEdit;
    private EditText mPasswordEdit;
    private Button mLogin;
    private SharedPreferences mPref;
    private CheckBox mRememberPass;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAccountEdit = (EditText) findViewById(R.id.account);
        mPasswordEdit = (EditText) findViewById(R.id.password);
        mLogin = (Button) findViewById(R.id.login);

        mPref = PreferenceManager.getDefaultSharedPreferences(this);
        mRememberPass = (CheckBox) findViewById(R.id.remember_pass);
        boolean isRemember = mPref.getBoolean("remember_password", false);
        if (isRemember) {
            String accout = mPref.getString("account", "");
            String password = mPref.getString("password", "");
            mAccountEdit.setText(accout);
            mPasswordEdit.setText(password);
            mRememberPass.setChecked(true);
        }

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String account = mAccountEdit.getText().toString();
                String password = mPasswordEdit.getText().toString();
                if ("admin".equals(account) && "123456".equals(password)) {
                    mEditor = mPref.edit();
                    if (mRememberPass.isChecked()) {
                        mEditor.putBoolean("remember_password", true);
                        mEditor.putString("account", account);
                        mEditor.putString("password", password);
                    } else {
                        mEditor.clear();
                    }
                    mEditor.apply();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "account or password is invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
