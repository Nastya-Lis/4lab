package com.example.a4lab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.a4lab.units.Listener;
import com.example.a4lab.units.Student;

public class ListenerActivity extends AppCompatActivity {

    Listener listener;
    Listener sendListener;
    Bundle bundle;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listener);
        installListenerForButton();
    }

    private Listener setListenerData(){
        bundle = getIntent().getExtras();
        listener = (Listener)  bundle.getSerializable(Listener.class.getSimpleName());
        return listener;
    }

    private void installListenerForButton(){

        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                switch(view.getId()){
                    case R.id.backListenerActivity:
                        finish();
                        break;
                    case R.id.nextListenerActivity:
                        sendListener = setListenerData();
                        sendListener.setOrganization(
                                ((EditText) findViewById(R.id.organizationId)).getText().toString());
                        intent = new Intent(ListenerActivity.this,
                                LastActivityListener.class);
                        intent.putExtra(Listener.class.getSimpleName(),sendListener);
                        startActivity(intent);
                        break;

                }
            }
        };

        Button btn1 = (Button) findViewById(R.id.backListenerActivity);
        Button btn2 = (Button) findViewById(R.id.nextListenerActivity);
        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);


    }
}