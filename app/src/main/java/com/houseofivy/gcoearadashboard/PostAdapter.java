package com.houseofivy.gcoearadashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_recycler, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //TODO: Change the data dynamically here

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView avatar;
        TextView user_name;
        TextView user_post;
        TextView post_description;
        ImageView post;

        ViewHolder(View itemView){
            super(itemView);

            avatar = (ImageView) itemView.findViewById(R.id.profile_image);
            user_name = (TextView) itemView.findViewById(R.id.profile_name);
            user_post = (TextView) itemView.findViewById(R.id.profile_post);
            post_description = (TextView) itemView.findViewById(R.id.post_description);
            post = (ImageView) itemView.findViewById(R.id.post_image);
        }
    }
}
