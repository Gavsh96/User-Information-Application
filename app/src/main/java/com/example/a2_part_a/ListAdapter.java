package com.example.a2_part_a;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListVH> {
    ListInterface listInterface;
    ArrayList<Person> person;

    public ListAdapter(ArrayList<Person> person,ListInterface listInterface)
    {
        this.listInterface = listInterface;
        this.person = person;
    }

    @NonNull
    @Override
    public ListVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater lI = LayoutInflater.from(parent.getContext());
        View v = lI.inflate(R.layout.list, parent, false);
        ListVH vh =new ListVH(v, listInterface);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ListVH holder, int position) {
        holder.textView.setText(person.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return person.size();
    }
}
