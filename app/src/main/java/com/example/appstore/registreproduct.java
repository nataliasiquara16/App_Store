package com.example.appstore;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.UploadTask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

public class registreproduct extends AppCompatActivity {

    private Button btn_take;
    private TextView tv_message;
    private ImageView iv_image;
    static final int REQUEST_TAKE_PHOTO = 1;
    String currentPhotoPath;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registreproduct);

        // associando as variaveis aos ids
        btn_take= findViewById(R.id.btn_take);
        iv_image = findViewById(R.id.iv_image);
        tv_message = findViewById(R.id.tv_message);

        // declarando metodo para quando clicar no Botao Take, a camera seja iniciada.
        btn_take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            //colocando a imagem em uma imagemview
            ImageView iv_image;
            iv_image=findViewById(R.id.iv_image);


            Glide.with(this).load(currentPhotoPath).into(iv_image);

            //mostrando o nome do arquivo no text view
            TextView tv_message;
            tv_message= findViewById(R.id.tv_message);
            tv_message.setText(currentPhotoPath);
        }
    }
    // método para salvar o arquivo
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // criando o arquivo onde vai ser salvo as imagens
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // caso der erro
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
            }
            // se o arquivo for salvo com sucesso
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.appstore.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private File createImageFile() throws IOException {
        // criando o nome do arquivo
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  //prefixo
                ".jpg",   //sufixo
                storageDir     //diretorio
        );

        // salvando o arquivo
        currentPhotoPath = image.getAbsolutePath();
        uploadImagem();
        return image;

    }

    private void uploadImagem() {
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        StorageReference imagesRef = storageRef.child("images");
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        StorageReference fileRef = imagesRef.child(imageFileName);

        UploadTask uploadTask = fileRef.putFile(Uri.fromFile(new File(currentPhotoPath)));
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // caso der erro
                Toast.makeText(registreproduct.this,"Error in Upload",Toast.LENGTH_SHORT).show();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String downloadUrl = taskSnapshot.getMetadata().getReference().getDownloadUrl().toString();
                Toast.makeText(registreproduct.this, "Upload finished!", Toast.LENGTH_SHORT).show();

            };

        });}}