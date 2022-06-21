package com.example.ilibrary.librarian;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ilibrary.R;

public class LibrarianHomepage extends AppCompatActivity {

    Button signout_librarian;
    TextView manageMember, manageBook, manageReservation;
    ImageView imageMember, imageBook, imageReservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_librarian_homepage);

        signout_librarian = findViewById(R.id.signout_librarian);
        manageMember = findViewById(R.id.MemberManage);
        manageBook = findViewById(R.id.BookManage);
        manageReservation = findViewById(R.id.ReservationManage);

        imageMember = findViewById(R.id.imgMember);
        imageBook = findViewById(R.id.imgBook);
        imageReservation = findViewById(R.id.imgReservation);

        signout_librarian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signout_librarian = new Intent(getApplicationContext(), librarian_login.class);
                startActivity(signout_librarian);
            }
        });

        manageMember.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                Intent manageMember = new Intent(getApplicationContext(), MemberList.class);
                startActivity(manageMember);
            }
        });
        imageMember.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                Intent manageMember = new Intent(getApplicationContext(), MemberList.class);
                startActivity(manageMember);
            }
        });

        manageBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent manageBook = new Intent(getApplicationContext(), BookList.class);
                startActivity(manageBook);
            }
        });

        imageBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent manageBook = new Intent(getApplicationContext(), BookList.class);
                startActivity(manageBook);
            }
        });

    }
}