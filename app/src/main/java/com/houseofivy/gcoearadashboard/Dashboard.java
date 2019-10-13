package com.houseofivy.gcoearadashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Set the Recycler to the defined view
        setRecycler();
    }


    public void setRecycler(){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView.Adapter adapter = new PostAdapter();
        RecyclerView view = findViewById(R.id.recycler_posts);
        view.setLayoutManager(layoutManager);
        view.setAdapter(adapter);
    }
}
