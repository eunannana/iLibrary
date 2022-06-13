package com.example.ilibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class librarian_login extends AppCompatActivity {

    Button librarian_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarian_login);

        librarian_login = findViewById(R.id.login_librarian_button);

        librarian_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent l_login = new Intent(getApplicationContext(), LibrarianHomepage.class);

                startActivity(l_login);

            }
        });

    }
}