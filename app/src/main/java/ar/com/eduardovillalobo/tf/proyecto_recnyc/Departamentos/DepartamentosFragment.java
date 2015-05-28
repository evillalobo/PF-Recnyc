package ar.com.eduardovillalobo.tf.proyecto_recnyc.Departamentos;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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

        recyclerView = (RecyclerView) layout.findViewById(R.id.deptoList);
        recyclerView.setHasFixedSize(true);//Increase performance
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity()));
        //Read Database
        DataBaseHandler db = new DataBaseHandler(this.getActivity());
        List<DeptoInfo> deptoInfo = db.getAllDeptos();
        db.close();
        //Asign list of Deptos to the adapter
        adapter = new DepartamentosAdapter(getActivity(), deptoInfo);
        adapter.setClickListener(this);
        //Asign the adapater to the recyclerview
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return layout;
    }

    /*
    En caso de CRUD usar este método
    public List<DeptoInfo> getDeptos(){

    List<String> allDeptos = new ArrayList<>();
    *//**
     * Read Deptos in Data Base
     *//*
    DataBaseHandler db = new DataBaseHandler(this.getActivity());
    Log.d("Leyendo los datos de la Base> ", "Leyendo deptos...");
    List<DeptoInfo> deptoInfo = db.getAllDeptos();

    System.out.println(deptoInfo);

    for (DeptoInfo di : deptoInfo){
        allDeptos.add(di.getName());
        String log = "Id: "+di.getId()+" ,Name: " + di.getName();
        Log.d("Nombre: ", log);
    }
    return deptoInfo;
    }*/

    @Override
    public void itemClicked(View view, int position) {

        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragment_opcion = new SelectedDepartamento();
        Bundle bundle = new Bundle();
        bundle.putInt("Opcion", position+1);
        fragment_opcion.setArguments(bundle);
        fragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .replace(R.id.frame_container, fragment_opcion).addToBackStack(null).commit();

        //Toast.makeText(getActivity(), "Item "+ position + " seleccionado",Toast.LENGTH_SHORT).show();
    }

}
