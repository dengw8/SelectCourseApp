package com.example.duang1996.selectcourseapp.fragement;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duang1996.selectcourseapp.BmobUtil;
import com.example.duang1996.selectcourseapp.R;
import com.example.duang1996.selectcourseapp.bean.Course;
import com.example.duang1996.selectcourseapp.bean.Lesson;
import com.example.duang1996.selectcourseapp.bean.TimeTableModel;
import com.example.duang1996.selectcourseapp.customView.TimeTableView;
import com.example.duang1996.selectcourseapp.global.Global;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

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

        // 初始化mList，添加数据
        mList = new ArrayList<TimeTableModel>();
        initList();

        mView = inflater.inflate(R.layout.fragment_timetable, container, false);
        mTimaTableView = mView.findViewById(R.id.main_timetable_ly);
        mTimaTableView.setTimeTable(mList);

        adapter= new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, weeks);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner = mView.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                Toast.makeText(getContext(), "你点击的是:"+ weeks[pos], Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
        spinner.setDropDownWidth(300);
        spinner.setAdapter(adapter);

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

    private void initList() {
        for(Course course : Global.getSelectedCourseList()) {
            String str1 = course.getLesson1().getObjectId();
            String str2 = course.getLesson2().getObjectId();
            if(!str1.equals("")) {
                for(Lesson item : Global.getLessonList()) {
                    if(str1.equals(item.getObjectId())) {
                        mList.add(new TimeTableModel(course.getName(), item.getStartWeek(), item.getEndWeek(), item.getStart(), item.getEnd(), item.getWeek(), item.getPlace()));
                        break;
                    }
                }
            }
            if(!str2.equals("")) {
                for(Lesson item : Global.getLessonList()) {
                    if(str2.equals(item.getObjectId())) {
                        mList.add(new TimeTableModel(course.getName(), item.getStartWeek(), item.getEndWeek(), item.getStart(), item.getEnd(), item.getWeek(), item.getPlace()));
                        break;
                    }
                }
            }
        }
        for(Course course : Global.getSelectingCourseList()) {
            String str1 = course.getLesson1().getObjectId();
            String str2 = course.getLesson2().getObjectId();
            Log.d("mydebug" ,"size : " + Global.getLessonListSize());
            Log.d("mydebug", str1);
            Log.d("mydebug", str2);
            Log.d("mydebug", Global.getLessonList(0).getObjectId());
            Log.d("mydebug", Global.getLessonList(1).getObjectId());
            if(!str1.equals("")) {
                for(Lesson item : Global.getLessonList()) {
                    if(str1.equals(item.getObjectId())) {
                        Log.d("mydebug", str1);
                        mList.add(new TimeTableModel(course.getName(), item.getStartWeek(), item.getEndWeek(), item.getStart(), item.getEnd(), item.getWeek(), item.getPlace()));
                        break;
                    }
                }
            }
            if(!str2.equals("")) {
                for(Lesson item : Global.getLessonList()) {
                    if(str2.equals(item.getObjectId())) {
                        Log.d("mydebug", str2);
                        mList.add(new TimeTableModel(course.getName(), item.getStartWeek(), item.getEndWeek(), item.getStart(), item.getEnd(), item.getWeek(), item.getPlace()));
                        break;
                    }
                }
            }
        }
    }
}
