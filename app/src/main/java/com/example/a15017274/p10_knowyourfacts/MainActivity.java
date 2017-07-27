package com.example.a15017274.p10_knowyourfacts;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<Fragment> al;
    FragmentAdapter adapter;
    ViewPager vPager;
    Button btnLater;
    int reqCode = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vPager = (ViewPager) findViewById(R.id.viewpager1);
        btnLater = (Button) findViewById(R.id.btnRead);

        FragmentManager fm = getSupportFragmentManager();

        al = new ArrayList<Fragment>();
        al.add(new Frag1());
        al.add(new Frag2());
        al.add(new Frag3());

        adapter = new FragmentAdapter(fm, al);

        vPager.setAdapter(adapter);

        btnLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, 6);

                Intent intent = new Intent(MainActivity.this,
                        ScheduledNotificationReceiver.class);
                intent.putExtra("current", vPager.getCurrentItem());
                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        MainActivity.this, reqCode,
                        intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager)
                        getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                        pendingIntent);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                if (vPager.getCurrentItem() > 0){
                    int previousPage = vPager.getCurrentItem() - 1;
                    vPager.setCurrentItem(previousPage, true);
                }
                return true;
            case R.id.item2:
                Random randomNo = new Random();
                int randomPage = randomNo.nextInt(3);
                vPager.setCurrentItem(randomPage, true);
                return true;
            case R.id.item3:
                int max = vPager.getChildCount();
                if (vPager.getCurrentItem() < max-1){
                    int nextPage = vPager.getCurrentItem() + 1;
                    vPager.setCurrentItem(nextPage, true);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences preferences = getSharedPreferences("saved", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("currents", vPager.getCurrentItem());
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Intent is for Read Later button
        Intent i = getIntent();
        int currentPage = i.getIntExtra("currents", -1);
        if(currentPage != -1) {
            vPager.setCurrentItem(currentPage);
        }
        else{
            // SharedPreference is for exiting app
            SharedPreferences preferences = getSharedPreferences("saved", MODE_PRIVATE);
            currentPage = preferences.getInt("currents", 0);
            vPager.setCurrentItem(currentPage);
        }
    }
}
