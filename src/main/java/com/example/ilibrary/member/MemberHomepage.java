package com.example.ilibrary.member;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ilibrary.R;

public class MemberHomepage extends AppCompatActivity {

    TextView viewBook, makeReservation;
    ImageView imageBook, imageReservation;
    Button signout_member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_homepage);
        signout_member = findViewById(R.id.signout_member);
        viewBook = findViewById(R.id.BookMember);
        makeReservation = findViewById(R.id.reservation_member);
        imageBook = findViewById(R.id.imgBookMember);
        imageReservation = findViewById(R.id.imgResvMember);

        signout_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signout_member = new Intent(getApplicationContext(), member_login.class);
                startActivity(signout_member);
            }
        });

        viewBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewBook = new Intent(getApplicationContext(), MemberViewBook.class);
                startActivity(viewBook);
            }
        });
        imageBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewBook = new Intent(getApplicationContext(), MemberViewBook.class);
                startActivity(viewBook);
            }
        });
        makeReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resv = new Intent(getApplicationContext(), ReservationBook.class);
                startActivity(resv);
            }
        });
        imageReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resv = new Intent(getApplicationContext(), ReservationBook.class);
                startActivity(resv);
            }
        });
    }
}