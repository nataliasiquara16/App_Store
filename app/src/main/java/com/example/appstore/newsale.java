package com.example.appstore;


import android.graphics.ColorSpace;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// configuração do arquivo java
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appstore.Model.salesmodel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.UUID;

public class newsale extends AppCompatActivity {
    private EditText edit_name;
    private EditText edit_product;
    private EditText edit_value;
    private Button btn_register;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    // conectando o arquivo java com o XML.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newsale);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        // conectando variavel com o id
        edit_name = findViewById(R.id.edit_name);
        edit_product = findViewById(R.id.edit_product);
        edit_value = findViewById(R.id.edit_value);
        btn_register = findViewById(R.id.btn_register);

        // registrando os valores ao botão ser clicado
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getName = edit_name.getText().toString();
                String getProduct = edit_product.getText().toString();
                String getValue = edit_value.getText().toString();

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("Name", getName);
                hashMap.put("Product", getProduct);
                hashMap.put("Value", getValue);

                // salvando no firebase
                databaseReference.child("Users").child(getName).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(newsale.this, "Sale added successfully", Toast.LENGTH_SHORT);
                    }
                });
            }
        });
    }
}
