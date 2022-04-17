package com.example.pet1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PerformanceHintManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    Button buttonRegister;
    EditText editEmail, editPassword, editPassword2;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        buttonRegister = findViewById(R.id.buttonRegister);
        editEmail = (findViewById(R.id.editEmail));
        editPassword = (findViewById(R.id.editPassword));
        editPassword2 = (findViewById(R.id.editPassword2));
        progressDialog = new ProgressDialog(this);

        mAuth= FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();


        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerforAuth();

            }


        });

    }

    private void PerforAuth() {
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();
        String confirmpassword = editPassword2.getText().toString();


        if (!email.matches(emailPattern)) {
            editEmail.setError("Dogru email giriniz");
        } else if (password.isEmpty() || password.length() < 6) {
            editPassword.setError("Dogru sifre giriniz");
        } else if (!password.equals(confirmpassword)) {
            editPassword2.setError("Sifreler aynı degil");
        } else {
            progressDialog.setMessage("Kayıt yapılırken bekleyiniz");
            progressDialog.setTitle("Kayıt");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        sendUserToNextActivity();
                        Toast.makeText(RegisterActivity.this, "Kayıt basarılı", Toast.LENGTH_SHORT).show();
                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void sendUserToNextActivity() {
        Intent intent= new Intent(RegisterActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
