package edu.miracostacollege.architecturecomponentsexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    NoteViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //reference to Recycler View
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        //need a Layout Manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //if the size won't change of the Recycler View
        recyclerView.setHasFixedSize(true);

        //Adapter - changes to final because accessing
        //from inner class
        final NoteAdapter adapter = new NoteAdapter();
        //pass the adapter - which is empty by default
        recyclerView.setAdapter(adapter);

        model = new NoteViewModel(this.getApplication());
        model.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                //update RecyclerView using adapter
                // //every time the list changes and pass
                //the notes we created and update the view
                adapter.setNotes(notes);
                Toast.makeText(MainActivity.this, "It works??", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
