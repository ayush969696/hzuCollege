package com.myandayush.college.ui.faculty;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.myandayush.college.R;

import java.util.ArrayList;
import java.util.List;

public class FacultyFragment extends Fragment {

    private RecyclerView topAuthority;
    private TeacherAdapter adapter;
    private DatabaseReference reference, dref;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_faculty, container, false);

        topAuthority = (RecyclerView) view.findViewById(R.id.topAuthority);


        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("Category");
        Query query = rootRef.child("Teacher");

        FirebaseRecyclerOptions<TeacherData> firebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<TeacherData>()
                .setQuery(query, TeacherData.class)
                .build();

        adapter = new TeacherAdapter(firebaseRecyclerOptions);
        topAuthority.setLayoutManager(new LinearLayoutManager(getContext()));
        topAuthority.setAdapter(adapter);

        return  view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}

