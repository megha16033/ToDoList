package com.example.mnrr.whattodolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static List<Task> tasksList = new ArrayList<>();
    private List<String> tasksTitle = new ArrayList<>();
    private RecyclerView recyclerView;
    private TaskAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // getSupportActionBar().hide();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tasksList = new TaskDatabase(this).getAllTasks();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new TaskAdapter(tasksList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.addOnItemTouchListener(new RecyclerClickListener(getApplicationContext(), recyclerView, new RecyclerClickListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                // Task task = tasksList.get(position);


                Intent displayIntent = new Intent(getApplicationContext(), DisplayDetailsActivity.class);
                displayIntent.putExtra("position", position);
                startActivity(displayIntent);
                // Toast.makeText(getApplicationContext(), task.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        recyclerView.setAdapter(mAdapter);

        //prepareTaskData();
        System.out.println("list of tasks is:" + tasksList);
        mAdapter.notifyDataSetChanged();
    }

    /*
        Displays task list
     */
    private void prepareTaskData() {

        Task task = new Task();
        tasksList = new TaskDatabase(this).getAllTasks();
        System.out.println("list of tasks is:" + tasksList);
//        for (int i=0; i<tasksTitle.size(); i++) {
//            System.out.println("title is:" + tasksTitle.get(i));
//            task = new Task(tasksTitle.get(i));
//            tasksList.add(task);
//        }
//        while(tasks!=null)
//        {
//            String[] separated = tasks.split("\n");
//            System.out.println("separated[0]" + separated[0] + " separated[1]" + separated[1] + "separated[2]" + separated[2]);
//        }
//        for(int i=0;i<separated.length; ++i)
//            System.out.println("on main screen title is:" + separated[i]);

//        task = new Task("Mad Max: Fury Road", "Action & Adventure");
//        tasksList.add(task);
//
//        task = new Task("Mad Max: Fury Road", "Action & Adventure");
//        tasksList.add(task);
//
//        task = new Task("Mad Max: Fury Road", "Action & Adventure");
//        tasksList.add(task);
//
//        task = new Task("Mad Max: Fury Road", "Action & Adventure");
//        tasksList.add(task);
//
//        task = new Task("Mad Max: Fury Road", "Action & Adventure");
//        tasksList.add(task);
//
//        task = new Task("Mad Max: Fury Road", "Action & Adventure");
//        tasksList.add(task);
//
//        task = new Task("Mad Max: Fury Road", "Action & Adventure");
//        tasksList.add(task);
//
//        task = new Task("Mad Max: Fury Road", "Action & Adventure");
//        tasksList.add(task);
//
//        task = new Task("Mad Max: Fury Road", "Action & Adventure");
//        tasksList.add(task);
//
//        task = new Task("Mad Max: Fury Road", "Action & Adventure");
//        tasksList.add(task);
//
//        task = new Task("Mad Max: Fury Road", "Action & Adventure");
//        tasksList.add(task);

        mAdapter.notifyDataSetChanged();
    }

    /*
        Called on clicking add button
     */
    public void onAdd( View view)
    {
        Intent addTaskIntent = new Intent(this, AddTaskActivity.class);
        startActivity(addTaskIntent);
    }

    //
    public void onBack(View view) {
        Toast.makeText(this,"HEY HARRY",Toast.LENGTH_SHORT).show();
    }

}