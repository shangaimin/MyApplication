package liangpin.myapplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import liangpin.myapplication.R;

/**
 * Created by Admin on 2016/12/31.
 * 自定义百分百布局
 */

public class PercentLayout extends RelativeLayout{
    private float layout_widthpercent;
    private float layout_heighpercent;

    public PercentLayout(Context context) {
        super(context);

    }

    public PercentLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PercentLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width= View.MeasureSpec.getSize(widthMeasureSpec);
        int height=View.MeasureSpec.getSize(heightMeasureSpec);
        //测量子空间的宽和高，进行改变
        float widthPercent=0;
        float heightPercent=0;
        int childcount=getChildCount();
        for(int i=0;i<childcount;i++){
            View child=getChildAt(i);
            ViewGroup.LayoutParams params=child.getLayoutParams();
            if(params instanceof PercentLayout.PercentLayoutParams){
                widthPercent=((PercentLayoutParams) params).getWidthpercent();
                heightPercent=((PercentLayoutParams) params).getHeighpercent();
            }
            if(widthPercent !=0){
                params.width= (int) (width*widthPercent);
            }
            if(heightPercent!=0){
                params.height= (int) (height*heightPercent);
            }
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        super.onLayout(changed, l, t, r, b);

    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new PercentLayoutParams(getContext(),attrs);
    }
    public static class PercentLayoutParams extends RelativeLayout.LayoutParams{
        private float widthpercent;
        private float heighpercent;

        public float getWidthpercent() {
            return widthpercent;
        }

        public void setWidthpercent(float widthpercent) {
            this.widthpercent = widthpercent;
        }

        public float getHeighpercent() {
            return heighpercent;
        }

        public void setHeighpercent(float heighpercent) {
            this.heighpercent = heighpercent;
        }

        public PercentLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            TypedArray array=c.obtainStyledAttributes(attrs, R.styleable.PercentLayout);
            widthpercent=array.getFloat(R.styleable.PercentLayout_layout_widthpercent,widthpercent);
            heighpercent=array.getFloat(R.styleable.PercentLayout_layout_heightpercent,heighpercent);
            array.recycle();
        }
    }
}
