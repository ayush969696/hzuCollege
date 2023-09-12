package com.myandayush.college.ebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.renderscript.ScriptGroup;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.myandayush.college.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class PdfViewerActivity extends AppCompatActivity {

    String url;
    ProgressBar progrssbar;
    private PDFView pdfView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);

        setTitle("PDF");
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#561E92"));
        assert actionBar != null;
        actionBar.setBackgroundDrawable(colorDrawable);


        url = getIntent().getStringExtra("pdfUral");
        pdfView = findViewById(R.id.pdfView);
        progrssbar = findViewById(R.id.progrssbar);

        new pdfDownload().execute(url);

    }
    private  class pdfDownload extends AsyncTask<String, Void, InputStream>{

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try {
                URL url1 = new URL(strings[0]);
                HttpsURLConnection urlConnection = (HttpsURLConnection) url1.openConnection();

                if (urlConnection.getResponseCode() == 200){
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return inputStream;
        }
        protected void onPostExecute(InputStream result) {
            pdfView.fromStream(result)
                    .defaultPage(0)
                    .spacing(8)
                    .load();

            progrssbar.setVisibility(View.GONE);
        }
    }
}