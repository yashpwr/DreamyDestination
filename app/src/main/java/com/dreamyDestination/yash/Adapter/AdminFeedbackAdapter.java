package com.dreamyDestination.yash.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dreamyDestination.yash.R;
import com.dreamyDestination.yash.entity.FeedbackModel;
import com.dreamyDestination.yash.entity.RegisteredUserModel;

import java.util.ArrayList;

public class AdminFeedbackAdapter extends RecyclerView.Adapter<AdminFeedbackAdapter.StudentListViewHolder> {


    private Context mContext;
    private ArrayList<FeedbackModel> studentList;


    public AdminFeedbackAdapter(Context context) {

    }

    public AdminFeedbackAdapter(Context mContext, ArrayList<FeedbackModel> appointmentList) {
        this.mContext = mContext;
        this.studentList = appointmentList;
    }

    @Override
    public AdminFeedbackAdapter.StudentListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_admin_feedback, parent, false);


        return new AdminFeedbackAdapter.StudentListViewHolder(itemView);
    }

    public void onBindViewHolder(AdminFeedbackAdapter.StudentListViewHolder holder, int position) {
        final FeedbackModel obj = studentList.get(position);
        holder.comment.setText("Comment : " + obj.getComment());
        holder.posted_by.setText("Posted By: " + obj.getPosted_by());
        holder.posted_on.setText("Posted On : " + obj.getPosted_on());
        holder.feedback_rating.setText(obj.getFeedback_rating());

    }


    @Override
    public int getItemCount() {
        return studentList.size();
    }

    protected static class StudentListViewHolder extends RecyclerView.ViewHolder {
        TextView feedback_rating,comment,posted_by,posted_on;

        public StudentListViewHolder(View itemView) {
            super(itemView);
            feedback_rating = (TextView) itemView.findViewById(R.id.admin_rating);
            comment = (TextView) itemView.findViewById(R.id.admin_comment);
            posted_by = (TextView) itemView.findViewById(R.id.admin_comment_posted_by);
            posted_on = (TextView) itemView.findViewById(R.id.admin_posted_on);

        }
    }
}



