package com.myandayush.college.ui.about;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.myandayush.college.R;
import com.myandayush.college.ui.faculty.TeacherAdapter;

import java.util.List;

public class CourseAdapter extends PagerAdapter {
    private Context context;
    private List<CourseModel> list;

    public CourseAdapter(Context context, List<CourseModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @SuppressLint("MissingInflatedId")
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.course_item_layout,container,false);

        ImageView courseIcon;
        TextView courseTitle, courseDescription;

        courseIcon = view.findViewById(R.id.courseIcon);
        courseTitle = view.findViewById(R.id.courseTitle);
        courseDescription = view.findViewById(R.id.courseDescription);

        courseIcon.setImageResource(list.get(position).getImg());
        courseTitle.setText(list.get(position).getTitle());
        courseDescription.setText(list.get(position).getDescription());

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
