package com.example.ilibrary.member;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ilibrary.librarian.BookReservationList;
import com.example.ilibrary.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class ReservationBook extends AppCompatActivity {

    private EditText memberID_resv, bookCode_resv, bookTitle_resv, date_resv, return_resv;
    private Button ReservNow;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_book);

        memberID_resv = findViewById(R.id.memberIDresv);
        bookCode_resv = findViewById(R.id.codeBookresv);
        bookTitle_resv = findViewById(R.id.titleBook_resv);
        date_resv = findViewById(R.id.resvDate);
        return_resv = findViewById(R.id.returnDate);

        ReservNow = findViewById(R.id.reservation_button);

        progressDialog = new ProgressDialog(ReservationBook.this);
        progressDialog.setTitle("On process");
        progressDialog.setMessage("Save Book Reservation...");

            ReservNow.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    saveReservation();
                }
            });
        }
        public void saveReservation(){
        String resvmID = memberID_resv.getText().toString();
        String resvcode = bookCode_resv.getText().toString();
        String resvtitle = bookTitle_resv.getText().toString();
        String dateresv = date_resv.getText().toString();
        String returnresv = return_resv.getText().toString();

        if(TextUtils.isEmpty(resvmID)) {
            Toast.makeText(this, "Input member ID", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(resvcode)) {
            Toast.makeText(this, "Input book code", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(resvtitle)) {
            Toast.makeText(this, "Input book Title", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(dateresv)) {
            Toast.makeText(this, "Input reservation date", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(returnresv)) {
            Toast.makeText(this, "Input return date", Toast.LENGTH_SHORT).show();
        }else{
            progressDialog.setTitle("On Process...");
            progressDialog.setMessage("Save Data");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }



        }

}