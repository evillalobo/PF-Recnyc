package ar.com.eduardovillalobo.tf.proyecto_recnyc;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


import ar.com.eduardovillalobo.tf.proyecto_recnyc.Departamentos.DepartamentosFragment;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.Search.SearchableActivity;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.NavigationDrawerFolder.NavigationDrawerFragment;


public class MainActivity extends ActionBarActivity {

    private Menu menu;
    final Context context = this;
    private Toolbar mToolbar;
    private NavigationDrawerFragment navigationDrawerFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Implementamos el Custom Toolbar
        mToolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Implementamos el Custom Navigation Drawer
        navigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        navigationDrawerFragment.setUp(R.id.fragment_navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout),
                mToolbar);

        if (savedInstanceState == null)
            displayView(0);

    }

    private void displayView(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new DepartamentosFragment();
                break;
            case 2:
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();


            // update selected home_item and title, then close the drawer
/*            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(navMenuTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);*/
        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //SearchableInfo searchableInfo = searchManager.getSearchableInfo(new ComponentName(context, SearchableActivity.class));
        /*getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;
        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });*/
        getMenuInflater().inflate(R.menu.menu_main, menu);

        this.menu = menu;

        SearchManager manager = (SearchManager) getSystemService(context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.buscar).getActionView();
        searchView.setSearchableInfo(manager.getSearchableInfo(new ComponentName(context, SearchableActivity.class)));
        searchView.setSubmitButtonEnabled(true);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar home_item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.buscar) {
            Toast.makeText(getApplicationContext(), "Buscando = "+onSearchRequested(), Toast.LENGTH_SHORT).show();

            onSearchRequested();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
