package com.tahirietrit.fragments_tdi;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends FragmentActivity {
    Button fragment1;
    Button fragment2;
    Button fragment3;
    Fragment1 fr1;
    Fragment2 fr2;
    Fragment3 fr3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fragment1 = findViewById(R.id.fragment_1);
        fragment2 = findViewById(R.id.fragment_2);
        fragment3 = findViewById(R.id.fragment_3);

        fr1 = new Fragment1();
        addFragment(fr1);
        selectButton(0);

        fragment1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateFragment(fr1);
                selectButton(0);
            }
        });
        fragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fr2 == null) {
                    fr2 = new Fragment2();
                }
                updateFragment(fr2);
                selectButton(1);
            }
        });
        fragment3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fr3 == null) {
                    fr3 = new Fragment3();
                }
                updateFragment(fr3);
                selectButton(2);
            }
        });
    }

    public void updateFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_holder, fragment)
                .addToBackStack(fragment.getClass().getName())
                .commit();
    }

    public void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_holder, fragment)
                .commit();
    }

    private void selectButton(int pos) {
        fragment1.setTextColor(Color.BLACK);
        fragment2.setTextColor(Color.BLACK);
        fragment3.setTextColor(Color.BLACK);
        fragment1.setBackgroundColor(Color.GRAY);
        fragment2.setBackgroundColor(Color.GRAY);
        fragment3.setBackgroundColor(Color.GRAY);
        switch (pos) {
            case 0: {
                fragment1.setTextColor(Color.WHITE);
                fragment1.setBackgroundColor(Color.BLACK);
            }
            break;
            case 1: {
                fragment2.setTextColor(Color.WHITE);
                fragment2.setBackgroundColor(Color.BLACK);
            }
            break;
            case 2: {
                fragment3.setTextColor(Color.WHITE);
                fragment3.setBackgroundColor(Color.BLACK);
            }
            break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        selectButton(getFragmentNumber());
    }

    private int getFragmentNumber() {
        String fragmentName;
        int pos = getSupportFragmentManager().getBackStackEntryCount();
        if(pos >= 1) {
            fragmentName = getSupportFragmentManager().getBackStackEntryAt(pos - 1).getName();
        }else{
            return 0;
        }

        if(fragmentName.equals(fr1.getClass().getName())){
            return 0;
        }else if (fragmentName.equals(fr2.getClass().getName())){
            return 1;
        }else if(fragmentName.equals(fr3.getClass().getName())){
            return 2;
        }
        else return -99;

    }
}
