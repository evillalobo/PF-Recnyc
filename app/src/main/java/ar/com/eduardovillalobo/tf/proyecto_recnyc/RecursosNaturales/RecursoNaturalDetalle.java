package ar.com.eduardovillalobo.tf.proyecto_recnyc.RecursosNaturales;

import android.os.Bundle;
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
import ar.com.eduardovillalobo.tf.proyecto_recnyc.DataBaseFolder.RecursoNaturalInfo;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.R;

/**
 * Created by Eduardo on 26/05/2015.
 */
public class RecursoNaturalDetalle extends Fragment implements View.OnClickListener{
    int id;
    RecursoNaturalInfo recursoNaturalInfo;

    public RecursoNaturalDetalle(int id){
        this.id = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recurso_natural_detalle,container,false);

        /*Set the data from the DB with the position given in the previous fragment*/
        TextView tv_titulo = (TextView) rootView.findViewById(R.id.title_selected_rec_nat);
        TextView tv_desc = (TextView) rootView.findViewById(R.id.descr_selected_rec_nat);
        ImageView iv_rec = (ImageView) rootView.findViewById(R.id.image_rec_nat_detalle);
        recursoNaturalInfo = getRecursoNat(id);

        //Sets data
        tv_titulo.setText(recursoNaturalInfo.getNombre());
        tv_desc.setText(recursoNaturalInfo.getDescripcion());
        /*int imageResource = getActivity().getResources().getIdentifier(recursoNaturalInfo.getImageID(),
                null,getActivity().getPackageName());
        Picasso.with(getActivity()).load(imageResource).into(iv_depto);*/

        Button btn_mapa = (Button) rootView.findViewById(R.id.bt_mapa_rec_nat);

        btn_mapa.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment = null;

        switch (v.getId()){
            case R.id.bt_mapa_rec_nat:
                fragment = new Contruyendo();
                break;
        }
        if(fragment != null)
        {
            fragmentManager.beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .replace(R.id.frame_container, fragment).addToBackStack(null).commit();
        }
    }

    private RecursoNaturalInfo getRecursoNat(int opcion) {
        DataBaseHandler db = new DataBaseHandler(this.getActivity());
        recursoNaturalInfo = db.getRecursoNatural(opcion);
        Log.d("Recurso Natural seleccionado > ", recursoNaturalInfo.getNombre());
        db.close();
        return recursoNaturalInfo;
    }
}
