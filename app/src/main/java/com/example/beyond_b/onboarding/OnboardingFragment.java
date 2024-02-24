package com.example.beyond_b.onboarding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.beyond_b.R;

public class OnboardingFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public OnboardingFragment(){}

    public static OnboardingFragment newInstance(int sectionNumber) {
        OnboardingFragment fragment = new OnboardingFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
        int layoutId = R.layout.fragment_onboarding_first;
        switch (sectionNumber) {
            case 1: layoutId = R.layout.fragment_onboarding_first; break;
            case 2: layoutId = R.layout.fragment_onboarding_second; break;
            case 3: layoutId = R.layout.fragment_onboarding_third; break;
            case 4: layoutId = R.layout.fragment_onboarding_fourth; break;
            case 5: layoutId = R.layout.fragment_onboarding_fifth; break;
        }
        return inflater.inflate(layoutId, container, false);
    }
}
