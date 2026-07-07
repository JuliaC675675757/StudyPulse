package com.example.studypulse;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class TasksActivity extends AppCompatActivity {


    EditText edtTask;
    Button btnAddTask;
    ListView listTasks;
    ImageButton btnBack;


    ArrayList<String> taskList;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tasks);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {

            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            v.setPadding(
                    systemBars.left,
                    systemBars.top,
                    systemBars.right,
                    systemBars.bottom
            );

            return insets;
        });


        // Connect XML
        edtTask = findViewById(R.id.edtTask);
        btnAddTask = findViewById(R.id.btnAddTask);
        listTasks = findViewById(R.id.listTasks);
        btnBack = findViewById(R.id.btnBack);



        // Back button
        btnBack.setOnClickListener(v -> {

            finish();

        });



        taskList = new ArrayList<>();


        // Default Tasks
        taskList.add("Review Chapter 5");
        taskList.add("Finish Android Project");
        taskList.add("Prepare Presentation");
        taskList.add("Submit Assignment");



        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                taskList
        );


        listTasks.setAdapter(adapter);



        // Add Task Button
        btnAddTask.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                String task = edtTask.getText()
                        .toString()
                        .trim();


                if(task.isEmpty()){


                    Toast.makeText(
                            TasksActivity.this,
                            "Enter a task",
                            Toast.LENGTH_SHORT
                    ).show();


                    return;

                }



                taskList.add(task);

                adapter.notifyDataSetChanged();

                edtTask.setText("");



                Toast.makeText(
                        TasksActivity.this,
                        "Task Added",
                        Toast.LENGTH_SHORT
                ).show();


            }

        });



        // Click Task -> Study Session
        listTasks.setOnItemClickListener((parent, view, position, id) -> {


            String selectedTask = taskList.get(position);


            Intent intent = new Intent(
                    TasksActivity.this,
                    StudySessionActivity.class
            );


            intent.putExtra(
                    "TASK_NAME",
                    selectedTask
            );


            startActivity(intent);


        });


    }

}