package ar.com.eduardovillalobo.tf.proyecto_recnyc.Categorias;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import ar.com.eduardovillalobo.tf.proyecto_recnyc.DataBaseFolder.CategoriasInfo;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.R;

/**
 * Created by Eduardo on 01/06/2015.
 */
public class CategoriasAdapter extends RecyclerView.Adapter<CategoriasAdapter.MyViewHolder> {
    List<CategoriasInfo> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    private ClickListener clickListener;

    public CategoriasAdapter(Context context, List<CategoriasInfo> data){
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
        View view = inflater.inflate(R.layout.cat_row_info, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CategoriasInfo current = data.get(position);
        holder.categoria.setText(current.getCategoria());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface  ClickListener{
        public void itemClicked(View view, int position);
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView categoria;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            categoria = (TextView) itemView.findViewById(R.id.titulo_categoria);

        }

        @Override
        public void onClick(View v) {
            if(clickListener!=null)
            {
                clickListener.itemClicked(v,getPosition());
                Log.d("Categoria Adapter>","Click en elemento");
            }
        }
    }
}
