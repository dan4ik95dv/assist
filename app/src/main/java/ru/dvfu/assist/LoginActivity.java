package ru.dvfu.assist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    @BindView(R.id.login_edittext)
    EditText mLoginEditText;
    @BindView(R.id.password_edittext)
    EditText mPasswordEditText;
    @BindView(R.id.sign_in_button)
    Button mButton;
    SharedPreferences mSharedPreference;

    String login, password;

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            login = mLoginEditText.getText().toString();
            password = mPasswordEditText.getText().toString();
            Log.d(TAG, "onClick: " + login + " " + password);
            if (login.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
            } else if (login.length() < 5 || password.length() < 5) {
                Toast.makeText(LoginActivity.this, "Меньше пяти знаков", Toast.LENGTH_SHORT).show();
            } else {
                mSharedPreference.edit().putString("LOGIN", login).apply();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("LOGIN", login);
                intent.putExtra(Constants.PASSWORD, password);
                startActivity(intent);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mButton.setOnClickListener(clickListener);
        mSharedPreference = getSharedPreferences("SETTINGS", MODE_PRIVATE);

        String login = mSharedPreference.getString("LOGIN", null);
        if (login != null) {
            mLoginEditText.setText(login);
            mLoginEditText.setSelection(login.length());
        }

    }
}
