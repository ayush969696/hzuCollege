package com.myandayush.college;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

public class FullImagView extends AppCompatActivity {

    private PhotoView photoView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_imag_view);

        setTitle("Himgiri Zee University");
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#561E92"));
        assert actionBar != null;
        actionBar.setBackgroundDrawable(colorDrawable);

        photoView = findViewById(R.id.photoView);

        String image = getIntent().getStringExtra("image");
        Glide.with(this).load(image).into(photoView);



    }
}