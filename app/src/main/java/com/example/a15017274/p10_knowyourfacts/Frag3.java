package com.example.a15017274.p10_knowyourfacts;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class Frag3 extends Fragment {
    Button btnChange;

    public Frag3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3, container, false);

        btnChange = (Button) view.findViewById(R.id.btnChange);
        final LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.Linearlayout);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
                linearLayout.setBackgroundColor(color);
            }
        });



        return view;
    }

}
