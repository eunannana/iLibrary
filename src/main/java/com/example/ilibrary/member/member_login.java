package com.example.ilibrary.member;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ilibrary.R;
import com.example.ilibrary.librarian.LibrarianHomepage;
import com.example.ilibrary.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class member_login extends AppCompatActivity {

    Button member_login;
    EditText ID_member, pass_member;
    private String memberID, memberPass, memberName;
    private String ParentDbName = "User";
    private ProgressDialog loadBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_login);

        member_login = findViewById(R.id.login_member_button);

        ID_member = findViewById(R.id.member_ID);
        pass_member = findViewById(R.id.member_Pass);

        loadBar = new ProgressDialog(this);

        member_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateMember();
            }
        });
    }
    public void validateMember(){
        memberID = ID_member.getText().toString();
        memberPass = pass_member.getText().toString();

        if(TextUtils.isEmpty(memberID)){
            Toast.makeText(this, "Input Member ID!", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(memberPass)){
            Toast.makeText(this, "Input Member password!", Toast.LENGTH_SHORT).show();
        }else{
            loadBar.setTitle("Login Process");
            loadBar.setMessage("Please wait...");
            loadBar.setCanceledOnTouchOutside(false);
            loadBar.show();

            validateMemberAcc(memberID, memberPass);
        }
    }

    private void validateMemberAcc(final String ID, final String phone){
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(ParentDbName).child("Member").child(ID).exists())
                {
                    User UserData = snapshot.child(ParentDbName).child("Member").child(ID).getValue(User.class);
                    if(UserData.getID().equals(ID))
                    {
                        if(UserData.getPhone().equals(phone))
                        {
                            loadBar.dismiss();
                            Toast.makeText(getApplicationContext(), "Welcome, iLibrary Member!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(getApplicationContext(), MemberHomepage.class);
                            startActivity(intent);
                        }
                        else{
                            loadBar.dismiss();
                            Toast.makeText(getApplicationContext(), "Incorrect Password!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                else{
                    loadBar.dismiss();
                    Toast.makeText(getApplicationContext(), "Member ID and Password doesn't exist!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


    }
}