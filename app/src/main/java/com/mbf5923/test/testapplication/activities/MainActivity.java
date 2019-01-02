package com.mbf5923.test.testapplication.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mbf5923.test.testapplication.Base.BaseActivity;
import com.mbf5923.test.testapplication.R;
import com.mbf5923.test.testapplication.Home.HomeFragment;

public class MainActivity extends BaseActivity {
    private static final String BACK_STACK_ROOT_TAG = "fragment";

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addExpedFragment(new HomeFragment());

    }




    public void addExpedFragment(Fragment fragment){
        String backStateName =  fragment.getClass().getName();
        String fragmentTag = backStateName;

        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);

        if (!fragmentPopped && manager.findFragmentByTag(fragmentTag) == null){ //fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.framelayout, fragment, fragmentTag);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(backStateName);
            ft.commit();
        }

    }

    @Override
    public void onBackPressed() {

        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count < 2) {
            super.onBackPressed();
            this.finish();
            //additional code
        } else {

//            getSupportFragmentManager().popBackStack();
            addExpedFragment(new HomeFragment());
        }

    }
}
