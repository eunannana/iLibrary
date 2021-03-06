package com.example.ilibrary.librarian;

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
import com.example.ilibrary.model.book;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class BookList extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FloatingActionButton fab_save_book;
    private RecyclerView recyclerView;
    private List<book> list = new ArrayList<>();
    private BookAdapter bookAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        fab_save_book = findViewById(R.id.button_save_book);
        recyclerView = findViewById(R.id.recycler_view_book);
        recyclerView.setHasFixedSize(true);
        progressDialog = new ProgressDialog(BookList.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Take the Data...");
        bookAdapter = new BookAdapter(getApplicationContext(), list);

        bookAdapter.setDialog(new BookAdapter.Dialog() {
            @Override
            public void onClick(int pos) {
            final CharSequence[] dialogItem = {"Edit", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(BookList.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            //send data to the next class
                            case 0:
                                Intent intent = new Intent(getApplicationContext(), ManageBook.class);
                                intent.putExtra("id", list.get(pos).getBookID());
                                intent.putExtra("code", list.get(pos).getBookCode());
                                intent.putExtra("title", list.get(pos).getBookTitle());
                                intent.putExtra("subject", list.get(pos).getBookSubject());
                                intent.putExtra("author", list.get(pos).getBookAuthor());
                                startActivity(intent);
                                break;
                            case 1:
                                // call class delete data
                                deleteData(list.get(pos).getBookID());
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

        fab_save_book.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ManageBook.class));
        });

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
    //method to delete data
    private void deleteData(String id){
        progressDialog.show();
        db.collection("book").document(id)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>(){
                    @Override
                    public void onComplete(@NonNull Task<Void> task){
                        if(!task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Failed to Delete Data!", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();

                        getData();
                    }
                });
    }
}

