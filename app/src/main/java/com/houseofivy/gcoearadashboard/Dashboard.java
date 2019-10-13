package com.houseofivy.gcoearadashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {

    Button createPost;
    private List<PostModel> posts;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Initialise views to be used
        init();

        //Set the Recycler to the defined view
        setRecycler();
    }

    @Override
    protected void onStart() {
        super.onStart();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                posts.clear();
                 for (DataSnapshot post : dataSnapshot.getChildren()){
                     PostModel newPost = post.getValue(PostModel.class);
                     posts.add(newPost);
                 }

                 setRecycler();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(Dashboard.this, "Seems that your internet is not working", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void init(){
        createPost = findViewById(R.id.btn_create_post);
        posts = new ArrayList<>();
        myRef = FirebaseDatabase.getInstance().getReference("posts");
        createPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this,PostCreator.class));
            }
        });
    }


    private void setRecycler(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.Adapter adapter = new PostAdapter(this,posts);
        RecyclerView view = findViewById(R.id.recycler_posts);
        view.setLayoutManager(layoutManager);
        view.setAdapter(adapter);
    }
}
