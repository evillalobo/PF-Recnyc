package ar.com.eduardovillalobo.tf.proyecto_recnyc.RecursosCulturales;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ar.com.eduardovillalobo.tf.proyecto_recnyc.Categorias.CategoriasAdapter;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.DataBaseFolder.CategoriasInfo;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.DataBaseFolder.DataBaseHandler;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.NavigationDrawerFolder.DividerItemDecoration;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.R;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.RecursosNaturales.RecursosNaturalesFragment;

/**
 * Created by Eduardo on 01/06/2015.
 */
public class CategoriasCulturalesFragment extends Fragment implements CategoriasAdapter.ClickListener{

    private RecyclerView recyclerView;
    private CategoriasAdapter adapter;
    private List<CategoriasInfo> categoriasInfoList = null;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_categorias,container,false);
        TextView type_cate = (TextView) rootView.findViewById(R.id.textCate);
        type_cate.setText("Recuros Culturales del Oeste");

        recyclerView = (RecyclerView) rootView.findViewById(R.id.categorialist);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));

        DataBaseHandler db = new DataBaseHandler(this.getActivity());
        categoriasInfoList = db.getCategoriasCultural();
        db.close();

        adapter = new CategoriasAdapter(getActivity(), categoriasInfoList);
        adapter.setClickListener(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }

    @Override
    public void itemClicked(View view, int position) {
        //Captura el id del recurso
        int id;
        CategoriasInfo categoriasInfo = categoriasInfoList.get(position);
        id = categoriasInfo.getId();
        //Envio el id al Fragmento de los Recursos Naturales para mostrar solo los de ese tipo
        //Toast.makeText(this.getActivity(),"Categoria id>"+id+" seleccionado", Toast.LENGTH_SHORT).show();
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment_opcion = new RecursosCulturalesFragment(id);
        fragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .replace(R.id.frame_container, fragment_opcion).addToBackStack(null)
                .commit();
    }
}
