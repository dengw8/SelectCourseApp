package com.example.duang1996.selectcourseapp.fragement;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.duang1996.selectcourseapp.util.BmobUtil;
import com.example.duang1996.selectcourseapp.R;

import com.example.duang1996.selectcourseapp.bean.Course;
import com.example.duang1996.selectcourseapp.global.Global;
import com.oragee.banners.BannerView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private BannerView bannerView;
    private int[] imgIds = {R.drawable.sysu1, R.drawable.sysu2, R.drawable.sysu3};
    private List<View> viewsList;

    private View mView;

    private TextView title;

    private ListView listView;

    public HomeFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1) {
        HomeFragment fragment = new HomeFragment();
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
        mView = inflater.inflate(R.layout.fragment_home, container, false);

        /*
         * 配置以及初始化Banner
         */
        initBanner();

        return mView;
    }

    private void initBanner() {
        bannerView = mView.findViewById(R.id.banner_home);

        viewsList = new ArrayList<>();
        for (int i = 0; i < imgIds.length; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(imgIds[i]);
            viewsList.add(imageView);
        }
        bannerView.startLoop(true);
        bannerView.setViewList(viewsList);

        title = mView.findViewById(R.id.title);
        title.setText("公告栏");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private void getLessonForCourse() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(Course course : Global.getSelectableCourseList()) {
                    String objectId1 = course.getLesson1().getObjectId();
                    String objectId2 = course.getLesson2().getObjectId();
                    if(!objectId1.equals("") ) {
                       course.setLesson1(BmobUtil.getInstance().fromObjectIdToLesson(objectId1));
                    } else {
                        course.setLesson1(null);
                    }
                    if(!objectId2.equals("")) {
                        course.setLesson2(BmobUtil.getInstance().fromObjectIdToLesson(objectId2));
                    } else {
                        course.setLesson2(null);
                    }
                }
            }
        }).start();
    }
}
