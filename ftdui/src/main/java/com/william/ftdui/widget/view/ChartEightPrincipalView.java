package com.william.ftdui.widget.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.william.ftd_core.entity.SixDiseaseBean;
import com.william.ftdui.R;

import org.xclcharts.chart.RadarChart;
import org.xclcharts.chart.RadarData;
import org.xclcharts.event.click.PointPosition;
import org.xclcharts.renderer.XEnum;

import java.util.LinkedList;
import java.util.List;

/**
 * 八纲图
 */
public class ChartEightPrincipalView extends DemoView {

    private String TAG = "EightPrincipalView";
    private RadarChart chart = new RadarChart();

    private SixDiseaseBean[] diseaseBeans;

    public void setData(SixDiseaseBean[] diseaseBeans) {
        this.diseaseBeans = diseaseBeans;
        initView();
    }

    //标签集合
    private List<String> labels = new LinkedList<String>();
    private List<RadarData> chartData = new LinkedList<RadarData>();


    public ChartEightPrincipalView(Context context) {
        super(context);
    }

    public ChartEightPrincipalView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChartEightPrincipalView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private void initView() {
        chartDataSet();
        chartRender();

        //綁定手势滑动事件
        this.bindTouch(this, chart);
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //图所占范围大小
        chart.setChartRange(w, h);
    }

    private void chartRender() {
        try {
            //设置绘图区默认缩进px值
            int[] ltrb = getPieDefaultSpadding();
            chart.setPadding(ltrb[0], ltrb[1], ltrb[2], ltrb[3]);

            //设定数据源
            chart.setCategories(labels);
            chart.setDataSource(chartData);
            //圆形雷达图
//            chart.setChartType(XEnum.RadarChartType.ROUND);
            //点击事件处理
            chart.ActiveListenItemClick();
            chart.extPointClickRange(30);


//            //数据轴最大值
            chart.getDataAxis().setAxisMax(100);
            //数据轴刻度间隔
            chart.getDataAxis().setAxisSteps(25);
            //数据轴最大值
//            chart.getDataAxis().setAxisMax(30);
//            //数据轴刻度间隔
//            chart.getDataAxis().setAxisSteps(10);
            //主轴标签偏移50，以便留出空间用于显示点和标签
//			chart.getDataAxis().setTickLabelMargin(20);

            chart.getDataAxis().hide();

//			chart.getLinePaint().setColor(Color.rgb(133, 194, 2));
            chart.getLinePaint().setColor(getResources().getColor(R.color.colorCorrect));

            chart.getLabelPaint().setFakeBoldText(true);
            chart.getLabelPaint().setTextSize(40f);

            chart.disablePanMode();
            chart.DeactiveListenItemClick();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e(TAG, e.toString());
        }

    }

    private void chartDataSet() {
        labels.clear();
        labels.add("实");
        labels.add("热");
        labels.add("里");
        labels.add("虚");
        labels.add("寒");
        labels.add("表");
        LinkedList<Double> dataSeries = new LinkedList<Double>();
        for (SixDiseaseBean disease : diseaseBeans) {
            dataSeries.add(disease.getScore());
        }
        RadarData lineData = new RadarData(null, dataSeries,
                getResources().getColor(R.color.colorWrong), XEnum.DataAreaStyle.FILL);
        lineData.setLineStyle(XEnum.LineStyle.DASH);
        lineData.getPlotLine().setDotStyle(XEnum.DotStyle.DOT);
        chartData.clear();
        chartData.add(lineData);
    }

    @Override
    public void render(Canvas canvas) {
        try {
            chart.render(canvas);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        if (event.getAction() == MotionEvent.ACTION_UP) {
            triggerClick(event.getX(), event.getY());
        }
        return true;
    }

    //触发监听
    private void triggerClick(float x, float y) {
        PointPosition record = chart.getPositionRecord(x, y);
        if (null == record) return;

        if (record.getDataID() < chartData.size()) {
            RadarData lData = chartData.get(record.getDataID());
            Double lValue = lData.getLinePoint().get(record.getDataChildID());

            Toast.makeText(this.getContext(),
                    " Current Value:" + Double.toString(lValue) +
                            " Point info:" + record.getPointInfo(),
                    Toast.LENGTH_SHORT).show();
        }
    }

}

