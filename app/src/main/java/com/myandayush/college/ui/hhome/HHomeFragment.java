package com.myandayush.college.ui.hhome;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.AnimationTypes;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.myandayush.college.FullImagView;
import com.myandayush.college.R;

import java.util.ArrayList;
import java.util.List;


public class HHomeFragment extends Fragment {
    ImageSlider imageSlidr, hzu_achivments, pageerourRecruits;
    private ImageView map, instagram, fb, lnkedin, yt;
    private ViewPager viewPagerHzu;
    private HomeAdapter adapter;
    private List<HomeModel> list;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_h_home, container, false);

        ArrayList<com.denzcoskun.imageslider.models.SlideModel> imageList = new ArrayList<>();
        imageList.add(new com.denzcoskun.imageslider.models.SlideModel("https://firebasestorage.googleapis.com/v0/b/hzu-app-e1af9.appspot.com/o/Gallery%2F%5BB%401a6119cjpg?alt=media&token=7394ef17-f30f-4d4b-9ef6-0a1a9b97a4a3", "Himgiri Zee University", ScaleTypes.CENTER_CROP));
        imageList.add(new com.denzcoskun.imageslider.models.SlideModel("https://firebasestorage.googleapis.com/v0/b/hzu-app-e1af9.appspot.com/o/Gallery%2F%5BB%4061558d8jpg?alt=media&token=262d13cf-a80d-44ee-8581-5786bf937d2c", "#1 India's Best Private University in Dehradun, Uttarakhand - HZU.",ScaleTypes.CENTER_CROP));
        imageList.add(new com.denzcoskun.imageslider.models.SlideModel("https://firebasestorage.googleapis.com/v0/b/hzu-app-e1af9.appspot.com/o/Gallery%2F%5BB%4059c9ff2jpg?alt=media&token=aa01f6f0-3dae-4fbf-bc26-d0f0c351c55b",  ScaleTypes.CENTER_CROP));
        imageList.add(new com.denzcoskun.imageslider.models.SlideModel("https://firebasestorage.googleapis.com/v0/b/hzu-app-e1af9.appspot.com/o/Gallery%2F%5BB%40bf611d8jpg?alt=media&token=e3ab45fb-abbb-4635-ba4d-cd94e8270ac9", "HZU", ScaleTypes.CENTER_CROP));
        imageList.add(new com.denzcoskun.imageslider.models.SlideModel("https://firebasestorage.googleapis.com/v0/b/hzu-app-e1af9.appspot.com/o/Gallery%2F%5BB%402894b8ejpg?alt=media&token=ce8643f9-1d49-4aca-b446-1bf9074eb7a0", "HZU", ScaleTypes.CENTER_CROP));
        imageList.add(new com.denzcoskun.imageslider.models.SlideModel("https://www.addressguru.in/images/1623655785.png", ScaleTypes.CENTER_CROP));
        imageList.add(new com.denzcoskun.imageslider.models.SlideModel("https://static.zollege.in/public/college_data/images/campusimage/1438867628himgiri-Zee-campus.jpg", "Campus", ScaleTypes.CENTER_CROP));
        imageList.add(new com.denzcoskun.imageslider.models.SlideModel("https://www.hzu.edu.in/images/gallery-new/16.jpg", ScaleTypes.CENTER_CROP));
        imageList.add(new com.denzcoskun.imageslider.models.SlideModel("https://images.collegedunia.com/public/college_data/images/campusimage/1438859595campus3.jpg",  ScaleTypes.CENTER_CROP));
        imageList.add(new com.denzcoskun.imageslider.models.SlideModel("https://www.readkong.com/static/ee/7b/ee7ba6ccb918c3a11f6ab0dab4c88d05/prospectus-ugc-recognised-himgiri-zee-university-7211355-5.jpg", ScaleTypes.CENTER_CROP));

        imageSlidr = view.findViewById(R.id.image_slider);
        imageSlidr.setImageList(imageList);
        imageSlidr.setSlideAnimation(AnimationTypes.ZOOM_IN);

        imageSlidr.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemSelected(int position) {
                Intent intent = new Intent(view.getContext(), FullImagView.class);
                intent.putExtra("image", imageList.get(position).getImageUrl());
                view.getContext().startActivity(intent);
            }
            @Override
            public void doubleClick(int position) { }
        });
        imageSlidr.startSliding(3000);// with new period


        ArrayList<com.denzcoskun.imageslider.models.SlideModel> achivmentList = new ArrayList<>();
        achivmentList.add(new com.denzcoskun.imageslider.models.SlideModel(R.drawable.bestuniversoty, ScaleTypes.CENTER_INSIDE));
        achivmentList.add(new com.denzcoskun.imageslider.models.SlideModel(R.drawable.hzuawardtwo, ScaleTypes.CENTER_INSIDE));
        achivmentList.add(new com.denzcoskun.imageslider.models.SlideModel(R.drawable.hzuachvimetnthre, ScaleTypes.CENTER_INSIDE));
        achivmentList.add(new com.denzcoskun.imageslider.models.SlideModel(R.drawable.hzuachivmentfour, ScaleTypes.CENTER_INSIDE));

        hzu_achivments  = view.findViewById(R.id.hzu_achivments);
        hzu_achivments.setImageList(achivmentList);
        hzu_achivments.setSlideAnimation(AnimationTypes.ROTATE_UP);
        hzu_achivments.startSliding(2000);


        map = view.findViewById(R.id.map);
        instagram = view.findViewById(R.id.instagram);
        fb = view.findViewById(R.id.fb);
        lnkedin = view.findViewById(R.id.lnkedin);
        yt = view.findViewById(R.id.yt);

        viewPagerHzu = view.findViewById(R.id.viewPagerHzu);
        list = new ArrayList<>();

        list.add(new HomeModel("Eminent faculty with vast academic & industry experience"));
        list.add(new HomeModel("50 Acres of Wi-Fi enabled (1 GB line) campus"));
        list.add(new HomeModel("Campus Placements/Industry Internship assistance with Essel Group & other multinational corporate."));
        list.add(new HomeModel("Secured & separate AC/Non AC hostel facility for boys and girls with 24 hrs power backup"));
        list.add(new HomeModel("In house-Community Radio Station “HIMGIRI KI AWAAZ 107.8 FM & HZU’s own video Production Studio."));
        list.add(new HomeModel("2 Acres of Agriculture Research Farm, Green House & Poly House Nursery in the campus"));
        list.add(new HomeModel("Regular yoga sessions to Day scholars & separate session for Hostellers."));
        list.add(new HomeModel("Student centric services including counselling, training, placement support and personality grooming"));
        list.add(new HomeModel("HZU is established by India's well renowned – Essel Group."));
        list.add(new HomeModel("Personalized grooming to students in their formative year at university."));
        list.add(new HomeModel("Member of the Association of Indian Universities (AIU), New Delhi."));
        list.add(new HomeModel("Splendid sporting facilities for Swimming, Lawn Tennis, Volleyball, Badminton, Basketball, Football, Cricket & Open Gymnasium."));
        list.add(new HomeModel("Student centric services including counselling, training, placement support and personality grooming"));
        list.add(new HomeModel("Recognized by UGC and therefore pass out students are eligible for government jobs across the country & abroad."));
        list.add(new HomeModel("Secured & separate AC/Non AC hostel facility for boys and girls with 24 hrs power backup"));
        list.add(new HomeModel("Splendid sporting facilities for Swimming, Lawn Tennis, Volleyball, Badminton, Basketball, Football, Cricket & Open Gymnasium."));
        list.add(new HomeModel("Recognized by UGC and therefore pass out students are eligible for government jobs across the country & abroad."));
        adapter = new HomeAdapter(getContext(),list);
        viewPagerHzu.setAdapter(adapter);


        // Recurits

        ArrayList<com.denzcoskun.imageslider.models.SlideModel> recruitsModels = new ArrayList<>();
        recruitsModels.add(new SlideModel("https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/Zee_news.svg/1200px-Zee_news.svg.png", ScaleTypes.CENTER_INSIDE));
        recruitsModels.add(new SlideModel("https://upload.wikimedia.org/wikipedia/commons/thumb/a/a0/Genpact_logo.svg/2560px-Genpact_logo.svg.png", ScaleTypes.CENTER_INSIDE));
        recruitsModels.add(new SlideModel("https://assets.stickpng.com/images/5a0c74bd9642de34b6b65cf5.png", ScaleTypes.CENTER_INSIDE));
        recruitsModels.add(new SlideModel("https://www.cloudera.com/content/dam/www/dynamic/images/logos/customers/axis-bank-dynamic.png", ScaleTypes.CENTER_INSIDE));
        recruitsModels.add(new com.denzcoskun.imageslider.models.SlideModel("https://www.bizasialive.com/wp-content/uploads/2018/11/zeemedialogo001.jpg", ScaleTypes.CENTER_CROP));
        recruitsModels.add(new SlideModel("https://www.jubilantbiosys.com/wp-content/uploads/2019/09/footer_logo.png", ScaleTypes.CENTER_INSIDE));
        recruitsModels.add(new SlideModel("https://blogs.microsoft.com/wp-content/uploads/prod/2012/08/8867.Microsoft_5F00_Logo_2D00_for_2D00_screen.jpg", ScaleTypes.CENTER_INSIDE));
        recruitsModels.add(new SlideModel("https://static.wixstatic.com/media/cdfee0_b774c2968f11475ca9329dfc52ee4fc0~mv2.png/v1/fill/w_240,h_92,al_c,q_85,usm_0.66_1.00_0.01,enc_auto/Agrostar-Register-Logo_Red%20(1)_edited.png", ScaleTypes.CENTER_INSIDE));
        recruitsModels.add(new SlideModel("https://www.pngmart.com/files/23/Deloitte-Logo-PNG-Pic.png", ScaleTypes.CENTER_INSIDE));
        recruitsModels.add(new SlideModel("https://seeklogo.com/images/H/hexaware-technologies-logo-7B0FA8DDA8-seeklogo.com.png", ScaleTypes.CENTER_INSIDE));
        recruitsModels.add(new SlideModel("https://logoeps.com/wp-content/uploads/2013/06/the-times-of-india-crest-vector-logo.png", ScaleTypes.CENTER_INSIDE));
        recruitsModels.add(new SlideModel("https://lamhas.com/wp-content/uploads/2022/06/3-1.png", ScaleTypes.CENTER_INSIDE));
        recruitsModels.add(new SlideModel("https://www.livehindustan.com/static-content/1y/lh/img/lh-logo-desk.webp", ScaleTypes.CENTER_INSIDE));
        recruitsModels.add(new SlideModel("https://prod-credit-card.s3.ap-south-1.amazonaws.com/s3fs-public/inline-images/Untitled%20design%20%284%29.png", ScaleTypes.CENTER_INSIDE));
        recruitsModels.add(new SlideModel("https://www.thefullformdictionary.com/wp-content/uploads/2020/04/abp-new.png.webp", ScaleTypes.CENTER_INSIDE));
        recruitsModels.add(new SlideModel("https://static.wixstatic.com/media/cb24b8_ffd1a5cdb19f45cf85f5af1e00fb42b3~mv2.png/v1/fill/w_142,h_142,al_c,q_85,usm_0.66_1.00_0.01,enc_auto/Sahara-Samay.png", ScaleTypes.CENTER_INSIDE));
        recruitsModels.add(new SlideModel("https://surveymonkey-assets.s3.amazonaws.com/survey/277040421/c9c3c6e8-7d31-4aed-80c4-58b6bce37b3c.png", ScaleTypes.CENTER_INSIDE));
        recruitsModels.add(new SlideModel("https://5.imimg.com/data5/DX/HD/MY-2/logo-jeevan2-250x250.png", ScaleTypes.CENTER_INSIDE));
        recruitsModels.add(new SlideModel("https://upload.wikimedia.org/wikipedia/commons/8/8c/Vivo_New_Logo_2019.png", ScaleTypes.CENTER_INSIDE));
        recruitsModels.add(new SlideModel("https://upload.wikimedia.org/wikipedia/commons/1/13/OPPO_Logo_wiki.png", ScaleTypes.CENTER_INSIDE));
        recruitsModels.add(new SlideModel("https://assets.stickpng.com/images/627ccb4e1b2e263b45696aa7.png", ScaleTypes.CENTER_INSIDE));
        recruitsModels.add(new SlideModel("https://upload.wikimedia.org/wikipedia/commons/thumb/2/24/Zee_news.svg/1200px-Zee_news.svg.png", ScaleTypes.CENTER_INSIDE));
        recruitsModels.add(new SlideModel("https://1000logos.net/wp-content/uploads/2019/11/Hyatt-Logo.png", ScaleTypes.CENTER_INSIDE));
        recruitsModels.add(new SlideModel("https://static.vecteezy.com/system/resources/previews/019/017/437/original/amazon-logo-free-png.png", ScaleTypes.CENTER_INSIDE));
        recruitsModels.add(new SlideModel("https://1000logos.net/wp-content/uploads/2021/05/The-Guardian-logo.png", ScaleTypes.CENTER_INSIDE));
        recruitsModels.add(new SlideModel("https://cdn.gyftr.com/payback/images/brands/logos/96410_Barbeque-Nation.png", ScaleTypes.CENTER_INSIDE));
        recruitsModels.add(new SlideModel("https://logos-download.com/wp-content/uploads/2016/07/Airtel_logo.png", ScaleTypes.CENTER_INSIDE));

        pageerourRecruits = view.findViewById(R.id.pageerourRecruits);
        pageerourRecruits.setImageList(recruitsModels);
        pageerourRecruits.setSlideAnimation(AnimationTypes.ZOOM_IN);
        pageerourRecruits.startSliding(3000);// with new period




        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });

        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInstagram();
            }
        });

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openfb();
            }
        });
        lnkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oenlinkedin();
            }
        });
        yt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openyt();
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

    private void openInstagram() {

        Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.instagram.com/hzu_ddn/"));
        startActivity(browserIntent);
    }

    private void openfb() {

        Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.facebook.com/hzuddn/"));
        startActivity(browserIntent);
    }

    private void oenlinkedin() {

        Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.linkedin.com/school/himgiri-zee-university/"));
        startActivity(browserIntent);
    }

    private void openyt() {

        Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.youtube.com/channel/UC_-z9XYtAlvVJALz6I4SujQ"));
        startActivity(browserIntent);
    }
}