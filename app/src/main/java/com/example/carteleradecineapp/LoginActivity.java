package com.example.carteleradecineapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carteleradecineapp.singleton.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class LoginActivity extends AppCompatActivity {
    EditText edtPasswordLogin,edtEmailLogin;
    TextView txtCrearCuenta;
    ImageView imgViewLogin;
    Button btnIniciarSesion;
    Auth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth= new Auth();

        edtPasswordLogin=findViewById(R.id.edtPasswordLogin);
        edtEmailLogin=findViewById(R.id.edtEmailLogin);
        txtCrearCuenta=findViewById(R.id.txtCrearCuenta);
        imgViewLogin=findViewById(R.id.imgViewLogin);
        btnIniciarSesion=findViewById(R.id.btnIniciarSesion);

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        txtCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent crearCuentaIntent=new Intent(LoginActivity.this,CreateAccountActivity.class);
                startActivity(crearCuentaIntent);
            }
        });
    }

    private void login() {
        String email = edtEmailLogin.getText().toString();
        String password = edtPasswordLogin.getText().toString();
        if (!email.isEmpty() && !password.isEmpty()  ){
            if (password.length() >=6){
                mAuth.login(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Usted se a conectado correctamente", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "el email o password son incorrectos", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
            else {
                Toast.makeText(LoginActivity.this, "la contraseña debe de tener más de 6 caracteres", Toast.LENGTH_SHORT).show();
            }

        }
        else{
            Toast.makeText(LoginActivity.this, "la contraseña  y el email son obligatorios", Toast.LENGTH_SHORT).show();
        }
    }
}