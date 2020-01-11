package com.lewisgreaves.mynavigationdrawer;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private Fragment fragmentNews;
    private Fragment fragmentProfile;
    private Fragment fragmentSettings;

    private static final int FRAGMENT_NEWS = 0;
    private static final int FRAGMENT_PROFILE = 1;
    private static final int FRAGMENT_SETTINGS = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.configureToolbar();
        this.configureDrawerLayout();
        this.configureNavigationView();
    }

    @Override
    public void onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            case R.id.activity_main_drawer_news:
                this.showFragment(FRAGMENT_NEWS);
                break;
            case R.id.activity_main_drawer_profile:
                this.showFragment(FRAGMENT_PROFILE);
                break;
            case R.id.activity_main_drawer_settings:
                this.showFragment(FRAGMENT_SETTINGS);
                break;
            default:
                break;

        }

        this.drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    private void showFragment (int fragmentIdentifier) {
        switch (fragmentIdentifier) {
            case FRAGMENT_NEWS:
                this.showNewsFragment();
                break;
            case FRAGMENT_PROFILE:
                this.showProfileFragment();
                break;
            case FRAGMENT_SETTINGS:
                this.showSettingsFragment();
                break;
        }
    }

    private void configureToolbar() {
        this.toolbar = findViewById(R.id.activity_main_toolbar);
        getSupportActionBar();
    }

    private void configureDrawerLayout() {
        this.drawerLayout = findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void configureNavigationView() {
        this.navigationView = findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void showProfileFragment() {
        if (this.fragmentProfile == null) this.fragmentProfile = ProfileFragment.newInstance();
            this.startTransactionFragment(this.fragmentProfile);
        }

    private void showSettingsFragment() {
        if (this.fragmentSettings == null) this.fragmentSettings = SettingsFragment.newInstance();
        this.startTransactionFragment(this.fragmentSettings);
    }

    private void showNewsFragment() {
        if (this.fragmentNews == null) this.fragmentNews = NewsFragment.newInstance();
        this.startTransactionFragment(this.fragmentNews);
    }

    private void startTransactionFragment(Fragment fragment) {
        if (!fragment.isVisible()){
            getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_frame_layout, fragment).commit();
        }
    }
}
