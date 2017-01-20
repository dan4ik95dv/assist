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
import ru.dvfu.assist.model.Theme;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    ThemeAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    Dialog dialog;

    ThemeAdapter.Callback callback = new ThemeAdapter.Callback() {
        @Override
        public void clickElement(int id) {
//            SQLite.delete().from(Theme.class).where()
            //код удаления записи из базы по id
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Log.d(TAG, "onCreate: " + "вызван");
        adapter = new ThemeAdapter(callback);

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

        ArrayList<Theme> themeList = (ArrayList<Theme>) SQLite.select().from(Theme.class).queryList();
        adapter.setDatasetList(themeList);
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
