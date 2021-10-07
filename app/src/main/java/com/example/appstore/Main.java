package com.example.appstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appstore.R;

public class Main extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    // conectando o button com o arquivo java
    public void botaosale(View v) {
        startActivity(new Intent(this, com.example.appstore.newsale.class));
    }
    // conectando o button com o arquivo java
    public void botaohistory(View v) {
        startActivity(new Intent(this, com.example.appstore.history.class));
    }

    // conectando o button com o arquivo java
    public void botaodelete(View v) {
        startActivity(new Intent(this, com.example.appstore.DeleteSale.class));
    }

}