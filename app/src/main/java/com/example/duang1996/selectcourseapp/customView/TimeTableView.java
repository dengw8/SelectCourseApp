package com.example.duang1996.selectcourseapp.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.duang1996.selectcourseapp.R;
import com.example.duang1996.selectcourseapp.bean.TimeTableModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by duang1996 on 2018/4/24.
 */

public class TimeTableView extends LinearLayout {

    private Context mContext;
    private FragmentManager mFragmentManager;

    /**
     * 配色数组
     */
    public static int colors[] = {R.drawable.select_label_san,
            R.drawable.select_label_er, R.drawable.select_label_si,
            R.drawable.select_label_wu, R.drawable.select_label_liu,
            R.drawable.select_label_qi, R.drawable.select_label_ba,
            R.drawable.select_label_jiu, R.drawable.select_label_sss,
            R.drawable.select_label_se, R.drawable.select_label_yiw,
            R.drawable.select_label_sy, R.drawable.select_label_yiwu,
            R.drawable.select_label_yi, R.drawable.select_label_wuw };

    private final static int START = 0;
    //最大节数
    public final static int MAXNUM = 11;
    //显示到星期几
    public final static int WEEKNUM = 7;
    /**
     * 单个View高度
     */
    private final static int TIME_TABLE_HEIGHT = 50;
    /**
     * 线的高度
     */
    private final static int TIME_TABLE_LINE_HEIGHT = 2;
    private final static int LEFT_TITLE_WIDTH = 20;
    private final static int WEEK_TITLE_HEIGHT = 30;
    /**
     * 第一行的星期显示
     */
    private LinearLayout mHorizontalWeekLayout;
    /*
     * 第一列的课程节数显示
     */
    private LinearLayout mVerticalWeekLaout;
    private String[] mWeekTitle = {"一", "二", "三", "四", "五", "六", "日"};
    public static String[] colorStr = new String[20];
    int colorNum = 0;

    private List<TimeTableModel> mListTimeTable = new ArrayList<TimeTableModel>();

    public TimeTableView(Context context) {
        super(context);
        this.mContext = context;
        this.mFragmentManager = mFragmentManager;
    }

    public TimeTableView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /**
     * 横的分界线
     *
     * @return
     */
    private View getWeekHorizontalLine() {
        View line = new View(getContext());
        line.setBackgroundColor(getResources().getColor(R.color.view_line));
        line.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, TIME_TABLE_LINE_HEIGHT));
        return line;
    }

    /**
     * 竖向分界线
     *
     * @return
     */
    private View getWeekVerticalLine() {
        View line = new View(mContext);
        line.setBackgroundColor(getResources().getColor(R.color.view_line));
        line.setLayoutParams(new ViewGroup.LayoutParams((TIME_TABLE_LINE_HEIGHT), dip2px(WEEK_TITLE_HEIGHT)));
        return line;
    }


    private void initView() {
        removeAllViews();
        mHorizontalWeekLayout = new LinearLayout(getContext());
        mHorizontalWeekLayout.setOrientation(HORIZONTAL);
        mVerticalWeekLaout = new LinearLayout(getContext());
        mVerticalWeekLaout.setOrientation(HORIZONTAL);

        //表格
        for (int i = 0; i <= WEEKNUM; i++) {
            if (i == 0) {
                layoutLeftNumber();
            } else {
                layoutWeekTitleView(i);
                layoutContentView(i);
            }

            mVerticalWeekLaout.addView(createTableVerticalLine());
            mHorizontalWeekLayout.addView(getWeekVerticalLine());
        }
        addView(mHorizontalWeekLayout);
        addView(getWeekHorizontalLine());
        addView(mVerticalWeekLaout);
    }

    @NonNull
    private View createTableVerticalLine() {
        View l = new View(getContext());
        l.setLayoutParams(new ViewGroup.LayoutParams(TIME_TABLE_LINE_HEIGHT, dip2px(TIME_TABLE_HEIGHT * MAXNUM) + (MAXNUM - 2) * TIME_TABLE_LINE_HEIGHT));
        l.setBackgroundColor(getResources().getColor(R.color.view_line));
        return l;
    }

    private void layoutContentView(int week) {
        List<TimeTableModel> weekClassList = findWeekClassList(week);
        //添加
        LinearLayout mLayout = createWeekTimeTableView(weekClassList, week);
        mLayout.setOrientation(VERTICAL);
        mLayout.setLayoutParams(new ViewGroup.LayoutParams((getViewWidth() - dip2px(20)) / WEEKNUM, LayoutParams.MATCH_PARENT));
        mLayout.setWeightSum(1);
        mVerticalWeekLaout.addView(mLayout);
    }

    /*
     * 遍历出星期1~7的课表
     * 再进行排序
     *
     * @param week 星期
     */
    @NonNull
    private List<TimeTableModel> findWeekClassList(int week) {
        List<TimeTableModel> list = new ArrayList<>();
        for (TimeTableModel timeTableModel : mListTimeTable) {
            if (timeTableModel.getWeek() == week) {
                list.add(timeTableModel);
            }
        }
        Collections.sort(list, new Comparator<TimeTableModel>() {
            @Override
            public int compare(TimeTableModel o1, TimeTableModel o2) {
                return o1.getStartNum() - o2.getStartNum();
            }
        });
        return list;
    }

    private void layoutWeekTitleView(int weekNumber) {
        TextView weekText = new TextView(getContext());
        weekText.setTextColor(getResources().getColor(R.color.text_color));
        weekText.setWidth(((getViewWidth() - dip2px(LEFT_TITLE_WIDTH))) / WEEKNUM);
        weekText.setHeight(dip2px(WEEK_TITLE_HEIGHT));
        weekText.setGravity(Gravity.CENTER);
        weekText.setTextSize(16);
        weekText.setText(mWeekTitle[weekNumber - 1]);
        mHorizontalWeekLayout.addView(weekText);
    }


    private void layoutLeftNumber() {
        //课表出的0,0格子 空白的
        TextView mTime = new TextView(mContext);
        mTime.setLayoutParams(new ViewGroup.LayoutParams(dip2px(LEFT_TITLE_WIDTH), dip2px(WEEK_TITLE_HEIGHT)));
        mHorizontalWeekLayout.addView(mTime);

        //绘制1~MAXNUM
        LinearLayout numberView = new LinearLayout(mContext);
        numberView.setLayoutParams(new ViewGroup.LayoutParams(dip2px(LEFT_TITLE_WIDTH), dip2px(MAXNUM * TIME_TABLE_HEIGHT) + MAXNUM * 2));
        numberView.setOrientation(VERTICAL);
        for (int j = 1; j <= MAXNUM; j++) {
            TextView number = createNumberView(j);
            numberView.addView(number);
            numberView.addView(getWeekHorizontalLine());
        }
        mVerticalWeekLaout.addView(numberView);
    }

    @NonNull
    private TextView createNumberView(int j) {
        TextView number = new TextView(getContext());
        number.setLayoutParams(new ViewGroup.LayoutParams(dip2px(LEFT_TITLE_WIDTH), dip2px(TIME_TABLE_HEIGHT)));
        number.setGravity(Gravity.CENTER);
        number.setTextColor(getResources().getColor(R.color.text_color));
        number.setTextSize(14);
        number.setText(String.valueOf(j));
        return number;
    }

    private int getViewWidth() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }

    /**
     * 绘制空白
     *
     * @param count 数量
     * @param week  星期
     * @param start 用着计算下标
     */
    private View addBlankView(int count, final int week, final int start) {
        LinearLayout blank = new LinearLayout(getContext());
        blank.setOrientation(VERTICAL);
        for (int i = 1; i < count; i++) {
            View classView = new View(getContext());
            classView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dip2px(TIME_TABLE_HEIGHT)));
            blank.addView(classView);
            blank.addView(getWeekHorizontalLine());
        }
        return blank;
    }

    /**
     * 星期一到星期天的课表
     *
     * @param weekList 每天的课程列表
     * @param week     周
     */
    private LinearLayout createWeekTimeTableView(List<TimeTableModel> weekList, int week) {
        LinearLayout weekTableView = new LinearLayout(getContext());
        weekTableView.setOrientation(VERTICAL);
        int size = weekList.size();
        if (weekList.isEmpty()) {
            weekTableView.addView(addBlankView(MAXNUM + 1, week, 0));
        } else {
            for (int i = 0; i < size; i++) {
                TimeTableModel tableModel = weekList.get(i);
                if (i == 0) {
                    //添加的0到开始节数的空格
                    weekTableView.addView(addBlankView(tableModel.getStartNum(), week, 0));
                    weekTableView.addView(createClassView(tableModel));
                } else if (weekList.get(i).getStartNum() - weekList.get(i - 1).getEndNum() > 0) {
                    //填充
                    weekTableView.addView(addBlankView(weekList.get(i).getStartNum() - weekList.get(i - 1).getEndNum(), week, weekList.get(i - 1).getEndNum()));
                    weekTableView.addView(createClassView(weekList.get(i)));
                }
                //绘制剩下的空白
                if (i + 1 == size) {
                    weekTableView.addView(addBlankView(MAXNUM - weekList.get(i).getEndNum() + 1, week, weekList.get(i).getEndNum()));
                }
            }
        }
        return weekTableView;
    }

    /**
     * 获取单个课表View 也可以自定义我这个
     *
     * @param model 数据类型
     * @return
     */
    @SuppressWarnings("deprecation")
    private View createClassView(final TimeTableModel model) {
        LinearLayout mTimeTableView = new LinearLayout(getContext());
        mTimeTableView.setOrientation(VERTICAL);
        int num = (model.getEndNum() - model.getStartNum());
        mTimeTableView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dip2px((num + 1) * TIME_TABLE_HEIGHT) + (num + 1) * TIME_TABLE_LINE_HEIGHT));

        TextView mTimeTableNameView = new TextView(getContext());
        mTimeTableNameView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dip2px((num + 1) * TIME_TABLE_HEIGHT) + (num) * TIME_TABLE_LINE_HEIGHT));

        mTimeTableNameView.setTextColor(getContext().getResources().getColor(
                android.R.color.white));
        mTimeTableNameView.setTextSize(16);
        mTimeTableNameView.setGravity(Gravity.CENTER);
        mTimeTableNameView.setText(model.getName() + "@" + model.getPlace());

        mTimeTableView.addView(mTimeTableNameView);
        mTimeTableView.addView(getWeekHorizontalLine());

        mTimeTableView.setBackgroundDrawable(getContext().getResources()
                .getDrawable(colors[getColorNum(model.getName())]));
        mTimeTableView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String alert= "";
                alert += "课程：" + model.getName() + "\n";
                alert += "课室：" + model.getPlace() + "\n";
                alert += "时间：" + getLessonTime(model) + "\n";
                PromptDialogFragment.newInstance(alert).show(mFragmentManager, "dialog");
            }
        });
        return mTimeTableView;
    }
    /**
     * 转换dp
     *
     * @param dpValue
     * @return
     */
    public int dip2px(float dpValue) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale);
    }

    public void setTimeTable(List<TimeTableModel> mlist, FragmentManager mFragmentManager) {
        this.mListTimeTable = mlist;
        this.mFragmentManager = mFragmentManager;
        for (TimeTableModel timeTableModel : mlist) {
            addTimeName(timeTableModel.getName());
        }
        initView();
        invalidate();
    }

    /**
     * 输入课表名循环判断是否数组存在该课表 如果存在输出true并退出循环 如果不存在则存入colorSt[20]数组
     *
     * @param name
     */
    private void addTimeName(String name) {
        boolean isRepeat = true;
        for (int i = 0; i < 20; i++) {
            if (name.equals(colorStr[i])) {
                isRepeat = true;
                break;
            } else {
                isRepeat = false;
            }
        }
        if (!isRepeat) {
            colorStr[colorNum] = name;
            colorNum++;
        }
    }

    /**
     * 获取数组中的课程名
     *
     * @param name
     * @return
     */
    public static int getColorNum(String name) {
        int num = 0;
        for (int i = 0; i < 20; i++) {
            if (name.equals(colorStr[i])) {
                num = i;
            }
        }
        return num;
    }

    private String getLessonTime(TimeTableModel model) {
        StringBuilder start = new StringBuilder();
        StringBuilder end = new StringBuilder();
        switch (model.getStartNum()) {
            case 1: {
                start.append("8:00-");
                break;
            }
            case 2: {
                start.append("8:55-");
                break;
            }
            case 3: {
                start.append("10:00-");
                break;
            }
            case 4: {
                start.append("10:55-");
                break;
            }
            case 5: {
                start.append("14:20-");
                break;
            }
            case 6: {
                start.append("15:15-");
                break;
            }
            case 7: {
                start.append("16:20-");
                break;
            }
            case 8: {
                start.append("17:15-");
                break;
            }
            case 9: {
                start.append("19:00-");
                break;
            }
            case 10: {
                start.append("19:55-");
                break;
            }
        }
        switch (model.getEndNum()) {
            case 1: {
                end.append("8:45");
                break;
            }
            case 2: {
                end.append("9:40");
                break;
            }
            case 3: {
                end.append("10:45");
                break;
            }
            case 4: {
                end.append("11:40");
                break;
            }
            case 5: {
                end.append("15:05");
                break;
            }
            case 6: {
                end.append("16:00");
                break;
            }
            case 7: {
                end.append("17:05");
                break;
            }
            case 8: {
                end.append("18:00");
                break;
            }
            case 9: {
                end.append("19:45");
                break;
            }
            case 10: {
                end.append("20:40");
                break;
            }
            case 11: {
                end.append("21:35");
                break;
            }
        }
        return start.toString() + end.toString();
    }
}


