package com.example.guillaume.naviguationdrawer;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.example.guillaume.naviguationdrawer.Fragment.HomeFragment;
import com.example.guillaume.naviguationdrawer.Fragment.DrivingSchoolFragment;

import java.util.List;
// import com.example.guillaume.naviguationdrawer.Fragment.ResultFragment;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG_HOME = "home";
    private static final String TAG_GALLERY = "gallery";
    private static final String TAG_SETTINGS = "settings";
    public static String CURRENT_TAG = TAG_HOME;
    public static final String TAG_DRIVEN = "driven";
    public static final String TAG_RESULT = "result";
    public static final String TAG_SEND = "send";
    public static final String TAG_SHARE = "share";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(savedInstanceState == null) {
            loadCurrentFragment(getCurrentFragment());
        }

        CarsDatabase carsData = new CarsDatabase(getApplicationContext());

        carsData.open();
        carsData.insert(new Cars("Opel", "Modele", 7));
        carsData.insert(new Cars("ScoobyMachine", "Samy", 8));
        carsData.insert(new Cars("Porsche", "Carrera", 9));

        List<Cars> list_cars = carsData.selectAll();
        carsData.close();
        for (Cars list_car : list_cars) {
            Log.e("MainActivity", list_car.toString());
        }
    }

    private Fragment getCurrentFragment()
    {
        Fragment currentFragment = null;
        switch(CURRENT_TAG)
        {
            case TAG_HOME :
                currentFragment = new HomeFragment();
                break;
            case TAG_DRIVEN :
                currentFragment = new DrivingSchoolFragment();
                break;
            case TAG_SETTINGS :
                currentFragment = new HomeFragment();
                break;
        }
        return currentFragment;
    }

    private void loadCurrentFragment(Fragment currentFragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.relative_layout_cont, currentFragment, CURRENT_TAG);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment currentFragment = null;

        switch (id)
        {
            case R.id.nav_home :
                CURRENT_TAG = TAG_HOME;
                currentFragment = getCurrentFragment();
                break;
            case R.id.nav_driving :
                CURRENT_TAG = TAG_DRIVEN;
                currentFragment = getCurrentFragment();
                break;
            case R.id.nav_result :
                CURRENT_TAG = TAG_RESULT;
                currentFragment = getCurrentFragment();
                break;
            case R.id.nav_manage :
                CURRENT_TAG = TAG_SETTINGS;
                currentFragment = getCurrentFragment();
                break;
        }
        loadCurrentFragment(currentFragment);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
