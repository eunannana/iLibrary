package com.example.ilibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class librarian_login extends AppCompatActivity {

    Button librarian_login;

    EditText librarian_id, librarian_pass;

    String ID, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarian_login);

        librarian_login = findViewById(R.id.login_librarian_button);

        librarian_id = findViewById(R.id.librarianID);
        librarian_pass = findViewById(R.id.librarianPass);

        librarian_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });

    }
    public void validate(){
        ID = librarian_id.getText().toString();
        pass = librarian_pass.getText().toString();

        String theID = "Admin";
        String thePass = "Admin123";

        if (ID.isEmpty() && pass.isEmpty()){
            librarian_id.setError("ID is required!");
            librarian_pass.setError("password is required!");
        }else{
            if(ID.equals(theID) && pass.equals(thePass)){
                Toast t = Toast.makeText(getApplicationContext(), "Welcome to iLibrary Admin!", Toast.LENGTH_LONG);
                t.show();

                Bundle b = new Bundle();

                b.putString("a", ID.trim());

                b.putString("b", pass.trim());

                Intent librarian = new Intent(getApplicationContext(), LibrarianHomepage.class);
                librarian.putExtras(b);

                startActivity(librarian);
            }else if(!ID.equals(theID) && pass.equals(thePass)){
                Toast t = Toast.makeText(getApplicationContext(),
                        "Input the correct ID!", Toast.LENGTH_LONG);
                t.show();
            }else if(ID.equals(theID) && !pass.equals(thePass)){
                Toast t = Toast.makeText(getApplicationContext(),
                        "Input the correct password!", Toast.LENGTH_LONG);
                t.show();
            }else if(!ID.equals(theID) && !pass.equals(thePass)){
                Toast t = Toast.makeText(getApplicationContext(),
                        "Input the correct ID and password!", Toast.LENGTH_LONG);
                t.show();
            }else if(theID.isEmpty()){
                librarian_id.setError("ID is required!");
            }else if(thePass.isEmpty()){
                librarian_pass.setError("Password is required!");
            }
        }
    }
}