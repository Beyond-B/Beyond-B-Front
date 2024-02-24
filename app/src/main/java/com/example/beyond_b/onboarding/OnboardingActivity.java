package com.example.beyond_b.onboarding;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.beyond_b.R;
import com.example.beyond_b.databinding.ActivityOnboardingBinding;
import com.example.beyond_b.membership.LogInActivity;

public class OnboardingActivity extends AppCompatActivity {

    private static final int NUM_PAGES = 5;
    private OnboardingPagerAdapter pagerAdapter;
    private ActivityOnboardingBinding activityOnboardingBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityOnboardingBinding = activityOnboardingBinding.inflate(getLayoutInflater());
        setContentView(activityOnboardingBinding.getRoot());

        pagerAdapter = new OnboardingPagerAdapter(this);
        activityOnboardingBinding.viewPager2.setAdapter(pagerAdapter);
        bind(activityOnboardingBinding);
    }

    private int num = 0;
    private void bind(ActivityOnboardingBinding item){
        item.txNext.setOnClickListener(v ->
        {
            num++;
            int currentItem = item.viewPager2.getCurrentItem();
            if (currentItem < NUM_PAGES -1) {
                item.viewPager2.setCurrentItem(currentItem + 1, true);
            } else {
                startLoginActivity();
            }
            selectIcon(num, item);

        });
        item.txSkip.setOnClickListener(v ->
        {
            startLoginActivity();
        });
    }

    private void selectIcon(int currentItem, ActivityOnboardingBinding item) {
        resetIcon(item);
        switch (currentItem) {
            case 0:
                item.icPage1.setBackgroundResource(R.drawable.ic_customonboardingcirclegreen);
                break;
            case 1:
                item.icPage2.setBackgroundResource(R.drawable.ic_customonboardingcirclegreen);
                break;
            case 2:
                item.icPage3.setBackgroundResource(R.drawable.ic_customonboardingcirclegreen);
                break;
            case 3:
                item.icPage4.setBackgroundResource(R.drawable.ic_customonboardingcirclegreen);
                break;
            case 4:
                item.icPage5.setBackgroundResource(R.drawable.ic_customonboardingcirclegreen);
                break;
            default:
                item.icPage1.setBackgroundResource(R.drawable.ic_customonboardingcirclegreen);
                break;
        }

    }

    private void resetIcon(ActivityOnboardingBinding item){
        item.icPage1.setBackgroundResource(R.drawable.ic_customonboardingcircle);
        item.icPage2.setBackgroundResource(R.drawable.ic_customonboardingcircle);
        item.icPage3.setBackgroundResource(R.drawable.ic_customonboardingcircle);
        item.icPage4.setBackgroundResource(R.drawable.ic_customonboardingcircle);
        item.icPage5.setBackgroundResource(R.drawable.ic_customonboardingcircle);
    }

    private void startLoginActivity() {
        Intent intent = new Intent(this, LogInActivity.class);
        startActivity(intent);
        finish();
    }

}
