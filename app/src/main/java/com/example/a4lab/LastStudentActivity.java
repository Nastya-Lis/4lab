package com.example.a4lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a4lab.educationManager.Manager;
import com.example.a4lab.units.Student;

import java.io.File;

public class LastStudentActivity extends AppCompatActivity {

    TextView name,surname,age,mark,university,curse,email,phone,network;
    ImageView photo;
    //Intent intent;
    Bundle bundle;
    Student student;
    Manager manager = new Manager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.last_activity_student);
        name = (TextView) findViewById(R.id.nameIdStudent);
        surname = (TextView) findViewById(R.id.surnameIdStudent);
        age = (TextView) findViewById(R.id.ageIdStudent);
        curse = (TextView) findViewById(R.id.curseIdStudent);
        university = (TextView) findViewById(R.id.universityIdStudent);
        mark = (TextView) findViewById(R.id.markIdStudent);
        email = (TextView) findViewById(R.id.emailIdStudent);
        phone = (TextView) findViewById(R.id.phoneIdStudent);
        network =(TextView) findViewById(R.id.networkIdStudent);

        String FILENAME = "mineJson.json";
        File file = new File(super.getFilesDir(),FILENAME);
        manager.takeFileFromActivity(file);

        show();

        installListenerForButton();
    }

    private void show(){
        bundle = getIntent().getExtras();
        student = (Student) bundle.getSerializable(Student.class.getSimpleName());
        name.setText(student.getName());
        surname.setText(student.getSurName());
        age.setText(String.valueOf(student.getAge()));
        curse.setText(student.getCurse());
        university.setText(student.getUniversity());
        mark.setText(String.valueOf(student.getMark()));
        email.setText(String.valueOf(student.getEmail()));
        phone.setText(String.valueOf(student.getPhone()));
        network.setText(String.valueOf(student.getNetworkReff()));
    }

    private void installListenerForButton(){

        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                switch(view.getId()){
                    case R.id.backLastActivityStudent:
                       finish();
                        break;
                    case R.id.saveLastActivityStudent:
                        manager.serialize(student);
                        Toast.makeText
                                (getApplicationContext(), "Writing in file is successfully done",
                                        Toast.LENGTH_LONG).show();
                        break;

                }
            }
        };

        Button btn1 = (Button) findViewById(R.id.backLastActivityStudent);
        Button btn2 = (Button) findViewById(R.id.saveLastActivityStudent);
        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);


    }
}