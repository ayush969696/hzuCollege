package com.myandayush.college;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.myandayush.college.authentication.LoginActivity;
import com.myandayush.college.ebook.EbootActivity;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private ProgressBar mainProgressBar;
    private NavController navController;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private SharedPreferences sharedPreferences; // we need this cause with the help of this we can implement the selected option
    private SharedPreferences.Editor editor;
    private int checkItem;
    private String selected;
    private final  String CHECKEDITEM = "checked_item";
    private SharedPreferences sharedPreferencesLogin;
    private SharedPreferences.Editor editorLogin;
    private FirebaseAuth auth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Himgiri Zee University");
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#561E92"));
        assert actionBar != null;
        actionBar.setBackgroundDrawable(colorDrawable);



        auth = FirebaseAuth.getInstance();
        sharedPreferences = this.getSharedPreferences("login", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        sharedPreferences = this.getSharedPreferences("Themes", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


        switch (getCheckedItem()){
            case 0:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);  // we can change theme help of this AppCompatDelegate
                break;
            case 1 :
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case 2 :
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
        }



        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        navController = Navigation.findNavController(this, R.id.frame_layout);
        drawerLayout = findViewById(R.id.drawe_layout);
        navigationView = findViewById(R.id.navigation_view);
        mainProgressBar = findViewById(R.id.mainProgressBar);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.start, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

       Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(this);

        NavigationUI.setupWithNavController(bottomNavigationView, navController);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (toggle.onOptionsItemSelected(item)){
            return true;
        }

        return true;
    }

    private void openLogin() {
        mainProgressBar.setVisibility(View.VISIBLE);

        Thread thread = new Thread(){
            public void run(){
                try {
                    sleep(3000);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                finally {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }; thread.start();
//        startActivity(new Intent(this, LoginActivity.class));
//        finish();;
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser == null) {
            openLogin();
        }
    }


        @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.navigation_video) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com/channel/UC_-z9XYtAlvVJALz6I4SujQ"));
            startActivity(browserIntent);
        }
        if (item.getItemId() == R.id.navigation_rate){
            Uri uri = Uri.parse("market://details?id=" + getPackageName());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                // If the Play Store app is not installed, open the Play Store website
                Uri webUri = Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName());
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webUri);
                startActivity(webIntent);
            }
        }
        if (item.getItemId() == R.id.navigation_ebook){
            startActivity(new Intent(this, EbootActivity.class));
        }
        if (item.getItemId() == R.id.navigation_wesite){
            Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.hzu.edu.in/"));
            startActivity(browserIntent);
        }
        if (item.getItemId() == R.id.navigation_theme){
            showDialog();
        }
        if (item.getItemId() == R.id.navigation_share){
            String shareText = "Check my University app : https://play.google.com/store/apps/details?id=com.myandayush.college";
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
            startActivity(Intent.createChooser(shareIntent, "Share via"));
        }

        if (item.getItemId() == R.id.logout){
            auth.signOut();
            mainProgressBar.setVisibility(View.VISIBLE);
            openLogin();
            }

        return true;
    }


    private void showDialog() {
        mainProgressBar.setVisibility(View.VISIBLE);

        String[] themes = this.getResources().getStringArray(R.array.theme);

        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
        builder.setTitle("Select Theme");
        builder.setSingleChoiceItems(R.array.theme, getCheckedItem(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
             selected = themes[which];
             checkItem = which;
            }
        });
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                   if (selected == null){
                       selected = themes[which];
                       checkItem = which;
                   }

                   switch (selected){
                       case "System Default" :
                           AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);  // we can change theme help of this AppCompatDelegate
                           break;
                       case "Light" :
                           AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                           break;
                       case "Dark" :
                           AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                           break;
                   }

                   setCheckItem(checkItem);  // now we need to save the value in the setCheckItem();

                 }
             });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                   dialog.dismiss();
                 }
             });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private int getCheckedItem(){
        return sharedPreferences.getInt(CHECKEDITEM, 0);
    }
    private void setCheckItem(int i){
        editor.putInt(CHECKEDITEM, i);
        editor.apply();
    }


    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else
            super.onBackPressed();
    }
}