package com.example.duang1996.selectcourseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duang1996.selectcourseapp.bean.Course;

public class CourseDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        Course course = (Course) getIntent().getSerializableExtra("course");
        TextView name = findViewById(R.id.name);
        TextView year = findViewById(R.id.year);
        TextView term = findViewById(R.id.tearm);
        TextView type = findViewById(R.id.type);
        TextView college = findViewById(R.id.college);
        TextView campus = findViewById(R.id.campus);
        TextView teacher = findViewById(R.id.teacher);
        TextView credit = findViewById(R.id.credit);
        TextView capacity = findViewById(R.id.capacity);
        TextView cover = findViewById(R.id.cover);
        TextView left = findViewById(R.id.left);
        TextView screen = findViewById(R.id.screen);
        TextView exam = findViewById(R.id.exam);
        TextView screenWay = findViewById(R.id.screenWay);
        TextView require = findViewById(R.id.require);

        ImageView imageView = findViewById(R.id.back);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        name.setText(course.getName());
        year.setText(course.getYear()+ "-" + (course.getYear() + 1));
        if(course.getTerm() == 1) {
            term.setText("第一学期");
        } else {
            term.setText("第二学期");
        }
        switch (course.getType()) {
            case 1:
                type.setText("专必");
                break;
            case 2:
                type.setText("专选");
                break;
            case 3:
                type.setText("公必");
                break;
            case 4:
                type.setText("公选");
                break;
        }
        college.setText("数据科学与计算机学院");
        teacher.setText(course.getTeacherName());
        credit.setText(course.getCredit() + "");
        capacity.setText(course.getCapacity() + "");
        cover.setText(course.getCover() + "");
        left.setText((course.getCapacity() - course.getCover()) + "");
        screen.setText(course.getScreen() + "");
        if(course.getScreenWay() == 1) {
            screenWay.setText("随机筛选");
        } else {
            screenWay.setText("先到先得");
        }
        switch (course.getCampus()) {
            case 1:
                campus.setText("南校区");
                break;
            case 2:
                campus.setText("东校区");
                break;
            case 3:
                campus.setText("北校区");
                break;
            case 4:
                campus.setText("珠海校区");
                break;
            case 5:
                campus.setText("深圳校区");
                break;
        }
        switch (course.getExam()) {
            case 1:
                exam.setText("开卷");
                break;
            case 2:
                exam.setText("闭卷");
                break;
            case 3:
                exam.setText("考查");
                break;
        }
        require.setText(course.getRequire());
    }
}
