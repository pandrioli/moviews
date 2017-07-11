package digitalhouse.android.a0317moacns1c_02.CustomViews;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.Activities.GenresSelectionTestActivity;
import digitalhouse.android.a0317moacns1c_02.Activities.ItemTabsActivity;
import digitalhouse.android.a0317moacns1c_02.Activities.SearchActivity;
import digitalhouse.android.a0317moacns1c_02.Activities.TopsActivity;
import digitalhouse.android.a0317moacns1c_02.R;

/**
 * Created by Pablo on 08/07/2017.
 */

public class BottomBar extends FrameLayout {
    //Color de icono seleccionado
    public static final Integer NORMAL_COLOR = Color.rgb(60,70,90);
    public static final Integer SELECTED_COLOR = Color.WHITE;

    private Context context;
    private LinearLayout container;
    private Integer currentIcon;
    List<Class> activities = new ArrayList<>();

    //Hardcodeado de activitys (respetar orden de los iconos)
    //Es lo unico que hay que tocar para darle funcionamiento a los botones
    private void setupActivities() {

        //0 - Home
        activities.add(ItemTabsActivity.class);

        //1 - Top 20
        activities.add(TopsActivity.class);

        //2 - Search
        activities.add(SearchActivity.class);

        //3 - Hall of fame
        activities.add(null);

        //4 - Profile
        activities.add(GenresSelectionTestActivity.class);
    }

    public BottomBar(@NonNull Context context) {
        super(context);
        this.context = context;
        setup();
    }

    public BottomBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setup();
    }

    public BottomBar(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        setup();
    }

    private void setup() {
        currentIcon = 0;
        setupActivities();

        //inflar vista
        View view = LayoutInflater.from(context).inflate(R.layout.bottom_bar, this, false);
        container = (LinearLayout)view;
        this.addView(container);

        //setup listeners y seleccionar icono segun actividad donde esta la bottombar
        for (int i=0; i<Math.min(activities.size(),container.getChildCount()); i++) {
            ImageView icon = (ImageView)container.getChildAt(i);
            icon.setOnClickListener(new IconClickListener());
            icon.setColorFilter(NORMAL_COLOR);
            if (context.getClass().equals(activities.get(i))) setCurrentIcon(i);
        }

        if (isInEditMode()) setCurrentIcon(0);
    }

    private void setCurrentIcon(Integer index) {
        currentIcon = index;
        ImageView icon = (ImageView)container.getChildAt(index);
        icon.setColorFilter(SELECTED_COLOR);
    }

    private void startActivity(Class c) {
        Intent intent = new Intent(context, c);
        context.startActivity(intent);
        Activity currentActivity = (Activity)context;
        currentActivity.overridePendingTransition(0,0);
        currentActivity.finish();
    }


    //Listener que inicia las activitys usando el contexto

    private class IconClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Integer index = container.indexOfChild(v);
            Class c = activities.get(index);
            if (c!=null && !index.equals(currentIcon)) {
                startActivity(c);
            }
        }
    }
}
