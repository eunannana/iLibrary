package com.example.ilibrary.member;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ilibrary.R;
import com.example.ilibrary.adapter.BookAdapter;
import com.example.ilibrary.librarian.BookList;
import com.example.ilibrary.librarian.ManageBook;
import com.example.ilibrary.model.book;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MemberViewBook extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RecyclerView recyclerView;
    private List<book> list = new ArrayList<>();
    private BookAdapter bookAdapter;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_view_book);
        recyclerView = findViewById(R.id.recycler_view_book);
        recyclerView.setHasFixedSize(true);
        progressDialog = new ProgressDialog(MemberViewBook.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Take the Data...");
        bookAdapter = new BookAdapter(getApplicationContext(), list);

        bookAdapter.setDialog(new BookAdapter.Dialog() {
            @Override
            public void onClick(int pos) {
                final CharSequence[] dialogItem = {"Make Reservation"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(MemberViewBook.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(getApplicationContext(), ReservationBook.class);
                                intent.putExtra("code", list.get(pos).getBookCode());
                                intent.putExtra("title", list.get(pos).getBookTitle());
                                intent.putExtra("subject", list.get(pos).getBookSubject());
                                intent.putExtra("author", list.get(pos).getBookAuthor());
                                startActivity(intent);
                                break;

                        }
                    }
                });
                dialog.show();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL,false);
        RecyclerView.ItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(bookAdapter);

    }
    @Override
    protected void onStart()
    {
        super.onStart();
        getData();
    }
    //method to display data
    private void getData(){
        progressDialog.show();
        // take data from firestore

        db.collection("book")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>(){
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task){
                        list.clear();
                        if(task.isSuccessful()){
                            //code to take data from collection
                            for (QueryDocumentSnapshot document : task.getResult()){
                                //data that want to take from collection
                                book book = new book(document.getString("code"), document.getString("title"), document.getString("subject"), document.getString("author"));
                                book.setBookID(document.getId());
                                list.add(book);
                            }
                            bookAdapter.notifyDataSetChanged();
                        }else{
                            Toast.makeText(getApplicationContext(), "Failed to take Data!", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }
    }
