package com.example.mvctaskmanager.Controller;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvctaskmanager.MainActivity;
import com.example.mvctaskmanager.Model.Beans.Task;
import com.example.mvctaskmanager.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RecyclerTaskAdapter extends RecyclerView.Adapter<RecyclerTaskAdapter.ViewHolder> {

    Context context;
    ArrayList<Task> arrayTasks;
    DatePickerDialog datePickerDialog;
    Button datePickerButton;


    public RecyclerTaskAdapter(Context context, ArrayList<Task> arrayTasks) {
        this.context = context;
        this.arrayTasks = arrayTasks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_task_row_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            String DueDate = arrayTasks.get(position).getDueDate();
            SimpleDateFormat dates = new SimpleDateFormat("MMM dd yyyy");
            String CurrentDate = dates.format(new Date());
            Date date1;
            Date date2;
            date1 = dates.parse(CurrentDate);
            date2 = dates.parse(DueDate);
            long dateDiff = date1.getTime() - date2.getTime();
            long difference = Math.abs(dateDiff);
            long differenceDates = difference / (24 * 60 * 60 * 1000);
            String daysDifference = Long.toString(differenceDates);
            if (dateDiff < 0) {
                holder.daysLeft.setText(String.format("%s Days left", daysDifference));
            } else {
                holder.daysLeft.setText(String.format("%s Days overdue", daysDifference));
            }
            holder.txtTitle.setText(arrayTasks.get(position).getTitle());
            holder.dueDate.setText(arrayTasks.get(position).getDueDate());
        } catch (Exception exception) {
            Toast.makeText(context, "Unable to Find Difference", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public int getItemCount() {
        return arrayTasks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, dueDate, daysLeft;
        View emptyLayout;
        LinearLayout LLTaskRow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtRecyclerCardTitle);
            dueDate = itemView.findViewById(R.id.txtRecyclerCardDueDays);
            daysLeft = itemView.findViewById(R.id.txtRecyclerCardDaysLeft);
            emptyLayout = itemView.findViewById(R.id.empty_state_layout);
            LLTaskRow = itemView.findViewById(R.id.LLTaskRow);
        }
    }
}
