package com.example.foodheroes.Mitra.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodheroes.Models.Mitra;
import com.example.foodheroes.Models.User;
import com.example.foodheroes.R;
import com.example.foodheroes.Relawan.Activities.MainActivity;
import com.example.foodheroes.Relawan.Activities.VerificationLoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class VerificationMitraActivity extends AppCompatActivity {

    EditText txtVerifCode;
    FirebaseAuth mAuth;
    String VerificationId, NumberPhone, Nama, Email, Password, Alamat;
    Mitra mitra;
    DatabaseReference MitraReff;
    FirebaseUser firebaseUser;

    private PhoneAuthProvider.ForceResendingToken mResendToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification_mitra);
        mAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();
        NumberPhone = intent.getStringExtra("NumberPhone");
        Nama = intent.getStringExtra("Nama");
        Email = intent.getStringExtra("Email");
        Password = intent.getStringExtra("Password");
        Alamat = intent.getStringExtra("Alamat");
        Log.d("CheckLog", NumberPhone+Nama+Email+Password+Alamat);

        txtVerifCode = findViewById(R.id.txtVerifCode);
        mitra = new Mitra();
        MitraReff = FirebaseDatabase.getInstance().getReference().child("Mitra");

        //+6282232356877

        sendVerificationCode(NumberPhone);
        findViewById(R.id.btnVerifCode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifySignInCode(txtVerifCode.getText().toString());
            }
        });

        findViewById(R.id.btnReSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reSendVerificatioonCode(NumberPhone);
            }
        });
    }

    private void verifySignInCode(String verifCode){
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(VerificationId, verifCode);
        signInWithPhoneAuthCredential(credential, NumberPhone);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential, String NumberPhone) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //here you can open new activity
                    Toast.makeText(getApplicationContext(),"Login Successfull", Toast.LENGTH_LONG).show();

                    mitra.setNamaMitra(Nama);
                    mitra.setAlamatMitra(Alamat);
                    mitra.setEmail(Email);
                    mitra.setPassword(Password);
                    mitra.setPhoneNumber(NumberPhone);
                    MitraReff.push().setValue(mitra);

                    Intent intent = new Intent(VerificationMitraActivity.this, MainMitraActivity.class);
                    intent.putExtra("NumberPhone",NumberPhone);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else {
                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(getApplicationContext(),
                                "Incorrect Verification Code ", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void sendVerificationCode(String numberPhone){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                numberPhone,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks

    }

    private void reSendVerificatioonCode(String numberPhone){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                numberPhone,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks,
                mResendToken);        // OnVerificationStateChangedCallbacks

    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String Code = phoneAuthCredential.getSmsCode();
            //Log.d("CodeCredential",String       );
            Log.d("SEMBARAMG", "onVerificationCompleted:" + phoneAuthCredential);

            signInWithPhoneAuthCredential(phoneAuthCredential, NumberPhone);
            Log.w("onVerifFailed", "onVerificationFailed");
//            if(Code != null){
//                txtVerifCode.setText(Code);
//                signInWithPhoneAuthCredential(phoneAuthCredential);
//            } else {
//                Toast.makeText(VerificationLoginActivity.this, "Code Verifikasi Salah", Toast.LENGTH_SHORT).show();
//            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            //super.onCodeSent(s, forceResendingToken);
            VerificationId = s;
            mResendToken = forceResendingToken;
        }
    };
}
