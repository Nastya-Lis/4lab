package com.example.a4lab;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a4lab.educationManager.Manager;
import com.example.a4lab.units.Listener;
import com.example.a4lab.units.Person;
import com.example.a4lab.units.PersonForJSON;
import com.example.a4lab.units.Student;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LastActivityListener extends AppCompatActivity {

    TextView name,surname,age,organization,curse,email,phone,network;
    ImageView photo;
    //Intent intent;
    Bundle bundle;
    Listener listener1;
    Manager manager = new Manager();
    File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.last_activity_listener);

        name = (TextView) findViewById(R.id.nameIdListener);
        surname = (TextView) findViewById(R.id.surnameIdListener);
        age = (TextView) findViewById(R.id.ageIdListener);
        curse = (TextView) findViewById(R.id.curseIdListener);
        organization = (TextView) findViewById(R.id.organizationIdListener);
        email = (TextView) findViewById(R.id.emailIdListener);
        phone = (TextView) findViewById(R.id.phoneIdListener);
        network =(TextView) findViewById(R.id.networkIdListener);
        photo = (ImageView) findViewById(R.id.photographyIdListener);

        String FILENAME = "mineJson.json";
        file = new File(super.getFilesDir(),FILENAME);
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
        email.setText(String.valueOf(listener1.getEmail()));
        phone.setText(String.valueOf(listener1.getPhone()));
        network.setText(String.valueOf(listener1.getNetworkReff()));
        photo.setImageURI(Uri.parse(listener1.getPhotography()));
    }

    private AlertDialog.Builder showAllPerson(){
        if(file != null){
            PersonForJSON persons = manager.deserialize();
            StringBuilder allUsers = new StringBuilder();
            if(persons.listPerson.size()!=0){

                for (int i = 0 ; i < persons.listPerson.size();i++) {

                  allUsers.append(persons.listPerson.get(i).getName()+ " " +
                          persons.listPerson.get(i).getSurName() + " " +
                          persons.listPerson.get(i).getCurse() + "\n");
                }
            }

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("All users").setMessage(allUsers).
                    setPositiveButton("Ok",null);
            return alertDialog;
          //  alertDialog.create().show();

        }
        else{
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("All users").setMessage("Something is going wrong").
                    setPositiveButton("Ok",null);
            return alertDialog;
        }
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
                        AlertDialog.Builder alert = showAllPerson();
                        alert.show();
                        Toast.makeText
                                (getApplicationContext(), "Writing in file is successfully done",
                                        Toast.LENGTH_LONG).show();

                        break;
                    case R.id.listPersonListenerFrom:
                        PersonForJSON personForJSON = manager.deserialize();
                        Intent intent = new Intent(LastActivityListener.this,
                                ListActivity.class);
                        intent.putExtra(PersonForJSON.class.getSimpleName(),personForJSON);
                        startActivity(intent);
                        break;

                }
            }
        };

        Button btn1 = (Button) findViewById(R.id.backLastActivityListener);
        Button btn2 = (Button) findViewById(R.id.saveLastActivityListener);
        Button btn3 = (Button) findViewById(R.id.listPersonListenerFrom);

        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);
        btn3.setOnClickListener(listener);

    }
}