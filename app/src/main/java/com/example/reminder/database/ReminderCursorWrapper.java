package com.example.reminder.database;

import android.database.Cursor;
import android.database.CursorWrapper;


import com.example.reminder.database.ReminderDBSchema.ReminderTable;
import com.example.reminder.pojo.ReminderModel;

import java.util.Date;
import java.util.UUID;


public class ReminderCursorWrapper extends CursorWrapper {

    public ReminderCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public ReminderModel getReminder(){
        UUID uuidString = UUID.fromString((getString (getColumnIndex(ReminderTable.Cols.UUID))));
        String title = getString(getColumnIndex(ReminderTable.Cols.TITLE));
        long date = getLong(getColumnIndex(ReminderTable.Cols.DATE));
        String text = getString(getColumnIndex(ReminderTable.Cols.TEXT));

        ReminderModel reminder = new ReminderModel(uuidString);
        reminder.setDate(new Date(date));
        reminder.setTitle(title);
        reminder.setText(text);
        return reminder;
    }
}

