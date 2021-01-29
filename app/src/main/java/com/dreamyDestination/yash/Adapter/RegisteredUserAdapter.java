package com.dreamyDestination.yash.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dreamyDestination.yash.R;
import com.dreamyDestination.yash.entity.RegisteredUserModel;

import java.util.ArrayList;

public class RegisteredUserAdapter extends RecyclerView.Adapter<RegisteredUserAdapter.StudentListViewHolder> {


    private Context mContext;
    private ArrayList<RegisteredUserModel> studentList;


    public RegisteredUserAdapter(Context context) {

    }

    public RegisteredUserAdapter(Context mContext, ArrayList<RegisteredUserModel> appointmentList) {
        this.mContext = mContext;
        this.studentList = appointmentList;
    }

    @Override
    public RegisteredUserAdapter.StudentListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_registered_user, parent, false);


        return new RegisteredUserAdapter.StudentListViewHolder(itemView);
    }

    public void onBindViewHolder(RegisteredUserAdapter.StudentListViewHolder holder, int position) {
        final RegisteredUserModel obj = studentList.get(position);
        holder.user_fullname.setText("Name : " + obj.getUser_full_name());
        holder.user_id.setText("Email: " + obj.getUser_email());

    }


    @Override
    public int getItemCount() {
        return studentList.size();
    }

    protected static class StudentListViewHolder extends RecyclerView.ViewHolder {
        TextView user_fullname,user_id;

        public StudentListViewHolder(View itemView) {
            super(itemView);
            user_fullname = (TextView) itemView.findViewById(R.id.user_full_name);
            user_id = (TextView) itemView.findViewById(R.id.user_id);

        }
    }
}


