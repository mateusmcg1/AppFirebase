package com.example.appfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class FormLogin extends AppCompatActivity {
    private TextView textTelaCadastro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        textTelaCadastro = findViewById(R.id.TextViewCadastrarLogin);
        this.chamaTelaCadastro();
    }

    public void chamaTelaCadastro(){
        textTelaCadastro = findViewById(R.id.TextViewCadastrarLogin);
        textTelaCadastro.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormLogin.this, FormCadastro.class);
                startActivity(intent);
            }
        });

        }


}