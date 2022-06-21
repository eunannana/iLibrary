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

public class ManageMember extends AppCompatActivity {

    private EditText id_member, name_member, phone_member;
    private Button save_button;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_member);
        id_member = findViewById(R.id.IDmember_resv);
        name_member = findViewById(R.id.nameMember);
        phone_member = findViewById(R.id.phoneMember);
        save_button = findViewById(R.id.save_member_button);

        progressDialog = new ProgressDialog(ManageMember.this);
        progressDialog.setTitle("On process");
        progressDialog.setMessage("Save Member Data...");

        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });
    }
    private void saveData(){
        String ID = id_member.getText().toString();
        String name = name_member.getText().toString();
        String phone = phone_member.getText().toString();

        if(TextUtils.isEmpty(ID)){
            Toast.makeText(this, "Input Member ID", Toast.LENGTH_SHORT).show();}
        else if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "Input Member Name", Toast.LENGTH_SHORT).show();}
        else if(TextUtils.isEmpty(phone)){
            Toast.makeText(this, "Input Member Phone", Toast.LENGTH_SHORT).show();}
        else{
            progressDialog.setTitle("On Process...");
            progressDialog.setMessage("Save Data");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            validateData(ID, name, phone);
        }
    }
    private void validateData(final String ID, final String name,  final String phone){
        final DatabaseReference db;
        db = FirebaseDatabase.getInstance().getReference();
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!(dataSnapshot.child("User").child("Member").child(ID).exists())) {
                    HashMap<String, Object> UserDataMap = new HashMap<>();
                    UserDataMap.put("ID", ID);
                    UserDataMap.put("name", name);
                    UserDataMap.put("phone", phone);

                    db.child("User").child("Member").child(ID).updateChildren(UserDataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(ManageMember.this, "Successfully Save Member Data", Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                        Intent i = new Intent(ManageMember.this, MemberList.class);
                                        startActivity(i);
                                    } else {
                                        progressDialog.dismiss();
                                        Toast.makeText(ManageMember.this, "Error, please try again", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(ManageMember.this, "this" + ID + "is already exist", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    Toast.makeText(ManageMember.this, "Input a new ID", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(ManageMember.this, MemberList.class);
                    startActivity(i);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}