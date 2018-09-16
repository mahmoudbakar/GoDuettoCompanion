package com.undecode.goduettocompanion;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;
import com.undecode.goduettocompanion.fragments.HomeFragment;
import com.undecode.goduettocompanion.fragments.NotificationFragment;
import com.undecode.goduettocompanion.fragments.ProfileFragment;
import com.undecode.goduettocompanion.fragments.ReportFragment;
import com.undecode.goduettocompanion.fragments.ScheduleFragment;
import com.undecode.goduettocompanion.interfaces.Constants;
import com.undecode.goduettocompanion.models.ProfileModel;

public class MainActivity extends AppCompatActivity implements OnTabSelectListener
{
    private BottomBar bottomBar;
    private HomeFragment homeFragment;
    private ReportFragment reportFragment;
    private ScheduleFragment scheduleFragment;
    private NotificationFragment notificationsFragment;
    private ProfileFragment profileFragment;
    private FragmentManager fragmentManager;
    private Fragment fragment;
    private int fragmentFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prepareFragments();
        bottomBar = findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(this);
        ProfileModel profileModel = ProfileModel.getInstance();
        //Toast.makeText(this, "Heba bet7b "+profileModel.getName(), Toast.LENGTH_SHORT).show();
    }

    private void prepareFragments()
    {
        fragmentFrame = R.id.frame;
        fragmentManager = getSupportFragmentManager();
        fragment = fragmentManager.findFragmentById(R.id.frame);

        homeFragment = HomeFragment.newInstance();
        reportFragment = ReportFragment.newInstance();
        scheduleFragment = ScheduleFragment.newInstance();
        notificationsFragment = NotificationFragment.newInstance();
        profileFragment = ProfileFragment.newInstance();
    }

    private void goToFragment(android.support.v4.app.Fragment myFragment, String TAG)
    {
        if (fragment != null && fragment.isVisible())
        {
            fragmentManager.beginTransaction().remove(fragment).commit();
            fragmentManager.beginTransaction().add(fragmentFrame, myFragment, TAG).commit();
        } else
            {
            fragmentManager.beginTransaction().add(fragmentFrame, myFragment, TAG).commit();
        }
        fragment = myFragment;
    }

    @Override
    public void onTabSelected(int tabId)
    {
        switch (tabId)
        {
            case R.id.tab_home:
                goToFragment(homeFragment, Constants.Fragments.HOME);
                break;
            case R.id.tab_schedule:
                goToFragment(scheduleFragment, Constants.Fragments.SCHEDULE);
                break;
            case R.id.tab_profile:
                goToFragment(profileFragment, Constants.Fragments.PROFILE);
                break;
        }
    }
}
