package com.example.ilibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LibrarianHomepage extends AppCompatActivity {

    Button addMember, addBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarian_homepage);

        addMember = findViewById(R.id.button_add_member);
        addBook = findViewById(R.id.button_add_book);

        addMember.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                Intent addMember = new Intent(getApplicationContext(), AddMember.class);
                startActivity(addMember);
            }
        });
        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addBook = new Intent(getApplicationContext(), AddBook.class);
                startActivity(addBook);
            }
        });

    }
}