package com.SindhiManu.Tipno;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        // Return the appropriate fragment for each tab
        switch (position) {
            case 0:
                return new UpcomingEventsFragment();
            case 1:
                return new OngoingEventsFragment();
            case 2:
                return new PastEventsFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3; // Number of tabs
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Set the tab titles
        switch (position) {
            case 0:
                return "Upcoming";
            case 1:
                return "Ongoing";
            case 2:
                return "Past";
            default:
                return null;
        }
    }
}
