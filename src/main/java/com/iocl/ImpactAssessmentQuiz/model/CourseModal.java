package com.iocl.ImpactAssessmentQuiz.model;

public class CourseModal {
    private String course_name;
    private String course_code;
    private String begin_date;
    private String completion_date;
    public String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CourseModal(String course_name, String course_code, String begin_date, String completion_date, String status) {
        this.course_name = course_name;
        this.course_code = course_code;
        this.begin_date = begin_date;
        this.completion_date = completion_date;
        this.status=status;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public String getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(String begin_date) {
        this.begin_date = begin_date;
    }

    public String getCompletion_date() {
        return completion_date;
    }

    public void setCompletion_date(String completion_date) {
        this.completion_date = completion_date;
    }
}
