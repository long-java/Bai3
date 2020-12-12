package com.example.thecoffeehouse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home extends AppCompatActivity {
    RecyclerView recycleuudai, recyclecapnhat,recycle_lover;
    itemAdapter itemAdapter;
    Button btLogin;
    TextView txtName;
    CircleImageView  imgAvatar;
    ImageView imgNotification;
    private FirebaseAuth mAuth;


    //realtime database
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btLogin = (Button) findViewById(R.id.btLogin);
        txtName = (TextView) findViewById(R.id.txtName);
        imgAvatar = (CircleImageView ) findViewById(R.id.imgAvatar) ;
        imgNotification = (ImageView) findViewById(R.id.imgNotification);

        mDatabase = FirebaseDatabase.getInstance().getReference(); //realtime database

        getInfo();

        //Sự kiện cho nút thông báo imgNotification
        imgNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent notification   = new Intent(Home.this, NotificationActivity.class);
                startActivity(notification);
            }
        });


        //Sự kiện khi đã đăng nhập và chưa đăng nhập cho Button Login và TextView User
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null)  //Nếu đã đăng nhập
        {
            btLogin.setVisibility(View.GONE);
            String email = user.getEmail();
//            String id = user.getUid();
            txtName.setText(email);
        }
        else{
            txtName.setVisibility(View.GONE);
        }


        List<item> uudailist = new ArrayList<>();
        uudailist.add(new item("Giảm 50%, thèm gi gọi nhé \"Go Green\"!","Tuần này, The Coffee House thật háo hức để gửi tặng một nửa Thế giới xinh đẹp chương trình vô cùng ngọt ngào 7 ngày Free Upsize","Order ngay",R.drawable.item_home_uudai));
        uudailist.add(new item("Top 10 cửa hàng The Coffee House bạn nên trải nghiệm tại Hà Nội","Tuần này, The Coffee House thật háo hức để gửi tặng một nửa Thế giới xinh đẹp chương trình vô cùng ngọt ngào 7 ngày Free Upsize ","Order ngay",R.drawable.item_50));
        uudailist.add(new item("Nhà mời cafe đậm đà chỉ 12k","Tuần này, The Coffee House thật háo hức để gửi tặng một nửa Thế giới xinh đẹp chương trình vô cùng ngọt ngào 7 ngày Free Upsize","Order ngay",R.drawable.item_3));
        uudailist.add(new item("Nhà mời 20% PICKUP nha, Duy nhất hôm nay","Tuần này, The Coffee House thật háo hức để gửi tặng một nửa Thế giới xinh đẹp chương trình vô cùng ngọt ngào 7 ngày Free Upsize.","Order ngay",R.drawable.item_4));
        uudailist.add(new item("Bánh ngon nhà mời, chỉ 19.000đ! ","Tuần này, The Coffee House thật háo hức để gửi tặng một nửa Thế giới xinh đẹp chương trình vô cùng ngọt ngào 7 ngày Free Upsize ","Order ngay",R.drawable.item_5));

        List<item> capnhatlist = new ArrayList<>();
        capnhatlist.add(new item("Nhà Riverside Vũ Tông Phan - Hà Nội vui khai trương","Tuần này, The Coffee House thật háo hức để gửi tặng một nửa Thế giới xinh đẹp chương trình vô cùng ngọt ngào 7 ngày Free Upsize ","Chi tiết",R.drawable.item_50));
        capnhatlist.add(new item("Taste of Xmas - Chạm vị giáng sinh","Tuần này, The Coffee House thật háo hức để gửi tặng một nửa Thế giới xinh đẹp chương trình vô cùng ngọt ngào 7 ngày Free Upsize ","Chi tiết",R.drawable.item_2));
        capnhatlist.add(new item("Trời se lạnh, thưởng thức ngay những món nóng","Tuần này, The Coffee House thật háo hức để gửi tặng một nửa Thế giới xinh đẹp chương trình vô cùng ngọt ngào 7 ngày Free Upsize","Chi tiết",R.drawable.item_3));
        capnhatlist.add(new item("Cùng trải nghiệm PICKUP với TheCoffeeHouse","Tuần này, The Coffee House thật háo hức để gửi tặng một nửa Thế giới xinh đẹp chương trình vô cùng ngọt ngào 7 ngày Free Upsize ","Chi tiết",R.drawable.item_4));
        capnhatlist.add(new item("Ưu đãi khủng giảm 50% trên thực đơn","Tuần này, The Coffee House thật háo hức để gửi tặng một nửa Thế giới xinh đẹp chương trình vô cùng ngọt ngào 7 ngày Free Upsize ","Chi tiết",R.drawable.item_5));

        List<item>  loverlist = new ArrayList<>();
        loverlist.add(new item("Bản sắc nơi đất trồng, Một hành trình tìm về cội nguồn","Tuần này, The Coffee House thật háo hức để gửi tặng một nửa Thế giới xinh đẹp chương trình vô cùng ngọt ngào 7 ngày Free Upsize .","Chi tiết",R.drawable.item_50));
        loverlist.add(new item("Hương vị cafe mới tại nhà Signature","Tuần này, The Coffee House thật háo hức để gửi tặng một nửa Thế giới xinh đẹp chương trình vô cùng ngọt ngào 7 ngày Free Upsize ","Chi tiết",R.drawable.item_2));
        loverlist.add(new item("Cảm ơn bạn, vì đã luôn là một bản nguyên khác biệt","Tuần này, The Coffee House thật háo hức để gửi tặng một nửa Thế giới xinh đẹp chương trình vô cùng ngọt ngào 7 ngày Free Upsize ","Chi tiết",R.drawable.item_3));
        loverlist.add(new item("Costa Rica - Tách hand Brew mới, Bạn đã thử chưa ? ","Tuần này, The Coffee House thật háo hức để gửi tặng một nửa Thế giới xinh đẹp chương trình vô cùng ngọt ngào 7 ngày Free Upsize ","Chi tiết",R.drawable.item_4));
        loverlist.add(new item("Chuyện về chai Tonci và TheCoffeHouse","Tuần này, The Coffee House thật háo hức để gửi tặng một nửa Thế giới xinh đẹp chương trình vô cùng ngọt ngào 7 ngày Free Upsize ","Chi tiết",R.drawable.item_5));

        setRecycle_uudai(uudailist);
        setRecyclecapnhat(capnhatlist);
        setRecycle_lover(loverlist);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.news);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.news:
//                        startActivity(new Intent(getApplicationContext(),Home.class));
//                        overridePendingTransition(0,0);
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
                        startActivity(new Intent(getApplicationContext(),account_screen.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        ///Su kien  nhan btLogin
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Home.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }

    ////Function get Info nguoi dung
    public void getInfo(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null)  //Nếu đã đăng nhập, tồn tại user
        {
            final long ONE_MEGABYTE=1024*1024;
//           StorageReference storageHinhAnh  = FirebaseStorage.getInstance().getReference().child("c4917b3a-6a45-4a4a-abdd-def4bbf61205");
            StorageReference storageHinhAnh  = FirebaseStorage.getInstance().getReference().child("NguoiDung/"+"30f53613-8052-4cb2-a6b3-3f8d521a8659");
            storageHinhAnh.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {

                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                    imgAvatar.setImageBitmap(bitmap);
                }
            });
        }
        else  //Nếu chưa đăng nhập, user rỗng
        {

        }
    }


    public void setRecycle_uudai(List<item> itemList ){
        recycleuudai=findViewById(R.id.recycle_uudai);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        recycleuudai.setLayoutManager(layoutManager);
        itemAdapter = new itemAdapter(this,itemList);
        recycleuudai.setAdapter(itemAdapter);
    }
    public void setRecyclecapnhat(List<item> itemList ){
        recyclecapnhat=findViewById(R.id.recycle_capnhat);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        recyclecapnhat.setLayoutManager(layoutManager);
        itemAdapter = new itemAdapter(this,itemList);
        recyclecapnhat.setAdapter(itemAdapter);
    }
    public void setRecycle_lover(List<item> itemList ){
        recycle_lover=findViewById(R.id.recycle_lover);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        recycle_lover.setLayoutManager(layoutManager);
        itemAdapter = new itemAdapter(this,itemList);
        recycle_lover.setAdapter(itemAdapter);
    }
}