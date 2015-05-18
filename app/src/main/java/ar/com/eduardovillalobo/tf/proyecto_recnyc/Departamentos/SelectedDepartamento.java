package ar.com.eduardovillalobo.tf.proyecto_recnyc.Departamentos;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ar.com.eduardovillalobo.tf.proyecto_recnyc.Contruyendo;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.R;

/**
 * Created by Eduardo on 22/04/2015.
 */
public class SelectedDepartamento extends Fragment implements View.OnClickListener{


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.selected_depto_fragment,container,false);

        Button btn_rn = (Button) rootView.findViewById(R.id.bt_rn_selected_depto);
        Button btn_rc = (Button) rootView.findViewById(R.id.bt_rc_selected_depto);
        Button btn_mapa = (Button) rootView.findViewById(R.id.bt_mapa_selected_depto);

        btn_rn.setOnClickListener(this);
        btn_rc.setOnClickListener(this);
        btn_mapa.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v){
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = null;

        switch (v.getId()){
            case R.id.bt_rn_selected_depto:
                fragment = new Contruyendo();
                break;
            case R.id.bt_rc_selected_depto:
                fragment = new Contruyendo();
                break;
            case R.id.bt_mapa_selected_depto:
                fragment = new Contruyendo();
                break;
        }

        if(fragment!=null)
            fragmentManager.beginTransaction()
                    .setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .replace(R.id.frame_container, fragment).addToBackStack(null).commit();

    }
}
