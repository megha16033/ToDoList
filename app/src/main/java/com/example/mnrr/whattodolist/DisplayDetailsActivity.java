package com.example.mnrr.whattodolist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class DisplayDetailsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_details);
        int position =0;
        ViewPager mViewPager  = (ViewPager) findViewById(R.id.pager);

        Bundle basket = getIntent().getExtras();
        if (basket != null) {
            position = basket.getInt("position");
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("");

        mViewPager.setAdapter(new CustomPagerAdapter(this));
        mViewPager.setCurrentItem(position);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_display_details, menu);
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

    //Returning to home page
    public void onBack(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    //Function for deleting task
    public void onDelete(View view)
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure,You want to delete this task?");

        alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                String taskTitle = ((TextView) findViewById(R.id.title)).getText().toString();
                System.out.println("task title:" + taskTitle);

                String taskDesc = ((TextView) findViewById(R.id.details)).getText().toString();
                System.out.println("task title:" + taskDesc);

                new TaskDatabase(getApplicationContext()).taskDelete(taskTitle, taskDesc);
                Toast.makeText(getApplicationContext(), "Task has been deleted successfully!!!", Toast.LENGTH_SHORT).show();

                ((TextView) findViewById(R.id.title)).setText("");
                ((TextView) findViewById(R.id.details)).setText("");

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              //  finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
