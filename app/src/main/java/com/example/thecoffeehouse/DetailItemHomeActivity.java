package com.example.thecoffeehouse;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailItemHomeActivity extends AppCompatActivity {
ImageView imgAvatarDetail;
TextView txtTitleDetail;
TextView txtTextDetail;
String playerTitle, playerText;
int image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_home);

        imgAvatarDetail = (ImageView) findViewById(R.id.imgAvatarDetail);
        txtTitleDetail = (TextView) findViewById(R.id.txtTitleDetail);
        txtTextDetail = (TextView) findViewById(R.id.txtTextDetail);

        playerTitle = getIntent().getStringExtra("title");
        playerText = getIntent().getStringExtra("text");
        image = getIntent().getIntExtra("image",0);

        txtTitleDetail.setText(playerTitle);
        txtTextDetail.setText(playerText);
        imgAvatarDetail.setImageResource(image);

    }
}