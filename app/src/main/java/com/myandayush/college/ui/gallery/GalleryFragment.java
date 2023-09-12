package com.myandayush.college.ui.gallery;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.myandayush.college.R;

import java.util.ArrayList;
import java.util.List;

public class GalleryFragment extends Fragment {

    RecyclerView specialOccasionsRecyclerView,studentlifeRecyclerView,  sportsRecyclerView,campusFacilitiesRecyclerView, technologyRecyclerView,
            eventRecyclerView, environmentalInitiativesRecyclerView, transportationRecyclerView,otherRecyclerView;
    GalleryAdapter adapter;
    DatabaseReference reference;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_gallery, container, false);

        specialOccasionsRecyclerView = view.findViewById(R.id.specialOccasionsRecyclerView);
        studentlifeRecyclerView = view.findViewById(R.id.studentlifeRecyclerView);
        sportsRecyclerView = view.findViewById(R.id.sportsRecyclerView);
        campusFacilitiesRecyclerView = view.findViewById(R.id.campusFacilitiesRecyclerView);
        technologyRecyclerView = view.findViewById(R.id.technologyRecyclerView);
        eventRecyclerView = view.findViewById(R.id.eventRecyclerView);
        environmentalInitiativesRecyclerView = view.findViewById(R.id.environmentalInitiativesRecyclerView);
        transportationRecyclerView = view.findViewById(R.id.transportationRecyclerView);
        otherRecyclerView = view.findViewById(R.id.otherRecyclerView);

        reference = FirebaseDatabase.getInstance().getReference("Category").child("Gallery");

        getspecialOccasions();
        getstudentlife();
        getsports();
        getcampusFac();
        gettechnology();
        getevent();
        getenvironment();
        gettransport();
        getOther();

        return view;
    }

    private void getcampusFac() {

        reference.child("Campus Facilities").addValueEventListener(new ValueEventListener() {

            List<String> imagelist = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    String data = (String) snapshot1.getValue();
                    imagelist.add(data);
                }
                adapter = new GalleryAdapter(getContext(), imagelist);
                campusFacilitiesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
                campusFacilitiesRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getstudentlife() {
        reference.child("Student Life").addValueEventListener(new ValueEventListener() {

            List<String> imagelist = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    String data = (String) snapshot1.getValue();
                    imagelist.add(data);
                }
                adapter = new GalleryAdapter(getContext(), imagelist);
                studentlifeRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
                studentlifeRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getsports() {
        reference.child("Sports Activity").addValueEventListener(new ValueEventListener() {

            List<String> imagelist = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    String data = (String) snapshot1.getValue();
                    imagelist.add(data);
                }
                adapter = new GalleryAdapter(getContext(), imagelist);
                sportsRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
                sportsRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getspecialOccasions() {
        reference.child("Special Occasions").addValueEventListener(new ValueEventListener() {

            List<String> imagelist = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    String data = (String) snapshot1.getValue();
                    imagelist.add(data);
                }
                adapter = new GalleryAdapter(getContext(), imagelist);
                specialOccasionsRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
                specialOccasionsRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void gettechnology() {
        reference.child("Technology").addValueEventListener(new ValueEventListener() {

            List<String> imagelist = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    String data = (String) snapshot1.getValue();
                    imagelist.add(data);
                }
                adapter = new GalleryAdapter(getContext(), imagelist);
                technologyRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
                technologyRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getevent() {
        reference.child("Event").addValueEventListener(new ValueEventListener() {

            List<String> imagelist = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    String data = (String) snapshot1.getValue();
                    imagelist.add(data);
                }
                adapter = new GalleryAdapter(getContext(), imagelist);
                eventRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
                eventRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getenvironment() {
        reference.child("Environmental Initiatives").addValueEventListener(new ValueEventListener() {

            List<String> imagelist = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    String data = (String) snapshot1.getValue();
                    imagelist.add(data);
                }
                adapter = new GalleryAdapter(getContext(), imagelist);
                environmentalInitiativesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
                environmentalInitiativesRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void gettransport() {
        reference.child("Transportation").addValueEventListener(new ValueEventListener() {

            List<String> imagelist = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    String data = (String) snapshot1.getValue();
                    imagelist.add(data);
                }
                adapter = new GalleryAdapter(getContext(), imagelist);
                transportationRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
                transportationRecyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getOther() {
            reference.child("Others").addValueEventListener(new ValueEventListener() {

                List<String> imagelist = new ArrayList<>();

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        String data = (String) snapshot1.getValue();
                        imagelist.add(data);
                    }
                    adapter = new GalleryAdapter(getContext(), imagelist);
                    otherRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
                    otherRecyclerView.setAdapter(adapter);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

}