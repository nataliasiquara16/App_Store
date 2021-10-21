package com.example.appstore;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class registerproduct extends AppCompatActivity {

    private Button btn_test;
    private Button btn_take;
    private Button btn_load;
    private TextView tv_message;
    private ImageView iv_image;
    static final int REQUEST_TAKE_PHOTO = 1;
    static final int SELECT_A_PHOTO = 2;
    String currentPhotoPath;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    static final int CAMERA_PERM_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registreproduct);

        // associando as variaveis aos ids
        btn_test = findViewById(R.id.btn_test);
        iv_image = findViewById(R.id.iv_image);
        tv_message = findViewById(R.id.tv_message);
        btn_take = findViewById(R.id.btn_take);
        btn_load = findViewById(R.id.btn_load);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        // método para o botão test
        btn_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dispatchTakePictureIntent(); //abre a camera
            }
        });
        //método para botão take
        btn_take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askpermissions(); // pede permissão para usar a camera
            }

        });
        //metodo para botão load
        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i,2); //selecionando a imagem da galeria
            }
        });
    }

    private void askpermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},CAMERA_PERM_CODE);
    } else {
            dispatchTakePictureIntent();

    }
        }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grandResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grandResults);
        if (requestCode == CAMERA_PERM_CODE) {
            if (grandResults.length < 0 && grandResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // caso a foto seja tirada
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            //colocando a imagem em uma imagemview
            iv_image = findViewById(R.id.iv_image);
            Glide.with(this).load(currentPhotoPath).into(iv_image);
            //mostrando o nome do arquivo no text view
            TextView tv_message;
            tv_message = findViewById(R.id.tv_message);
            tv_message.setText(currentPhotoPath);
            File f = new File(currentPhotoPath);
            Uri contentUri = Uri.fromFile(f);

            sendPhoto1(f.getName(),contentUri);
            }

        // caso a foto seja selecionado na galeria
        if (requestCode == SELECT_A_PHOTO && resultCode == RESULT_OK){
            Uri selectedPhoto = data.getData();
            Glide.with(this).load(selectedPhoto).into(iv_image);
            tv_message.setText(selectedPhoto.toString());
            sendPhoto2(selectedPhoto);
        }
    }

  private void sendPhoto1(String name, Uri contentUri) {
        final StorageReference ref = storageReference.child("pictures/" + name);
        ref.putFile(contentUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(getApplicationContext(), "Upload Successfull", Toast.LENGTH_SHORT).show();
                ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                    }
                });
            }
        } ).addOnFailureListener( new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Upload Failed", Toast.LENGTH_SHORT).show();
            }
        } );
    }

    //metodo para salvar a imagem selecionado
    private void sendPhoto2(Uri selectedPhoto) {
        StorageReference ref = storageReference.child("images/");
        ref.putFile(selectedPhoto).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
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

    // método para tirar a foto
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // criando o arquivo onde vai ser salvo as imagens
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // caso der erro
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
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

    //criando o arquivo
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

