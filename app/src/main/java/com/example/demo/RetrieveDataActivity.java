package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Display;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class RetrieveDataActivity extends AppCompatActivity {
ListView lvManga;
List<Model> mangaList;
DatabaseReference mangaDBRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_data);

        lvManga = findViewById(R.id.lvManga);
        mangaList = new ArrayList<>();

        mangaDBRef = FirebaseDatabase.getInstance().getReference("Data");
        mangaDBRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mangaList.clear();

                for(DataSnapshot mangaDatasnap : dataSnapshot.getChildren()){
                    Model model = mangaDatasnap.getValue(Model.class);
                    mangaList.add(model);
                }
                ListAdapter adapter = new ListAdapter(RetrieveDataActivity.this,mangaList);
                lvManga.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
//`````````````````````

        //``````````````````````````````
    }
}