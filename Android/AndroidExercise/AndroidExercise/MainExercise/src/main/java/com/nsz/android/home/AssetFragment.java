package com.nsz.android.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.nsz.android.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;

public class AssetFragment extends BaseFragment {

    private LineChartView mLineChartView;

    // X轴的标注
    private String[] date = {
            "10-22", "11-22", "12-22",
            "1-22", "6-22", "5-23",
            "5-22", "6-22", "5-23",
            "5-22"
    };
    // 图表的数据点
    private int[] amount = {
            5, 42, 60,
            33, 10, 36,
            22, 18, 47,
            20};

    public static AssetFragment newInstance() {
        AssetFragment fragment = new AssetFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.main_fragment_asset;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        View view = getView();
        if (view == null) return;
        mLineChartView = view.findViewById(R.id.LineChartView);

        generateData();
    }

    private void generateData() {
        List<Line> lines = new ArrayList<>();
        List<AxisValue> axisValues = new ArrayList<>();
        for (int i = 0; i < date.length; i++) {
            String label = date[i];
            AxisValue axisValue = new AxisValue(i).setLabel(label);
            axisValues.add(axisValue);
        }

        for (int i = 0; i < 3; ++i) {
            List<PointValue> values = new ArrayList<>();
            for (int j = 0; j < amount.length; ++j) {
                PointValue pointValue = new PointValue(j, amount[j] + i * 5);
                values.add(pointValue);
            }

            Line line = new Line(values);
            if (i == 0) {
                line.setColor(ChartUtils.COLOR_RED);
            } else if (i == 1) {
                line.setColor(ChartUtils.COLOR_VIOLET);
            } else {
                line.setColor(ChartUtils.COLOR_BLUE);
            }
            line.setShape(ValueShape.CIRCLE);   // 折线图上每个数据点的形状  这里是圆形 （有三种 ：ValueShape.SQUARE  ValueShape.CIRCLE  ValueShape.DIAMOND）
            line.setCubic(false);               // 曲线是否平滑，即是曲线还是折线
            line.setFilled(false);              // 是否填充曲线的面积
            line.setHasLabels(true);            // 曲线的数据坐标是否加上备注
            line.setHasLabelsOnlyForSelected(true); // 点击数据坐标提示数据（设置了这个line.setHasLabels(true) 就无效）
            line.setHasLines(true);             // 是否用线显示。如果为false 则没有曲线只有点显示
            line.setHasPoints(true);            // 是否显示圆点 如果为false 则没有原点只有点显示（每个数据点都是个大的圆点）
            int color = ChartUtils.COLORS[(i + 1) % ChartUtils.COLORS.length];
            line.setPointColor(color);
            lines.add(line);
        }

        LineChartData data = new LineChartData(lines);

        if (true) {
            // X轴
            Axis axisX = new Axis();
            axisX.setHasTiltedLabels(false);    // X坐标轴字体是斜的显示还是直的，true是斜的显示
            // axisX.setTextColor(Color.BLACK);    // 设置字体颜色
            // axisX.setName("Date");              // 表格名称
            axisX.setTextSize(10);              // 设置字体大小
            // axisX.setMaxLabelChars(8);          // 最多几个X轴坐标，意思就是你的缩放让X轴上数据的个数7<=x<=mAxisXValues.length
            axisX.setValues(axisValues);        // 填充X轴的坐标名称
            data.setAxisXBottom(axisX);         // x 轴在底部
            // data.setAxisXTop(axisX);         // x 轴在顶部
            axisX.setHasLines(true);           // x 轴分割线

            // Y轴是根据数据的大小自动设置Y轴上限(在下面我会给出固定Y轴数据个数的解决方案)
            Axis axisY = new Axis();            // Y轴
            axisY.setHasLines(true);
            // axisY.setName("Amount");            // y轴标注
            axisY.setTextSize(10);              // 设置字体大小
            data.setAxisYLeft(axisY);           // Y轴设置在左边
            // data.setAxisYRight(axisY);       // y轴设置在右边
        } else {
            data.setAxisXBottom(null);
            data.setAxisYLeft(null);
        }

        data.setBaseValue(Float.NEGATIVE_INFINITY);

        // 设置行为属性，支持缩放、滑动以及平移
        mLineChartView.setInteractive(true);
        mLineChartView.setZoomType(ZoomType.HORIZONTAL);
        mLineChartView.setMaxZoom(2); // 最大方法比例
        mLineChartView.setContainerScrollEnabled(true, ContainerScrollType.HORIZONTAL);
        mLineChartView.setLineChartData(data);

        // 注：下面的7，10只是代表一个数字去类比而已 当时是为了解决X轴固定数据个数。
        Viewport maximumViewport = mLineChartView.getMaximumViewport();
        Viewport v = new Viewport(maximumViewport);
        v.bottom = 0;
        v.top = 100;
        v.left = 0;
        v.right = 9;
        mLineChartView.setCurrentViewport(v);
    }


}
