package com.example.a4lab;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.a4lab.recyclerViewPack.PersonAdapter;
import com.example.a4lab.units.Person;
import com.example.a4lab.units.PersonForJSON;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Bundle bundle = getIntent().getExtras();
        PersonForJSON personForJSON =(PersonForJSON)
                bundle.getSerializable(PersonForJSON.class.getSimpleName());
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.myRecycler);
        recyclerView.setAdapter(new PersonAdapter(personForJSON,getApplicationContext()));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}