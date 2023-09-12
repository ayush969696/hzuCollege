package com.myandayush.college.ui.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.myandayush.college.R;

import java.util.ArrayList;
import java.util.List;

public class AboutFragment extends Fragment {

    private ViewPager viewPager;
    private CourseAdapter adapter;
    private List<CourseModel> list;
    private ImageView map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_about, container, false);
        list = new ArrayList<>();
        list.add(new CourseModel(R.drawable.csit,"School of Science and Technology","The School of Science and Technology at HZU came to existence in 2008 with the inception of different Computer Science Programs. We emphasize on sound Computer Science fundamentals, Coding, Research, Innovation, and Entrepreneurship. Currently, we offer B.Tech. in CSE (with Specialization in Internet of Things, Data Science, Artificial Intelligence & Machine Learning and Cyber Security, BCA in Internet of Things and Data Science."));
        list.add(new CourseModel(R.drawable.agriculture,"School of Agriculture, Forestry & Fisheries","School of Agriculture, Forestry and Fisheries is one of the most prestigious constituent of Himgiri Zee University.Our students and Faculty are actively involved in Research. All the departments of the school aims to create innovative thinkers and instill technical and managerial skills among graduate and postgraduate students to prepare them to pursue a rewarding career in the field of Agriculture and Allied Sciences."));
        list.add(new CourseModel(R.drawable.humanitiesstudy,"School of Education and Humanities","SEH has Education and Humanities departments with diverse programs. We focus on value-based student development for societal benefit. Our aim is skilled professionals through traditional and digital learning, guided by \"Youth are Nation Builders\". We integrate New Education Policy and 21st-century skills for well-rounded growth. Our student-centric approach readies adaptable professionals for future challenges, working towards a better world."));
        list.add(new CourseModel(R.drawable.massmedia,"School of Journalism and Mass Communication", "Himgiri Zee University's Journalism and Mass Communication School readies students for media with diverse programs and hands-on training. Led by industry-experienced faculty, it blends practical learning in studios and a radio station with internships. Nestled in the tranquil Himalayan foothills, it's the perfect launchpad for media hopefuls."));
        list.add(new CourseModel(R.drawable.busines,"School Of Business Studies","The School of Business Studies provides specialized programs cultivating skills in Finance, HR, Marketing, and more. The curriculum prioritizes functional expertise, critical thinking, and holistic development. Through interactive activities, guest lectures, and expert guidance, students are prepared for success in government, private, and entrepreneurial sectors."));
        list.add(new CourseModel(R.drawable.hm,"School of Hotel & Hospitality Management","Himgiri Zee University's School of Hotel & Hospitality Management fills the skill gap in India's Hospitality sector. It offers diverse programs, including Bachelor of Hotel Management, Bachelor of Tourism & Travel Management, and more. With modern infrastructure and industry connections, students gain practical experience and expertise."));
        list.add(new CourseModel(R.drawable.lawstudy,"School of Legal Studies","The School of Legal Studies at Himgiri Zee University offers diverse and holistic legal education. It promotes interdisciplinary and comparative legal research. We provide a dynamic learning environment for students to excel in law. Admitting bright learners with analytical skills, our focus is on experimental learning and soft skill development. Activities like Moot court training, Legal aid clinics, Seminars, Debates, and Workshops are emphasized."));
        list.add(new CourseModel(R.drawable.pharmacy,"School of Pharmaceutical Sciences","Established in 2018, the School of Pharmaceutical Sciences added the Pharmacy department in 2019, approved by the Pharmacy Council of India. We offer programs like Pharmacy Diploma and Bachelor, as well as Master's and Ph.D. options in fields including Clinical Research, Health Care Management, Public Health, and Pharmaceutical Sciences."));
        adapter = new CourseAdapter(getContext(),list);
        viewPager = view.findViewById(R.id.viewPages);
        viewPager.setAdapter(adapter);

        ImageView imageView = view.findViewById(R.id.collegeImage);
        Glide.with(getContext())
                .load("https://firebasestorage.googleapis.com/v0/b/hzu-app-e1af9.appspot.com/o/Gallery%2F%5BB%401096025jpg?alt=media&token=22a654e9-ddd3-4516-858b-70fad8b708d4")
                .into(imageView);

        map = view.findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });

        return view;
    }

    private void openMap() {
        Uri uri = Uri.parse("geo:0, 0?q=Himgiri Zee University");

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }

}