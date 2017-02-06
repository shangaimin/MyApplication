package liangpin.myapplication.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import liangpin.myapplication.R;

/**
 * Created by Admin on 2017/2/3.
 */

public class RoundImageView extends ImageView {
    private Paint paint=new Paint();
    private int percent;
    ;
    public RoundImageView(Context context) {
        super(context);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray=context.getTheme().obtainStyledAttributes(attrs, R.styleable.RoundImageView,0,0);
        percent=typedArray.getInt(R.styleable.RoundImageView_round_percent,0);
        typedArray.recycle();
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Drawable draw=getDrawable();
        Bitmap b=((BitmapDrawable)draw).getBitmap();
        Bitmap bitmap=getRoundBitmap(b,percent);
        Rect rectSrc=new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
        Rect rectDest=new Rect(0,0,getWidth(),getHeight());
        paint.reset();
        canvas.drawBitmap(bitmap,rectSrc,rectDest,paint);
    }
    private Bitmap getRoundBitmap(Bitmap b,int RoundPx){
        Bitmap output=Bitmap.createBitmap(b.getWidth(),b.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas cavas=new Canvas(output);
        int color=0xff424242;
        Rect r=new Rect(0,0,b.getWidth(),b.getHeight());
        RectF rectf=new RectF(r);
        paint.setAntiAlias(true);
        cavas.drawARGB(0,0,0,0);
        paint.setColor(color);
        int x=b.getWidth();
        cavas.drawRoundRect(rectf,RoundPx,RoundPx,paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        cavas.drawBitmap(b,r,r,paint);
        return output;
    }


}
