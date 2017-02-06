package liangpin.myapplication.loadingAnimation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import liangpin.myapplication.R;

/**
 * Created by Admin on 2017/1/12.
 */

public class DNLoadingView extends View{
//    大圆半径
    private float mRotationRadius=120;
    private float mCircleRadius=15;//小圆半径
    private int[] mCircleColors;//小圆圈的颜色列表
    private float ff=5;

    private float mCenterX,mCenterY,mDiagonalDist;//中心点与对剑仙一盘
    private float mCurrentRotationRadius=mRotationRadius;
    private float mCurrentRotationAngel;
    ValueAnimator mAnimator;
    private int DNLoadingViewcolor;
    private long mRotationDuration=5000;//动画时间
    float mHoloRadius;
private Paint mPaint,mPaintBackground;

    public DNLoadingView(Context context) {
        super(context);
    }

    public DNLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DNLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void init(){
        mPaint.setAntiAlias(true);
        mCircleColors=getResources().getIntArray(R.array.ciclecolor);
    }
    private DNLoadingViewState dnLoadingViewState=null;
    private abstract  class DNLoadingViewState{
        public abstract  void drawState(Canvas canvas);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX=w/2f;
        mCenterY=h/2f;
        mDiagonalDist= (float) Math.sqrt((w*w+h*h)/2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(dnLoadingViewState==null){
            dnLoadingViewState=new RotationState();
        }
        dnLoadingViewState.drawState(canvas);
        super.onDraw(canvas);

//        if(){
//
//        }else if(){
//
//        }else if(){
//
//        }
    }
    //第一个动画小圆的
    private class RotationState extends DNLoadingViewState{
        public RotationState(){
            //花1600ms,计算某个时刻当前的角度是多少,0-2PI的某个值
            mAnimator=ValueAnimator.ofFloat(0,(float) Math.PI*2);
            mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mCurrentRotationAngel= (float) animation.getAnimatedValue();
                    postInvalidate();
                }
            });
            mAnimator.setDuration(mRotationDuration);
            mAnimator.setRepeatCount(ValueAnimator.INFINITE);
            mAnimator.start();
        }
        @Override
        public void drawState(Canvas canvas) {
            //1擦灰板，绘制背景
            drabackground(canvas);
            //2 绘制小圆
            drawCicle(canvas);
        }

    }
    //第而个动画
    private class MergingState extends DNLoadingViewState{
        public MergingState(){
            //花1600ms,计算某个时刻当前的角度是多少,0-2PI的某个值
            mAnimator=ValueAnimator.ofFloat(0,(mRotationRadius));
            mAnimator.setInterpolator(new LinearInterpolator());
            mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mCurrentRotationRadius= (float) animation.getAnimatedValue();
                    postInvalidate();
                }
            });
            mAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    DNLoadingView.super.onAnimationEnd();
                    dnLoadingViewState=new ExpandState();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            mAnimator.setDuration(mRotationDuration);
            mAnimator.reverse();
        }
        @Override
        public void drawState(Canvas canvas) {
            //1擦灰板，绘制背景
            drabackground(canvas);
            //2 绘制小圆
            drawCicle(canvas);
        }
    }
    //第3个动画水波纹的空心扩散动画
    private class ExpandState extends DNLoadingViewState{
        public ExpandState(){
            //花800ms,计算某个时刻当前的空心圆的半径是多少,0-对角线/2的某个值
            mAnimator=ValueAnimator.ofFloat(0,(mDiagonalDist));
            mAnimator.setInterpolator(new LinearInterpolator());
            mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mHoloRadius= (float) animation.getAnimatedValue();
                    postInvalidate();
                }
            });
            mAnimator.setDuration(mRotationDuration/2);
            mAnimator.reverse();
        }
        @Override
        public void drawState(Canvas canvas) {
            drabackground(canvas);
        }
    }

    public  void SplashDisappear(){
        if(dnLoadingViewState!=null&&dnLoadingViewState instanceof RotationState){

        }
        dnLoadingViewState=new MergingState();
    }
    private void drabackground(Canvas canvas) {
        if(mHoloRadius>0f){
            float strokeWidth=mDiagonalDist-mHoloRadius;
            mPaintBackground.setStrokeWidth(strokeWidth);
            float radius=mHoloRadius+strokeWidth/2;
            canvas.drawCircle(mCenterX,mCenterY,radius,mPaintBackground);
        }else {
            canvas.drawColor(DNLoadingViewcolor);
        }
    }
    private void drawCicle(Canvas canvas) {
        float rotationAngel= (float) (2*Math.PI/mCircleColors.length);
        for(int i=0;i<mCircleColors.length;i++){
            double angel=i*rotationAngel+mCurrentRotationAngel;
            float  cx= (float) (mCurrentRotationRadius*Math.cos(angel)+mCenterX);
            float  cy= (float) (mCurrentRotationRadius*Math.sin(angel)+mCenterX);
            canvas.drawCircle(cx,cy,mCircleRadius,mPaint);
        }
    }
}
