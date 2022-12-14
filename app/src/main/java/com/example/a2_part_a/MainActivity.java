package com.example.a2_part_a;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager frag = getSupportFragmentManager();
        ListFragment lF = (ListFragment) frag.findFragmentById(R.id.fragment);
        lF = new ListFragment();
        frag.beginTransaction().add(R.id.fragment, lF).commit();

    }
}