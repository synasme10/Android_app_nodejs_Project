package com.e.contracterandworkerfinder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import HirerFragment.HireFragment;
import HirerFragment.HireProfileFragment;
import HirerFragment.HireUpdateProfile;

public class HirerSideBarMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hirer_side_bar_menu);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer=findViewById(R.id.drawerlayout);
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawer,toolbar,R.string.open,R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
//
//        if (savedInstanceState==null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                    new HirerDashboardFragment()).commit();
//            navigationView.setCheckedItem(R.id.Hirerdasboard);
//        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
       switch (item.getItemId()){
//           case R.id.Hirerdasboard:
//               getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                       new HirerFragment.HirerDashboardFragment()).commit();
//                       break;
//           case R.id.Hire:
//               getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                       new HireFragment()).commit();
//               break;
//           case R.id.myprofile:
//               getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                       new HireProfileFragment()).commit();
//               break;
//           case R.id.Updateprofile:
//               getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                       new HireUpdateProfile()).commit();
//               break;
       }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


}
