package digitalhouse.android.a0317moacns1c_02.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import digitalhouse.android.a0317moacns1c_02.R;

/**
 * Created by Gregorio Martin on 8/7/2017.
 */

public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.ViewHolder>  {

    private Context context;
    private List<Integer> drawableIdList;

    public ImageRecyclerAdapter(Context context, List<Integer> drawableIdList){
        this.context = context;
        this.drawableIdList = drawableIdList;
    }

    @Override
    public ImageRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(context);
        View cellView = inflater.inflate(R.layout.cell_multimedia_list, parent, false);
        ImageRecyclerAdapter.ViewHolder viewHolder = new ImageRecyclerAdapter.ViewHolder(cellView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ImageRecyclerAdapter.ViewHolder holder, int position){
        // - obtenemos un elemento del dataset según su posición
        // - reemplazamos el contenido de los views según tales datos

        if (!((Activity)context).isDestroyed()) Glide.with(context).load(drawableIdList.get(position)).into(holder.picture);

    }

    // Método que define la cantidad de elementos del RecyclerView
    // Puede ser más complejo en RecyclerViews que implementar filtros o búsquedas
    @Override
    public int getItemCount() {
        return drawableIdList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView picture;

        public ViewHolder(View v){
            super(v);
            picture = (ImageView) itemView.findViewById(R.id.multimedia_recycler_image_view);
            picture.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }
}
