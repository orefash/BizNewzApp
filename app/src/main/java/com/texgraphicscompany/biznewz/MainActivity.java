package com.texgraphicscompany.biznewz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.texgraphicscompany.biznewz.fragment.ContactFragment;
import com.texgraphicscompany.biznewz.fragment.HomeFragment;
import com.texgraphicscompany.biznewz.fragment.SavedFragment;

public class MainActivity extends AppCompatActivity {

    private Fragment fragment;
    private Activity activity = MainActivity.this;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {



            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_saved:

                    fragment = new SavedFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_contact:
                    fragment = new ContactFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try{
            startActivity(new Intent(MainActivity.this, LauncherActivity.class));
        }catch(Exception ex){

        }

        setContentView(R.layout.activity_main);
        fragment = new HomeFragment();
        loadFragment(fragment);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

}
