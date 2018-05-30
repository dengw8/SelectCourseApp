package com.example.duang1996.selectcourseapp.fragement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.duang1996.selectcourseapp.global.Global;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Select_ResultFragment extends Fragment implements  View.OnClickListener {
    private TextView title;
    private ImageView person;
    private View mView;

    private RecyclerView recyclerView;
    private CourseAdapter adapter;

    private final List<Map<String, Object>> itemList = new ArrayList<>();


    public static Select_ResultFragment newInstance(String param1) {
        Select_ResultFragment fragment = new Select_ResultFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_select_result, container, false);

        // 根据Global中的已选课程列表和待筛选课程列表更新itemList
        resetCourseList();
        /*
         *初始化控件
         */
        initViews();

        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {

    }

    private void initViews() {
        title = mView.findViewById(R.id.title);
        title.setText("已选课程");

        person = mView.findViewById(R.id.person);


        recyclerView = mView.findViewById(R.id.selected_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        adapter = new CourseAdapter(itemList);
        recyclerView.setAdapter(adapter);

        /*
         * 设置控件的点击事件
         */
        setOnClickListenner();
    }

    private void setOnClickListenner() {
        // 点击查看课程详情
        adapter.setOnItemClickListener(new CourseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int i) {
                /*
                 * 考虑自定义一个弹出控件用来显示课程详情
                 */
            }
        });


        // 长按退掉这门课
        adapter.setOnItemLongClickListener(new CourseAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int pos) {
                boolean isFind = false;
                Map<String, Object> tem = itemList.get(pos);

                for(int i = 0; i < Global.getSelectingCourseList().size(); i++) {
                    Course item = Global.getSelectingCourse(i);
                    if(item.getName().equals(tem.get("name")) && item.getTeacherName().equals(tem.get("teacher"))) {
                        int cover = item.getCover();
                        BmobUtil.getInstance().removeCourseFromSelectingCourseList(item.getObjectId(), BmobUserUtil.getInstance().getCurrentUser(), cover);
                        isFind = true;
                        item.setCover(--cover);
                        Global.addSelectableCourseList(item);
                        Global.removeSelectingCourseList(i);
                    }
                }

                if(!isFind) {
                    for(int i = 0; i < Global.getSelectedCourseList().size(); i++) {
                        Course item = Global.getSelectedCourse(i);
                        if(item.getName().equals(tem.get("name")) && item.getTeacherName().equals(tem.get("teacher"))) {
                            int cover = item.getCover();
                            BmobUtil.getInstance().removeCourseFromSelectedCourseList(item.getObjectId(), BmobUserUtil.getInstance().getCurrentUser(), cover);
                            item.setCover(--cover);
                            Global.addSelectableCourseList(item);
                            Global.removeSelectedCourseList(i);
                        }
                    }
                }
                itemList.remove(pos);
                adapter.notifyItemRemoved(pos);
            }
        });
    }

    /*
     * 加载完数据后，使用获得的新数据更新 CourseList
     * 因为需要在MyHandler这个内部类中使用，所以需要设为static类型的
     */
    private void resetCourseList() {
        itemList.clear();
        StringBuilder time = new StringBuilder();
        for(Course course : Global.getSelectedCourseList()) {
            Map<String, Object> temp = new LinkedHashMap<>();
            temp.put("name", course.getName());
            temp.put("teacher", course.getTeacherName() + "");
            temp.put("point", "" + course.getCredit());
            temp.put("type", "选课成功");
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
            itemList.add(temp);
        }
        for(Course course : Global.getSelectingCourseList()) {
                Map<String, Object> temp = new LinkedHashMap<>();
                temp.put("name", course.getName());
                temp.put("teacher", course.getTeacherName() + "");
                temp.put("point", "" + course.getCredit());
                temp.put("type", "待筛选");
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
                itemList.add(temp);
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
}