package ar.com.eduardovillalobo.tf.proyecto_recnyc.RecursosNaturales;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ar.com.eduardovillalobo.tf.proyecto_recnyc.DataBaseFolder.DataBaseHandler;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.DataBaseFolder.RecursoNaturalInfo;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.NavigationDrawerFolder.DividerItemDecoration;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.R;

/**
 * Created by Eduardo on 26/05/2015.
 */
public class RecursosNaturalesFragment extends Fragment implements RecursosNaturalesAdapter.ClickListener{

    private RecyclerView recyclerView;
    private RecursosNaturalesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recursos_naturales, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rec_nat_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        DataBaseHandler db = new DataBaseHandler(this.getActivity());
        List<RecursoNaturalInfo> recursoNaturalInfo = db.getAllRecNat();
        db.close();
        adapter = new RecursosNaturalesAdapter(getActivity(), recursoNaturalInfo);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }

    @Override
    public void itemClicked(View view, int position) {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment_opcion = new RecursoNaturalDetalle();
        Bundle bundle = new Bundle();
        bundle.putInt("Opcion", position+1);
        fragment_opcion.setArguments(bundle);
        fragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .replace(R.id.frame_container, fragment_opcion).addToBackStack(null).commit();
    }
}
