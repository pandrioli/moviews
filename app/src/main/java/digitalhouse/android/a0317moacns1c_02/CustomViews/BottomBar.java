package digitalhouse.android.a0317moacns1c_02.CustomViews;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import digitalhouse.android.a0317moacns1c_02.Activities.BookmarkActivity;
import digitalhouse.android.a0317moacns1c_02.Activities.ItemTabsActivity;
import digitalhouse.android.a0317moacns1c_02.Activities.SearchActivity;
import digitalhouse.android.a0317moacns1c_02.Activities.TopsActivity;
import digitalhouse.android.a0317moacns1c_02.Activities.WelcomeActivity;
import digitalhouse.android.a0317moacns1c_02.R;

/**
 * Created by Pablo on 08/07/2017.
 */

public class BottomBar extends FrameLayout {
    //Color de icono seleccionado
    public static Integer NORMAL_COLOR = Color.argb(100,255,255,255);
    public static final Integer SELECTED_COLOR = Color.WHITE;
    private Boolean onBoarding = false;
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
        activities.add(BookmarkActivity.class);

        //4 - Profile
        activities.add(WelcomeActivity.class);
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
            icon.setColorFilter(NORMAL_COLOR, PorterDuff.Mode.SRC_IN);
            if (context.getClass().equals(activities.get(i))) setCurrentIcon(i);
        }

        if (isInEditMode()) setCurrentIcon(0);
    }

    public void setOnBoarding() {
        this.onBoarding = true;
        container.setBackgroundColor(Color.argb(0,0,0,0));
    }

    public void setCurrentIcon(Integer index) {
        currentIcon = index;
        ImageView selectedIcon = (ImageView)container.getChildAt(index);
        if (onBoarding) {
            for (int i=0; i<Math.min(activities.size(),container.getChildCount()); i++) {
                ImageView icon = (ImageView)container.getChildAt(i);
                icon.setColorFilter(NORMAL_COLOR, PorterDuff.Mode.SRC_IN);
                icon.setScaleX(1f);
                icon.setScaleY(1f);
            }
            selectedIcon.setScaleX(1.5f);
            selectedIcon.setScaleY(1.5f);
        }
        selectedIcon.setColorFilter(SELECTED_COLOR);
    }

    private void startActivity(Class c) {
        if (onBoarding) return;
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
