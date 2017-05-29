package digitalhouse.android.a0317moacns1c_02.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import digitalhouse.android.a0317moacns1c_02.R;

/**
 * Created by Gregorio Martin on 28/5/2017.
 */

public class MultimediaRecyclerAdapter extends RecyclerView.Adapter<MultimediaRecyclerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> mDataSet;

    public MultimediaRecyclerAdapter(Context context, ArrayList<String> myDataSet){
        this.context = context;
        mDataSet = myDataSet;
    }

    @Override
    public MultimediaRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(context);
        View cellView = inflater.inflate(R.layout.cell_multimedia_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(cellView);

        // Aquí podemos definir tamaños, márgenes, paddings
        // ...

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        // - obtenemos un elemento del dataset según su posición
        // - reemplazamos el contenido de los views según tales datos

        String URL = mDataSet.get(position);

        Picasso.with(context).load(URL).fit().centerCrop().into(holder.picture);
    }

    // Método que define la cantidad de elementos del RecyclerView
    // Puede ser más complejo en RecyclerViews que implementar filtros o búsquedas
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView picture;

        public ViewHolder(View v){
            super(v);
            picture = (ImageView) itemView.findViewById(R.id.multimedia_recycler_image_view);
        }
    }






}
