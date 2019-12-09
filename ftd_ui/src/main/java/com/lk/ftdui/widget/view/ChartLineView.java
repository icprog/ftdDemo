package com.lk.ftdui.widget.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.lk.ftd_core.entity.TendencyResult;

import org.xclcharts.chart.AreaChart;
import org.xclcharts.chart.AreaData;
import org.xclcharts.chart.LineChart;
import org.xclcharts.chart.LineData;
import org.xclcharts.event.click.PointPosition;
import org.xclcharts.renderer.XEnum;

import java.util.LinkedList;
import java.util.List;

public class ChartLineView extends DemoView {

    private String TAG = "LineChart01View";
    private AreaChart chart = new AreaChart();

    //标签集合
    private LinkedList<String> labels = new LinkedList<String>();
    private LinkedList<AreaData> chartData = new LinkedList<AreaData>();

    private Paint mPaintTooltips = new Paint(Paint.ANTI_ALIAS_FLAG);


    public ChartLineView(Context context) {
        super(context);
    }

    public ChartLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ChartLineView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setData(TendencyResult tendencyResult) {
        List<TendencyResult.ResultBean> list = tendencyResult.getResult();
        LinkedList<Double> dataSeries = new LinkedList<Double>();
        int size = list.size();
        for (int i = size - 1; i > -1; i--) {
            labels.add(dateFormate(list.get(i).getDate()));
            dataSeries.add(list.get(i).getScore());
        }

        AreaData lineData = new AreaData("", dataSeries, Color.BLUE, Color.BLUE);
        lineData.setDotStyle(XEnum.DotStyle.DOT);
        lineData.getDotLabelPaint().setColor(Color.BLUE);
        lineData.getDotLabelPaint().setTextSize(22);
        lineData.getDotLabelPaint().setTextAlign(Paint.Align.LEFT);
        lineData.setItemLabelRotateAngle(45.f);
        lineData.setGradientDirection(XEnum.Direction.VERTICAL);
        lineData.getLabelOptions().setLabelBoxStyle(XEnum.LabelBoxStyle.TEXT);
        lineData.getLabelOptions().setMargin(0);//todo
        chartData.add(lineData);

        chartRender();
        invalidate();

        this.bindTouch(this, chart);
    }

    private String dateFormate(String date) {
        String[] strs = date.split("-");
        StringBuilder sb = new StringBuilder(strs[1]);
        sb.append(".");
        sb.append(strs[2]);
        return sb.toString();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //图所占范围大小
        chart.setChartRange(w, h);
    }

    private void chartRender() {
        try {

            //设置绘图区默认缩进px值,留置空间显示Axis,Axistitle....
            int[] ltrb = getBarLnDefaultSpadding();
            chart.setPadding(ltrb[0], ltrb[1], ltrb[2], ltrb[3]);

            //限制Tickmarks可滑动偏移范围
            chart.setXTickMarksOffsetMargin(ltrb[2] - 20.f);
            chart.setYTickMarksOffsetMargin(ltrb[3] - 20.f);


            //显示边框
//			chart.showRoundBorder();


            //设定数据源
            chart.setCategories(labels);
            chart.setDataSource(chartData);

            //数据轴最大值
            chart.getDataAxis().setAxisMax(100);
            //数据轴刻度间隔
            chart.getDataAxis().setAxisSteps(20);
            chart.getDataAxis().hideAxisLine();
            chart.getDataAxis().hideTickMarks();

            //背景网格
            chart.getPlotGrid().showHorizontalLines();

            chart.getPlotGrid().getHorizontalLinePaint().setStrokeWidth(1);
            chart.getPlotGrid().setHorizontalLineStyle(XEnum.LineStyle.SOLID);
            chart.getPlotGrid().setVerticalLineStyle(XEnum.LineStyle.DOT);

            chart.getPlotGrid().getHorizontalLinePaint().setColor(Color.GRAY);
            chart.getPlotGrid().getVerticalLinePaint().setColor(Color.BLUE);

            chart.setTitle("趋势分析");
            chart.setTitleAlign(XEnum.HorizontalAlign.LEFT);

//            chart.getAxisTitle().setLowerTitle("(年份)");

            //激活点击监听
            chart.ActiveListenItemClick();
            //为了让触发更灵敏，可以扩大5px的点击监听范围
            chart.extPointClickRange(5);
            chart.showClikedFocus();

            //绘制十字交叉线
            chart.showDyLine();
            chart.getDyLine().setDyLineStyle(XEnum.DyLineStyle.Vertical);
            chart.getPlotArea().extWidth(100.f);

            //调整轴显示位置
            chart.setDataAxisLocation(XEnum.AxisLocation.LEFT);
            chart.setCategoryAxisLocation(XEnum.AxisLocation.BOTTOM);

            //收缩绘图区右边分割的范围，让绘图区的线不显示出来
//            chart.getClipExt().setExtRight(0.f);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e(TAG, e.toString());
        }
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

        if (event.getAction() == MotionEvent.ACTION_UP) {
            triggerClick(event.getX(), event.getY());
        }
        super.onTouchEvent(event);
        return true;
    }


    //触发监听
    private void triggerClick(float x, float y) {

        //交叉线
        if (chart.getDyLineVisible()) chart.getDyLine().setCurrentXY(x, y);
        if (!chart.getListenItemClickStatus()) {
            //交叉线
            if (chart.getDyLineVisible()) this.invalidate();
        } else {
            PointPosition record = chart.getPositionRecord(x, y);
            if (null == record) {
                if (chart.getDyLineVisible()) this.invalidate();
                return;
            }

            LineData lData = chartData.get(record.getDataID());
            Double lValue = lData.getLinePoint().get(record.getDataChildID());

            float r = record.getRadius();
            chart.showFocusPointF(record.getPosition(), r + r * 0.5f);
            chart.getFocusPaint().setStyle(Paint.Style.STROKE);
            chart.getFocusPaint().setStrokeWidth(3);
            if (record.getDataID() >= 3) {
                chart.getFocusPaint().setColor(Color.BLUE);
            } else {
                chart.getFocusPaint().setColor(Color.RED);
            }

            //在点击处显示tooltip
//            mPaintTooltips.setColor(Color.RED);
            //chart.getToolTip().setCurrentXY(x,y);
//            chart.getToolTip().setCurrentXY(record.getPosition().x, record.getPosition().y);
//
//            chart.getToolTip().addToolTip(" Key:" + lData.getLineKey(), mPaintTooltips);
//            chart.getToolTip().addToolTip(" Label:" + lData.getLabel(), mPaintTooltips);
//            chart.getToolTip().addToolTip(" Current Value:" + Double.toString(lValue), mPaintTooltips);


            //当前标签对应的其它点的值
            int cid = record.getDataChildID();
            String xLabels = "";
            for (LineData data : chartData) {
                if (cid < data.getLinePoint().size()) {
                    xLabels = Double.toString(data.getLinePoint().get(cid));
                    chart.getToolTip().addToolTip("Line:" + data.getLabel() + "," + xLabels, mPaintTooltips);
                }
            }
            this.invalidate();
        }
    }
}
