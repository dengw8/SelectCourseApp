package com.example.duang1996.selectcourseapp.fragement;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duang1996.selectcourseapp.R;
import com.example.duang1996.selectcourseapp.customView.TimeTableModel;
import com.example.duang1996.selectcourseapp.customView.TimeTableView;

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
        mList = new ArrayList<TimeTableModel>();
        mTimaTableView = mView.findViewById(R.id.main_timetable_ly);
        addList();
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

    private void addList() {
        mList.add(new TimeTableModel(0, 1, 2, 1, "8:20", "10:10", "财务报表分析",
                "王老师", "1", "2-13"));
        mList.add(new TimeTableModel(0, 3, 4, 1, "8:20", "10:10", "审计实务",
                "李老师", "2", "2-13"));
        mList.add(new TimeTableModel(0, 6, 7, 1, "8:20", "10:10", "市场营销实务",
                "王", "3", "2-13"));


        mList.add(new TimeTableModel(0, 6, 7, 2, "8:20", "10:10", "财务管理实务",
                "老师1", "4", "2-13"));
        mList.add(new TimeTableModel(0, 8, 9, 2, "8:20", "10:10", "财务报表分析",
                "老师2", "5", "2-13"));

        mList.add(new TimeTableModel(0, 1, 2, 3, "8:20", "10:10", "审计实务",
                "老师3", "6", "2-13"));

        mList.add(new TimeTableModel(0, 6, 7, 3, "8:20", "10:10", "管理会计实务",
                "老师4", "7", "2-13"));
        mList.add(new TimeTableModel(0, 8, 9, 4, "8:20", "10:10", "管理会计实务",
                "老师5", "9", "2-13"));
        mList.add(new TimeTableModel(0, 3, 5, 4, "8:20", "10:10", "财务管理实务",
                "老师4", "8", "2-13"));
        mList.add(new TimeTableModel(0, 6, 8, 5, "8:20", "10:10", "证券投资分析",
                "老师7", "11", "2-13"));
        mList.add(new TimeTableModel(0, 3, 5, 5, "8:20", "10:10", "税务筹划",
                "老师6", "10", "2-13"));
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
