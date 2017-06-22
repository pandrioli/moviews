package digitalhouse.android.a0317moacns1c_02.CustomViews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pablo on 20/06/2017.
 */

public class PageIndicator extends LinearLayout {
    private Integer totalPages = 5;
    private Integer currentPage = 0;
    private List<IndicatorDot> dotList;
    private Context context;


    public PageIndicator(Context context) {
        super(context);
        this.context = context;
        dotList = new ArrayList<>();
    }

    public PageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        dotList = new ArrayList<>();
    }

    public PageIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        dotList = new ArrayList<>();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isInEditMode()) {
            setTotalPages(5);
            setPage(0);
        }
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
        createDots();
    }

    private void createDots(){
        if (totalPages>1) {
            for (IndicatorDot dot : dotList) {
                this.removeView(dot);
            }
            dotList = new ArrayList<>();
            for (int i = 0; i < totalPages; i++) {
                IndicatorDot dot = new IndicatorDot(context, this.getLayoutParams().height);
                dotList.add(dot);
                this.addView(dot);
            }
        }
    }

    public void setPage(Integer page) {
        if (totalPages>1) {
            dotList.get(currentPage).selected(false);
            dotList.get(page).selected(true);
            currentPage = page;
        }
    }



    private class IndicatorDot extends View {
        private Boolean selected;
        private Integer size;
        public IndicatorDot(Context context, Integer size) {
            super(context);
            this.selected = false;
            this.size = size;
        }

        public void selected(Boolean selected) {
            this.selected = selected;
            this.invalidate();
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            ViewGroup.LayoutParams layoutParams = this.getLayoutParams();
            layoutParams.width = size;
            layoutParams.height = size;
            this.setLayoutParams(layoutParams);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            float halfSize = size / 2;
            Paint dotPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            dotPaint.setColor(Color.WHITE);
            dotPaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(halfSize, halfSize, halfSize*0.7f, dotPaint);
            if (!selected) {
                dotPaint.setColor(Color.BLACK);
                canvas.drawCircle(halfSize, halfSize, halfSize*0.5f, dotPaint);
            }
        }
    }

}
