package com.e.contracterandworkerfinder;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import UserFragment.EmployeeBookDetail;
import UserFragment.EmployeeProfile;
import UserFragment.UpdateEmployeeDetail;
import UserFragment.UpdateProfile;
import UserFragment.WorkDetailForm;

public class UserSideBarMenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private ActionBarDrawerToggle mToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_side_bar_menu);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        NavigationView navigationView=(NavigationView) findViewById(R.id.nvbar);
        navigationView.setNavigationItemSelectedListener(this);

        drawer =findViewById(R.id.drawerlayout);
        mToggle=new ActionBarDrawerToggle(this, drawer,R.string.open,R.string.close);
        drawer.addDrawerListener(mToggle);
        mToggle.syncState();

        if (savedInstanceState==null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.
                    fragment_container, new WorkDetailForm()).commit();
            navigationView.setCheckedItem(R.id.workdetail);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
       switch (menuItem.getItemId()){
           case R.id.workdetail:
              getSupportFragmentManager().beginTransaction().replace(R.id.
                      fragment_container,new WorkDetailForm()).commit();
              break;
           case R.id.bookme:
               getSupportFragmentManager().beginTransaction().replace(R.id.
                       fragment_container,new EmployeeBookDetail()).commit();
               break;
           case R.id.profile:
               getSupportFragmentManager().beginTransaction().replace(R.id.
                       fragment_container,new EmployeeProfile()).commit();
           case R.id.updatework:
               getSupportFragmentManager().beginTransaction().replace(R.id.
                       fragment_container,new UpdateEmployeeDetail()).commit();
               break;
           case R.id.updateprofile:
               getSupportFragmentManager().beginTransaction().replace(R.id.
                       fragment_container,new UpdateProfile()).commit();
               break;
           case R.id.logout:
               Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
               break;
       }
       drawer.closeDrawer(GravityCompat.START);
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
