package com.example.duang1996.selectcourseapp.fragement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.duang1996.selectcourseapp.util.BmobUserUtil;
import com.example.duang1996.selectcourseapp.util.BmobUtil;
import com.example.duang1996.selectcourseapp.CourseDetailActivity;
import com.example.duang1996.selectcourseapp.R;
import com.example.duang1996.selectcourseapp.adapter.CourseAdapter;
import com.example.duang1996.selectcourseapp.bean.Course;
import com.example.duang1996.selectcourseapp.bean.Lesson;
import com.example.duang1996.selectcourseapp.customview.PromptDialogFragment;
import com.example.duang1996.selectcourseapp.global.Global;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Select_CourseFragment extends Fragment implements  View.OnClickListener {
    private View mView;
    private TextView general_com;
    private TextView general_elec;
    private TextView major_com;
    private TextView major_elec;

    private RecyclerView recyclerView;
    private CourseAdapter adapter;

    private final List<Map<String, Object>> itemList = new ArrayList<>();

    public static Select_CourseFragment newInstance(String param1) {
        Select_CourseFragment fragment = new Select_CourseFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_select_course, container, false);

        // 根据Global中的可选课程列表更新itemList
        resetCourseList();

        // 初始化控件
        initViews();

        onClick(general_com);

        return mView;
    }

    @Override
    public void onActivityCreated(Bundle instance) {
        super.onActivityCreated(instance);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onDestroy () {
        super.onDestroy ();
    }

    @Override
    public void onClick(View v) {
        resetTextViewState();
        switch (v.getId()) {
            case R.id.general_compulsory:
                setTextViewState(general_com, getLocalColor(R.color.tab_active), getLocalColor(R.color.white));
                selectByType(1);
                break;
            case R.id.general_elective:
                setTextViewState(general_elec, getLocalColor(R.color.tab_active), getLocalColor(R.color.white));
                selectByType(2);
                break;
            case R.id.major_compulsory:
                setTextViewState(major_com, getLocalColor(R.color.tab_active), getLocalColor(R.color.white));
                selectByType(3);
                break;
            case R.id.major_elective:
                setTextViewState(major_elec, getLocalColor(R.color.tab_active), getLocalColor(R.color.white));
                selectByType(4);
                break;
            default:
                break;
        }
    }

    private void initViews() {
        TextView title;
        title = mView.findViewById(R.id.title);
        title.setText("待选课程");

        general_com = mView.findViewById(R.id.general_compulsory);
        general_elec = mView.findViewById(R.id.general_elective);
        major_com = mView.findViewById(R.id.major_compulsory);
        major_elec = mView.findViewById(R.id.major_elective);

        recyclerView = mView.findViewById(R.id.selectable_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new CourseAdapter(itemList);
        recyclerView.setAdapter(adapter);

        /*
         * 设置控件的点击事件
         */
        setOnClickListenner();
    }

    private void setOnClickListenner() {
        general_com.setOnClickListener(this);
        general_elec.setOnClickListener(this);
        major_com.setOnClickListener(this);
        major_elec.setOnClickListener(this);

        // 点击查看课程详情
        adapter.setOnItemClickListener(new CourseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int i) {
                Map<String, Object> tem = itemList.get(i);
                Course course = new Course();
                for(Course item : Global.getSelectableCourseList()) {
                    if(item.getTeacherName().equals(tem.get("teacher").toString()) && item.getName().equals(tem.get("name").toString())) {
                        course = item;
                        break;
                    }
                }
                Intent intent = new Intent(getActivity(), CourseDetailActivity.class);
                intent.putExtra("course", course);
                startActivity(intent);
            }
        });

        // 长按可以进行选课
        adapter.setOnItemLongClickListener(new CourseAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int i) {
                Map<String, Object> tem = itemList.get(i);
                Course course = new Course();
                for(Course item : Global.getSelectableCourseList()) {
                    if(item.getTeacherName().equals(tem.get("teacher").toString()) && item.getName().equals(tem.get("name").toString())) {
                        course = item;
                        break;
                    }
                }
                Course res = isRushed(course);
                if( res == null) {                 // 时间不冲突
                    final int screen = course.getScreen();
                    final String objectId = course.getObjectId();
                    // 更新后台数据
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            BmobUtil.getInstance().selectOneCourse(objectId, BmobUserUtil.getInstance().getCurrentUser(), screen);
                        }
                    }).start();
                    int temp = screen;
                    course.setScreen(++temp);
                    Global.addSelectingCourseList(course);
                    Global.removeSelectableCourseList(i);
                    itemList.remove(i);
                    adapter.notifyItemRemoved(i);
                } else {
                    String alert = "选课冲突！\n\n" + "当前课程与 " + res.getName() + " 存在时间冲突";
                    PromptDialogFragment.newInstance(alert).show(getFragmentManager(), "dialog");
                }
            }
        });
    }

    private void resetTextViewState() {
        setTextViewState(general_com, getLocalColor(R.color.white),getLocalColor(R.color.tab_active));
        setTextViewState(general_elec, getLocalColor(R.color.white), getLocalColor(R.color.tab_active));
        setTextViewState(major_com, getLocalColor(R.color.white), getLocalColor(R.color.tab_active));
        setTextViewState(major_elec, getLocalColor(R.color.white), getLocalColor(R.color.tab_active));
    }

    private void setTextViewState(TextView textView, int backColor, int textColor) {
        textView.setBackgroundColor(backColor);
        textView.setTextColor(textColor);
    }

    private int getLocalColor(int id) {
        return ContextCompat.getColor(getActivity(), id);
    }
    /*
     * 加载完数据后，使用获得的新数据更新 CourseList
     * 因为需要在MyHandler这个内部类中使用，所以需要设为static类型的
     */
    private void resetCourseList() {
        itemList.clear();
        StringBuilder time = new StringBuilder();

        for(Course course : Global.getSelectableCourseList()) {
            itemList.add(fromCourseToMap(course));
        }
    }

    private Map<String, Object> fromCourseToMap(Course course) {
        StringBuilder time = new StringBuilder();
        Map<String, Object> temp = new LinkedHashMap<>();
        temp.put("name", course.getName());
        temp.put("teacher", course.getTeacherName() + "");
        temp.put("point", "" + course.getCredit());
        if(course.getScreenWay() == 1) {
            temp.put("type", "随机筛选");
        } else {
            temp.put("type", "先到先得");
        }
        if(course.getLesson1() != null) {
            for(Lesson lesson : Global.getLessonList()) {
                if(course.getLesson1().getObjectId().equals(lesson.getObjectId())) {
                    time.append(mapLessonToString(lesson));
                }
            }
        }
        if(course.getLesson2() != null) {
            for(Lesson lesson : Global.getLessonList()) {
                if(course.getLesson2().getObjectId().equals(lesson.getObjectId())) {
                    time.append("\n");
                    time.append(mapLessonToString(lesson));
                }
            }
        }
        temp.put("time",time);
        return temp;
    }

    // 将Leeson对象使用字符串打印出来
    private StringBuilder mapLessonToString(Lesson lesson) {
        StringBuilder res = new StringBuilder();
        switch (lesson.getWeek()) {
            case 1:
                res.append("星期一 ");
                break;
            case 2:
                res.append("星期二 ");
                break;
            case 3:
                res.append("星期三 ");
                break;
            case 4:
                res.append("星期四 ");
                break;
            case 5:
                res.append("星期五 ");
                break;
            case 6:
                res.append("星期六 ");
                break;
            case 7:
                res.append("星期天 ");
            default:
                break;
        }
        res.append(lesson.getStart());
        res.append("-");
        res.append(lesson.getEnd());
        res.append("节");
        res.append("（");
        res.append(lesson.getStartWeek());
        res.append("-");
        res.append(lesson.getEndWeek());
        res.append(")");
        return res;
    }

    // 查询待选择的课程是否和已经选择课程存在时间冲突
    private Course isRushed(Course course) {
        Lesson lesson1 = getLesson1ForCourse(course);
        Lesson lesson2 = getLesson2ForCourse(course);
        for(Course item : Global.getSelectedCourseList()) {
            if(isTwoLessonsRushed(lesson1, getLesson1ForCourse(item)) || isTwoLessonsRushed(lesson1, getLesson2ForCourse(item))
                    || isTwoLessonsRushed(lesson2, getLesson1ForCourse(item)) || isTwoLessonsRushed(lesson2, getLesson2ForCourse(item))) {
                return item;
            }
        }
        for(Course item : Global.getSelectingCourseList()) {
            if(isTwoLessonsRushed(lesson1, getLesson1ForCourse(item)) || isTwoLessonsRushed(lesson1, getLesson2ForCourse(item))
                  || isTwoLessonsRushed(lesson2, getLesson1ForCourse(item)) || isTwoLessonsRushed(lesson2, getLesson2ForCourse(item)) ) {
                return item;
            }
        }
        return null;
    }

    // 作用如函数名
    private Lesson getLesson1ForCourse(Course course) {
        if(course.getLesson1() != null) {
            for(Lesson lesson : Global.getLessonList()) {
                if(course.getLesson1().getObjectId().equals(lesson.getObjectId())) {
                    return lesson;
                }
            }
        }
        /*
         * 当前Course 没有Lesson1对象时返回null
         */
        return null;
    }

    private Lesson getLesson2ForCourse(Course course) {
        if(course.getLesson2() != null) {
            for(Lesson lesson : Global.getLessonList()) {
                if(course.getLesson2().getObjectId().equals(lesson.getObjectId())) {
                    return lesson;
                }
            }
        }
        /*
         * 当前Course没有lesson2对象时返回null
         */
        return null;
    }

    // 判断两个lesson之间是否存在时间冲突
    private boolean isTwoLessonsRushed(Lesson lesson1, Lesson lesson2) {
        if(lesson1 == null || lesson2 == null) {
            return false;
        }
        if(!lesson1.getWeek().equals(lesson2.getWeek())) {
            return false;
        }
        if(lesson1.getStart() > lesson2.getEnd() || lesson2.getStart() > lesson1.getEnd()) {
            return false;
        }
        return true;
    }

    /*
     * 根据课程类型显示列表
     *  @param 课程类型代码
     */
    private void selectByType(int type) {
        itemList.clear();
        for(Course course : Global.getSelectableCourseList()) {
            if(course.getType() == type) {
                itemList.add(fromCourseToMap(course));
            }
        }
        adapter.setDataList(itemList);
        adapter.notifyDataSetChanged();
    }
}
