package com.dreamyDestination.yash.entity;

public class DietPlanModel {

    String dish_breakfast;
    String dish_lunch;
    String dish_dinner;
    String cal_breakfast;
    String cal_lunch;
    String cal_dinner;
    String carbs_breakfast;
    String days;

    public DietPlanModel(String dish_breakfast, String dish_lunch, String dish_dinner, String cal_breakfast, String cal_lunch, String cal_dinner,String carbs_breakfast,String days) {
        this.dish_breakfast = dish_breakfast;
        this.dish_lunch = dish_lunch;
        this.dish_dinner = dish_dinner;
        this.cal_breakfast = cal_breakfast;
        this.cal_lunch = cal_lunch;
        this.cal_dinner	 = cal_dinner;
        this.carbs_breakfast = carbs_breakfast;
        this.days = days;
    }

    public DietPlanModel() {

    }

    public String getDish_breakfast() {
        return dish_breakfast;
    }

    public void setDish_breakfast(String dish_breakfast) {
        this.dish_breakfast = dish_breakfast;
    }

    public String getDish_lunch() {
        return dish_lunch;
    }

    public void setDish_lunch(String dish_lunch) {
        this.dish_lunch = dish_lunch;
    }

    public String getDish_dinner() {
        return dish_dinner;
    }

    public void setDish_dinner(String dish_dinner) {
        this.dish_dinner = dish_dinner;
    }

    public String getCal_breakfast() {
        return cal_breakfast;
    }

    public void setCal_breakfast(String cal_breakfast) {
        this.cal_breakfast = cal_breakfast;
    }

    public String getCal_lunch() {
        return cal_lunch;
    }

    public void setCal_lunch(String cal_lunch) {
        this.cal_lunch = cal_lunch;
    }

    public String getCal_dinner() {
        return cal_dinner;
    }

    public void setCal_dinner(String cal_dinner) {
        this.cal_dinner = cal_dinner;
    }

    public String getCarbs_breakfast() {
        return carbs_breakfast;
    }
    public void setCarbs_breakfast(String carbs_breakfast) {
        this.carbs_breakfast = carbs_breakfast;
    }

    public String getDays() {
        return days;
    }
    public void setDays(String days) {
        this.days = days;
    }

}

