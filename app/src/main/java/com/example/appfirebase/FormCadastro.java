package com.example.appfirebase;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FormCadastro extends AppCompatActivity {
    private ImageButton imageButtonVoltar;
    private String usuarioId;
    private EditText edit_nome, edit_email, edit_senha, edit_senha2;
    private AppCompatButton bt_cadastrar;
    private final String [] msgs = {
            "Prencha todos os campos", "Cadastro realizado com sucesso!", "Senhas não são iguais"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);
        this.iniciarComponentes();
        this.voltarLogin();
        this.gerenciarCadastro();


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
        private void iniciarComponentes(){
        edit_nome= findViewById(R.id.EditTextNomeCadastro);
        edit_email = findViewById(R.id.EditTextEmailCadastro);
        edit_senha = findViewById(R.id.editTextSenhaCadastro);
        edit_senha2 = findViewById(R.id.editTextSenha2Cadastro);
        bt_cadastrar = findViewById(R.id.bt_cadastrar);
        }

        public void gerenciarCadastro(){
        bt_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = edit_nome.getText().toString();
                String email = edit_email.getText().toString();
                String senha = edit_senha.getText().toString();
                String senha2 = edit_senha2.getText().toString();
    //validar campos nao preenchidos
                if(nome.isEmpty() || email.isEmpty() || senha.isEmpty() || senha2.isEmpty()){
                    Snackbar objSnackbar = Snackbar.make(view, msgs[0], Snackbar.LENGTH_SHORT);
                    objSnackbar.setBackgroundTint(Color.WHITE);
                    objSnackbar.setTextColor(Color.BLACK);
                    objSnackbar.show();
                }//validar senha == senha 2
                else if(!senha.equals(senha2)){
                    Snackbar objSnackbar = Snackbar.make(view, msgs[2], Snackbar.LENGTH_SHORT);
                    objSnackbar.setBackgroundTint(Color.WHITE);
                    objSnackbar.setTextColor(Color.BLACK);
                    objSnackbar.show();
                }else{//validações passaram
                    cadastrarUser(view, email, senha);
                }
            }
        });
        }

        private void cadastrarUser(View view, String email, String senha){
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha).addOnCompleteListener(
                    new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                salvarDadosUsuario();
                                Snackbar objSnackbar = Snackbar.make(view, msgs[1], Snackbar.LENGTH_SHORT);
                                objSnackbar.setBackgroundTint(Color.WHITE);
                                objSnackbar.setTextColor(Color.BLACK);
                                objSnackbar.show();
                                new Handler().postDelayed(
                                        new Runnable() {
                                            @Override
                                            public void run() {
                                                Intent objIntent = new Intent(FormCadastro.this, FormLogin.class);
                                            }//fim runn
                                        }
                            , 3000);
                            }else{
                                String erro="";
                                try{
                                    throw task.getException();
                                }catch(FirebaseAuthWeakPasswordException e){
                                    erro = "Digite uma senha com 6 caracteres ou mais";
                                }catch(FirebaseAuthUserCollisionException e){
                                    erro = "Já existe conta vinculada ao email";
                                }catch(FirebaseAuthInvalidCredentialsException e){
                                    erro = "Email inválido";
                                }catch(Exception e){
                                    erro = "Erro ao cadastrara usuario!";
                                }
                                Snackbar objSnackbar = Snackbar.make(view, erro, Snackbar.LENGTH_SHORT);
                                objSnackbar.setBackgroundTint(Color.WHITE);
                                objSnackbar.setTextColor(Color.BLACK);
                                objSnackbar.show();
                            }
                        }

                    }
            );//fim complete listener
        }
        public void salvarDadosUsuario(){
        String nome = edit_nome.getText().toString();
            FirebaseFirestore db=FirebaseFirestore.getInstance();
            Map<String,Object> usuario = new HashMap<>();
            usuario.put("nome", nome);
            usuarioId = FirebaseAuth.getInstance().getCurrentUser().getUid();
            DocumentReference documentReference = db.collection("Usuarios").document(usuarioId);
            documentReference.set(usuario).addOnSuccessListener(
                    new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d("d","Sucesso ao salvar dados");
                        }
                    }).addOnFailureListener(
                    new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("db_error", "Erro ao salvar dados"+ e.getMessage());
                        }
                    }
            );
        }
    }
