package com.myandayush.college.ui.notice;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myandayush.college.R;

import java.util.ArrayList;

public class NoticeFragment extends Fragment {


    private RecyclerView deleteNoticeRecyclerView;
    private ProgressBar progressBar;
    private ArrayList<NoticeData> list;
    private NoticeAdapter adapter;
    private DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_notice, container, false);


        deleteNoticeRecyclerView = (RecyclerView)view.findViewById(R.id.deleteNoticeRecyclerView);
        progressBar = view.findViewById(R.id.prgressbar);
        deleteNoticeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        deleteNoticeRecyclerView.setHasFixedSize(true);

        reference = FirebaseDatabase.getInstance().getReference("Category").child("Notice");

        list = new ArrayList<>();

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    NoticeData data = postSnapshot.getValue(NoticeData.class);  //getting the data
                    list.add(0,data);
                }

                adapter = new NoticeAdapter(getContext(), list);
                progressBar.setVisibility(View.GONE);
                deleteNoticeRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });

        return view;
    }
}