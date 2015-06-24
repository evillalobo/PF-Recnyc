package ar.com.eduardovillalobo.tf.proyecto_recnyc.Search;


import android.app.Activity;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ar.com.eduardovillalobo.tf.proyecto_recnyc.DataBaseFolder.DataBaseHandler;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.DataBaseFolder.DeptoInfo;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.R;

/**
 * Created by Eduardo on 22/05/2015.
 */
public class SearchableActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("SearchableActivity"," -->buscando");
        handleIntent(getIntent());

        /*// Get the intent, verify the action and get the query
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            System.out.println("ENTROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
        }
        */
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("SearchableActivity-OnNewIntent"," -->buscando");
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doSearch(query);
        }
    }

    private void doSearch(String query) {
        Toast.makeText(getApplicationContext(), query, Toast.LENGTH_LONG).show();
    }

}
