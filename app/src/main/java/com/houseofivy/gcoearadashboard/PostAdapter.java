package com.houseofivy.gcoearadashboard;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private Activity context;
    private List<PostModel> posts;
    private StorageReference photoReference;
    private final long ONE_MEGABYTE = 1024 * 1024;

    PostAdapter(Activity context, List<PostModel> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_recycler, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        //TODO: Change the data dynamically here
        holder.post_description.setText(posts.get(position).getPostDescription());
        photoReference = FirebaseStorage.getInstance().getReference().child("images_post/"+posts.get(position).getId());
        photoReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                holder.post.setImageBitmap(bmp);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(context, "Cannot Load Image for a few posts\n Using the default background instead", Toast.LENGTH_SHORT).show();
                holder.post.setImageResource(R.drawable.balloon);
            }
        });

    }

    @Override
    public int getItemCount() {
        return posts.size();
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
