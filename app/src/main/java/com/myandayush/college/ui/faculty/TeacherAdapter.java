package com.myandayush.college.ui.faculty;


      import android.annotation.SuppressLint;
      import android.content.Intent;
      import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;
        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;

        import com.bumptech.glide.Glide;
      import com.firebase.ui.database.FirebaseRecyclerAdapter;
      import com.firebase.ui.database.FirebaseRecyclerOptions;
      import com.myandayush.college.FullImagView;
      import com.myandayush.college.R;

      import de.hdodenhof.circleimageview.CircleImageView;

public class TeacherAdapter extends FirebaseRecyclerAdapter<TeacherData, TeacherAdapter.TeacherViewAdapter> {

    private String teacherCategory;


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options;
     */

    public TeacherAdapter(@NonNull FirebaseRecyclerOptions<TeacherData> options) {
        super(options);
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onBindViewHolder(@NonNull TeacherViewAdapter holder, int position, @NonNull TeacherData model) {
        holder.name.setText(model.getName());
        holder.email.setText(model.getEmail());
        holder.post.setText(model.getPost());
        holder.teacherDepartment.setText(model.getTeacherDepartment());

        Glide.with(holder.imageView.getContext())
                .load(model.image)
                .placeholder(R.drawable.user)
                .circleCrop()
                .error(R.drawable.user)
                .into(holder.imageView);
    }

    @NonNull
    @Override
    public TeacherViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faculty_item_layout, parent,false);
        return new TeacherViewAdapter(view);
    }

    class TeacherViewAdapter extends RecyclerView.ViewHolder{

        private TextView name;
        private TextView email;
        private TextView post;
        private  TextView teacherDepartment;
        private  CircleImageView imageView;


        public TeacherViewAdapter(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.teacherName);
            email = itemView.findViewById(R.id.teacherEmail);
            post = itemView.findViewById(R.id.teacherPost);
            teacherDepartment = itemView.findViewById(R.id.teacherDepartment);
            imageView = itemView.findViewById(R.id.teacherImage);
        }
    }

}


