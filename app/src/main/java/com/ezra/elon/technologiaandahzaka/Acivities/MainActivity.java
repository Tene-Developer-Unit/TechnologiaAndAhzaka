package com.ezra.elon.technologiaandahzaka.Acivities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.transition.Transition;
import android.transition.TransitionManager;
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
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.ezra.elon.technologiaandahzaka.Adapter.Asistent;
import com.ezra.elon.technologiaandahzaka.Adapter.GridViewAdapter;
import com.ezra.elon.technologiaandahzaka.Adapter.MainSrcnCourseGridViewAdapter;
import com.ezra.elon.technologiaandahzaka.Fragments.AboutUs;
import com.ezra.elon.technologiaandahzaka.Fragments.ComunicateFragment;
import com.ezra.elon.technologiaandahzaka.Fragments.JobInfoFragment;
import com.ezra.elon.technologiaandahzaka.Fragments.MainMenuFragment;
import com.ezra.elon.technologiaandahzaka.Fragments.MalshabimFragment;
import com.ezra.elon.technologiaandahzaka.Fragments.MaslulimListFragment;
import com.ezra.elon.technologiaandahzaka.Fragments.MiktzoaLaFragment;
import com.ezra.elon.technologiaandahzaka.Fragments.NewsFragment;
import com.ezra.elon.technologiaandahzaka.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , LoaderManager.LoaderCallbacks {
    FragmentTransaction ft;
    String title;

    FragmentManager fragmentManager = getSupportFragmentManager();
private boolean viewIsAtHome;
    String LOG_TAG = "Elon Test";
    private DatabaseReference mDatabase;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        ///you can put in the defult in the display view


       //MainMenuFragment mainMenuFragment = (MainMenuFragment) getSupportFragmentManager().findFragmentById(R.id.mainfragment);

        if(!Asistent.isNetworkConnected(getBaseContext()))//ther is an internet connection
        {        Toast.makeText(getApplicationContext(),"האינטרנט כבוי חלק מהפונקציות לא יפעלו כהלכה",Toast.LENGTH_LONG).show();

        }

        /////start the course menu//////



        //TODO: get courses random, and display


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.

        displayView(R.id.main_screen);
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
        if (id == R.id.action_contect) {

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("*/email");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"logitechidfdev@gmail.com"});
            intent.putExtra(Intent.EXTRA_SUBJECT, "דואר משתמש מהאפליקציה");
            intent.putExtra(Intent.EXTRA_TEXT,"תיאור התקלה:\n\n");

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }

            Toast.makeText(this,"צור קשר",Toast.LENGTH_SHORT).show();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressLint("ResourceAsColor")
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }


        if(getSupportFragmentManager().getBackStackEntryCount() < 2 )
        {
            finish();

        }




        getSupportFragmentManager().popBackStack();
        getSupportActionBar().setTitle(title);


    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        displayView(id);

        return true;
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */





    @SuppressLint("ResourceAsColor")
    public void displayView(int viewId)
    {
        ViewGroup frame =(FrameLayout) findViewById(R.id.frame_layout);
        Fragment fragment = null;
         title = "";

        switch (viewId)
        {
            case R.id.comunication:
                fragment = new ComunicateFragment();
                title = getResources().getString(R.string.comunication);
                break;

            case R.id.facebookpage:
                fragment = new MainMenuFragment();
                title = getResources().getString(R.string.app_name);
                openWebPage("https://www.facebook.com/mitgaisim.tech/?fref=ts");
                break;

            case R.id.about_us:
                fragment = new AboutUs();
                title = getResources().getString(R.string.about_us);
            break;

            case R.id.malshabim:
                fragment = new MalshabimFragment();
                title = getResources().getString(R.string.malshabim);
                break;

            case R.id.miktzoa_lahaim:
                fragment = new MiktzoaLaFragment();
                title = getResources().getString(R.string.mktzoalhaim);
                break;

            default:
               fragment = new MainMenuFragment();
                title = getResources().getString(R.string.app_name);
                break;

        }
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.rect_red_background));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(R.color.colorPrimaryDark);
        }

        if(fragment != null)
        {
            ActionBar bard = getSupportActionBar();
            ft = fragmentManager.beginTransaction();
            ft.replace(R.id.frame_layout,fragment);

            ft.addToBackStack(null);
            ft.commit();
        }
        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setTitle(title);


        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            TransitionManager.beginDelayedTransition(frame);
        }

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
    }


    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        Log.i(LOG_TAG,"onCreatLoader() called...");

        return null;
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {
        Log.i(LOG_TAG,"onLoadFinished() called...");

    }

    @Override
    public void onLoaderReset(Loader loader) {
        Log.i(LOG_TAG,"onLoaderReset() called...");
    }


}




