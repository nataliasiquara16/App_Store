package com.example.appstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // conectando o button com o arquivo java
    public void botaosale(View v) {
        startActivity(new Intent(this, com.example.appstore.newsale.class));
    }
}