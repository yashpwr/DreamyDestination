package com.dreamyDestination.yash.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dreamyDestination.yash.R;
import com.dreamyDestination.yash.entity.RegisteredUserModel;
import com.dreamyDestination.yash.entity.TravelTipURL;

import java.util.ArrayList;

public class TravelTipAdapter extends RecyclerView.Adapter<TravelTipAdapter.StudentListViewHolder> {


    private Context mContext;
    private ArrayList<TravelTipURL> studentList;


    public TravelTipAdapter(Context context) {

    }

    public TravelTipAdapter(Context mContext, ArrayList<TravelTipURL> appointmentList) {
        this.mContext = mContext;
        this.studentList = appointmentList;
    }

    @Override
    public TravelTipAdapter.StudentListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_registered_user, parent, false);


        return new TravelTipAdapter.StudentListViewHolder(itemView);
    }

    public void onBindViewHolder(TravelTipAdapter.StudentListViewHolder holder, int position) {
        final TravelTipURL obj = studentList.get(position);
        holder.user_fullname.setText("Tip : " + obj.getTravel_description());
        holder.user_id.setText("Email: " + obj.getDate_added());

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


