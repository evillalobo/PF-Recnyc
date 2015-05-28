package ar.com.eduardovillalobo.tf.proyecto_recnyc.Search;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import android.widget.ListView;
import android.widget.TextView;

import ar.com.eduardovillalobo.tf.proyecto_recnyc.DataBaseFolder.DataBaseHandler;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.R;

/**
 * Created by Eduardo on 22/05/2015.
 */
public class SearchResultsActivity extends Activity {

    DataBaseHandler db = new DataBaseHandler(this);
    final Context context = this;
    ListView list;
    TextView v;
    String res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.simple_list_item_1);
    }
}
