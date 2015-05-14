package ar.com.eduardovillalobo.tf.proyecto_recnyc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Eduardo on 08/05/2015.
 */
public class FragmentRecursosCulturales extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recursos_culturales,container,false);
        return rootView;
    }
}
