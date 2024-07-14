package com.example.adoptme.addAnimal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.example.adoptme.DetailAnimalActivity;
import com.example.adoptme.LoginActivity;
import com.example.adoptme.MainActivity;
import com.example.adoptme.R;
import com.example.adoptme.adoptme.AdoptmeActivity;

public class AddAnimalActivity extends AppCompatActivity {

    Button btn_back, btn_done, btn_submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_add_animal);

        btn_back = findViewById(R.id.button_cancel);
        btn_done = findViewById(R.id.button_done);
        btn_submit = findViewById(R.id.button_submit);


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddAnimalActivity.this, MainActivity.class));
                finish();
            }
        });

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog progressDialog = new Dialog(AddAnimalActivity.this);
                progressDialog.setContentView(R.layout.activity_loading);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Dismiss the dialog
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        // Redirect to MainActivity
                        Intent intent = new Intent(AddAnimalActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 500);
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Dialog progressDialog = new Dialog(AddAnimalActivity.this);
                progressDialog.setContentView(R.layout.activity_loading);
                progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                progressDialog.setCancelable(false);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Dismiss the dialog
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        // Redirect to MainActivity
                        Intent intent = new Intent(AddAnimalActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 500);
            }
        });
    }
}