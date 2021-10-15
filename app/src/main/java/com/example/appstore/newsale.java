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
import com.example.appstore.setting.setFirebase;
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
    private salesmodel s;
    public HashMap<String,String> hash;



    // conectando o arquivo java com o XML.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newsale);

        // conectando com o firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        // conectando variavel com o id
        edit_name = findViewById(R.id.edit_name);
        edit_product = findViewById(R.id.edit_product);
        edit_value = findViewById(R.id.edit_value);
        btn_register = findViewById(R.id.btn_register);

        //Método para definir as funções ao clicar no botão
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recuperarDados();
                salvarDados();
            }
        });

    }
     private void recuperarDados() {
        // checando se os dados foram preenchidos
            if(edit_name.getText().toString()==""||edit_product.getText().toString()==""||edit_value.getText().toString()==""){
                Toast.makeText(getApplicationContext(),"Register the sale", Toast.LENGTH_LONG);
        }else{
                //obtendo os valores inseridos
                s = new salesmodel();
                s.setBuyer(edit_name.getText().toString());
                s.setProduct(edit_product.getText().toString());
                s.setValue(edit_value.getText().toString());
                }
    }

    private void salvarDados() {
        // usando o hashmap para atribuir a propriedade e o valor
        hash = new HashMap<>();
        hash.put("Buyer",s.getBuyer());
        hash.put("Product",s.getProduct());
        hash.put("Value",s.getValue());
        // salvando o valor no firebase
        DatabaseReference firebase = setFirebase.getFirebaseDatabase();
        firebase.child("Users").child(s.getBuyer()).setValue(hash).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(newsale.this, "Sale added successfully", Toast.LENGTH_SHORT);
            }
        });


    }
}
