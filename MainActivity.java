package com.proj2_calc;

import android.app.PendingIntent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity
        implements BottomFragment.SendMessage
{

    LinearLayout main_linearlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_linearlayout = (LinearLayout) findViewById(R.id.main_linearlayout);
        main_linearlayout.setOrientation(LinearLayout.VERTICAL);


    }//end


    @Override
    public void sendData(String message) {

        DisplayFragment f = (DisplayFragment) getSupportFragmentManager().findFragmentById(R.id.frg_top);
        f.displayReceivedData(message);
    }
}
