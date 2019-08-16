package com.william.ftdui.widget.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;

import com.william.ftd_core.entity.DataBean;
import com.william.ftd_core.entity.QuotaInfoListBean;
import com.william.ftdui.R;

import org.xclcharts.chart.BarChart;
import org.xclcharts.chart.BarData;
import org.xclcharts.common.DensityUtil;
import org.xclcharts.common.IFormatterDoubleCallBack;
import org.xclcharts.common.IFormatterTextCallBack;
import org.xclcharts.renderer.XEnum;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BarChartView extends DemoView {

    private String TAG = "BarChart12View";
    private BarChart chartA = new BarChart();
    //轴数据源
    private List<String> chartLabels = new LinkedList<String>();
    private List<BarData> chartDataA = new LinkedList<BarData>();

    private BarChart chart2 = new BarChart();
    private List<BarData> chartDataB = new LinkedList<BarData>();

    public BarChartView(Context context) {
        this(context, null);
    }

    public BarChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BarChartView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public void setData(List<QuotaInfoListBean> quotaInfoList) {
        List<DataBean> list = new ArrayList<DataBean>();
        for (QuotaInfoListBean bean : quotaInfoList) {
            list.addAll(bean.getData());
        }

        List<Double> dataSeriesA = new LinkedList<Double>();
        List<Integer> dataColorA = new LinkedList<Integer>();
        List<Double> dataSeriesB = new LinkedList<Double>();
        List<Integer> dataColorB = new LinkedList<Integer>();
        chartLabels.clear();
        dataSeriesA.clear();
        dataColorA.clear();
        dataSeriesB.clear();
        dataColorB.clear();
        for (DataBean bean : list) {
            chartLabels.add(bean.getQuotaName());
            dataSeriesA.add(bean.getNormalScore());
            dataColorA.add(R.color.colorGrey);
            dataSeriesB.add(bean.getScore());
            dataColorB.add(bean.getScore() < bean.getNormalScore() ? getResources().getColor(R.color.colorWrong) : getResources().getColor(R.color.colorCorrect));
        }

        chartDataA.clear();
        chartDataA.add(new BarData("", dataSeriesA, dataColorA, R.color.colorGrey));

        chartDataB.clear();
        chartDataB.add(new BarData("", dataSeriesB, dataColorB, R.color.colorWrong));

        chartRenderA();
        chartRenderB();

        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //图所占范围大小
        chartA.setChartRange(w, h);
        chart2.setChartRange(w, h);
    }


    private void chartRenderA() {
        try {

            //设置绘图区默认缩进px值,留置空间显示Axis,Axistitle....
            int[] ltrb = getBarLnDefaultSpadding();
            chartA.setPadding(ltrb[0], ltrb[1],
                    ltrb[2], ltrb[3] + DensityUtil.dip2px(this.getContext(), 30));

            //标题
//            chartA.setTitle("指数分解");
//            chartA.setTitleAlign(XEnum.HorizontalAlign.LEFT);
//            chartA.addSubtitle("(XCL-Charts Demo)");
            //数据源
            chartA.setCategories(chartLabels);
            chartA.setDataSource(chartDataA);

            chartA.getCategoryAxis().setTickLabelMargin(20);

            //数据轴
            chartA.getDataAxis().setAxisMax(10);
            chartA.getDataAxis().setAxisMin(0);
            chartA.getDataAxis().setAxisSteps(1);


            //指隔多少个轴刻度(即细刻度)后为主刻度
            chartA.getDataAxis().setDetailModeSteps(5);

            //背景网格
//			chartA.getPlotGrid().hideHorizontalLines();
//			chartA.getPlotGrid().hideVerticalLines();
//			chartA.getPlotGrid().hideEvenRowBgColor();
//			chartA.getPlotGrid().hideOddRowBgColor();

            //定义数据轴标签显示格式
            chartA.getDataAxis().setLabelFormatter(new IFormatterTextCallBack() {

                @Override
                public String textFormatter(String value) {
                    // TODO Auto-generated method stub
                    Double tmp = Double.parseDouble(value);
                    DecimalFormat df = new DecimalFormat("#0");
                    String label = df.format(tmp).toString();
                    return (label);
                }
            });

            //在柱形顶部显示值
            chartA.getBar().setItemLabelVisible(true);
            chartA.getBar().setItemLabelStyle(XEnum.ItemLabelStyle.INNER);
            chartA.getBar().getItemLabelPaint().setColor(Color.rgb(162, 53, 46));
            chartA.getBar().getItemLabelPaint().setTextSize(20);

            chartA.getBar().setBarRoundRadius(15);
            chartA.getBar().setBarStyle(XEnum.BarStyle.ROUNDBAR);

//			chartA.getCategoryAxis().hideAxisLine();
//			chartA.getCategoryAxis().hideTickMarks();
//			chartA.getCategoryAxis().hideAxisLabels();
//			chartA.getBar().setBarTickSpacePercent(0.9f);
//			chartA.getDataAxis().hide();

            chartA.disablePanMode();
            chartA.disableHighPrecision();

            //设定格式
            chartA.setItemLabelFormatter(new IFormatterDoubleCallBack() {
                @Override
                public String doubleFormatter(Double value) {
                    // TODO Auto-generated method stub
                    DecimalFormat df = new DecimalFormat("#0");
                    String label = df.format(value).toString();
                    return label;
                }
            });

            //隐藏Key
            chartA.getPlotLegend().hide();

            chartA.getClipExt().setExtTop(150.f);

            //柱形和标签居中方式
            chartA.setBarCenterStyle(XEnum.BarCenterStyle.TICKMARKS);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    private void chartRenderB() {
        try {

            //设置绘图区默认缩进px值,留置空间显示Axis,Axistitle....
            int[] ltrb = getBarLnDefaultSpadding();
            chart2.setPadding(ltrb[0], ltrb[1],
                    ltrb[2], ltrb[3] + DensityUtil.dip2px(this.getContext(), 30));


            //标题
            //chart2.setTitle("小小熊 - 期末考试成绩");
            //chart2.addSubtitle("(XCL-Charts Demo)");
            //数据源
            chart2.setCategories(chartLabels);
            chart2.setDataSource(chartDataB);

            //数据轴
            chart2.getDataAxis().setAxisMax(10);
            chart2.getDataAxis().setAxisMin(0);
            chart2.getDataAxis().setAxisSteps(1);


            //指隔多少个轴刻度(即细刻度)后为主刻度
            chart2.getDataAxis().setDetailModeSteps(5);

            //背景网格
            chart2.getPlotGrid().hideHorizontalLines();
            chart2.getPlotGrid().hideVerticalLines();
            chart2.getPlotGrid().hideEvenRowBgColor();
            chart2.getPlotGrid().hideOddRowBgColor();

            //定义数据轴标签显示格式
            chart2.getDataAxis().setLabelFormatter(new IFormatterTextCallBack() {

                @Override
                public String textFormatter(String value) {
                    // TODO Auto-generated method stub
                    Double tmp = Double.parseDouble(value);
                    DecimalFormat df = new DecimalFormat("#0");
                    String label = df.format(tmp).toString();
                    return (label);
                }
            });

            //在柱形顶部显示值
            chart2.getBar().setItemLabelVisible(true);
            chart2.getBar().setItemLabelStyle(XEnum.ItemLabelStyle.INNER);
            chart2.getBar().getItemLabelPaint().setColor(Color.WHITE);
            chart2.getBar().getItemLabelPaint().setTextSize(20);

            chart2.getBar().setBarRoundRadius(15);
            chart2.getBar().setBarStyle(XEnum.BarStyle.ROUNDBAR);

            chart2.getCategoryAxis().hideAxisLine();
            chart2.getCategoryAxis().hideTickMarks();

            chart2.getDataAxis().hide();
            //柱形比设大点
//            chart2.getBar().setBarTickSpacePercent(0.9f);
            chart2.getCategoryAxis().getTickLabelPaint().setColor(Color.TRANSPARENT);


            chart2.disablePanMode();
            chart2.disableHighPrecision();

            //设定格式
            chart2.setItemLabelFormatter(new IFormatterDoubleCallBack() {
                @Override
                public String doubleFormatter(Double value) {
                    // TODO Auto-generated method stub
                    DecimalFormat df = new DecimalFormat("#0");
                    String label = df.format(value).toString();
                    return label;
                }
            });

            //隐藏Key
            chart2.getPlotLegend().hide();

            //	chart2.getClipExt().setExtTop(150.f);

            //柱形和标签居中方式
            chart2.setBarCenterStyle(XEnum.BarCenterStyle.TICKMARKS);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void render(Canvas canvas) {
        try {
            chartA.render(canvas);
            chart2.render(canvas);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }


}
