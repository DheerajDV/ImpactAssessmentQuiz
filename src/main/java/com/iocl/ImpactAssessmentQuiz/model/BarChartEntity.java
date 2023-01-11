package com.iocl.ImpactAssessmentQuiz.model;



public class BarChartEntity {
    private int count;


    public BarChartEntity() {
    }

    public BarChartEntity(int count, String e_date) {
        this.count = count;
        this.e_date = e_date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    private String e_date;

}
