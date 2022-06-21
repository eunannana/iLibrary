package com.example.ilibrary.member;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ilibrary.BookReservationList;
import com.example.ilibrary.R;
import com.example.ilibrary.librarian.ManageBook;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;

public class ReservationBook extends AppCompatActivity {

    private EditText member_ID_resv,  book_resv, book_return;
    private TextView book_ID_resv, book_Title_resv, code_Book, title_Book;
    private Button ReservNow;
    private ProgressDialog progressDialog;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_book);

        book_ID_resv = findViewById(R.id.IDbook_resv);
        book_Title_resv = findViewById(R.id.titleBook_resv);
        member_ID_resv = findViewById(R.id.IDmember_resv);
        book_resv = findViewById(R.id.date_resv);
        book_return = findViewById(R.id.date_return);
        ReservNow = findViewById(R.id.reservation_button);

        progressDialog = new ProgressDialog(ReservationBook.this);
        progressDialog.setTitle("On process");
        progressDialog.setMessage("Save Book Reservation...");

        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getStringExtra("id");
            code_Book.setText(intent.getStringExtra("code"));
            title_Book.setText(intent.getStringExtra("title"));

            ReservNow.setOnClickListener(v->{
                startActivity(new Intent(getApplicationContext(), BookReservationList.class));
                )
            }
        }

}