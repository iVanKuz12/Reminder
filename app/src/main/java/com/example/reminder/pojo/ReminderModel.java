package com.example.reminder.pojo;

import java.util.Date;
import java.util.UUID;

public class ReminderModel {

    private UUID mId;
    private String mTitle;
    private String mText;
    private Date mDate;

    public ReminderModel() {

        this.mId = UUID.randomUUID();
        mDate = new Date();

    }

    public ReminderModel(UUID uuid){
        this.mId = uuid;
    }

    public UUID getId() {
        return mId;
    }

    public void setId(UUID mId) {
        this.mId = mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getText() {
        return mText;
    }

    public void setText(String mText) {
        this.mText = mText;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }
}
