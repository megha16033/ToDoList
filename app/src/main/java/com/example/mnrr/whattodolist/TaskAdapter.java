package com.example.mnrr.whattodolist;

/**
 * Created by rohan on 01-11-2016.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

    private List<Task> tasksList = new ArrayList<Task>();

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title, details;
        public ImageButton doneButton;
        private Context context;



        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            details = (TextView) view.findViewById(R.id.details);
           // doneButton = (ImageButton) view.findViewById(R.id.deletebutton);
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
