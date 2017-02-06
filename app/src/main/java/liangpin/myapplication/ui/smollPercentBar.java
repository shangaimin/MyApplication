package liangpin.myapplication.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/**
 * Created by Admin on 2017/2/4.
 */

public class smollPercentBar extends ListView {
    public smollPercentBar(Context context) {
        super(context);
    }

    public smollPercentBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public smollPercentBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        //在viewgroup上面加一个自己的view
        View view;
    }
}
