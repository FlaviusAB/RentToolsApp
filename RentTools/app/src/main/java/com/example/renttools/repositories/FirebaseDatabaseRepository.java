package com.example.renttools.repositories;

import com.google.firebase.database.FirebaseDatabase;

public class FirebaseDatabaseRepository {
    private FirebaseDatabase databaseInstance;



    public FirebaseDatabaseRepository() {
        this.databaseInstance = FirebaseDatabase.getInstance("https://renttools-b4395-default-rtdb.europe-west1.firebasedatabase.app");

    }

    public FirebaseDatabase getDatabaseInstance() {
        return databaseInstance;
    }


}
