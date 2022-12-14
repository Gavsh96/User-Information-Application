package com.example.a2_part_a;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PostAdapter extends RecyclerView.Adapter<PostVH>{
    ArrayList<Post> post;

    public PostAdapter(ArrayList<Post> post)
    {
        this.post = post;
    }

    @NonNull
    @Override
    public PostVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater lI = LayoutInflater.from(parent.getContext());
        View v = lI.inflate(R.layout.postlist, parent, false);
        PostVH vh =new PostVH(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PostVH holder, int position) {
        holder.personId.setText("ID: "+post.get(position).getUserId());
        holder.postId.setText("Post ID: "+post.get(position).getId());
        holder.postTitle.setText("Title: "+post.get(position).getTitle());
        holder.postBody.setText("Body: "+post.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return post.size();
    }
}
