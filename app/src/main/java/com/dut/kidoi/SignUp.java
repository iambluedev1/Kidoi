package com.dut.kidoi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dut.kidoi.repositories.FirebaseRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class SignUp extends AppCompatActivity {

    private EditText et_email,et_pwd,et_conpwd,et_user;
    private String email,pwd,conPwd,username;
    private Button btn_inscription;
    private FirebaseAuth mAuth= FirebaseAuth.getInstance();
    private FirebaseRepository db = new FirebaseRepository();

    public void initializeUser() {
        et_email = findViewById(R.id.et_email);
        et_pwd=findViewById(R.id.et_pwd);
        et_conpwd=findViewById(R.id.et_pwdConfirm);
        et_user=findViewById(R.id.et_user);

        email=et_email.getText().toString();
        pwd=et_pwd.getText().toString();
        conPwd=et_conpwd.getText().toString();
        username=et_user.getText().toString();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btn_inscription = findViewById(R.id.btn_inscription);


        btn_inscription.setOnClickListener(view -> {
           // startActivity(new Intent(SignUp.this, Root.class));
            initializeUser();
            createAccount();
        });
    }

    public void createAccount(){
        mAuth.createUserWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d("SignUp", "Success");
                        db.addUser(email,username, task.getResult().getUser().getUid(), user -> {
                            if(user == null){
                                Toast.makeText(SignUp.this,"Impossible de créer le compte", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            signIn();
                        });

                    } else {
                        if(task.getException() instanceof FirebaseAuthWeakPasswordException){
                            Toast.makeText(SignUp.this,"Le mot de passe doit faire au minimum 6 caractères de long", Toast.LENGTH_SHORT).show();
                        } else if(task.getException() instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(SignUp.this,"L'adresse mail que vous tentez d'utiliser est déjà attribuée à un compte", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SignUp.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                     }
                });
    }

    //check if the form is valid
    public boolean validate(){
        boolean valid = true;
        if(username.isEmpty()){
            et_user.setError("Login manquant");
            et_user.requestFocus();
            valid =false;
        }

        else if(pwd.length()<6){
            et_pwd.setError("Le mot de passe doit contenir au moins 6 caractères");
            et_pwd.requestFocus();
            valid =false;
        }
        else if(conPwd.isEmpty()) {
            et_conpwd.setError("Confirmez votre mot de passe");
            et_conpwd.requestFocus();
            valid =false;
        }
        else if (!pwd.equals(conPwd)){
            et_conpwd.setError("Les mots de passe ne correspondent pas ");
            et_conpwd.requestFocus();
            valid =false;
        }
        else if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            et_email.setError("Email manquant/incorrect");
            et_email.requestFocus();
            valid =false;
        }

        return valid;
    }

    public void signIn (){
        mAuth.signInWithEmailAndPassword(email, pwd)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("Authentication", "Success");
                        FirebaseRepository.getInstance().getUser(user -> {
                            if(user == null){
                                Toast.makeText(SignUp.this, "Impossible de récupérer les données du compte", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            Log.d("connected", user.getEmail());
                            Log.d("connected", user.getLogin());
                            Log.d("connected", user.getDocumentId());

                            startActivity(new Intent(SignUp.this, Root.class));
                        });
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("Authentification", "signInWithEmail:failure", task.getException());
                        Toast.makeText(SignUp.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }


}
