package com.example.a2_part_a;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PostVH extends RecyclerView.ViewHolder{
    TextView personId, postId, postTitle, postBody;

    public PostVH(@NonNull View itemView) {
        super(itemView);

        personId = itemView.findViewById(R.id.personID);
        postId = itemView.findViewById(R.id.postID);
        postTitle = itemView.findViewById(R.id.titleID);
        postBody = itemView.findViewById(R.id.bodyID);
    }
}
