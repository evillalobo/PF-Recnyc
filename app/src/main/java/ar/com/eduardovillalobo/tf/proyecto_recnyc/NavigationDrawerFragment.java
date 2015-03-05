package ar.com.eduardovillalobo.tf.proyecto_recnyc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eduardo on 05/03/2015.
 */
public class NavigationDrawerFragment extends Fragment implements ItemAdapt.ClickListener{

    private RecyclerView recyclerView;

    public static final String PREF_FILE_Name = "testpref";
    public static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private ItemAdapt adapter;

    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private View containerView;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserLearnedDrawer = Boolean.valueOf(readToPreferencesContext(getActivity(),
                KEY_USER_LEARNED_DRAWER,"false"));
        if(savedInstanceState != null)
        {
            mFromSavedInstanceState = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout=inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);

        adapter = new ItemAdapt(getActivity(),getData());

        adapter.setClickListener(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return layout;
    }

    public static List<RowInfo> getData()
    {
        List<RowInfo> data = new ArrayList<>();
        int[] icons = {R.drawable.abc_ic_menu_paste_mtrl_am_alpha,
                R.drawable.abc_ic_menu_paste_mtrl_am_alpha,
                R.drawable.abc_ic_menu_paste_mtrl_am_alpha,
                R.drawable.abc_ic_menu_paste_mtrl_am_alpha,
                R.drawable.abc_ic_menu_paste_mtrl_am_alpha,
                R.drawable.abc_ic_menu_paste_mtrl_am_alpha,
                R.drawable.abc_ic_menu_paste_mtrl_am_alpha,
                R.drawable.abc_ic_menu_paste_mtrl_am_alpha};
        String [] titles = {"Item 1",
                "Item 2",
                "Item 3",
                "Item 4",
                "Item 5",
                "Item 6",
                "Item 7",
                "Item 8"};
        for (int i=0;i<8;i++)
        {
            RowInfo current = new RowInfo();
            current.iconId = icons[i % icons.length];
            current.title = titles [i % icons.length];
            data.add(current);
        }
        return data;
    }

    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolBar) {
        containerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(),
                drawerLayout,
                toolBar,
                R.string.drawer_abre,
                R.string.drawer_cierra) {
            @Override
            //Llamado cuando el Drawer esta completamente abierto
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //Si el Drawer fue abierto por primera vez guarda las preferencias para no mostrarlo de nuevo
                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    savedToPreferencesContext(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer + "");
                }
                //En caso contrario lo muestra. Redibuja la barra
                getActivity().invalidateOptionsMenu();
            }

            @Override
            //Llamado cuando cerramos completamente el drawer
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //Redibuja la Barra
                getActivity().invalidateOptionsMenu();
            }
            /*
            @Override
            //Es llamado cuando el Drawer se esta abriendo. Cambio el Alpha del ToolBar
            public void onDrawerSlide(View drawerView, float slideOffset){
                if (slideOffset<0.6) {
                    toolBar.setAlpha(1 - slideOffset);
                }
            }*/
        };
        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            //Dibuja el Drawer si el usuario no vio el drawer o giro la pantalla
            mDrawerLayout.openDrawer(containerView);
        }
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {

            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    public static void savedToPreferencesContext(Context context,
                                                 String preferenceName,
                                                 String preferenceValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readToPreferencesContext(Context context,
                                                  String preferenceName,
                                                  String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_Name, Context.MODE_PRIVATE);

        return sharedPreferences.getString(preferenceName, defaultValue);
    }

    @Override
    public void itemClicked(View view, int position) {
        startActivity(new Intent(getActivity(), MainActivity.class));

    }
}
