package edu.miracostacollege.architecturecomponentsexample;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    //Application is a subclass of Context, so we can use
    //that to pass for the Context in the getInstance()
    public NoteRepository(Application application){
        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        noteDao = noteDatabase.noteDao(); //this is the call to the
                                        //abstract method in our database class, for
                                        //which Room has generated all the methods

        allNotes = noteDao.getAllNotes();
    }

    //Here we will have to create these methods

    public void update(Note note){
        new UpdateNoteAsyncTask(noteDao).execute(note);
    }

    public void delete(Note note){
        new DeleteNoteAsyncTask(noteDao).execute(note);
    }

    public void insert(Note note){
        new InsertNoteAsyncTask(noteDao).execute(note);
    }

    public void deleteAllNotes() {
        new DeleteAllNoteAsyncTask(noteDao).execute();
    }

    //this one Room will take care for us because we called it in the constructor
    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }


    //to take care of the above methods, we need asyncTasks
    private static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao noteDao; //we need this to make database operations

        //constructor
        private InsertNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]); //this is the first notes in the varargs passed in
            //in this case, there is only 1, but it is still the first one
            return null;
        }
    }

    //to take care of the above methods, we need asyncTasks
    private static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao noteDao; //we need this to make database operations

        //constructor
        private DeleteNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]); //this is the first notes in the varargs passed in
            //in this case, there is only 1, but it is still the first one
            return null;
        }
    }


    //to take care of the above methods, we need asyncTasks
    private static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {

        private NoteDao noteDao; //we need this to make database operations

        //constructor
        private UpdateNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]); //this is the first notes in the varargs passed in
            //in this case, there is only 1, but it is still the first one
            return null;
        }
    }


    //to take care of the above methods, we need asyncTasks
    private static class DeleteAllNoteAsyncTask extends AsyncTask<Void, Void, Void> {

        private NoteDao noteDao; //we need this to make database operations

        //constructor
        private DeleteAllNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes(); //we don't pass anything into this
            return null;
        }
    }


}
