package com.example.a4lab.educationManager;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a4lab.MainActivity;
import com.example.a4lab.units.Listener;
import com.example.a4lab.units.Person;
import com.example.a4lab.units.Student;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class JsonManipulations {
    ObjectMapper objectMapper;

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
              if(person instanceof Student)
              objectMapper.writerWithDefaultPrettyPrinter().writeValue(file,(Student)person);
              else if(person instanceof Listener){
                  objectMapper.writerWithDefaultPrettyPrinter().writeValue(file,(Listener)person);
              }
          }
          catch (IOException e) {
             Log.i("Log_json","Oops, your serialization doesn't work");
          }
      }

    }

   /* public Person deserializationFromJson(File file){
        if(file!=null){
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
        }
    }*/
}
