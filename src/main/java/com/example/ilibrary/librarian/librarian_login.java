package com.example.ilibrary.librarian;
import com.example.ilibrary.R;
import com.example.ilibrary.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class librarian_login extends AppCompatActivity {

    private Button librarian_login;

    private EditText librarian_id, librarian_pass;

    private String librarianID, librarianpass;

    private String ParentDbName = "User";

    private ProgressDialog loadBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarian_login);

        librarian_id = findViewById(R.id.librarianID);
        librarian_pass = findViewById(R.id.librarianPass);

        librarian_login = findViewById(R.id.login_librarian_button);

        loadBar = new ProgressDialog(this);

        librarian_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });

    }
    public void validate(){
        librarianID = librarian_id.getText().toString();
        librarianpass = librarian_pass.getText().toString();

        if(TextUtils.isEmpty(librarianID)){
            Toast.makeText(this, "Input Librarian ID!", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(librarianpass)){
            Toast.makeText(this, "Input Librarian password!", Toast.LENGTH_SHORT).show();
        }else{
            loadBar.setTitle("Login Process");
            loadBar.setMessage("Please wait...");
            loadBar.setCanceledOnTouchOutside(false);
            loadBar.show();

            AllowAccessAccount(librarianID, librarianpass);
        }
    }
    private void AllowAccessAccount(final String l_ID, final String l_pass){
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(ParentDbName).child("Librarian").child(l_ID).exists())
                {
                    User UserData = snapshot.child(ParentDbName).child("Librarian").child(l_ID).getValue(User.class);
                    if(UserData.getL_ID().equals(l_ID))
                    {
                        if(UserData.getL_pass().equals(l_pass))
                        {
                            loadBar.dismiss();
                            Toast.makeText(getApplicationContext(), "Welcome, iLibrary Librarian!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), LibrarianHomepage.class);
                            startActivity(intent);
                        }
                        else{
                            loadBar.dismiss();
                            Toast.makeText(getApplicationContext(), "Incorrect Password!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else{
                    loadBar.dismiss();
                    Toast.makeText(getApplicationContext(), "Librarian ID and Password doesn't exist!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}