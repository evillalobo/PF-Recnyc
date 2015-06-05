package ar.com.eduardovillalobo.tf.proyecto_recnyc.RecursosCulturales;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ar.com.eduardovillalobo.tf.proyecto_recnyc.Categorias.CategoriasAdapter;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.DataBaseFolder.CategoriasInfo;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.DataBaseFolder.DataBaseHandler;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.NavigationDrawerFolder.DividerItemDecoration;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.R;

/**
 * Created by Eduardo on 01/06/2015.
 */
public class CategoriasCulturalesFragment extends Fragment implements CategoriasAdapter.ClickListener{
    private RecyclerView recyclerView;
    private CategoriasAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_categorias,container,false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.categorialist);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));

        DataBaseHandler db = new DataBaseHandler(this.getActivity());
        List<CategoriasInfo> categoriasInfoList = db.getCategoriasCultural();
        db.close();

        adapter = new CategoriasAdapter(getActivity(), categoriasInfoList);
        adapter.setClickListener(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }

    @Override
    public void itemClicked(View view, int position) {
        //TODO
    }
}
