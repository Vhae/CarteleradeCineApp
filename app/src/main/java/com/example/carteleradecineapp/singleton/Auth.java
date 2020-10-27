package com.example.carteleradecineapp.singleton;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Auth {
    FirebaseAuth mAuth;

    public Auth() {
        mAuth = FirebaseAuth.getInstance();
    }

    public FirebaseUser getCurrentUser(){
       return mAuth.getCurrentUser();
    }

    public Task<AuthResult> register(String email, String password){
        return mAuth.createUserWithEmailAndPassword(email, password);
    }



    public Task<AuthResult> login(String email, String password){
        return mAuth.signInWithEmailAndPassword(email, password);
    }

    public void logOut(){
        mAuth.signOut();
    }
}
