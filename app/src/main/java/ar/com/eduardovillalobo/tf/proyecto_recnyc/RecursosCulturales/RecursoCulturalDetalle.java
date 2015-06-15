package ar.com.eduardovillalobo.tf.proyecto_recnyc.RecursosCulturales;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import ar.com.eduardovillalobo.tf.proyecto_recnyc.Contruyendo;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.DataBaseFolder.DataBaseHandler;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.DataBaseFolder.RecursoCulturalInfo;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.R;

/**
 * Created by Eduardo on 11/06/2015.
 */
public class RecursoCulturalDetalle extends Fragment implements View.OnClickListener{
    int id;
    RecursoCulturalInfo recursoCulturalInfo;

    public RecursoCulturalDetalle(int id){
        this.id = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recurso_cultural_detalle, container, false);

        TextView tv_titulo = (TextView) rootView.findViewById(R.id.title_selected_rec_cult);
        TextView tv_desc = (TextView) rootView.findViewById(R.id.descr_selected_rec_cult);
        ImageView iv_rec = (ImageView) rootView.findViewById(R.id.image_rec_cult_detalle);
        recursoCulturalInfo = getRecursoCult(id);

        tv_titulo.setText(recursoCulturalInfo.getNombre());
        tv_desc.setText(recursoCulturalInfo.getDescripcion());

        Button btn_mapa = (Button) rootView.findViewById(R.id.bt_mapa_rec_cult);
        btn_mapa.setOnClickListener(this);
        return rootView;
    }

    private RecursoCulturalInfo getRecursoCult(int id) {
        DataBaseHandler db = new DataBaseHandler(this.getActivity());
        recursoCulturalInfo = db.getRecursoCultural(id);
        Log.d("Recurso Cultural seleccionado>", recursoCulturalInfo.getNombre());
        db.close();

        return recursoCulturalInfo;
    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = null;
        switch (v.getId()){
            case R.id.bt_mapa_rec_cult:
                fragment = new Contruyendo();
                break;
        }
        if (fragment != null)
        {
            fragmentManager.beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .replace(R.id.frame_container, fragment).addToBackStack(null).commit();
        }
    }
}
