package com.example.ilibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button tap_member, tap_librarian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tap_member = findViewById(R.id.member_button);
        tap_librarian = findViewById(R.id.librarian_button);

        tap_member.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view1) {
                Intent member = new Intent(getApplicationContext(), member_login.class);

                startActivity(member);
            }
        });
        tap_librarian.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view2) {
                Intent librarian = new Intent(getApplicationContext(), librarian_login.class);

                startActivity(librarian);
            }
        });


    }
}