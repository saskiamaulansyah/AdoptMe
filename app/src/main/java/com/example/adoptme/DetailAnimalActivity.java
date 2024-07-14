package com.example.adoptme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.adoptme.adoptme.AdoptmeActivity;

public class DetailAnimalActivity extends AppCompatActivity {

    CardView btn_back, btn_love, btn_adopt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_animal);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getSupportActionBar().hide();

        btn_back = findViewById(R.id.btn_back);
        btn_love = findViewById(R.id.btn_love);
        btn_adopt = findViewById(R.id.btn_adopt);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailAnimalActivity.this, MainActivity.class));
            }
        });

        btn_love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailAnimalActivity.this, "You like this", Toast.LENGTH_SHORT).show();
            }
        });

        btn_adopt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailAnimalActivity.this, AdoptmeActivity.class));
            }
        });
    }
}