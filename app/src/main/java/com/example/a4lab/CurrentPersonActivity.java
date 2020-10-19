package com.example.a4lab;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a4lab.units.Listener;
import com.example.a4lab.units.Person;
import com.example.a4lab.units.Student;

public class CurrentPersonActivity extends AppCompatActivity {

    TextView name,surname,age,curse,uniAndOrgan,mark,
    uniandOrganHint,markHint;

    final String university = "University:";
    final String organization = "Organization:";


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
            if(uniandOrganHint.getText().equals(organization))
                uniandOrganHint.setText(university);
            else
                uniAndOrgan.setText(organization);
        }
        else if(personInstances == PersonInstances.LISTENER){

            if(markHint.getVisibility() == View.VISIBLE && mark.getVisibility() == View.VISIBLE) {
                mark.setVisibility(View.INVISIBLE);
                markHint.setVisibility(View.INVISIBLE);
            }
            if(uniAndOrgan.getText().equals(university))
                uniAndOrgan.setText(organization);
            else
                uniAndOrgan.setText(university);
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



    public void emailCross(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO,Uri.parse("mailto:"
                + getCurrentPersonFromPreviousActivity().getEmail()));
        startActivity(intent);
    }

    public void phoneCross(View view) {
        String telephone = getCurrentPersonFromPreviousActivity().getPhone();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+ telephone));
        startActivity(intent);
    }

    public void photoCross(View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse(getCurrentPersonFromPreviousActivity().getPhotography()),
                "image/*");
        startActivity(intent);
    }


    public void networkCross(View view) {
        try{
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(getCurrentPersonFromPreviousActivity().getNetworkReff()));
            startActivity(intent);
        }
        catch(Exception e){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Error message").setMessage("Invalid reference").
                    setPositiveButton("Ok",null);
            alert.create().show();
        }
    }


    private enum PersonInstances{
        STUDENT,LISTENER,NANOFTHEM;
    }
}