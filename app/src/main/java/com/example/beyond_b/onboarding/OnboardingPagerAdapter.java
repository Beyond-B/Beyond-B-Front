package com.example.beyond_b.onboarding;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class OnboardingPagerAdapter extends FragmentStateAdapter {
    private static final int NUM_PAGES = 5;

    public OnboardingPagerAdapter(@NonNull FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return OnboardingFragment.newInstance(position + 1);
    }





    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }

}
