package com.example.reminder;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reminder.pojo.ReminderModel;

import java.util.List;

public class AdapterReminderList extends RecyclerView.Adapter<AdapterReminderList.ReminderHolder> {

    private List<ReminderModel> mReminders;
    Context mContext;

    public AdapterReminderList(List<ReminderModel> reminders, Context context) {
        this.mReminders = reminders;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ReminderHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_reminder_list,
                viewGroup, false);
        return new ReminderHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ReminderHolder reminderHolder, int i) {
        ReminderModel mReminder = mReminders.get(i);
        reminderHolder.bind(mReminder);

        reminderHolder.itemView.setTag(R.string.model, mReminder);
        reminderHolder.itemView.setTag(R.string.position, i);
        reminderHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ReminderModel reminderModel = (ReminderModel) v.getTag(R.string.model);
                openReminder(reminderModel);




            }
        });

    }

    private void openReminder(ReminderModel reminderModel) {
        Intent intent = NewReminderActivity.newIntent(mContext, reminderModel.getId());
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return mReminders.size();
    }

    public class ReminderHolder extends RecyclerView.ViewHolder  {
        private TextView texViewTitle;
        private TextView texViewText;

        public ReminderHolder(@NonNull View itemView) {
            super(itemView);
            texViewTitle = itemView.findViewById(R.id.texViewTitle);
            texViewText = itemView.findViewById(R.id.texViewText);

        }

        public void bind(ReminderModel reminder) {
            texViewTitle.setText(reminder.getTitle());
            texViewText.setText(reminder.getText());

        }

    }

    public void setReminders(List<ReminderModel> reminders){

        mReminders = reminders;
    }

}
