package liangpin.myapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.ListView;

/**
 * Created by Admin on 2016/12/21.
 */

public class QQZoneHeadZoomView extends ListView{
    private ImageView mImageView;//缩放的图
    private int mImageViewHead;//图片原本的大小
    public QQZoneHeadZoomView(Context context) {
        super(context);
    }

    public QQZoneHeadZoomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public QQZoneHeadZoomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mImageViewHead=context.getResources().getDimensionPixelSize(R.dimen.imageview);

    }
    public void setImageview(ImageView imageView){
        mImageView=imageView;
    }
    //listview下拉过度或者上拉过度
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        /*
        deltaY dy y轴上的偏移量
        -下啦过度
        +上啦过度
         */
        if(deltaY<0){
            //下啦过度，改变图片高度宽度不变
            mImageView.getLayoutParams().height=mImageView.getHeight()-deltaY;
            mImageView.requestLayout();
        }else{
            if(mImageView.getHeight()>mImageViewHead){
                mImageView.getLayoutParams().height=mImageView.getHeight()-deltaY;
                mImageView.requestLayout();
            }
        }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(ev.getAction()==MotionEvent.ACTION_UP){
//            //执行动画
//            ivanimate ivanimate=new ivanimate(mImageView,mImageViewHead);
//            ivanimate.setDuration(300);
//            mImageView.setAnimation(ivanimate);
        }
        return super.onTouchEvent(ev);
    }

    class ivanimate extends Animation{
        private int targetheight,originHeight,extraheight;
        public ivanimate(ImageView imageView,int targetheight){
            this.targetheight=targetheight;
            this.originHeight=imageView.getHeight();
            this.extraheight=originHeight-targetheight;
        }
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            mImageView.getLayoutParams().height=0;
            mImageView.requestLayout();
            super.applyTransformation(interpolatedTime, t);
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        View parent= (View) mImageView.getParent();
        int delaty=parent.getTop();
        if(delaty<0&&mImageView.getHeight()>mImageViewHead){
            mImageView.getLayoutParams().height=mImageView.getHeight()-delaty;
            parent.layout(parent.getLeft(),0,parent.getRight(),parent.getHeight());
            mImageView.requestLayout();
        }

        super.onScrollChanged(l, t, oldl, oldt);
    }
}
