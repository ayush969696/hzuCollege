package com.myandayush.college.ebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myandayush.college.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EbootActivity extends AppCompatActivity {

    private RecyclerView ebookrecycler;
    private DatabaseReference reference;
    private List<ebookData> list;
    private EbookAdapter adapter;
    private EditText searchPdf;
    private ShimmerFrameLayout shimmer_view_container;
//    private ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eboot);

        setTitle("E-Books");
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#561E92"));
        assert actionBar != null;
        actionBar.setBackgroundDrawable(colorDrawable);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ebookrecycler = (RecyclerView) findViewById(R.id.ebookrecycler);
        shimmer_view_container = findViewById(R.id.shimmer_view_container);
        searchPdf = findViewById(R.id.search_text);

        reference = FirebaseDatabase.getInstance().getReference("Category").child("pdf");

        getData();

    }

    private void getData() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                for(DataSnapshot snapshot1 : snapshot.getChildren()){
                    ebookData data = snapshot1.getValue(ebookData.class);
                    list.add(data);
                }
                adapter = new EbookAdapter(list);
                ebookrecycler.setLayoutManager(new LinearLayoutManager(EbootActivity.this));
                ebookrecycler.setAdapter(adapter);
                shimmer_view_container.stopShimmer();
                shimmer_view_container.setVisibility(View.GONE);
                searchPdf.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(EbootActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        // search for pdf
        searchPdf.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

    }
    private void filter(String text){
        ArrayList<ebookData> filterlist = new ArrayList<>();
        for(ebookData item : list){
            if(item.getpdfTitle().toLowerCase().contains(text.toLowerCase())){
                filterlist.add(item);
            }
        }

        adapter.FilteredList(filterlist);
    }


    @Override
    protected void onPause() {
        shimmer_view_container.stopShimmer();
        super.onPause();
    }

    @Override
    protected void onResume() {
        shimmer_view_container.startShimmer();
        super.onResume();
    }
}