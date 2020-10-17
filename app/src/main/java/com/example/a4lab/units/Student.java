package com.example.a4lab.units;

public class Student extends Person {
    String university;
    Integer mark;

    public Student(){

    }
    public Student(String university,int mark) {
        this.university = university;
        this.mark = mark;
    }
    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        if(mark >=0 && mark <= 10)
        this.mark = mark;
        else
            this.mark = null;
    }

    @Override
    public String toString() {
        return "Student{" + super.toString() +
                "university=" + university +
                ", mark=" + mark +
                '}';
    }

}
