package ar.com.eduardovillalobo.tf.proyecto_recnyc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Eduardo on 05/03/2015.
 */
public class NavItemAdapt extends RecyclerView.Adapter<NavItemAdapt.MyViewHolder>{

    List<NavRowInfo> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    private ClickListener clickListener;

    public NavItemAdapt(Context context, List<NavRowInfo> data)
    {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }
    //Se llama cada vez que se quiere agregar un nuevo item
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_custom_row,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NavRowInfo current = data.get(position);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.iconId);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    //Clase interna que se encarga de crear el nuevo elemento y ahorra tener que hacer el
    //elemento una y otra vez cada vez que se agregue un nuevo item.
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView icon;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            //buscamos el ImageView y el TextView del item
            title = (TextView) itemView.findViewById(R.id.listText);
            icon = (ImageView) itemView.findViewById(R.id.listIcon);
        }

        @Override
        public void onClick(View v) {
            //context.start Activity(new Intent(context, SubActivity.class));
            if (clickListener!=null)
            {
                clickListener.itemClicked(v, getPosition());
            }
        }

    }
    public interface  ClickListener{
        public void itemClicked (View view, int position);
    }

}