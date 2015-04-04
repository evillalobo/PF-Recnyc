package ar.com.eduardovillalobo.tf.proyecto_recnyc;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Eduardo on 01/04/2015.
 */
public class ProvinciasFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_provincias, container, false);
        return rootView;
    }
}
