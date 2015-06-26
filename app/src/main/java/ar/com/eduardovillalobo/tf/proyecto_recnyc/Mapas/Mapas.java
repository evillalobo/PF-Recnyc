package ar.com.eduardovillalobo.tf.proyecto_recnyc.Mapas;

import android.annotation.TargetApi;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import ar.com.eduardovillalobo.tf.proyecto_recnyc.R;

/**
 * Created by Eduardo on 05/03/2015.
 */
public class Mapas extends ActionBarActivity {

    GoogleMap googleMap;
    MapView mapView;
    String USER_CAMERA_POSITION;
    CameraPosition currentFocus;
    private Toolbar mToolbar;
    private Menu menu;
    private Float c_lat;
    private Float c_long;

    @Override
    protected void onResume(){
        super.onResume();
        mapView.onResume();
        Log.d("GoogleMapa","Entrá en onResume");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    protected void onPause(){
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapas);
        //Tratar de poner toolbar en el mapa
        Bundle b = getIntent().getExtras();
        c_lat = b.getFloat("lat");
        c_long = b.getFloat("long");

        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        googleMap = mapView.getMap();
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.setMyLocationEnabled(true);

        addMarker(c_lat, c_long);
        if(savedInstanceState != null){
            Log.d("GoogleMapa", "Entra en el if del Oncreate savedInstanceState");
        }

        MapsInitializer.initialize(this);
        if(googleMap==null) {
            Log.d("GoogleMapa", "Mapa no inicializado");
        }
        else
        {
            Log.d("GoogleMapa", "Mapa Inicializado");

        }
        if(googleMap != null)
        {
            Log.d("GoogleMapa","Entró en el if para mover la camara");
            LatLng catamarca = new LatLng(-28.461087, -65.787203);
            googleMap.setMyLocationEnabled(true);
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(catamarca, 7));
        }

    }
    @Override
    @TargetApi(15)
    public void onSaveInstanceState(Bundle savedInstanceState) {
        //currentFocus = googleMap.getCameraPosition();
        savedInstanceState.putParcelable(USER_CAMERA_POSITION, googleMap.getCameraPosition());
        super.onSaveInstanceState(savedInstanceState);
        Log.d("GoogleMapa","Guarda el estado en onSaveInstanceState- cameraPosition:"+currentFocus);

    }

    @Override
    @TargetApi(15)
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        currentFocus = savedInstanceState.getParcelable(USER_CAMERA_POSITION);
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(currentFocus));
        super.onRestoreInstanceState(savedInstanceState);
        //googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(currentFocus));
        Log.d("GoogleMapa","Entró par restaurar el mapa en onRestoreInstanceState");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        this.menu = menu;

        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.buscar).getActionView();
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
        });
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
            onSearchRequested();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addMarker(Float cor_lat, Float cor_long){
        Marker marker = googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(cor_lat, cor_long))
                .title("Capital")
                .snippet("Paseo Gral Navarro")
        );
        /*googleMap.addMarker(new MarkerOptions()
        .position(new LatLng(0,0))
        .title("Marker")
        .draggable(true));*/
    }

}
