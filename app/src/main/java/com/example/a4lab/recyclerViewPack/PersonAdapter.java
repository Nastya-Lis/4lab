package com.example.a4lab.recyclerViewPack;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a4lab.R;
import com.example.a4lab.units.Person;
import com.example.a4lab.units.PersonForJSON;

import java.util.ArrayList;
import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder>{

    PersonForJSON personForJSON;

    public PersonAdapter(PersonForJSON personForJSON){
        this.personForJSON = personForJSON;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_example_template,
                parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ArrayList<Person> persons =(ArrayList<Person>) personForJSON.listPerson;
        Person person = persons.get(position);

        holder.name.setText(person.getName());
        holder.surname.setText(person.getSurName());
        holder.age.setText(String.valueOf(person.getAge()));
        holder.curse.setText(person.getCurse());
    }

    @Override
    public int getItemCount() {
        return personForJSON.listPerson.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,surname,age,curse;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.nameElementId);
            surname = (TextView) itemView.findViewById(R.id.surnameElementId);
            age = (TextView) itemView.findViewById(R.id.ageElementId);
            curse = (TextView) itemView.findViewById(R.id.curseElementId);
        }
    }

}
