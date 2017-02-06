package liangpin.myapplication.tablepicture;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.LineChart;
import org.achartengine.chart.PieChart;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;
import java.util.List;

import liangpin.myapplication.MyApplication;
import liangpin.myapplication.R;
import liangpin.myapplication.gen.UserDao;
import liangpin.myapplication.ndk.TestModel;
import liangpin.myapplication.ndk.User;

/**
 * Created by Admin on 2017/2/5.
 */

public class TableActivity extends Activity {
    private final static String TAG = TableActivity.class.getSimpleName();

    private LinearLayout chartLyt;
    private LineChart mLineChart;
    private PieChart mPieChart;
    Typeface mTf; // 自定义显示字体
    private UserDao userDao;
    private Button getDataBtn;

    private List<Integer> lists = new ArrayList<Integer>();

    private void setLists() {
        lists.clear();
        for (int i = 1; i < 20; i++) {
            int value = ((int) (Math.random() * 100));
            lists.add(value);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tableactivity);
        initView();
        initData();
//        mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Bold.ttf");
        drawTheChart();
        TestModel.Builder builder=new TestModel.Builder();
        TestModel model=builder.name("zhangsan").age(20).url("www.baidu.com").builder();
        Log.e("name",model.getName()+"//"+model.getAge()+"//"+model.getUrl());
        userDao= MyApplication.getInstances().getDaoSession().getUserDao();
        User user=new User(3,"尚爱民",25);
        userDao.insert(user);
    }

    public void drawTheChart() {
        XYMultipleSeriesRenderer mRenderer = getXYMulSeriesRenderer();

        XYSeriesRenderer renderer = getXYSeriesRenderer();

        mRenderer.addSeriesRenderer(renderer);

        setLists();

        XYMultipleSeriesDataset dataset = getDataSet();

        GraphicalView chartView = ChartFactory.getLineChartView(this, dataset, mRenderer);

        chartLyt.addView(chartView, 0);
        //chartLyt.invalidate();
    }

    private void initData() {

    }
    public XYSeriesRenderer getXYSeriesRenderer() {
        XYSeriesRenderer renderer = new XYSeriesRenderer();
        //设置折线宽度
        renderer.setLineWidth(2);
        //设置折线颜色
        renderer.setColor(Color.GRAY);
        renderer.setDisplayBoundingPoints(true);
        //点的样式
        renderer.setPointStyle(PointStyle.CIRCLE);
        //设置点的大小
        renderer.setPointStrokeWidth(3);
        //设置数值显示的字体大小
        renderer.setChartValuesTextSize(30);
        //显示数值
        renderer.setDisplayChartValues(true);
        return renderer;
    }

    public XYMultipleSeriesDataset getDataSet() {
        XYMultipleSeriesDataset barDataset = new XYMultipleSeriesDataset();
        CategorySeries barSeries = new CategorySeries("2016年");

        for (int i = 0; i < lists.size(); i++) {
            barSeries.add(lists.get(i));
        }

        barDataset.addSeries(barSeries.toXYSeries());
        return barDataset;
    }

    public XYMultipleSeriesRenderer getXYMulSeriesRenderer() {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        renderer.setMarginsColor(Color.argb(0x00, 0xF3, 0xF3, 0xF3));

        // 设置背景颜色
        renderer.setApplyBackgroundColor(true);
        renderer.setBackgroundColor(Color.WHITE);

        //设置Title的内容和大小
        renderer.setChartTitle("访问量统计");
        renderer.setChartTitleTextSize(50);

        //图表与四周的边距
        renderer.setMargins(new int[]{80, 80, 50, 50});

        //设置X,Y轴title的内容和大小
        renderer.setXTitle("日期");
        renderer.setYTitle("访问数");
        renderer.setAxisTitleTextSize(30);
        //renderer.setAxesColor(Color.WHITE);
        renderer.setLabelsColor(Color.BLACK);

        //图例文字的大小
        renderer.setLegendTextSize(20);

        // x、y轴上刻度颜色和大小
        renderer.setXLabelsColor(Color.BLACK);
        renderer.setYLabelsColor(0, Color.BLACK);
        renderer.setLabelsTextSize(20);
        renderer.setYLabelsPadding(30);

        // 设置X轴的最小数字和最大数字，由于我们的数据是从1开始，所以设置为0.5就可以在1之前让出一部分
        // 有兴趣的童鞋可以删除下面两行代码看一下效果
        renderer.setPanEnabled(false, false);

        //显示网格
        renderer.setShowGrid(true);

        //X,Y轴上的数字数量
        renderer.setXLabels(10);
        renderer.setYLabels(10);

        // 设置X轴的最小数字和最大数字
        renderer.setXAxisMin(1);
        renderer.setXAxisMax(20);
        // 设置Y轴的最小数字和最大数字
        renderer.setYAxisMin(0);
        renderer.setYAxisMax(100);

        // 设置渲染器显示缩放按钮
        renderer.setZoomButtonsVisible(true);
        // 设置渲染器允许放大缩小
        renderer.setZoomEnabled(true);
        // 消除锯齿
        renderer.setAntialiasing(true);

        // 刻度线与X轴坐标文字左侧对齐
        renderer.setXLabelsAlign(Paint.Align.LEFT);
        // Y轴与Y轴坐标文字左对齐
        renderer.setYLabelsAlign(Paint.Align.LEFT);

        // 允许左右拖动,但不允许上下拖动.
        renderer.setPanEnabled(true, false);

        return renderer;
    }


    private void initView() {
        chartLyt= (LinearLayout) findViewById(R.id.chart);
    }

}
