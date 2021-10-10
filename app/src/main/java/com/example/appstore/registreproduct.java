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

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class registreproduct extends AppCompatActivity {

    private Button btn_take;
    private Button btn_list;
    private Button btn_load;
    private TextView tv_message;
    private ImageView iv_image;
    static final int REQUEST_TAKE_PHOTO = 1;
    String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registreproduct);

        // associando as variaveis aos ids
        btn_list = findViewById(R.id.btn_list);
        btn_take= findViewById(R.id.btn_take);
        btn_load = findViewById(R.id.btn_list);
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
                ".jpg",         //sufixo
                storageDir      //diretorio
        );

        // salvando o arquivo
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
}
