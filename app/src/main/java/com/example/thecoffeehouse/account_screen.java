package com.example.thecoffeehouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class account_screen extends AppCompatActivity {

    LinearLayout logout;
    private FirebaseAuth mAuth;
    TextView txtNameprofile;
    private DatabaseReference mDatabase; //realtime


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_screen);
        mAuth = FirebaseAuth.getInstance();



        txtNameprofile = (TextView) findViewById(R.id.txtNameProfile);
        logout = (LinearLayout) findViewById(R.id.logout);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.account);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.news:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.delivery:
                        startActivity(new Intent(getApplicationContext(),orderScreen.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.store:
                        startActivity(new Intent(getApplicationContext(), map_screen.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.account:
//                        startActivity(new Intent(getApplicationContext(),account_screen.class));
//                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });



        ///Visible cho nut Đăng xuất và set chữ cho txtNameProfile
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null)  //Nếu chưa đăng nhập, user rỗng
        {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            txtNameprofile.setText(email);
            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
//            btLogin.setVisibility(View.GONE);
        }else{
            logout.setVisibility(View.GONE);
        }


        ////////Su kien dang xuat
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(account_screen.this, "Cliked", Toast.LENGTH_SHORT).show();
                mAuth.signOut();
                Intent intent=new Intent(account_screen.this, Home.class);
                startActivity(intent);

            }
        });

    }




}



























