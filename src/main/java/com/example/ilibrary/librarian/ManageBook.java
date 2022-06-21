package com.example.ilibrary.librarian;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ilibrary.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class ManageBook extends AppCompatActivity {

    private String bookID, titleBook, ISBNBook, subjectBook, shelfBook, authorBook, publisherBook, yearBook, stockBook, descBook, summaryBook, imgBook;
    private ImageButton img_Book;
    private EditText ID_Book, title_Book, ISBN_Book, subject_Book, shelf_Book, author_Book, publisher_Book, year_Book, stock_Book, desc_Book, summary_Book;
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
        saveBook = findViewById(R.id.save_book);

        img_Book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AccessGallery();
            }
        });

        saveBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Manage_Book();
            }
        });
    }

    private void AccessGallery() {
        Intent GalleryIntent = new Intent();
        GalleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        GalleryIntent.setType("image/*");
        startActivityForResult(GalleryIntent, galleryPick);
    }

    private void Manage_Book() {
        bookID = ID_Book.getText().toString();
        titleBook = title_Book.getText().toString();
        ISBNBook = ISBN_Book.getText().toString();
        subjectBook = subject_Book.getText().toString();
        shelfBook = shelf_Book.getText().toString();
        authorBook = author_Book.getText().toString();
        publisherBook = publisher_Book.getText().toString();
        yearBook = year_Book.getText().toString();
        stockBook = stock_Book.getText().toString();
        descBook = desc_Book.getText().toString();
        summaryBook = summary_Book.getText().toString();

        if (image == null) {
            Toast.makeText(this, "Insert Book Image", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(bookID)) {
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

            final StorageReference file = storageRef.child(image.getLastPathSegment() + ".jpg");
            final UploadTask upload = file.putFile(image);
            upload.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    String message = e.toString();
                    Toast.makeText(ManageBook.this, "Error" + message, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(com.google.firebase.storage.UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(ManageBook.this, "Successfully Upload Book Image", Toast.LENGTH_SHORT).show();
                    Task<Uri> urlTask = upload.continueWithTask(new Continuation<com.google.firebase.storage.UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<com.google.firebase.storage.UploadTask.TaskSnapshot> task) throws Exception {
                            if (!task.isSuccessful()) {
                                throw task.getException();
                            } else {
                                imgBook = file.getDownloadUrl().toString();
                                return file.getDownloadUrl();
                            }
                        }});}});}}}
//                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Uri> task) {
//                            if (task.isSuccessful()) {
//                                imgBook = task.getResult().toString();
//                                Toast.makeText(ManageBook.this, "successfully create image url", Toast.LENGTH_SHORT).show();
//                                bookData();
//                            }
//                        }
//                    });
//                }
//            });
//        }
//        private void bookData () {
//            HashMap<String, Object> BookMap = new HashMap<>();
//            BookMap.put("image", imgBook);
//            BookMap.put("bookID", bookID);
//            BookMap.put("title", titleBook);
//            BookMap.put("ISBN", ISBNBook);
//            BookMap.put("subject", subjectBook);
//            BookMap.put("shelf", shelfBook);
//            BookMap.put("author", authorBook);
//            BookMap.put("publisher", publisherBook);
//            BookMap.put("year", yearBook);
//            BookMap.put("stock", stockBook);
//            BookMap.put("description", descBook);
//            BookMap.put("summary", summaryBook);
//
//            dbRef.child(bookID).updateChildren(BookMap)
//                    .addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if (task.isSuccessful()) {
//                                Intent intent = new Intent(ManageBook.this, ManageBook.class);
//                                startActivity(intent);
//
//                                progressDialog.dismiss();
//                                Toast.makeText(ManageBook.this, "Successfully Save Book Data", Toast.LENGTH_SHORT).show();
//                            } else {
//                                progressDialog.dismiss();
//                                String message = task.getException().toString();
//                                Toast.makeText(ManageBook.this, "Error" + message, Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//        }
//
//
//        @Override
//        protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
//            super.onActivityResult(requestCode, resultCode, data);
//            if (requestCode == galleryPick && resultCode == RESULT_OK && data != null) {
//                image = data.getData();
//                img_Book.setImageURI(image);
//            }
//        }
//
//    }
// }