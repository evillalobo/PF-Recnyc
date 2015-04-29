package ar.com.eduardovillalobo.tf.proyecto_recnyc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.lucasr.twowayview.TwoWayLayoutManager;
import org.lucasr.twowayview.widget.SpannableGridLayoutManager;
import org.lucasr.twowayview.widget.TwoWayView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eduardo on 17/03/2015.
 */
public class MainOptionLayoutAdapter extends RecyclerView.Adapter<MainOptionLayoutAdapter.SimpleViewHolder> {
    private final Context mContext;
    private final TwoWayView mRecyclerView;
    private ArrayList<MainOptionInfo> arrayList;
    private final int mLayoutId;
    private List<MainOptionInfo> productList = null;

    public static class SimpleViewHolder extends RecyclerView.ViewHolder{
        public final TextView title;
        public final ImageView feature_src;
        public MainOptionInfo mainOptionInfo;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            feature_src = (ImageView) itemView.findViewById(R.id.feature_src);
        }

        public void setData(Context mContext, MainOptionInfo mainOptionInfo){
            this.mainOptionInfo = mainOptionInfo;
            if (title !=null)
                title.setText(mainOptionInfo.getTitle());
            if (feature_src !=null)
                Picasso.with(mContext).load(mainOptionInfo.getFeatured_src()).into(feature_src);
        }
    }

    public MainOptionLayoutAdapter(Context context, TwoWayView recyclerView, int layoutId, List<MainOptionInfo> productList){
        mContext = context;
        this.productList = productList;
        mRecyclerView = recyclerView;
        mLayoutId = layoutId;
        this.arrayList = new ArrayList<MainOptionInfo>();
        this.arrayList.addAll(productList);
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(R.layout.home_item, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.setData(mContext, productList.get(position));
        boolean isVertical = (mRecyclerView.getOrientation() == TwoWayLayoutManager.Orientation.VERTICAL);

        final View itemView = holder.itemView;

        final int itemId = position;

        if (mLayoutId == R.layout.fragment_main) {
            final SpannableGridLayoutManager.LayoutParams lp =
                    (SpannableGridLayoutManager.LayoutParams) itemView.getLayoutParams();


            final int span1 = (itemId == 0 || itemId == 1 ? 2 : 1);
            final int span2 = (itemId == 0 ? 1 : (itemId == 2 || itemId == 3 ? 2 : 1));


            final int colSpan = (isVertical ? span2 : span1);
            final int rowSpan = (isVertical ? span1 : span2);


            if (lp.rowSpan != rowSpan || lp.colSpan != colSpan) {
                lp.rowSpan = rowSpan;
                lp.colSpan = colSpan;
                itemView.setLayoutParams(lp);
            }
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }



}
