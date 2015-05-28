package ar.com.eduardovillalobo.tf.proyecto_recnyc.Departamentos;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import ar.com.eduardovillalobo.tf.proyecto_recnyc.Contruyendo;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.DataBaseFolder.DataBaseHandler;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.DataBaseFolder.DeptoInfo;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.R;

/**
 * Created by Eduardo on 22/04/2015.
 */
public class SelectedDepartamento extends Fragment implements View.OnClickListener{

    int opcion;
    DeptoInfo depto_selected;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!= null)
        {
            //Search database with position
            //asign elements to a new deptoInfo object
            //depto_selected = new DeptoInfo(
            opcion = getArguments().getInt("Opcion", 99);
            Log.d("En SelectedDepto.", "La opción elegida es: "+ opcion);
            getSelectedDepto(opcion);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.selected_depto_fragment,container,false);

        /*Set the data from the DB with the position given in the previous fragment*/
        TextView tv_titulo = (TextView) rootView.findViewById(R.id.title_selected_depto);
        TextView tv_desc = (TextView) rootView.findViewById(R.id.descr_selected_depto);
        ImageView iv_depto = (ImageView) rootView.findViewById(R.id.image_selected_depto);
        //Sets data
        tv_titulo.setText(depto_selected.getName());
        tv_desc.setText(depto_selected.getDesc());
        int imageResource = getActivity().getResources().getIdentifier(depto_selected.getImageID(),
                null,getActivity().getPackageName());
        Picasso.with(getActivity()).load(imageResource).into(iv_depto);

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

    public DeptoInfo getSelectedDepto(int opcion){
        DataBaseHandler db = new DataBaseHandler(this.getActivity());
        depto_selected = db.getDepto(opcion);
        Log.d("Depto obtenido ", depto_selected.getName());
        db.close();
        return depto_selected;
    }
}
