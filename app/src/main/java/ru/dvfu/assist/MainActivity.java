package ru.dvfu.assist;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.dvfu.AssistApi;
import ru.dvfu.assist.model.Theme;
import ru.dvfu.assist.model.ThemeSuccess;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    ThemeAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    Dialog dialog;
    Retrofit retrofit;
    AssistApi assistApi;
    String token;

    ThemeAdapter.Callback callback = new ThemeAdapter.Callback() {
        @Override
        public void clickElement(int id) {
            Toast.makeText(MainActivity.this, "Запись была удалена!", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        token = getIntent().getStringExtra("TOKEN");

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Log.d(TAG, "onCreate: " + "вызван");
        adapter = new ThemeAdapter(callback);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://private-32252-assist3.apiary-mock.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        assistApi = retrofit.create(AssistApi.class);

        linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(adapter);

        final EditText themeEditText = new EditText(this);

        DialogInterface.OnClickListener okClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String theme = themeEditText.getText().toString();

                        Log.d(TAG, "onClick: " + theme);

                        Theme newTheme = new Theme();
                        newTheme.setName(theme);
                        newTheme.save();

                        Toast.makeText(MainActivity.this, "Тема добавлена!", Toast.LENGTH_SHORT).show();

                        ArrayList<Theme> themeList = (ArrayList<Theme>) SQLite.select().from(Theme.class).queryList();
                        adapter.setDatasetList(themeList);
                    }
                };
        dialog = new AlertDialog.Builder(this)
                .setTitle("Введите тему:")
                .setMessage("Введите тему для тестирования")
                .setView(themeEditText)
                .setPositiveButton("Ok", okClickListener)
                .setNegativeButton("Отмена", null)
                .create();

//        ArrayList<Theme> themeList = (ArrayList<Theme>) SQLite.select().from(Theme.class).queryList();
        assistApi.themes(token).enqueue(new Callback<ThemeSuccess>() {
            @Override
            public void onResponse(Call<ThemeSuccess> call, Response<ThemeSuccess> response) {
                ArrayList<Theme> themeList = response.body().getThemes().getThemesList();
                adapter.setDatasetList(themeList);
            }

            @Override
            public void onFailure(Call<ThemeSuccess> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Произошла ошибка" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
//
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_theme:
                dialog.show();
                break;
        }
        return true;
    }

}
