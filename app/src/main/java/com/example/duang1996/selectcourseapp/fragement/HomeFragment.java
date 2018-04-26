package com.example.duang1996.selectcourseapp.fragement;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.duang1996.selectcourseapp.R;

import com.oragee.banners.BannerView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private BannerView bannerView;
    private int[] imgIds = {R.drawable.abc, R.drawable.def};
    private List<View> viewsList;

    private View mView;

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
