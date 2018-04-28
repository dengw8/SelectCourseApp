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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duang1996.selectcourseapp.PersonDetailActivity;
import com.example.duang1996.selectcourseapp.R;
import com.example.duang1996.selectcourseapp.adapter.CourseAdapter;
import com.example.duang1996.selectcourseapp.bean.Course;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Select_CourseFragment extends Fragment implements  View.OnClickListener {
    private View mView;

    private TextView title;
    private ImageView person;
    private TextView general_com;
    private TextView general_elec;
    private TextView major_com;
    private TextView major_elec;

    private RecyclerView recyclerView;
    private CourseAdapter adapter;
    final List<Course> items = new ArrayList<Course>();
    final List<Map<String, Object>> itemList = new ArrayList<>();


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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        //本地测试数据
        initList();

        mapCourse(items);


        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_select_course, container, false);

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
        resetTextViewState();
        switch (v.getId()) {
            case R.id.general_compulsory:
                setTextViewState(general_com, getLocalColor(R.color.course_type_non), getLocalColor(R.color.white));
                Toast.makeText(getContext(), "你选择了公必", Toast.LENGTH_SHORT).show();
                break;
            case R.id.general_elective:
                setTextViewState(general_elec, getLocalColor(R.color.course_type_non), getLocalColor(R.color.white));
                Toast.makeText(getContext(), "你选择了公选", Toast.LENGTH_SHORT).show();
                break;
            case R.id.major_compulsory:
                setTextViewState(major_com, getLocalColor(R.color.course_type_non), getLocalColor(R.color.white));
                Toast.makeText(getContext(), "你选择了专必", Toast.LENGTH_SHORT).show();
                break;
            case R.id.major_elective:
                setTextViewState(major_elec, getLocalColor(R.color.course_type_non), getLocalColor(R.color.white));
                Toast.makeText(getContext(), "你选择了专选", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void initViews() {
        title = mView.findViewById(R.id.tab2).findViewById(R.id.title);
        title.setText("待选课程");

        person = mView.findViewById(R.id.tab2).findViewById(R.id.person);
        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PersonDetailActivity.class);
                startActivity(intent);
            }
        });

        general_com = mView.findViewById(R.id.general_compulsory);
        general_elec = mView.findViewById(R.id.general_elective);
        major_com = mView.findViewById(R.id.major_compulsory);
        major_elec = mView.findViewById(R.id.major_elective);

        general_com.setOnClickListener(this);
        general_elec.setOnClickListener(this);
        major_com.setOnClickListener(this);
        major_elec.setOnClickListener(this);

        setTextViewState(general_com, getLocalColor(R.color.course_type_non), getLocalColor(R.color.white));

        recyclerView = mView.findViewById(R.id.selectable_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new CourseAdapter(itemList);

        // 查看课程详情
        adapter.setOnItemClickListener(new CourseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int i) {

            }
        });
        // 长按进行选课
        adapter.setOnItemLongClickListener(new CourseAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int i) {

            }
        });
        recyclerView.setAdapter(adapter);
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

    private void initList() {
        items.clear();
        items.add(new Course("工作流技术",1234, 3, 4, 1, 3, 4, 5, 3, 4));
        items.add(new Course("软件测试",1235, 3, 3, 3, 3, 4, 0, 0, 0));
        items.add(new Course("系统分析与设计",1236, 3, 4, 1, 5, 6, 3, 5, 6));
        items.add(new Course("虚拟现实",1237, 2, 4, 3, 1, 2, 0, 3, 4));
        items.add(new Course("IT项目管理",1238, 2, 4, 2, 9, 10, 0, 3, 4));
        items.add(new Course("多核程序设计",1239, 2, 4, 3, 7, 8, 0, 3, 4));
    }

    private void mapCourse(List<Course> courses) {
        StringBuilder time;
        for(Course course : courses) {
            Map<String, Object> temp = new LinkedHashMap<>();
            temp.put("name", course.getName());
            temp.put("teacher", course.getTeacherId() + "");
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

            temp.put("time", "" + course.getDay1());

            if(course.getDay1() != 0) {

            } else if(course.getDay2() != 0) {

            }

            itemList.add(temp);
        }
    }
}
