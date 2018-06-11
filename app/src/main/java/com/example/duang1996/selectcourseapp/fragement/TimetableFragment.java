package com.example.duang1996.selectcourseapp.fragement;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.duang1996.selectcourseapp.R;
import com.example.duang1996.selectcourseapp.bean.Course;
import com.example.duang1996.selectcourseapp.bean.Lesson;
import com.example.duang1996.selectcourseapp.bean.TimeTableModel;
import com.example.duang1996.selectcourseapp.customview.TimeTableView;
import com.example.duang1996.selectcourseapp.global.Global;

import java.util.ArrayList;
import java.util.List;

public class TimetableFragment extends Fragment {
    private TimeTableView mTimaTableView;
    private List<TimeTableModel> mList;
    private View mView;

    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private String[] weeks={"  第1周  ","  第2周  ","  第3周  ","  第4周  ","  第5周  ","  第6周  ","  第7周  ","  第8周  ","  第9周  ","  第10周  ",
            "  第11周  ","  第12周  ","  第13周  ","  第14周  ","  第15周  ","  第16周  ","  第17周  ","  第18周  ","  第19周  ","  第20周  "};

    public static TimetableFragment newInstance(String param1) {
        TimetableFragment fragment = new TimetableFragment();
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
        mView = inflater.inflate(R.layout.fragment_timetable, container, false);

        adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, weeks);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = mView.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Global.pos = pos;
                reInitList(pos + 1);
                mTimaTableView.setTimeTable(mList, getFragmentManager());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
        spinner.setDropDownWidth(300);
        spinner.setAdapter(adapter);

        // 初始化mList，添加数据
        mList = new ArrayList<>();
        if(Global.pos == -1) {
            reInitList(1);
        } else {
            reInitList(Global.pos + 1);
        }

        mTimaTableView = mView.findViewById(R.id.main_timetable_ly);
        mTimaTableView.setTimeTable(mList, getFragmentManager());

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

    /*
     * 添加当前周要上的课程到mList中
     * @param 要显示周数
     */
    private void reInitList(int week) {
        mList.clear();
        for(Course course : Global.getSelectedCourseList()) {
            String str1 = course.getLesson1().getObjectId();
            String str2 = course.getLesson2().getObjectId();
            if(!str1.equals("")) {
                for(Lesson item : Global.getLessonList()) {
                    if(str1.equals(item.getObjectId()) && item.getStartWeek() <= week && item.getEndWeek() >= week) {
                        mList.add(new TimeTableModel(course.getName(), item.getStartWeek(), item.getEndWeek(), item.getStart(), item.getEnd(), item.getWeek(), item.getPlace()));
                        break;
                    }
                }
            }
            if(!str2.equals("")) {
                for(Lesson item : Global.getLessonList()) {
                    if(str2.equals(item.getObjectId()) && item.getStartWeek() <= week && item.getEndWeek() >= week) {
                        mList.add(new TimeTableModel(course.getName(), item.getStartWeek(), item.getEndWeek(), item.getStart(), item.getEnd(), item.getWeek(), item.getPlace()));
                        break;
                    }
                }
            }
        }
        for(Course course : Global.getSelectingCourseList()) {
            String str1 = course.getLesson1().getObjectId();
            String str2 = course.getLesson2().getObjectId();
            if(!str1.equals("")) {
                for(Lesson item : Global.getLessonList()) {
                    if(str1.equals(item.getObjectId()) && item.getStartWeek() <= week && item.getEndWeek() >= week) {
                        mList.add(new TimeTableModel(course.getName(), item.getStartWeek(), item.getEndWeek(), item.getStart(), item.getEnd(), item.getWeek(), item.getPlace()));
                        break;
                    }
                }
            }
            if(!str2.equals("")) {
                for(Lesson item : Global.getLessonList()) {
                    if(str2.equals(item.getObjectId()) && item.getStartWeek() <= week && item.getEndWeek() >= week) {
                        mList.add(new TimeTableModel(course.getName(), item.getStartWeek(), item.getEndWeek(), item.getStart(), item.getEnd(), item.getWeek(), item.getPlace()));
                        break;
                    }
                }
            }
        }
    }
}
