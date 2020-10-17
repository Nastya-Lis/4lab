package com.example.a4lab;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.a4lab.units.Listener;
import com.example.a4lab.units.Person;
import com.example.a4lab.units.Student;

public class CurrentPersonActivity extends AppCompatActivity {

    TextView name,surname,age,curse,uniAndOrgan,mark,
    uniandOrganHint,markHint,
            email,phone,network;

    final String university = "University:";
    final String organization = "Organization:";

    //ImageView photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_person);

        name = (TextView) findViewById(R.id.nameIdCurrent);
        surname =(TextView) findViewById(R.id.surnameIdCurrent);
        age =(TextView) findViewById(R.id.ageIdCurrent);
        curse =(TextView) findViewById(R.id.curseIdCurrent);
        uniAndOrgan =(TextView) findViewById(R.id.universityAndOrganizationIdCurrent);
        mark = (TextView) findViewById(R.id.markIdCurrent);
        uniandOrganHint = (TextView) findViewById(R.id.uniOrgHintId);
        markHint = (TextView) findViewById(R.id.markHintId);

        activateVisible();
        setDataInView();
    }

    private Person getCurrentPersonFromPreviousActivity(){
        Bundle bundle = getIntent().getExtras();
        Person person = (Person) bundle.getSerializable(Person.class.getSimpleName());
        return person;
    }

    private PersonInstances checkInstance(){
        Person checkInstancePerson = getCurrentPersonFromPreviousActivity();
        if(checkInstancePerson instanceof Student)
            return PersonInstances.STUDENT;
        else if(checkInstancePerson instanceof Listener)
            return PersonInstances.LISTENER;
        else
            return PersonInstances.NANOFTHEM;
    }

    private void activateVisible(){
        PersonInstances personInstances = checkInstance();
        if(personInstances == PersonInstances.STUDENT){

            if(markHint.getVisibility() == View.INVISIBLE
                    && mark.getVisibility() == View.INVISIBLE) {
                mark.setVisibility(View.VISIBLE);
                markHint.setVisibility(View.VISIBLE);
            }
            if(uniandOrganHint.getHint() == organization)
                uniandOrganHint.setHint(university);
        }
        else if(personInstances == PersonInstances.LISTENER){

            if(markHint.getVisibility() == View.VISIBLE && mark.getVisibility() == View.VISIBLE) {
                mark.setVisibility(View.INVISIBLE);
                markHint.setVisibility(View.INVISIBLE);
            }
            if(uniAndOrgan.getText() == university)
                uniAndOrgan.setText(organization);
        }
    }

    private void setDataInView(){

        PersonInstances personInstances = checkInstance();
        Person person = getCurrentPersonFromPreviousActivity();

        name.setText(person.getName());
        surname.setText(person.getSurName());
        age.setText(String.valueOf(person.getAge()));
        curse.setText(person.getCurse());
        if(personInstances == PersonInstances.STUDENT){
            Student student = (Student) person;
            uniAndOrgan.setText(student.getUniversity());
            mark.setText(String.valueOf(student.getMark()));
        }
        if(personInstances == PersonInstances.LISTENER)
        {
            Listener listener = (Listener) person;
            uniAndOrgan.setText(listener.getOrganization());
        }

    }

    private enum PersonInstances{
        STUDENT,LISTENER,NANOFTHEM;
    }
}