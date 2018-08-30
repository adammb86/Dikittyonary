package com.example.adammb.dikittyonary.dictionary;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.adammb.dikittyonary.R;
import com.example.adammb.dikittyonary.dictionary.fragment.EnglishIndonesiaFragment;
import com.example.adammb.dikittyonary.dictionary.fragment.IndonesiaEnglishFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DictionaryActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        ButterKnife.bind(this);

        //mengubag action bar menjadi toolbar
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle(R.string.title_bahasa);
        }

        //inisialisasi fragment yang muncul pertama
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        EnglishIndonesiaFragment enginFragment=new EnglishIndonesiaFragment();
        fragmentTransaction.replace(R.id.frame_container,enginFragment);
        fragmentTransaction.commit();


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_engin) {
            if(getSupportActionBar()!=null){
                getSupportActionBar().setTitle(R.string.title_bahasa);
            }

            fragmentManager=getSupportFragmentManager();
            fragmentTransaction=fragmentManager.beginTransaction();
            EnglishIndonesiaFragment enginFragment=new EnglishIndonesiaFragment();
            fragmentTransaction.replace(R.id.frame_container,enginFragment);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_ineng) {
            if(getSupportActionBar()!=null){
                getSupportActionBar().setTitle(R.string.title_bahasa_dua);
            }

            fragmentManager=getSupportFragmentManager();
            fragmentTransaction=fragmentManager.beginTransaction();
            IndonesiaEnglishFragment indonesiaEnglishFragment=new IndonesiaEnglishFragment();
            fragmentTransaction.replace(R.id.frame_container,indonesiaEnglishFragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
