package com.example.a4lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.a4lab.units.Listener;
import com.example.a4lab.units.Person;
import com.example.a4lab.units.Student;

public class ChooseActivity extends AppCompatActivity {
    Intent intent;
    Person person;
    Intent choosableIntent;

    final String NAMEVALUE = "namePerson";
    final  String SURNAMEVALUE = "surnamePerson";
    final String AGEVALUE = "agePerson";
    final String CURSEVALUE = "cursePerson";
    final String EMAILVALUE = "emailPerson";
    final String PHONEVALUE = "phonePerson";
    final String NETWORKVALUE = "networkPerson";
    final String PHOTOVALUE = "photoPerson";

   // final String PERSONOBJECT = "person";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        installListenerForButton();
    }


    private void installListenerForButton(){
        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                switch(view.getId()){
                    case R.id.backSecondActivity:
                       finish();
                        break;
                    case R.id.nextSecondActivity:
                        choosableIntent = chooseRadio();
                        if(choosableIntent!=null)
                        startActivity(choosableIntent);
                        break;

                }
            }
        };

        Button btn1 = (Button) findViewById(R.id.backSecondActivity);
        Button btn2 = (Button) findViewById(R.id.nextSecondActivity);
        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);


    }

    private Intent chooseRadio(){
        RadioGroup radios = (RadioGroup) findViewById(R.id.radios);
        RadioButton btn1 = (RadioButton) findViewById(R.id.studentRadio);
        RadioButton btn2 = (RadioButton) findViewById(R.id.listenerRadio);
        Bundle bundle  = getIntent().getExtras();
        if(radios.getCheckedRadioButtonId() != -1){
            int selectedId = radios.getCheckedRadioButtonId();
            if(btn1.getId() == selectedId){
                person = new Student();
                person.setName(bundle.get(NAMEVALUE).toString());
                person.setSurName(bundle.get(SURNAMEVALUE).toString());
                person.setAge( Integer.valueOf(bundle.get(AGEVALUE).toString()));
                person.setCurse(bundle.get(CURSEVALUE).toString());
                person.setEmail(bundle.get(EMAILVALUE).toString());
                person.setPhone(bundle.get(PHONEVALUE).toString());
                person.setNetworkReff(bundle.get(NETWORKVALUE).toString());

                choosableIntent  =
                        new Intent(ChooseActivity.this,StudentActivity.class);
                choosableIntent.putExtra(Student.class.getSimpleName(),person);
                return choosableIntent;
            }
            else if(btn2.getId() == selectedId){
                person = new Listener();
                person.setName(bundle.get(NAMEVALUE).toString());
                person.setSurName(bundle.get(SURNAMEVALUE).toString());
                person.setAge(Integer.valueOf(bundle.get(AGEVALUE).toString()));
                person.setCurse(bundle.get(CURSEVALUE).toString());
                person.setEmail(bundle.get(EMAILVALUE).toString());
                person.setPhone(bundle.get(PHONEVALUE).toString());
                person.setNetworkReff(bundle.get(NETWORKVALUE).toString());

                choosableIntent  =
                        new Intent(ChooseActivity.this,ListenerActivity.class);
                choosableIntent.putExtra(Listener.class.getSimpleName(),person);
                return choosableIntent;
            }
            else
                return null;
        }
        else
            return null;
    }

}