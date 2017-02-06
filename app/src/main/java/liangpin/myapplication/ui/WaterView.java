package liangpin.myapplication.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import liangpin.myapplication.R;

/**
 * Created by Admin on 2017/1/16.
 */

public class WaterView extends View {
    Paint paint;
    Path path;
    int wave_length=400;
    int width,height,origintY=500;
    int dx;
    int dy;
    int Duration=2000;
    public WaterView(Context context) {
        super(context);
    }

    public WaterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        init();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
    }
    private void init(){
        paint=new Paint();
        paint.setColor(getResources().getColor(R.color.blue));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        path=new Path();
    }
    public WaterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setPathData();

        canvas.drawPath(path,paint);
    }
    private void setPathData(){
        width=getWidth();
        height=getHeight();
        paint.reset();
        path.moveTo(-wave_length+dx,origintY);
//        path.addOval(new RectF(100,100,300,300), Path.Direction.CCW);
        //不断的绘制一个又一个的波形，
        for(int i=-wave_length;i<width+wave_length;i+=wave_length){
//            path.quadTo(x1,y1,x2,y2);
            //相对于二阶贝塞尔曲线（相对于自己起始点（上一个动作的终点）的偏移量）
            path.rQuadTo(wave_length/4,-wave_length,wave_length/2,0);
            path.rQuadTo(wave_length/4,wave_length,wave_length/2,0);

        }
        path.lineTo(width,height);
        path.lineTo(0,height);
        path.close();
    }
    public void startAnimation(){
        ValueAnimator animator=ValueAnimator.ofFloat(0,1);
        animator.setDuration(Duration);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float fraction= (Float) animation.getAnimatedValue();
                dx= (int) (wave_length*fraction);
//                if(wave_View_rise){
//
//                }
                postInvalidate();
            }
        });
        animator.start();
    }
}
