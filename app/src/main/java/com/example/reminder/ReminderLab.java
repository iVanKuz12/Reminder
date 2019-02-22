package com.example.reminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.reminder.database.ReminderBaseHelper;
import com.example.reminder.database.ReminderCursorWrapper;
import com.example.reminder.database.ReminderDBSchema.ReminderTable;
import com.example.reminder.pojo.ReminderModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReminderLab {

    private static ReminderLab sReminderLab;

    private Context mContex;
    private SQLiteDatabase mDataBase;

    public static ReminderLab get (Context context) {
        if (sReminderLab == null) {
            sReminderLab = new ReminderLab(context);
        }
        return sReminderLab;
    }

    private ReminderLab(Context context) {
        mContex = context.getApplicationContext();
        mDataBase = new ReminderBaseHelper(mContex).getWritableDatabase();


    }
    public void newReminder(ReminderModel r) {
        ContentValues values = getContentValues(r);
        mDataBase.insert(ReminderTable.NAME, null, values);
    }


    public void deleteReminder(ReminderModel reminder) {
        mDataBase.delete(ReminderTable.NAME, ReminderTable.Cols.UUID + " = ?", new String[]{reminder.getId().toString()});
    }

    public List<ReminderModel> getReminders(){
        List<ReminderModel> reminders = new ArrayList<>();
        ReminderCursorWrapper cursor = queryReminders(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                reminders.add(cursor.getReminder());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return reminders;
    }


    public ReminderModel getReminder (UUID id) {
        ReminderCursorWrapper cursor = queryReminders(ReminderTable.Cols.UUID
                + " = ? " , new String[] {id.toString()}
        );

        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getReminder();
        } finally {
            cursor.close();
        }
    }

    public void updateReminder(ReminderModel reminder) {
        String uuidString = reminder.getId().toString();
        ContentValues values = getContentValues(reminder);

        mDataBase.update(ReminderTable.NAME, values, ReminderTable.Cols.UUID
                + " = ? " , new String[]{uuidString});
    }

    private ReminderCursorWrapper queryReminders(String whereClause, String[] whereArgs) {
        Cursor cursor = mDataBase.query(
                ReminderTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new ReminderCursorWrapper(cursor);
    }

    private static ContentValues getContentValues (ReminderModel reminder) {
        ContentValues values = new ContentValues();
        values.put(ReminderTable.Cols.UUID, reminder.getId().toString() );
        values.put(ReminderTable.Cols.TITLE, reminder.getTitle() );
        values.put(ReminderTable.Cols.DATE, reminder.getDate().getTime() );
        values.put(ReminderTable.Cols.TEXT, reminder.getText() );
        return values;
    }

}
