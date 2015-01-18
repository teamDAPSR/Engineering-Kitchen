package in.ac.ducic.ek;

// pa1pal branch
// new branch

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity {
    public static final int NUM_PAGES = 3;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ListView leftDrawerList;
    private ArrayList<String> stringArrayAdapter;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private Timer timer;
    private int count =0;
    private boolean one_two_three=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nitView();
        if (toolbar != null) {
            toolbar.setTitle("Navigation Drawer");
            setSupportActionBar(toolbar);
        }
        initDrawer();
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When changing pages, reset the action bar actions since they are dependent
                // on which page is currently active. An alternative approach is to have each
                // fragment expose actions itself (rather than the activity exposing actions),
                // but for simplicity, the activity provides the actions in this sample.
                invalidateOptionsMenu();
            }
        });
        final Handler handler = new Handler();

        final Runnable Update = new Runnable() {
            public void run() {
                if(one_two_three){
                mPager.setCurrentItem(count++, true);}
                else{
                    mPager.setCurrentItem(count--,true);
                }
                if(count==0){
                    one_two_three=true;
                }
                if(count==NUM_PAGES){
                    one_two_three=false;
                }

            }
        };

        timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                handler.post(Update);
            }
        }, 1000, 5000);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    /**
     * A simple pager adapter that represents 5 {@link ScreenSlidePageFragment} objects, in
     * sequence.
     */


    private void nitView() {
        leftDrawerList = (ListView) findViewById(R.id.left_drawer);
        stringArrayAdapter = new ArrayList<String>();
        stringArrayAdapter.add("Home");
        stringArrayAdapter.add("Home1");
        stringArrayAdapter.add("Home2");
        stringArrayAdapter.add("Home3");
        stringArrayAdapter.add("ghg");
        stringArrayAdapter.add("Home2");
        stringArrayAdapter.add("Home3");
        stringArrayAdapter.add("Home4");
        stringArrayAdapter.add("Home5");


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        MyAdapter navigationDrawerAdapter = new MyAdapter(this, stringArrayAdapter);
        LayoutInflater inflater = getLayoutInflater();
        //LinearLayout listFooterView = (LinearLayout)inflater.inflate(
        //      R.layout., null);


        LinearLayout listHeaderView = (LinearLayout) inflater.inflate(
                R.layout.header, null);

        leftDrawerList.addHeaderView(listHeaderView, null, false);
        leftDrawerList.setAdapter(navigationDrawerAdapter);


    }

    private void initDrawer() {
        // setListViewHeightBasedOnChildren(leftDrawerList);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                toolbar.setTitle("Navigation Drawer");

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                toolbar.setTitle("open");

            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        leftDrawerList.setOnItemClickListener(new DrawerItemClickListener());

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    /**
     * Swaps fragments in the main content view
     */
    private void selectItem(int position) {
        // Create a new fragment and specify the planet to show based on position
        Intent x = new Intent(this, navigationclickresult.class);
        startActivity(x);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ScreenSlidePageFragment.create(position);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
