package com.example.a4lab.recyclerViewPack;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a4lab.CurrentPersonActivity;
import com.example.a4lab.R;
import com.example.a4lab.units.Listener;
import com.example.a4lab.units.Person;
import com.example.a4lab.units.PersonForJSON;
import com.example.a4lab.units.Student;

import java.util.ArrayList;
import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder>{

    PersonForJSON personForJSON;
    Context context;

    public PersonAdapter(PersonForJSON personForJSON, Context context){
        this.personForJSON = personForJSON;
        this.context = context;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,surname,age,curse,instance;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.nameElementId);
            surname = (TextView) itemView.findViewById(R.id.surnameElementId);
            age = (TextView) itemView.findViewById(R.id.ageElementId);
            curse = (TextView) itemView.findViewById(R.id.curseElementId);
            instance = (TextView) itemView.findViewById(R.id.instanceElementId);
        }
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_example_template,
                parent,false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                Intent intent = new Intent(context, CurrentPersonActivity.class);
                int index = parent.indexOfChild(view1);
                Person personSend = personForJSON.listPerson.get(index);
                intent.putExtra(Person.class.getSimpleName(),personSend);
                context.startActivity(intent);
            }
        });
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ArrayList<Person> persons =(ArrayList<Person>) personForJSON.listPerson;
        Person person = persons.get(position);

        if(person instanceof Student)
            holder.instance.setText("Student");
        else if(person instanceof Listener)
            holder.instance.setText("Listener");

        holder.name.setText(person.getName());
        holder.surname.setText(person.getSurName());
        holder.age.setText(String.valueOf(person.getAge()));
        holder.curse.setText(person.getCurse());
    }

    @Override
    public int getItemCount() {
        return personForJSON.listPerson.size();
    }


}
