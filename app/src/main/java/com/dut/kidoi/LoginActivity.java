package com.dut.kidoi;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.dut.kidoi.models.User;
import com.dut.kidoi.repositories.FirebaseRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private ArrayList<User> myDataSet = new ArrayList<>();
    private TextView link_connect;
    private String email, password;
    private FirebaseAuth mAuth;
    private EditText et_email, et_password;
    private Button btn_connexion;

    private void initialize() {
        et_email = findViewById(R.id.et_email);
        email = et_email.getText().toString();
        et_password = findViewById(R.id.et_pwd);
        password = et_password.getText().toString();
        btn_connexion = findViewById(R.id.btn_connexion);
        link_connect = findViewById(R.id.tv_inscription);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) {
            FirebaseRepository.getInstance().getUser(user -> {
                if(user == null){
                    Toast.makeText(LoginActivity.this, "Impossible de récupérer les données du compte", Toast.LENGTH_SHORT).show();
                    return;
                }

                Log.d("connected", user.getEmail());
                Log.d("connected", user.getLogin());
                Log.d("connected", user.getDocumentId());

                startActivity(new Intent(LoginActivity.this, Root.class));
            });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        link_connect.setOnClickListener(view ->
                startActivity(new Intent(LoginActivity.this, SignUp.class))
        );

        btn_connexion.setOnClickListener(v -> {
            signIn();
        });
    }

    public void signIn() {
        initialize();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("Authentication", "Success");
                        FirebaseRepository.getInstance().getUser(user -> {
                            if(user == null){
                                Toast.makeText(LoginActivity.this, "Impossible de récupérer les données du compte", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            Log.d("connected", user.getEmail());
                            Log.d("connected", user.getLogin());
                            Log.d("connected", user.getDocumentId());

                            startActivity(new Intent(LoginActivity.this, Root.class));
                        });
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("Authentification", "signInWithEmail:failure", task.getException());
                        Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }
                });
    }
}
