package com.example.ilibrary.librarian;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.ilibrary.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ManageBook extends AppCompatActivity {

    private String bookID, titleBook, ISBNBook, subjectBook, authorBook, publisherBook, yearBook, stockBook, languageBook, descBook, summaryBook, imgBoook;
    private ImageButton img_Book;
    private EditText ID_Book, title_Book, ISBN_Book, subject_Book, author_Book, publisher_Book, year_Book, stock_Book, language_Book, desc_Book, summary_Book, img_Boook;
    private Button saveBook;
    private Uri image;
    private StorageReference storageRef;
    private DatabaseReference dbRef;
    private ProgressDialog progressDialog;

    private static final int galleryPick = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_book);

        storageRef = FirebaseStorage.getInstance().getReference().child("Book Image");
        dbRef = FirebaseDatabase.getInstance().getReference().child("Book");
        progressDialog = new ProgressDialog(this);

        img_Book = findViewById(R.id.imageBook);
        ID_Book = findViewById(R.id.IDbook);
        title_Book = findViewById(R.id.ISBN);



    }
}