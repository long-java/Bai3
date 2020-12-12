package com.example.thecoffeehouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import Model.NguoiDungModel;

public class LoginActivity extends AppCompatActivity implements FirebaseAuth.AuthStateListener,View.OnClickListener {

    private FirebaseAuth mAuth; //Khai báo một phiên bản của FirebaseAuth
    TextView txtChuaCoTaiKhoan;
    EditText email;
    EditText password;
    EditText user;
    Button btLogin;
    Button btRegister;


    //realtime database
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mDatabase = FirebaseDatabase.getInstance().getReference(); //realtime database

        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance(); //khởi tạo phiên bản FirebaseAuth

        btLogin = (Button) findViewById(R.id.btLogin);
        btRegister = (Button) findViewById(R.id.btRegister);
        email = (EditText) findViewById(R.id.edtEmail);
        password = (EditText) findViewById(R.id.edtPassword);
        user = (EditText) findViewById(R.id.edtUser);
        txtChuaCoTaiKhoan = (TextView) findViewById(R.id.txtChuaCoTaiKhoan);

        btRegister.setVisibility(View.GONE); //Mới vào sẽ không có nút đăng ký
        user.setVisibility(View.GONE); //Mới vào sẽ không có EditText user


        //Sự kiện khi nhấn vào chưa có tài khoản (hiện nút Đăng ký, thêm edtUser, ẩn nút Login)
        txtChuaCoTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btLogin.setVisibility(View.GONE);
                btRegister.setVisibility(View.VISIBLE);
                user.setVisibility(View.VISIBLE);
            }
        });


        ///Sự kiện nhấn button Login
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty())
                    Toast.makeText(LoginActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                else
                    Login(email.getText().toString(), password.getText().toString());

            }

        });
        //////////////////////////

        ///////////// Sự kiện nhấn button đăng ký
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (user.getText().toString().isEmpty() || email.getText().toString().isEmpty() || password.getText().toString().isEmpty())
                    Toast.makeText(LoginActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                else
                    //gọi hàm register để đăng ký ghi dữ liệu vào realtime vá auth
                    Register(user.getText().toString(),email.getText().toString(), password.getText().toString());
                    Toast.makeText(LoginActivity.this, "Register Success", Toast.LENGTH_SHORT).show();
                    Intent iTrangChu = new Intent(LoginActivity.this, Home.class);
                    startActivity(iTrangChu);
            }
        });
        /////// End đăng ký



    }///protected

    ////////////////kiem tra da dang nhap hay chua
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    /////////////Dang nhap
    void Login(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
//                            showDialog();
                            Intent iTrangChu = new Intent(LoginActivity.this, Home.class);
                            startActivity(iTrangChu);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Trạng thái", "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không hợp lệ", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
     ////////////Dang ky
    void Register(String userId, String email, String password) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
//                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                updateUI(user);

//                                writeNewUser(String userId, String email, String image )
//                                writeNewUser("imgggg",email,"userrrrr");

                                writeNewPost("imgg",email.toString(),userId);

                            } else {
                                // If sign in fails, display a message to the user.
//                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                updateUI(null);
                            }

                            // ...
                        }
                    });
     }

     /////////////////End Đăng ký







    @Override
    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Toast.makeText(LoginActivity.this, "Đã đăng nhập với " + currentUser.getEmail(), Toast.LENGTH_SHORT).show();
            Intent iTrangChu = new Intent(LoginActivity.this, Home.class);
            startActivity(iTrangChu);
            finish();
        }


    }


    ///Cập nhật thông tin khi đăng ký Push() vào Realtime Database
    private void writeNewPost(String image, String email, String userId) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        String key = mDatabase.child("posts").push().getKey();
        NguoiDungModel nguoiDungModel = new NguoiDungModel(image, email, userId);
        Map<String, Object> postValues = nguoiDungModel.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/Profile/" +userId, postValues);
//        childUpdates.put("/user-posts/" + userId + "/" + key, postValues);

        mDatabase.updateChildren(childUpdates);
    }









    @Override
    protected void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(this);
    }

    private void updateUI(FirebaseUser user){

    }


    @Override
    public void onClick(View v) {

    }

    public void showDialog()
    {
            //Tạo đối tượng
            AlertDialog.Builder b = new AlertDialog.Builder(this);
    //Thiết lập tiêu đề
            b.setTitle("Xác nhận");
            b.setMessage("Bạn đã đăng nhập thành công !");
    // Nút Ok
//            b.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int id) {
//                    finish();
//                }
//            });
    //Nút Cancel
            b.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
    //Tạo dialog
            AlertDialog al = b.create();
    //Hiển thị
            al.show();
    }

}