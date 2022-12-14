package com.example.a2_part_a;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListVH extends RecyclerView.ViewHolder {
    public TextView textView;

    public ListVH(@NonNull View itemView, ListInterface lI) {
        super(itemView);

        textView = itemView.findViewById(R.id.personName);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lI != null)
                {
                    int position = getAdapterPosition();

                    if(position != RecyclerView.NO_POSITION)
                    {
                        lI.onItemClick(position);
                    }
                }
            }
        });

    }
}
