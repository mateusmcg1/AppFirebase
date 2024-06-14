package com.example.appfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FormCadastro extends AppCompatActivity {
    private ImageButton imageButtonVoltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);
        this.voltarLogin();
        }
        public void voltarLogin(){
            this.imageButtonVoltar = findViewById(R.id.ButtonBackCadastro);
            imageButtonVoltar.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(FormCadastro.this, FormLogin.class);
                    startActivity(intent);
                }
            });
        }
    }
