/* package com.example.appstore;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.UploadTask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class takepicture extends AppCompatActivity {

    private Button btn_take;
    private TextView tv_message;
    private ImageView iv_image;
    static final int REQUEST_TAKE_PHOTO = 1;
    private StorageReference storage;
    private DatabaseReference database;
    private Uri photoURI;
    String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registreproduct);

        // associando as variaveis aos ids
        btn_take= findViewById(R.id.btn_test);
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

        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK && data != null) {
            photoURI= data.getData();
            //colocando a imagem em uma imagemview
            ImageView iv_image;
            iv_image=findViewById(R.id.iv_image);
            Glide.with(this).load(data).into(iv_image);

            //mostrando o nome do arquivo no text view
            TextView tv_message;
            tv_message= findViewById(R.id.tv_message);
            tv_message.setText(currentPhotoPath);

            sendPhoto(photoURI);
        }
    }

    private void sendPhoto(Uri photoURI) {
        StorageReference filepath = storage.child("Photos").child( this.photoURI.getLastPathSegment());
        filepath.putFile(this.photoURI).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getApplicationContext(), "Upload Successfull", Toast.LENGTH_SHORT).show();
            }
        } ).addOnFailureListener( new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Upload Failed", Toast.LENGTH_SHORT).show();
            }
        } );
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
        return image;

    }


}
*/