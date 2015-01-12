package in.ac.ducic.ek;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
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


public class MainActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ListView leftDrawerList;
    private ArrayList<String> stringArrayAdapter;
    private String[] leftSliderData = {"Home", "Android", "Sitemap", "About", "Contact Me", "Contact Me1", "Contact Me2", "Contact Me3"};



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
    }

    private void nitView() {
        leftDrawerList = (ListView) findViewById(R.id.left_drawer);
        stringArrayAdapter= new ArrayList<String>();
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
        MyAdapter navigationDrawerAdapter = new MyAdapter(this,stringArrayAdapter);
        LayoutInflater inflater = getLayoutInflater();
        //LinearLayout listFooterView = (LinearLayout)inflater.inflate(
        //      R.layout., null);




        LinearLayout listHeaderView = (LinearLayout)inflater.inflate(
                R.layout.header, null);

        leftDrawerList.addHeaderView(listHeaderView);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


}
