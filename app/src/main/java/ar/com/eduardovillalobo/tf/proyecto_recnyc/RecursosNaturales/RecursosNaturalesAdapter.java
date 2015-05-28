package ar.com.eduardovillalobo.tf.proyecto_recnyc.RecursosNaturales;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

import ar.com.eduardovillalobo.tf.proyecto_recnyc.DataBaseFolder.RecursoNaturalInfo;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.R;


/**
 * Created by Eduardo on 26/05/2015.
 */
public class RecursosNaturalesAdapter extends RecyclerView.Adapter<RecursosNaturalesAdapter.MyViewHolder> {

    List<RecursoNaturalInfo> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    private ClickListener clickListener;

    public RecursosNaturalesAdapter(Context context, List<RecursoNaturalInfo> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }
    public void setClickListener(ClickListener clickListener)
    {
        this.clickListener = clickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rec_nat_row_info, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RecursoNaturalInfo current = data.get(position);
        holder.nombre.setText(current.getNombre());
        //TODO problema con la carga de imagen
       /* int imageResource = context.getResources().getIdentifier(current.getImageID(),
                null,context.getPackageName());
        Picasso.with(context).load(imageResource).into(holder.imagen);*/
        holder.descripcion.setText(current.getDescripcion());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface ClickListener {
        public void itemClicked(View view, int position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nombre;
        TextView descripcion;
        ImageView imagen;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            nombre = (TextView) itemView.findViewById(R.id.titulo_rec_nat);
            descripcion = (TextView) itemView.findViewById(R.id.desc_rec_nat);
            imagen = (ImageView) itemView.findViewById(R.id.image_rec_nat);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) {
                clickListener.itemClicked(v, getPosition());
            }
        }
    }
}
