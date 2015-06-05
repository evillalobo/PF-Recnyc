package ar.com.eduardovillalobo.tf.proyecto_recnyc.RecursosCulturales;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ar.com.eduardovillalobo.tf.proyecto_recnyc.DataBaseFolder.DataBaseHandler;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.DataBaseFolder.RecursoCulturalInfo;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.DataBaseFolder.RecursoNaturalInfo;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.NavigationDrawerFolder.DividerItemDecoration;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.R;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.RecursosNaturales.RecursoNaturalDetalle;

/**
 * Created by Eduardo on 08/05/2015.
 */
public class RecursosCulturalesFragment extends Fragment implements RecursoCulturalesAdapter.ClickListener {

    private RecyclerView recyclerView;
    private RecursoCulturalesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recursos_culturales,container,false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.rec_cult_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));

        DataBaseHandler db = new DataBaseHandler(this.getActivity());
        List<RecursoCulturalInfo> recursoCulturalInfo = db.getAllRecCult();
        db.close();

        adapter = new RecursoCulturalesAdapter(getActivity(), recursoCulturalInfo);
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
