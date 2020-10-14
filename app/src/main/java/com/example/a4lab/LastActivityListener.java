package com.example.a4lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a4lab.educationManager.Manager;
import com.example.a4lab.units.Listener;
import com.example.a4lab.units.Student;

import java.io.File;

public class LastActivityListener extends AppCompatActivity {

    TextView name,surname,age,organization,curse;
    Intent intent;
    Bundle bundle;
    Listener listener1;
    Manager manager = new Manager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.last_activity_listener);

        name = (TextView) findViewById(R.id.nameIdListener);
        surname = (TextView) findViewById(R.id.surnameIdListener);
        age = (TextView) findViewById(R.id.ageIdListener);
        curse = (TextView) findViewById(R.id.curseIdListener);
        organization = (TextView) findViewById(R.id.organizationIdListener);

        String FILENAME = "mineJson.json";
        File file = new File(super.getFilesDir(),FILENAME);
        manager.takeFileFromActivity(file);

        show();

        installListenerForButton();
    }

    private void show(){
        bundle = getIntent().getExtras();
        listener1 = (Listener) bundle.getSerializable(Listener.class.getSimpleName());
        name.setText(listener1.getName());
        surname.setText(listener1.getSurName());
        age.setText(String.valueOf(listener1.getAge()));
        curse.setText(listener1.getCurse());
        organization.setText(listener1.getOrganization());
    }
    private void installListenerForButton(){

        final View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                switch(view.getId()){
                    case R.id.backLastActivityListener:
                     finish();
                        break;
                    case R.id.saveLastActivityListener:
                        manager.serialize(listener1);
                        Toast.makeText
                                (getApplicationContext(), "Writing in file is successfully done",
                                        Toast.LENGTH_LONG).show();
                        break;

                }
            }
        };

        Button btn1 = (Button) findViewById(R.id.backLastActivityListener);
        Button btn2 = (Button) findViewById(R.id.saveLastActivityListener);
        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);


    }
}