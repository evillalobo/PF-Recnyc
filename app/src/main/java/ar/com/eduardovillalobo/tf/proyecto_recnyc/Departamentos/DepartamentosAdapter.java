package ar.com.eduardovillalobo.tf.proyecto_recnyc.Departamentos;

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

import ar.com.eduardovillalobo.tf.proyecto_recnyc.DataBaseFolder.DeptoInfo;
import ar.com.eduardovillalobo.tf.proyecto_recnyc.R;


/**
 * Created by Eduardo on 20/04/2015.
 */
public class DepartamentosAdapter extends RecyclerView.Adapter<DepartamentosAdapter.MyViewHolder> {
    List<DeptoInfo> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;
    private ClickListener clickListener;

    public DepartamentosAdapter(Context context, List<DeptoInfo> data)
    {
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
        View view = inflater.inflate(R.layout.depto_row_info,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DeptoInfo current = data.get(position);
        holder.name.setText(current.getName());
        int imageResource = context.getResources().getIdentifier(current.getImageID(),
                null,context.getPackageName());
        Picasso.with(context).load(imageResource).into(holder.image);
        holder.desc.setText(current.getDesc());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface  ClickListener{
        public void itemClicked(View view, int position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;
        ImageView image;
        TextView desc;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name = (TextView) itemView.findViewById(R.id.titulo_depto);
            image = (ImageView) itemView.findViewById(R.id.image_depto);
            desc = (TextView) itemView.findViewById(R.id.desc_depto);
        }

        @Override
        public void onClick(View v) {
            if(clickListener!=null)
            {
                clickListener.itemClicked(v,getPosition());
            }
        }
    }
}
