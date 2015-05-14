package ar.com.eduardovillalobo.tf.proyecto_recnyc.NavigationDrawerFolder;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import ar.com.eduardovillalobo.tf.proyecto_recnyc.R;

/**
 * Created by Eduardo on 05/03/2015.
 */
public class NavItemAdapt extends RecyclerView.Adapter<NavItemAdapt.MyViewHolder> {

    private int expandedPosition = -1;
    List<NavRowInfo> data = Collections.emptyList();
    private ClickListener clickListener;

    private LayoutInflater inflater;
    private Context context;
    private int mOriginalHeight = 0;
    private boolean mIsViewExpanded = false;


    public NavItemAdapt(Context context, List<NavRowInfo> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    //Se llama cada vez que se quiere agregar un nuevo home_item
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nav_custom_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NavRowInfo current = data.get(position);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.iconId);
        /**
         * Lista expansible
         */
        if (position == expandedPosition) {
            holder.llExpandedArea.setVisibility(View.VISIBLE);
        } else {
            holder.llExpandedArea.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    //Clase interna que se encarga de crear el nuevo elemento y ahorra tener que hacer el
    //elemento una y otra vez cada vez que se agregue un nuevo home_item.

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout llExpandedArea;

        TextView title;
        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            //buscamos el ImageView y el TextView del home_item
            title = (TextView) itemView.findViewById(R.id.listText);
            icon = (ImageView) itemView.findViewById(R.id.listIcon);
            llExpandedArea = (LinearLayout) itemView.findViewById(R.id.llExpandArea);
        }

        @Override
        public void onClick(View v) {
            int position = getPosition();
            if (position == 1) {
                final int originalHeight = llExpandedArea.getHeight();
                animationDown((LinearLayout) v.findViewById(R.id.llExpandArea), originalHeight);
            }
            //context.start Activity(new Intent(context, SubActivity.class));
            if (clickListener != null) {
                clickListener.itemClicked(v, getPosition());
            }
        }

        public void animationDown(final LinearLayout opciones, int originalHeight) {

            // Declare a ValueAnimator object
            ValueAnimator valueAnimator;
            if (!opciones.isShown()) {
                opciones.setVisibility(View.VISIBLE);
                opciones.setEnabled(true);
                valueAnimator = ValueAnimator.ofInt(0, originalHeight + originalHeight); // These values in this method can be changed to expand however much you like
            } else {
                valueAnimator = ValueAnimator.ofInt(originalHeight + originalHeight, 0);

                Animation a = new AlphaAnimation(1.00f, 0.00f); // Fade out

                a.setDuration(200);
                // Set a listener to the animation and configure onAnimationEnd
                a.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        opciones.setVisibility(View.INVISIBLE);
                        opciones.setEnabled(false);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                // Set the animation on the custom view
                opciones.startAnimation(a);
            }
            valueAnimator.setDuration(200);
            valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator animation) {
                    Integer value = (Integer) animation.getAnimatedValue();
                    opciones.getLayoutParams().height = value.intValue();
                    opciones.requestLayout();
                }
            });
            valueAnimator.start();
        }

    }

    public interface ClickListener {
        public void itemClicked(View view, int position);
    }

}