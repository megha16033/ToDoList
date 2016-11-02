package com.example.mnrr.whattodolist;

/**
 * Created by rohan on 01-11-2016.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

    private List<Task> tasksList = new ArrayList<Task>();

    public class MyViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        public TextView title, details;
        private Context context;



        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            details = (TextView) view.findViewById(R.id.details);
        }

        public MyViewHolder(Context context, View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.title);
            // Store the context
            this.context = context;
            // Attach a click listener to the entire row view
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition(); // gets item position
            System.out.println("inside onclick");
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                //Task task = tasksList.get(position);
                // We can access the data within the views
                Toast.makeText(context, title.getText(), Toast.LENGTH_SHORT).show();
            }
        }
    }



    public TaskAdapter(List<Task> tasksList) {
        this.tasksList = tasksList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Task task = tasksList.get(position);
        holder.title.setText(task.getTitle());
        //holder.details.setText(task.getDetails());
    }

    @Override
    public int getItemCount() {
        return tasksList.size();
    }



}
