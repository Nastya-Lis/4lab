package com.example.a4lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.a4lab.units.Student;

public class StudentActivity extends AppCompatActivity {

    Intent intent;
    Student student;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        installListenerForButton();
    }

    private void installListenerForButton(){

        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                switch(view.getId()){
                    case R.id.backStudentActivity:
                       finish();
                        break;
                    case R.id.nextStudentActivity:
                        Student sendStudent = setStudentData();
                        sendStudent.setUniversity(
                                ((EditText) findViewById(R.id.universityId)).getText().toString());
                        sendStudent.setMark(
                                Integer.valueOf( ((EditText) findViewById(R.id.markId))
                                        .getText().toString() ));
                        intent = new Intent(StudentActivity.this,
                                LastStudentActivity.class);
                        intent.putExtra(Student.class.getSimpleName(), student);
                        startActivity(intent);
                        break;

                }
            }
        };

        Button btn1 = (Button) findViewById(R.id.backStudentActivity);
        Button btn2 = (Button) findViewById(R.id.nextStudentActivity);
        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);


    }

    private Student setStudentData(){
        bundle = getIntent().getExtras();
        student = (Student)  bundle.getSerializable(Student.class.getSimpleName());
        return student;
    }
}