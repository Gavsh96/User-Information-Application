package com.example.a2_part_a;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ListFragment extends Fragment implements ListInterface {

    private RecyclerView rv;
    JSONPlaceholder jp;
    private ArrayList<Person> personList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        rv = v.findViewById(R.id.ListRecyclerView);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        Retrofit rf = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jp = rf.create(JSONPlaceholder.class);
        getPerson(this);
        return v;
    }

    private void getPerson(ListInterface listInterface)
    {
        Call<ArrayList<Person>> call = jp.getPerson();
        call.enqueue(new Callback<ArrayList<Person>>() {
            @Override
            public void onResponse(Call<ArrayList<Person>> call, Response<ArrayList<Person>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                personList = response.body();
                ListAdapter adapter = new ListAdapter(personList, listInterface);
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<ArrayList<Person>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Fragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString("id",personList.get(position).getId());
        fragment.setArguments(args);
        FragmentManager frag = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = frag.beginTransaction();
        ft.replace(R.id.fragment, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}