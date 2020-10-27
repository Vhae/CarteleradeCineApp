package com.example.carteleradecineapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.carteleradecineapp.singleton.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class CreateAccountActivity extends AppCompatActivity {
    EditText edtNameCrearCuenta,edtApellidosCrearCuenta,edtEmailCrearCuenta,edtPassword;
    Button btnCrearCuenta;
    Auth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        mAuth= new Auth();

        edtNameCrearCuenta=findViewById(R.id.edtNameCrearCuenta);
        edtApellidosCrearCuenta=findViewById(R.id.edtApellidosCrearCuenta);
        edtEmailCrearCuenta=findViewById(R.id.edtEmailCreaCuenta);
        edtPassword=findViewById(R.id.edtPasswordCrearCuenta);
        btnCrearCuenta=findViewById(R.id.btnCrearCuentaCrearCuenta);

        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearCuenta();
            }
        });
    }

    private void crearCuenta() {
         final String name=edtNameCrearCuenta.getText().toString();
         final String lastname=edtApellidosCrearCuenta.getText().toString();
         String email=edtEmailCrearCuenta.getText().toString();
         String password=edtPassword.getText().toString();

        if (!name.isEmpty() && !lastname.isEmpty() && !email.isEmpty() && !password.isEmpty()){
            if (password.length()>=6){
                mAuth.register(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            final FirebaseUser user= mAuth.getCurrentUser();
                            user.updateProfile(CreateProfile(user,name,lastname)).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        String name=user.getDisplayName();
                                        Toast.makeText(CreateAccountActivity.this, "Se Creo el usuario con el nombre:" +name, Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                            mAuth.logOut();
                            Intent intent=new Intent(CreateAccountActivity.this,LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(CreateAccountActivity.this, "Ocurrio un error en la creacion del usuario, error: " + task.getException(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            else{
                Toast.makeText(this, "La contraseña debe de tener al menos 6 caracteres " , Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(this, "Se necesita Llenar todos los campos", Toast.LENGTH_LONG).show();
        }
    }

    private UserProfileChangeRequest CreateProfile(FirebaseUser user,String name,String lastname) {
        // Obtenemos la data de la vista y le creamos el profile
        String nombreCompleto=name;
        nombreCompleto+=" "+lastname;
        UserProfileChangeRequest newProfile= new UserProfileChangeRequest.Builder().setDisplayName(nombreCompleto).build();
        return newProfile;
    }
}