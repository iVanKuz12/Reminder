package com.example.reminder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.reminder.pojo.ReminderModel;

import java.util.ArrayList;
import java.util.List;

public class ReminderListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private AdapterReminderList mAdapter;
    private List<ReminderModel> mReminderModels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_list);

        initToolBar();
        initRecyclerView();
        updateUI();
        initAndFloatActionButton();







    }

    private void initAndFloatActionButton() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewReminderActivity();

            }
        });
    }

    private void initToolBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        ReminderLab reminderLab = ReminderLab.get(this);
        mReminderModels = reminderLab.getReminders();
        // Если адаптер пустой создаём если нет, то обновляем данные
        if (mAdapter == null) {
            mAdapter = new AdapterReminderList(mReminderModels, this);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.setReminders(mReminderModels);
            mAdapter.notifyDataSetChanged();
        }
        if (mReminderModels.size() == 0) {
            Toast.makeText(this, "Список пуст! Добавьте заметку! ", Toast.LENGTH_LONG).show();
        }
    }

    private void initRecyclerView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.reciclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void startNewReminderActivity() {
        Intent intent = new Intent(this, NewReminderActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
