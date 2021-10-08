package com.example.appstore;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class registreproduct extends AppCompatActivity {

    private Button btn_take;
    private Button btn_list;
    private Button btn_load;
    private TextView tv_message;
    private ImageView iv_image;
    static final int REQUEST_IMAGE_CAPTURE = 1;

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
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            iv_image.setImageBitmap(imageBitmap);
        }
    }

    // chamando uma intent para capturar uma foto
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
}
