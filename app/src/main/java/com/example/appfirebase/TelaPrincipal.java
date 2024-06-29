package com.example.appfirebase;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.snackbar.Snackbar;

public class TelaPrincipal extends AppCompatActivity {

    private String[] msgs = {"Preencha todos os campos!", "Login realizado com sucesso!"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);

    }
    //meoto para capturar e tratar o evento de logar



}