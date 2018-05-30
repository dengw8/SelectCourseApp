package com.example.duang1996.selectcourseapp.fragement;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duang1996.selectcourseapp.BmobUserUtil;
import com.example.duang1996.selectcourseapp.BmobUtil;
import com.example.duang1996.selectcourseapp.PersonDetailActivity;
import com.example.duang1996.selectcourseapp.R;
import com.example.duang1996.selectcourseapp.adapter.CourseAdapter;
import com.example.duang1996.selectcourseapp.bean.Course;
import com.example.duang1996.selectcourseapp.bean.Lesson;
import com.example.duang1996.selectcourseapp.bean.Student;
import com.example.duang1996.selectcourseapp.global.Global;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.bmob.v3.Bmob;

public class Select_CourseFragment extends Fragment implements  View.OnClickListener {

    private View mView;
    private TextView title;
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
                setTextViewState(general_com, getLocalColor(R.color.course_type_non), getLocalColor(R.color.white));
                selectByType(1);
                break;
            case R.id.general_elective:
                setTextViewState(general_elec, getLocalColor(R.color.course_type_non), getLocalColor(R.color.white));
                selectByType(2);
                break;
            case R.id.major_compulsory:
                setTextViewState(major_com, getLocalColor(R.color.course_type_non), getLocalColor(R.color.white));
                selectByType(3);
                break;
            case R.id.major_elective:
                setTextViewState(major_elec, getLocalColor(R.color.course_type_non), getLocalColor(R.color.white));
                selectByType(4);
                break;
            default:
                break;
        }
    }

    private void initViews() {
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
                /*
                 * 考虑自定义一个弹出控件用来显示课程详情
                 */

                // do something
            }
        });

        // 长按可以进行选课
        adapter.setOnItemLongClickListener(new CourseAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int i) {
                Course course = Global.getSelectableCourse(i);
                final int cover = course.getCover();
                int cap = course.getCapacity();

                if(cover < cap) {
                    final String objectId = course.getObjectId();
                    // 更新后台数据
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            BmobUtil.getInstance().selectOneCourse(objectId, BmobUserUtil.getInstance().getCurrentUser(), cover);
                        }
                    }).start();
                    int tem = cover;
                    course.setCover(++tem);
                    Global.addSelectingCourseList(course);
                    Global.removeSelectableCourseList(i);
                    itemList.remove(i);
                    adapter.notifyItemRemoved(i);
                } else {
                    // 提示该们课程的人数已满

                }

            }
        });
    }

    private void resetTextViewState() {
        setTextViewState(general_com, getLocalColor(R.color.white),getLocalColor(R.color.course_type_non));
        setTextViewState(general_elec, getLocalColor(R.color.white), getLocalColor(R.color.course_type_non));
        setTextViewState(major_com, getLocalColor(R.color.white), getLocalColor(R.color.course_type_non));
        setTextViewState(major_elec, getLocalColor(R.color.white), getLocalColor(R.color.course_type_non));
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

    private Map<String, Object> fromCourseToMap(Course course) {
        Map<String, Object> temp = new LinkedHashMap<>();
        temp.put("name", course.getName());
        temp.put("teacher", course.getTeacherName() + "");
        temp.put("point", "" + course.getCredit());
        if(course.getType() == 1) {
            temp.put("type", "公必");
        } else if(course.getType() == 2) {
            temp.put("type", "专选");
        } else if(course.getType() == 3) {
            temp.put("type", "专必");
        } else if(course.getType() == 4) {
            temp.put("type", "专选");
        }
            /*
            if(course.getLesson1() != null) {
                time.append(mapLessonToString(course.getLesson1()));
            }
            if(course.getLesson2() != null) {
                time.append(",");
                time.append(mapLessonToString(course.getLesson2()));
            }
            */
        temp.put("time","星期一");
        return temp;
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
