package com.example.mnrr.whattodolist;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class AddTaskActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
        Function to send back to home page
     */
    public void onHomeTask(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    /*
    Adding a task with details in database
     */
    public void onAddTask(View view){
        String taskTitle = ((EditText) findViewById(R.id.titlename)).getText().toString();
        System.out.println("task title:" + taskTitle);

        String taskDesc = ((EditText) findViewById(R.id.taskdetails)).getText().toString();
        System.out.println("task description:" + taskDesc);
        if(taskTitle.equals("") || taskDesc.equals(""))
        {
            Toast.makeText(this, " Please enter both the data", Toast.LENGTH_SHORT).show();
        }
        else{
            int res = new TaskDatabase(this).addTask(new Task(taskTitle, taskDesc));
            System.out.println("value of res is:" + res);
            Toast.makeText(this, " Task added in the list", Toast.LENGTH_SHORT).show();

            ((EditText) findViewById(R.id.titlename)).setText("");
            ((EditText) findViewById(R.id.taskdetails)).setText("");

        }

        startActivity(new Intent(this, MainActivity.class));

    }



}

