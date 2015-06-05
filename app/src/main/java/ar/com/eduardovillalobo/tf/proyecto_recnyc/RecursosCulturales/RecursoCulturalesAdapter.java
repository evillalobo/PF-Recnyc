package ar.com.eduardovillalobo.tf.proyecto_recnyc.RecursosCulturales;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import ar.com.eduardovillalobo.tf.proyecto_recnyc.DataBaseFolder.RecursoCulturalInfo;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.R;

/**
 * Created by Eduardo on 29/05/2015.
 */
public class RecursoCulturalesAdapter extends RecyclerView.Adapter<RecursoCulturalesAdapter.MyViewHolder>{

    List<RecursoCulturalInfo> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    private ClickListener clickListener;

    public RecursoCulturalesAdapter(Context context, List<RecursoCulturalInfo> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rec_cult_row_info, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RecursoCulturalInfo current = data.get(position);
        holder.nombre.setText(current.getNombre());
        holder.descripcion.setText(current.getDescripcion());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface ClickListener {
        public void itemClicked(View view, int position);
    }

    public void setClickListener(ClickListener clickListener)
    {
        this.clickListener = clickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView nombre;
        TextView descripcion;
        ImageView imagen;
        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            nombre = (TextView) itemView.findViewById(R.id.titulo_rec_cult);
            descripcion = (TextView) itemView.findViewById(R.id.desc_rec_cult);
            imagen = (ImageView) itemView.findViewById(R.id.image_rec_cult);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.itemClicked(v, getPosition());
            }
        }
    }
}
