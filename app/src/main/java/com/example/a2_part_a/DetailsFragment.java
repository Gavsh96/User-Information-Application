package com.example.a2_part_a;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsFragment extends Fragment {

    private String personId;
    private TextView id, name, username, email, address, phone, website, company;
    private Button loadPostBt;
    private ArrayList<Person> personList;
    JSONPlaceholder jp;
    private Iterator<Person> itP;
    private ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_details, container, false);
        personId = getArguments().getString("id");

        id = v.findViewById(R.id.personID);
        name = v.findViewById(R.id.personName);
        username = v.findViewById(R.id.personUsername);
        email = v.findViewById(R.id.personEmail);
        address = v.findViewById(R.id.personAddress);
        phone = v.findViewById(R.id.personPhone);
        website = v.findViewById(R.id.personWebsite);
        company = v.findViewById(R.id.personCompany);
        loadPostBt = v.findViewById(R.id.postBt);
        progressBar = v.findViewById(R.id.progressBarId);
        progressBar.setVisibility(View.VISIBLE);

        Retrofit rf = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jp = rf.create(JSONPlaceholder.class);
        getPerson();
        progressBar.setVisibility(View.INVISIBLE);

        loadPostBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new PostFragment();
                Bundle args = new Bundle();
                args.putString("id",personId);
                fragment.setArguments(args);
                FragmentManager frag = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = frag.beginTransaction();
                ft.replace(R.id.fragment, fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        return v;

    }

    public void getPerson()
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
                Person person = searchPerson(personId);
                id.setText("ID: "+person.getId());
                name.setText("Name: "+person.getName());
                username.setText("Username: "+person.getUsername());
                email.setText("Email: "+person.getEmail());
                address.setText("Address: "+person.getAddress().getStreet()+" "+person.getAddress().getSuite()+" "+person.getAddress().getCity()+" "+person.getAddress().getZipcode()+" "+person.getAddress().getGeo().getLat()+" "+person.getAddress().getGeo().getLng());
                phone.setText("Phone: "+person.getPhone());
                website.setText("Website: "+person.getWebsite());
                company.setText("Company: "+person.getCompany().name+" "+person.getCompany().catchPhrase+" "+person.getCompany().bs);

            }

            @Override
            public void onFailure(Call<ArrayList<Person>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Person searchPerson(String PersonID)
    {
        itP = personList.iterator();

        while (itP.hasNext())
        {
            Person p = itP.next();
            if(p.getId().equals(personId))
            {
                return p;
            }
        }
        return null;
    }
}