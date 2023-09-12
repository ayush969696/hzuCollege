package com.myandayush.college.ui.notice;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.myandayush.college.FullImagView;
import com.myandayush.college.R;

import java.util.ArrayList;


public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewAdapter> {

    private Context context;
    private ArrayList<NoticeData> list;

    public NoticeAdapter(Context context, ArrayList<NoticeData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NoticeViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsfeed_item_layout, parent, false);
        return new NoticeViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewAdapter holder, int position) {
        NoticeData currentItem = list.get(position);
        holder.noticeTitle.setText(currentItem.getTitle());
        holder.date.setText(currentItem.getData());
        holder.time.setText(currentItem.getTime());
        try {
            if (currentItem.getImage() != null) {
                Glide.with(context).load(currentItem.getImage()).into(holder.noticeImage);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        holder.noticeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FullImagView.class);
                intent.putExtra("image",currentItem.getImage());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NoticeViewAdapter extends RecyclerView.ViewHolder {
        private TextView noticeTitle, date, time;
        private ImageView noticeImage;

        public NoticeViewAdapter(@NonNull View itemView) {
            super(itemView);
            noticeTitle = itemView.findViewById(R.id.noticeTitle);
            noticeImage = itemView.findViewById(R.id.noticeImage);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
        }
    }
}

//


//
//    /**
//     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
//     * {@link FirebaseRecyclerOptions} for configuration options.
//     *
//     * @param options;
//     */
//    public NoticeAdapter(@NonNull FirebaseRecyclerOptions<NoticeData> options) {
//        super(options);
//    }
//
//    @Override
//    protected void onBindViewHolder(@NonNull NoticeViewHolder holder, int position, @NonNull NoticeData model) {
//        holder.deleteNoticeTitle.setText(model.getTitle());
//
//        Glide.with(holder.deleteNoticeImage.getContext())
//                .load(model.image)
//                .placeholder(R.drawable.facultyicon)
//                .circleCrop()
//                .error(R.drawable.facultyicon)
//                .into(holder.deleteNoticeImage);
//
//    }