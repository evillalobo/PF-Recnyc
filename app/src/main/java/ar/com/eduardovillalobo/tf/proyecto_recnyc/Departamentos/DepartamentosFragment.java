package ar.com.eduardovillalobo.tf.proyecto_recnyc.Departamentos;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ar.com.eduardovillalobo.tf.proyecto_recnyc.DataBaseFolder.DataBaseHandler;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.DataBaseFolder.DeptoInfo;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.NavigationDrawerFolder.DividerItemDecoration;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.R;

/**
 * Created by Eduardo on 01/04/2015.
 */
public class DepartamentosFragment extends Fragment implements DepartamentosAdapter.ClickListener{

    private RecyclerView recyclerView;
    private DepartamentosAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_departamentos, container, false);
        if (getActivity() == null)
            System.out.print("el contexto es null");
        recyclerView = (RecyclerView) layout.findViewById(R.id.deptoList);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        adapter = new DepartamentosAdapter(getActivity(), getData());
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        /**
         * Leer los deptos desde la base de Datos
         */
        DataBaseHandler db = new DataBaseHandler(this.getActivity());
        /**
         * CRUD
         */
        Log.d("Ingres datos de: ", "Insertando...");
        db.addDepto(new DeptoInfo("Andalgalá"));

        Log.d("Leyendo los datos de la Base> ", "Leyendo deptos...");
        List<DeptoInfo> deptoInfo = db.getAllDeptos();

        for (DeptoInfo di : deptoInfo){
            String log = "Id: "+di.getId()+" ,Name: " + di.getName();
            Log.d("Nombre: ", log);
        }

        return layout;
    }

    @Override
    public void itemClicked(View view, int position) {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment_opcion = null;
        switch (position) {
            case 0:
                fragment_opcion = new SelectedDepartamento();
                break;
        }

        if(fragment_opcion != null) {
            fragmentManager.beginTransaction()
                    .setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                    .replace(R.id.frame_container, fragment_opcion).addToBackStack(null).commit();

        }
        Toast.makeText(getActivity(), "Item "+ position + " seleccionado",Toast.LENGTH_SHORT).show();
    }

    public static List<DepartamentosRowInfo> getData()
    {
        List<DepartamentosRowInfo> data = new ArrayList<>();
        int[] image = {
                R.drawable.deptos4,
                R.drawable.deptos3,
                R.drawable.deptos5,
                R.drawable.deptos2,
                R.drawable.deptos1
        };
        String [] titles = {
                "Andalgalá",
                "Belén",
                "Santa María",
                "Pomán",
                "Tinogasta"
        };
        for (int i=0;i<5;i++)
        {
            DepartamentosRowInfo current = new DepartamentosRowInfo();
            current.imageId = image[i % image.length];
            current.title = titles[i % titles.length];
            data.add(current);
        }

        return data;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
