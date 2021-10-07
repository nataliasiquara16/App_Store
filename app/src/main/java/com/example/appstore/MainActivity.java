package com.example.appstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextSenha;
    private Button login;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //conectando a variavel com o id
        editTextEmail=findViewById(R.id.editTextEmail);
        editTextSenha=findViewById(R.id.editTextSenha);
        login=findViewById(R.id.login);
        mAuth=FirebaseAuth.getInstance();

        // definindo o método do botão
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String LoginEmail = editTextEmail.getText().toString();
                String LoginSenha = editTextSenha.getText().toString();
                if(!TextUtils.isEmpty(LoginEmail)|| !TextUtils.isEmpty(LoginSenha)){
                    mAuth.signInWithEmailAndPassword(LoginEmail,LoginSenha)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                abrirMenu();
                            }else{
                                    String error = task.getException().getMessage();
                                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    };
                }
            });
        };

    private void abrirMenu() {
        Intent intent = new Intent(MainActivity.this,Main.class);
        startActivity(intent);
        finish();
    }
}