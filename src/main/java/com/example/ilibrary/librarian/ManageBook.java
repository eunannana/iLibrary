package com.example.ilibrary.librarian;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ilibrary.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ManageBook extends AppCompatActivity {

    private EditText ID_Book, title_Book, ISBN_Book, subject_Book, shelf_Book, author_Book, publisher_Book, year_Book, stock_Book, desc_Book, summary_Book;
    private Button saveBook;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_book);

        progressDialog = new ProgressDialog(this);

        ID_Book = findViewById(R.id.IDbook);
        title_Book = findViewById(R.id.titleBook);
        ISBN_Book = findViewById(R.id.ISBN);
        subject_Book = findViewById(R.id.subjectBook);
        shelf_Book = findViewById(R.id.shelfBook);
        author_Book = findViewById(R.id.authorBook);
        publisher_Book = findViewById(R.id.publisherBook);
        year_Book = findViewById(R.id.yearBook);
        stock_Book = findViewById(R.id.stockBook);
        desc_Book = findViewById(R.id.descBook);
        summary_Book = findViewById(R.id.summaryBook);
        saveBook = findViewById(R.id.save_book_button);

        saveBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Manage_Book();
            }
        });
    }

    private void Manage_Book() {
        String bookID = ID_Book.getText().toString();
        String titleBook = title_Book.getText().toString();
        String ISBNBook = ISBN_Book.getText().toString();
        String subjectBook = subject_Book.getText().toString();
        String shelfBook = shelf_Book.getText().toString();
        String authorBook = author_Book.getText().toString();
        String publisherBook = publisher_Book.getText().toString();
        String yearBook = year_Book.getText().toString();
        String stockBook = stock_Book.getText().toString();
        String descBook = desc_Book.getText().toString();
        String summaryBook = summary_Book.getText().toString();

        if (TextUtils.isEmpty(bookID)) {
            Toast.makeText(this, "Enter Book ID", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(titleBook)) {
            Toast.makeText(this, "Enter Book Title", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(ISBNBook)) {
            Toast.makeText(this, "Enter Book ISBN", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(subjectBook)) {
            Toast.makeText(this, "Enter Book Subject", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(shelfBook)) {
            Toast.makeText(this, "Enter Book Shelf", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(authorBook)) {
            Toast.makeText(this, "Enter Book Author", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(publisherBook)) {
            Toast.makeText(this, "Enter Book Publisher", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(yearBook)) {
            Toast.makeText(this, "Enter Book Year Publish", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(stockBook)) {
            Toast.makeText(this, "Enter Book Stock", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(descBook)) {
            Toast.makeText(this, "Enter Book Physical Description", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(summaryBook)) {
            Toast.makeText(this, "Enter Book Summary", Toast.LENGTH_SHORT).show();
        } else {
            progressDialog.setTitle("On Process...");
            progressDialog.setMessage("Save Data");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            bookData(bookID, titleBook, ISBNBook, subjectBook, shelfBook, authorBook, publisherBook, yearBook, stockBook, descBook, summaryBook);
        }
    }

        private void bookData (final String bookID, final String titleBook, final String ISBNBook, final String subjectBook, final String shelfBook, final String authorBook, final String publisherBook, final String yearBook, final String stockBook, final String descBook, final String summaryBook ) {
        final DatabaseReference db;
            db = FirebaseDatabase.getInstance().getReference();
            db.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(!(dataSnapshot.child("Book").child(bookID).exists())){

                        HashMap<String, Object> BookMap = new HashMap<>();
                        BookMap.put("bookID", bookID);
                        BookMap.put("title", titleBook);
                        BookMap.put("ISBN", ISBNBook);
                        BookMap.put("subject", subjectBook);
                        BookMap.put("shelf", shelfBook);
                        BookMap.put("author", authorBook);
                        BookMap.put("publisher", publisherBook);
                        BookMap.put("year", yearBook);
                        BookMap.put("stock", stockBook);
                        BookMap.put("description", descBook);
                        BookMap.put("summary", summaryBook);

                        db.child("Book").child(bookID).updateChildren(BookMap)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                           @Override
                                                           public void onComplete(@NonNull Task<Void> task) {
                                                               if (task.isSuccessful()) {

                                                                   Intent i = new Intent(ManageBook.this, BookList.class);
                                                                   startActivity(i);
                                                                   progressDialog.dismiss();
                                                                   Toast.makeText(ManageBook.this, "Successfully Save Book Data", Toast.LENGTH_SHORT).show();
                                                               } else {
                                                                   progressDialog.dismiss();
                String message = task.getException().toString();                                                   Toast.makeText(ManageBook.this, "Error, please try again" + message, Toast.LENGTH_SHORT).show();
                                                               }
                                                           }
                                });
                    } else {
                        Toast.makeText(ManageBook.this, "this" + bookID + "is already exist", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        Toast.makeText(ManageBook.this, "Input a new ID", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(ManageBook.this, BookList.class);
                        startActivity(i);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

}