package com.myandayush.college.ui.hhome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.myandayush.college.R;
import com.myandayush.college.ui.about.CourseModel;

import java.util.List;

public class HomeAdapter extends PagerAdapter {
    private Context context;
    private List<HomeModel> list;

    public HomeAdapter(Context context, List<HomeModel> list) {
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

    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_hzuitem,container,false);

        TextView courseDescription;

        courseDescription = view.findViewById(R.id.courseDescription);

        courseDescription.setText(list.get(position).getDescription());

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
