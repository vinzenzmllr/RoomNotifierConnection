package com.example.vinze.roomnotifierconnection;

import android.app.AlertDialog;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vinze.roomnotifierconnection.Entities.Reminder;
import com.example.vinze.roomnotifierconnection.ViewModels.ReminderViewModel;


public class ReminderAdapter extends ListAdapter<Reminder, ReminderAdapter.ReminderHolder> {

    private OnItemClickListener listener;


    public ReminderAdapter() {
        super(DIFF_CALLBACK);

    }

    private static final DiffUtil.ItemCallback<Reminder> DIFF_CALLBACK = new DiffUtil.ItemCallback<Reminder>() {
        @Override
        public boolean areItemsTheSame(Reminder oldItem, Reminder newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Reminder oldItem, Reminder newItem) {
            return oldItem.getMedicationName().equals(newItem.getMedicationName());
        }
    };

    @NonNull
    @Override
    public ReminderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reminder_item, parent, false);
        return new ReminderHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderHolder holder, int position) {
        Reminder currentReminder = getItem(position);
        holder.textViewTitle.setText(currentReminder.getMedicationName());
        holder.textViewMorningTime.setText(currentReminder.getMorningTime());
        holder.textViewNoonTime.setText(currentReminder.getNoonTime());
        holder.textViewEveningTime.setText(currentReminder.getEveningTime());

    }

        public Reminder getReminderat ( int position){
            return getItem(position);
        }

        class ReminderHolder extends RecyclerView.ViewHolder {
            private TextView textViewTitle;
            private TextView textViewMorningTime;
            private TextView textViewNoonTime;
            private TextView textViewEveningTime;

            public ReminderHolder(View itemView) {
                super(itemView);
                textViewTitle = itemView.findViewById(R.id.text_view_title);
                textViewMorningTime = itemView.findViewById(R.id.text_view_morning);
                textViewNoonTime = itemView.findViewById(R.id.text_view_noon);
                textViewEveningTime = itemView.findViewById(R.id.text_view_evening);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        if (listener != null && position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(getItem(position));
                        }
                    }

                });
            }
        }

        public interface OnItemClickListener {
            void onItemClick(Reminder reminder);
        }

        public void setOnItemClickListener (OnItemClickListener listener){
            this.listener = listener;
        }


}
