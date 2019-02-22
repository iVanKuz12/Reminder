package com.example.reminder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.reminder.pojo.ReminderModel;
import java.util.UUID;

public class NewReminderActivity extends AppCompatActivity {

    private static final String EXTRA_REMINDER_ID = "reminder_id";
    private ReminderModel mReminder;
    private Toolbar toolbar;
    private EditText edTextTitle, edText;

    public static Intent newIntent(Context context, UUID uuid){
        Intent intent = new Intent(context, NewReminderActivity.class);
        intent.putExtra(EXTRA_REMINDER_ID, uuid);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reminder);

        initUI();

        UUID uuid = (UUID) getIntent().getSerializableExtra(EXTRA_REMINDER_ID);
        if (uuid != null) {
            mReminder = ReminderLab.get(this).getReminder(uuid);
            edTextTitle.setText(mReminder.getTitle());
            edText.setText(mReminder.getText());
        }

    }

    private void saveReminder(){
        if (mReminder == null){
            ReminderModel reminderModel = new ReminderModel();
            reminderModel.setTitle(edTextTitle.getText().toString());
            reminderModel.setText(edText.getText().toString());
            ReminderLab.get(this).newReminder(reminderModel);
        } else {
            mReminder.setTitle(edTextTitle.getText().toString());
            mReminder.setText(edText.getText().toString());
            ReminderLab.get(this).updateReminder(mReminder);
        }
    }

    private void delReminder(){
        if (mReminder == null){

        } else {
            ReminderLab.get(this).deleteReminder(mReminder);
        }
    }

    private void initUI(){
        toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        edTextTitle = (EditText) findViewById(R.id.edTextTitle);
        edText = (EditText) findViewById(R.id.edText);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save_del, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_del:
                delReminder();
                finish();
                return true;
            case R.id.action_save:
                saveReminder();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
