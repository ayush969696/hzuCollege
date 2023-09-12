package com.myandayush.college.ebook;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.myandayush.college.R;

import java.util.ArrayList;
import java.util.List;

public class EbookAdapter extends RecyclerView.Adapter<EbookAdapter.EbookViewHolder> {

    private List<ebookData> list;
    public EbookAdapter(List<ebookData> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public EbookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ebook_item_layout, parent, false);
        return new EbookViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EbookViewHolder holder, @SuppressLint("RecyclerView") int position) {
       holder.ebookname.setText(list.get(position).getpdfTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PdfViewerActivity.class);
                intent.putExtra("pdfUral",list.get(position).getPdfUrl());
                v.getContext().startActivity(intent);
            }
        });
        holder.ebookdownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse((list.get(position).getPdfUrl())));
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void FilteredList(ArrayList<ebookData> filterlist) {
        list = filterlist;
        notifyDataSetChanged();
    }


    public class EbookViewHolder extends RecyclerView.ViewHolder {
        private TextView ebookname;
        private ImageView ebookdownload;
        public EbookViewHolder(@NonNull View itemView) {
            super(itemView);
            ebookname = itemView.findViewById(R.id.ebookname);
            ebookdownload = itemView.findViewById(R.id.ebookdownload);
        }
    }

}
