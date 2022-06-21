package com.example.ilibrary.librarian;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.ilibrary.R;
import com.example.ilibrary.model.book;
import com.example.ilibrary.view.BookViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BookList extends AppCompatActivity {

    private FloatingActionButton fab_save_book;
    private RecyclerView recyclerView;
    private DatabaseReference ref;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        fab_save_book = findViewById(R.id.button_save_book);
        ref = FirebaseDatabase.getInstance().getReference().child("Book").child("bookID");
        recyclerView = findViewById(R.id.recycler_view_book);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        fab_save_book.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ManageBook.class));
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        FirebaseRecyclerOptions<book> options = new FirebaseRecyclerOptions.Builder<book>().setQuery(ref, book.class).build();
        FirebaseRecyclerAdapter<book, BookViewHolder> adapter = new FirebaseRecyclerAdapter<book, BookViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull BookViewHolder holder, int position, @NonNull book model) {
                holder.bookID.setText(model.getBook_ID());
                holder.titleBook.setText(model.getBook_title());
                holder.ISBNBook.setText(model.getBook_ISBN());
                holder.subjectBook.setText(model.getBook_subject());
                holder.shelfBook.setText(model.getBook_shelf());
                holder.authorBook.setText(model.getBook_author());
                holder.publisherBook.setText(model.getBook_publisher());
                holder.yearBook.setText(model.getBook_year_publish());
                holder.stockBook.setText(model.getBook_stock());
                holder.descBook.setText(model.getBook_physical());
                holder.summaryBook.setText(model.getBook_summary());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getApplicationContext(), ManageBook.class);
                        i.putExtra("bookID", model.getBook_ID());
                        startActivity(i);
                    }
                });
            }
            @NonNull
            @Override public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewtype)
            {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_book, parent, false);
                BookViewHolder holder = new BookViewHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

}