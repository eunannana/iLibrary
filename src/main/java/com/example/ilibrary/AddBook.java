package com.example.ilibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddBook extends AppCompatActivity {

    Button tap_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        tap_add = findViewById(R.id.btn_add);

        tap_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(getApplicationContext(), DetailBook.class);
                startActivity(add);
            }
        });
    }
}