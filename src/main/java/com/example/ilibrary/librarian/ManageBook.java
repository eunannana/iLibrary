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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ManageBook extends AppCompatActivity {

    private EditText code_Book, title_Book, subject_Book,author_Book;
    private Button saveBook;
    private ProgressDialog progressDialog;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_book);

        code_Book = findViewById(R.id.codeBook);
        title_Book = findViewById(R.id.titleBook);
        subject_Book = findViewById(R.id.subjectBook);
        author_Book = findViewById(R.id.authorBook);

        saveBook = findViewById(R.id.save_book_button);

        progressDialog = new ProgressDialog(ManageBook.this);
        progressDialog.setTitle("On process");
        progressDialog.setMessage("Save Book Data...");

        saveBook.setOnClickListener(v -> {
            if (code_Book.getText().length() > 0 && title_Book.getText().length() > 0 && subject_Book.getText().length() > 0 && author_Book.getText().length() > 0) {
                saveBookData(code_Book.getText().toString(), title_Book.getText().toString(), subject_Book.getText().toString(), author_Book.getText().toString());
            } else {
                Toast.makeText(getApplicationContext(), "Please fill all data!", Toast.LENGTH_SHORT).show();
            }
        });
        Intent intent = getIntent();
        if (intent != null) {
            id = intent.getStringExtra("id");
            code_Book.setText(intent.getStringExtra("code"));
            title_Book.setText(intent.getStringExtra("title"));
            subject_Book.setText(intent.getStringExtra("subject"));
            author_Book.setText(intent.getStringExtra("author"));
        }
    }
    private void saveBookData(String code, String title, String subject, String author){
    Map<String, Object> book = new HashMap<>();
    book.put("code", code);
    book.put("title", title);
    book.put("subject", subject);
    book.put("author", author);

    progressDialog.show();
    if(id!=null){
        db.collection("book").document(id)
                .set(book)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(getApplicationContext(), "Failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }else{
        // code to add data with .add
        db.collection("book")
                .add(book)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>(){
                    @Override
                    public void onSuccess(DocumentReference documentReference){
                        Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener(){
                    @Override
                    public void onFailure(@NonNull Exception e){
                        Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                });
    }
    }
}