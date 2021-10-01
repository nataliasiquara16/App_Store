package com.example.appstore.setting;

import android.provider.ContactsContract;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

// classe para declarar as configurações do Firebase.

public class setFirebase {
    private static DatabaseReference database;

    public static DatabaseReference getFirebaseDatabase() {
        if (database==null){
            database = FirebaseDatabase.getInstance().getReference();
        }
        return database;
    }
}
