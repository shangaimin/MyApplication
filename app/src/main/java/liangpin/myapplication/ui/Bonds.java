package liangpin.myapplication.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Admin on 2017/1/14.
 */

public class Bonds extends SurfaceView implements SurfaceHolder.Callback{
    public Bonds(Context context) {
        super(context);
    }

    public Bonds(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        initAttr(context,attrs);
        getHolder().addCallback(this);
    }

    private void initAttr(Context context, AttributeSet attrs) {

    }

    public Bonds(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 1绳子
        // 2 绘制小球
        /**
         * 下坠
         * 上弹
         * 自由落体
         * 3 两边的小球
         */
        super.onDraw(canvas);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
       Canvas cavas= holder.lockCanvas();//锁定画布
        draw(cavas);
        holder.unlockCanvasAndPost(cavas);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
