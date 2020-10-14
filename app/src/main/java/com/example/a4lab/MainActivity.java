package com.example.a4lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    final String NAMEVALUE = "namePerson";
    final  String SURNAMEVALUE = "surnamePerson";
    final String AGEVALUE = "agePerson";
    final String CURSEVALUE = "cursePerson";
    final String EMAILVALUE = "emailPerson";
    final String PHONEVALUE = "phonePerson";
    final String NETWORKVALUE = "networkPerson";
    final String PHOTOVALUE = "photoPerson";

    EditText name,surname,age,curse,email,phone,networkReference;
    ImageView photo;
    String str;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.nameId);
        surname = (EditText) findViewById(R.id.surnameId);
        age = (EditText) findViewById(R.id.ageId);
        curse = (EditText) findViewById(R.id.curseId);
        email = (EditText) findViewById(R.id.emailId);
        phone = (EditText) findViewById(R.id.phoneId);
        networkReference = (EditText) findViewById(R.id.networkId);
        photo = (ImageView) findViewById(R.id.photoId);
    }

    public void nextClick(View view) {
        EditText edit  = (EditText) findViewById(R.id.nameId);
        Intent intent = new Intent(MainActivity.this, ChooseActivity.class);
        intent.putExtra(NAMEVALUE,name.getText().toString());
        intent.putExtra(SURNAMEVALUE,surname.getText().toString());
        intent.putExtra(AGEVALUE,age.getText().toString());
        intent.putExtra(CURSEVALUE,curse.getText().toString());
        intent.putExtra(EMAILVALUE,email.getText().toString());
        intent.putExtra(PHONEVALUE,phone.getText().toString());
        intent.putExtra(NETWORKVALUE,networkReference.getText().toString());

        startActivity(intent);
    }

    public void setPicture(View view) {
        
    }
}