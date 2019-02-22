package com.example.reminder.database;


public class ReminderDBSchema {
    public static final class ReminderTable{
        public static final String NAME = "reminders";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DATE = "date";
            public static final String TEXT = "text";
        }
    }
}

