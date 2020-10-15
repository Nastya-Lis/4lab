package com.example.a4lab.educationManager;

import android.util.Log;

import com.example.a4lab.units.Listener;
import com.example.a4lab.units.Person;
import com.example.a4lab.units.PersonForJSON;
import com.example.a4lab.units.Student;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonManipulations {
    ObjectMapper objectMapper;
    PersonForJSON persons;

    public boolean isFileExists(File file) {
        if(file!=null) {
            if (!file.exists()) {
                try {
                    file.createNewFile();
                    Log.i("Log_json", "File has been just created");
                    return true;
                } catch (IOException e) {
                    Log.i("Log_json", "File is not created. Lox");
                    return false;
                }
            }
            else {
                //file.delete();
                Log.i("Log_json", "File exists");
                return true;
            }
        }
        else{
            Log.i("Log_json","File == null");
            return false;
        }

    }

    public void serializationToJson(File file, Person person){

      if(isFileExists(file)){
          objectMapper = new ObjectMapper();

          try {

              persons = getFromFileList(file);
              if(persons == null){
                  persons = new PersonForJSON();
                  persons.listPerson = new ArrayList<>();
              }
              persons.listPerson.add(person);
              objectMapper.writerWithDefaultPrettyPrinter().writeValue(file,persons);
           /*   if(person instanceof Student) {
                  persons = getFromFileList(file);
                  //new ArrayList<Person>();
                  if(personList == null)
                      personList = new ArrayList<>();
                  personList.add((Student)person);
                  objectMapper.writerWithDefaultPrettyPrinter().writeValue(file,
                          personList);
              }
              else if(person instanceof Listener){
                  persons = getFromFileList(file);
                  if(personList == null)
                      personList = new ArrayList<>();
                  personList.add((Listener)person);
                  objectMapper.writerWithDefaultPrettyPrinter().writeValue(file,
                          personList);
              }*/
          }
          catch (IOException e) {
             Log.i("Log_json","Oops, your serialization doesn't work");
          }
      }

    }

    private PersonForJSON getFromFileList(File file){
        if(file!=null){
            try {
                objectMapper = new ObjectMapper();
                persons = objectMapper.readValue(file,PersonForJSON.class);
                return persons;
            } catch (IOException e) {
                Log.i("Log_json","Couldn't read file");
                return null;
            }
        }
        else{
            return null;
        }
    }

    public PersonForJSON deserializationFromJson(File file) {
    /*    if(file!=null){
            try {
               objectMapper = new ObjectMapper();
                Person person = objectMapper.readValue(file,Person.class);
                return person;
            } catch (IOException e) {
                Log.i("Log_json","Couldn't read file");
                return null;
            }
        }
        else{
            return null;
        }*/
      return getFromFileList(file);
    }
}
