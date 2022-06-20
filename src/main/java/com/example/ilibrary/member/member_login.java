package com.example.ilibrary.member;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ilibrary.R;

public class member_login extends AppCompatActivity {

    Button member_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_login);

        member_login = findViewById(R.id.login_member_button);

        member_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent m_login = new Intent(getApplicationContext(), MemberHomepage.class);

                startActivity(m_login);
            }
        });

    }
}