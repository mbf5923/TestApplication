package com.mbf5923.test.testapplication.activities;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.mbf5923.test.testapplication.Base.BaseActivity;
import com.mbf5923.test.testapplication.R;
import com.mbf5923.test.testapplication.Home.HomeFragment;

public class MainActivity extends BaseActivity {
    private static final String BACK_STACK_ROOT_TAG = "fragment";
private boolean closeapp=true;
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addExpedFragment(new HomeFragment());

    }






    public void addExpedFragment(Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        FragmentManager manager = getSupportFragmentManager();
        String fragmentTag = backStateName;
        if (fragmentTag.equals("com.mbf5923.test.testapplication.Detail.DetailFragment")) {
            closeapp = false;
            manager.beginTransaction().add(R.id.framelayout, fragment, fragmentTag).commit();
        }else {
            closeapp = true;
            if (manager.findFragmentByTag(fragmentTag) != null) {
                manager.beginTransaction().show(fragment).commit();
                manager.beginTransaction().remove(manager.findFragmentByTag("com.mbf5923.test.testapplication.Detail.DetailFragment")).commit();
            } else {
                manager.beginTransaction().add(R.id.framelayout, fragment, fragmentTag).commit();
            }

        }
    }

    @Override
    public void onBackPressed() {



        if (closeapp) {
            super.onBackPressed();
            this.finish();
            //additional code
        } else {

//            getSupportFragmentManager().popBackStack();
            addExpedFragment(new HomeFragment());
        }

    }
}