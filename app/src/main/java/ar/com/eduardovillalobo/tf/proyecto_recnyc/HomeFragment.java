package ar.com.eduardovillalobo.tf.proyecto_recnyc;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.lucasr.twowayview.widget.DividerItemDecoration;
import org.lucasr.twowayview.widget.TwoWayView;

import java.util.ArrayList;

/**
 * Created by Eduardo on 01/04/2015.
 */
public class HomeFragment extends Fragment {
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
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Activity activity = getActivity();

        mRecyclerView = (TwoWayView) view.findViewById(R.id.list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setHapticFeedbackEnabled(true);

        /* final Drawable divider = getResources().getDrawable(R.drawable.abc_list_divider_mtrl_alpha);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(divider));*/
        loadData();
    }

    public void loadData() {

        String [] titulos = {"Recursos Naturales",
                "Recursos Culturales",
                "Mapas",
                "¿Dónde estoy?"};
        int [] imagenes = {R.drawable.rn1,
                R.drawable.rc1,
                R.drawable.map1,
                R.drawable.dond1};
        for (int i=0;i<titulos.length;i++) {
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
