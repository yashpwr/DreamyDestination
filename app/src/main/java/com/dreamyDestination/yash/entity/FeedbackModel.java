package com.dreamyDestination.yash.entity;

import java.util.ArrayList;

public class FeedbackModel extends ArrayList<FeedbackModel>
{
    private String feedback_rating;
    private String comment;
    private String posted_by;
    private String posted_on;

    public FeedbackModel( String feedback_rating , String comment, String posted_by,String posted_on) {
        this.feedback_rating = feedback_rating;
        this.comment = comment;
        this.posted_by = posted_by;
        this.posted_on = posted_on;

    }

    public FeedbackModel() {

    }

    public String getFeedback_rating() {
        return feedback_rating;
    }

    public void setFeedback_rating(String feedback_rating) {
        this.feedback_rating = feedback_rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPosted_by() {
        return posted_by;
    }

    public void setPosted_by(String posted_by) {
        this.posted_by = posted_by;
    }

    public String getPosted_on() {
        return posted_on;
    }

    public void setPosted_on(String posted_on) {
        this.posted_on = posted_on;
    }

}


