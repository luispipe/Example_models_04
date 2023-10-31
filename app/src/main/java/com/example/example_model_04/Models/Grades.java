package com.example.example_model_04.Models;

public class Grades {

    String code;
    int student_code;
    String course;
    String teacher;
    String date;
    double grade;

    public Grades(String code, int student_code, String course, String teacher, String date, double grade) {
        this.code = code;
        this.student_code = student_code;
        this.course = course;
        this.teacher = teacher;
        this.date = date;
        this.grade = grade;
    }

    public String getCode() {
        return code;
    }

    public int getStudent_code() {
        return student_code;
    }

    public void setStudent_code(int student_code) {
        this.student_code = student_code;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
