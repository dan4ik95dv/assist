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

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.net.InetSocketAddress;
import java.net.Proxy;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.dvfu.AssistApi;
import ru.dvfu.assist.model.Auth;
import ru.dvfu.assist.model.AuthSuccess;
import ru.dvfu.assist.model.Profile;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    @BindView(R.id.login_edittext)
    EditText mLoginEditText;
    @BindView(R.id.password_edittext)
    EditText mPasswordEditText;
    @BindView(R.id.sign_in_button)
    Button mButton;
    SharedPreferences mSharedPreference;
    AssistApi assistApi;
    Retrofit retrofit;
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

                assistApi.auth(new Auth(login, password)).enqueue(new Callback<AuthSuccess>() {
                    @Override
                    public void onResponse(Call<AuthSuccess> call, Response<AuthSuccess> response) {
                        mSharedPreference.edit().putString("LOGIN", login).apply();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("LOGIN", login);
                        intent.putExtra("TOKEN", response.body().getProfile().getToken());
                        intent.putExtra("FULLNAME", response.body().getProfile().getFullname());
                        intent.putExtra(Constants.PASSWORD, password);
                        response.body().getProfile().save();
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<AuthSuccess> call, Throwable t) {

                        Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://private-32252-assist3.apiary-mock.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        assistApi = retrofit.create(AssistApi.class);
        mButton.setOnClickListener(clickListener);
        mSharedPreference = getSharedPreferences("SETTINGS", MODE_PRIVATE);

        String login = mSharedPreference.getString("LOGIN", null);
        if (login != null) {
            mLoginEditText.setText(login);
            mLoginEditText.setSelection(login.length());
        }

    }
}
