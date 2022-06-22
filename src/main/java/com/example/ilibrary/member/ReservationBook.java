package com.example.ilibrary.member;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ilibrary.adapter.BookAdapter;
import com.example.ilibrary.librarian.BookReservationList;
import com.example.ilibrary.R;
import com.example.ilibrary.model.book;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ReservationBook extends AppCompatActivity {


    private TextView code_Book, title_Book, subjectBook, authorBook;
    private Button ReservNow;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RecyclerView recyclerView;
    private List<book> list = new ArrayList<>();
    private BookAdapter bookAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_book);

        code_Book = findViewById(R.id.IDbook_resv);
        title_Book = findViewById(R.id.titleBook_resv);
        ReservNow = findViewById(R.id.reservation_button);
        subjectBook = findViewById(R.id.subjectBook_resv);
        authorBook = findViewById(R.id.authorBook_resv);
        progressDialog = new ProgressDialog(ReservationBook.this);
        progressDialog.setTitle("On process");
        progressDialog.setMessage("Save Book Reservation...");

        Intent intent = getIntent();
        if (intent != null) {
            code_Book.setText(intent.getStringExtra("code"));
            title_Book.setText(intent.getStringExtra("title"));
            subjectBook.setText(intent.getStringExtra("subject"));
            authorBook.setText(intent.getStringExtra("author"));

            ReservNow.setOnClickListener(v-> {
                startActivity(new Intent(getApplicationContext(), BookReservationList.class));
            });
            }

        }

}