package com.example.appstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.appstore.databinding.ActivityDeleteSaleBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DeleteSale extends AppCompatActivity {

    // biblioteca binding que permite vincular componentes do layout com fonte de dados.
    ActivityDeleteSaleBinding binding;
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  ActivityDeleteSaleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setContentView(R.layout.activity_delete_sale);

        //converter o edit text em string a partir do click no botão
        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = binding.buyerdelete.getText().toString();
               // checar se o username é vazio
                if (!username.isEmpty()){
                    // chamando o método delete
                    deleteSale(username);

                } else{
                    Toast.makeText(DeleteSale.this,"Enter the name", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
// definindo o método
    private void deleteSale(String username) {

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.child(username).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()) {
                    Toast.makeText(DeleteSale.this, "Deleted", Toast.LENGTH_SHORT).show();
                    binding.buyerdelete.setText("");
                }
                else {
                    Toast.makeText(DeleteSale.this,"Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}