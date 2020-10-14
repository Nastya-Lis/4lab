package com.example.a4lab.educationManager;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.a4lab.units.Listener;
import com.example.a4lab.units.Person;
import com.example.a4lab.units.Student;



import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

public class Manager{

    File file;
    public JsonManipulations jsonManipulations = new JsonManipulations();

    public void serialize(Person person){
        if(file!=null)
            jsonManipulations.serializationToJson(file,person);
    }

 /*   public Person deserialize(){
        Person personReturn = jsonManipulations.deserializationFromJson(file);
        return personReturn;
    }*/

    public void takeFileFromActivity(File file){
        this.file = file;
    }

}
