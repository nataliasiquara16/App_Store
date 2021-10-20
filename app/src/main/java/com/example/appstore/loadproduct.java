package com.example.appstore;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class loadproduct extends AppCompatActivity {

    private ImageView iv_image;
    private Button btn_load;
    private static final int PICK_IMAGE_REQUEST = 234;
    private Uri fileUri;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registreproduct);

        storageReference = FirebaseStorage.getInstance().getReference();

        // associando o componente ao id
        iv_image=findViewById(R.id.iv_image);
        btn_load=findViewById(R.id.btn_load);

        // define que ao clicar no botão, iniciar os metodos
        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showfileSelected();
            }

            // metodo para selecionar a imagem
            private void showfileSelected() {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select a image of product"), PICK_IMAGE_REQUEST);
                uploadImage();
            }

            //metodo para fazer o upload da imagem no Firebase Storage
            private void uploadImage() {
                StorageReference imagesRef = storageReference.child("images");
                imagesRef.putFile(fileUri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Toast.makeText(getApplicationContext(), "Upload Completed", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            protected void onActivityResult(int requestCode,int resultCode, Intent data){
                loadproduct.super.onActivityResult(requestCode,resultCode,data);
                 if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() !=null){
                     fileUri = data.getData();
                     Glide.with(loadproduct.this).load(fileUri).into(iv_image);

                 }
            }
        });
}}
