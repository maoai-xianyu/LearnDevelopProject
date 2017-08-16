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

        List<StudentCourse> studentC3 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            studentC3.add(new StudentCourse("english_" + i, 4000 - (1000 * i)));
        }

        List<Student> students = new ArrayList<>();
        students.add(new Student("zhangyi", "12", studentC1));
        students.add(new Student("lier", "13", studentC2));
        students.add(new Student("zhaosi", "16", studentC3));
        return students;
    }


    public static List<StudentCourse> initStudentCourseData() {
        List<StudentCourse> studentCourses = new ArrayList<>();
        studentCourses.add(new StudentCourse("测试一班", "测试一班好班", 5000));
        studentCourses.add(new StudentCourse("测试二班", "测试二班好班第二，品质不错", 5000));
        studentCourses.add(new StudentCourse("测试一班", "测试一班好班第一，品质优良", 6000));
        studentCourses.add(new StudentCourse("测试二班", "测试二班好班xx,需要加油", 5000));
        studentCourses.add(new StudentCourse("测试一班", "测试一班好班，我们更为努力,需要加油", 5000));
        studentCourses.add(new StudentCourse("测试三班", "测试三班是个什么鬼", 5000));
        return studentCourses;
    }
}
