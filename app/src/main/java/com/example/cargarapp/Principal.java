package com.example.cargarapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Principal extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout sliderDots;
    TextView bienvenida;
    RecyclerView recycled;
    RecyclerView.Adapter adapter;
    private int dotscount;
    private ImageView[] dots;
    private int currentPage = 0;
    private int NUM_PAGES = 0;
    private int[] images = {R.drawable.image1, R.drawable.image2, R.drawable.image3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        //sliderDots = (LinearLayout) findViewById(R.id.SliderDots);
        recycled = findViewById(R.id.recycler_view);

        AdaptadorPrincipal viewPagerAdapter = new AdaptadorPrincipal(this );
        viewPager.setAdapter(viewPagerAdapter);
        NUM_PAGES = viewPagerAdapter.getCount();

        bienvenida = findViewById(R.id.bienvenido);
        bienvenida.setText("Bienvenido Miguel");
        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];


        List<String> data = new ArrayList<>();
        data.add("Item 1");
        data.add("Item 2");
        data.add("Item 3");
        data.add("Item 4");
        data.add("Item 5");
        data.add("Item 6");

        adapter = new RecycledPrincipal(data);
        recycled.setLayoutManager(new LinearLayoutManager(this));
        recycled.setAdapter(adapter);

        for (int i = 0; i < dotscount; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0, 0, 0, 0);
//            sliderDots.addView(dots[i], params);
        }
        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < dotscount; i++) {
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));
                }
                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        // Create a timer to automatically slide through the images
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);
    }

    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            Principal.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (currentPage == NUM_PAGES - 1) {
                        currentPage = 0;
                    } else {
                        currentPage++;
                    }
                    viewPager.setCurrentItem(currentPage);
                }
            });
        }
    }
}