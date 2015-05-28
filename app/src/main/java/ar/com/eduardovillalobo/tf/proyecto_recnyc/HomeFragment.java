package ar.com.eduardovillalobo.tf.proyecto_recnyc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.lucasr.twowayview.ItemClickSupport;
import org.lucasr.twowayview.widget.TwoWayView;

import java.util.ArrayList;

import ar.com.eduardovillalobo.tf.proyecto_recnyc.ComoLlego.ComoLlego;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.Mapas.Mapas;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.RecursosNaturales.RecursosNaturalesFragment;

/**
 * Created by Eduardo on 01/04/2015.
 */
public class HomeFragment extends Fragment {
    Toast mToast;
    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }

    private TwoWayView mRecyclerView;
    MainOptionLayoutAdapter adapter;
    ArrayList<MainOptionInfo> arrayList = new ArrayList<MainOptionInfo>();
    private int mLayoutId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLayoutId = R.layout.fragment_main;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Activity activity = getActivity();

        mRecyclerView = (TwoWayView) view.findViewById(R.id.list);
        mRecyclerView.setHasFixedSize(true);

        //Add support for Item Clicked
        final ItemClickSupport itemClickSupport = ItemClickSupport.addTo(mRecyclerView);
        itemClickSupport.setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView recyclerView, View view, int position, long l) {
                FragmentManager fragmentManager = getFragmentManager();
                Fragment fragment_opcion = null;
                Intent intent = null;
                //Change the fragment for the selected one
                switch (position) {
                    case 0:
                        fragment_opcion = new RecursosNaturalesFragment();
                        break;
                    case 1:
                        fragment_opcion = new FragmentRecursosCulturales();
                        break;
                    case 2:
                        intent = new Intent(getActivity(), Mapas.class);
                        break;
                    case 3:
                        intent = new Intent(getActivity(), ComoLlego.class);
                        break;
                }
                if(fragment_opcion != null)
                fragmentManager.beginTransaction()
                        .setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                        .replace(R.id.frame_container, fragment_opcion).addToBackStack(null).commit();
                if(intent!=null)
                    startActivity(intent);

                mToast = Toast.makeText(activity, "Item "+position,Toast.LENGTH_SHORT);
                mToast.show();
            }
        });

        /* final Drawable divider = getResources().getDrawable(R.drawable.abc_list_divider_mtrl_alpha);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(divider));*/
        loadData();
    }

    public void loadData() {

        //Empty the arrayList for make sure that doesn't load the items again
        arrayList.clear();

        String[] titulos = {"Recursos Naturales",
                "Recursos Culturales",
                "Mapas",
                "¿Cómo llego?"};
        int[] imagenes = {R.drawable.rn1,
                R.drawable.rc1,
                R.drawable.map1,
                R.drawable.dond1};
        for (int i = 0; i < titulos.length; i++) {
            MainOptionInfo item = new MainOptionInfo();
            item.setId(i);
            item.setTitle(titulos[i]);
            item.setFeatured_src(imagenes[i]);

            arrayList.add(item);
            Log.d("Load Data", item.getId() + " " + item.getTitle());
        }
        adapter = new MainOptionLayoutAdapter(getActivity(), mRecyclerView, mLayoutId, arrayList);
        mRecyclerView.setAdapter(adapter);
    }
}
