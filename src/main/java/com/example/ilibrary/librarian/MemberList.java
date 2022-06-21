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
import android.widget.Button;

import com.example.ilibrary.R;
import com.example.ilibrary.view.MemberViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.ilibrary.model.User;

import java.lang.reflect.Member;

public class MemberList extends AppCompatActivity {

    private FloatingActionButton fab_save;
    private RecyclerView recyclerView;
    private DatabaseReference ref;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);

        fab_save = findViewById(R.id.save_member);
        ref = FirebaseDatabase.getInstance().getReference().child("User").child("Member");
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        fab_save.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), ManageMember.class));
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        FirebaseRecyclerOptions<User> options = new FirebaseRecyclerOptions.Builder<User>().setQuery(ref, User.class).build();
        FirebaseRecyclerAdapter<User, MemberViewHolder> adapter = new FirebaseRecyclerAdapter<User, MemberViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MemberViewHolder holder, int position, @NonNull User model) {
                holder.textMemberID.setText(model.getID());
                holder.textMemberName.setText(model.getName());

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(getApplicationContext(), ManageMember.class);
                        i.putExtra("ID", model.getID());
                        startActivity(i);
                    }
                });
            }
            @NonNull
            @Override public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewtype)
            {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_member, parent, false);
                MemberViewHolder holder = new MemberViewHolder(view);
                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

}