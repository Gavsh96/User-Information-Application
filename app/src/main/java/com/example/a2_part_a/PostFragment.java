package com.example.a2_part_a;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PostFragment extends Fragment {
    private RecyclerView rv;
    JSONPlaceholder jp;
    private ArrayList<Post> postList;
    private ArrayList<Post> postList2;
    private Iterator<Post> itP;
    String personId;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_post, container, false);
        personId = getArguments().getString("id");
        rv = v.findViewById(R.id.PostRecyclerView);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        Retrofit rf = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jp = rf.create(JSONPlaceholder.class);
        getPost();
        return v;
    }

    private void getPost()
    {
        Call<ArrayList<Post>> call = jp.getPost();
        call.enqueue(new Callback<ArrayList<Post>>() {
            @Override
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getContext(), response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                postList = response.body();
                makePostArr(personId, postList);
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage() , Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void makePostArr(String personId,ArrayList<Post> postListIn)
    {
        itP = postListIn.iterator();
        ArrayList<Post> newList = new ArrayList<>();

        while (itP.hasNext())
        {
            Post p = itP.next();
            if(p.getUserId().equals(personId))
            {
                newList.add(p);
            }
        }
        PostAdapter adapter = new PostAdapter(newList);
        rv.setAdapter(adapter);
    }
}