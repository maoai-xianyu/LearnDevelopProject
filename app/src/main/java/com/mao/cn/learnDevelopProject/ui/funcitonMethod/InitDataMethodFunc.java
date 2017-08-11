package com.mao.cn.learnDevelopProject.ui.funcitonMethod;

import com.mao.cn.learnDevelopProject.model.Student;
import com.mao.cn.learnDevelopProject.model.StudentCourse;

import java.util.ArrayList;
import java.util.List;

/**
 * author:  zhangkun .
 * date:    on 2017/8/11.
 */

public class InitDataMethodFunc {

    public static List<Student> initStudentData() {
        List<StudentCourse> studentC1 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            studentC1.add(new StudentCourse("math_" + i, 6000 - (1000 * i)));
        }
        List<StudentCourse> studentC2 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            studentC2.add(new StudentCourse("chinese_" + i, 4000 - (1000 * i)));
        }

        List<Student> students = new ArrayList<>();
        students.add(new Student("zhangyi", "12", studentC1));
        students.add(new Student("lier", "13", studentC2));
        return students;
    }
}
