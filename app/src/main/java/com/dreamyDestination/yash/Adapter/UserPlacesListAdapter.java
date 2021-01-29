package com.dreamyDestination.yash.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dreamyDestination.yash.R;
import com.dreamyDestination.yash.entity.PlacesDetailListModel;

import java.util.ArrayList;

public class UserPlacesListAdapter extends RecyclerView.Adapter<UserPlacesListAdapter.StudentListViewHolder> {


    private Context mContext;
    private ArrayList<PlacesDetailListModel> studentList;

    private ArrayList<String> names;


    public UserPlacesListAdapter(Context mContext, ArrayList<PlacesDetailListModel> appointmentList) {
        this.mContext = mContext;
        this.studentList = appointmentList;
        //this.names = names;
    }

    @Override
    public UserPlacesListAdapter.StudentListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_layout, parent, false);


        return new UserPlacesListAdapter.StudentListViewHolder(itemView);
    }

    public void onBindViewHolder(UserPlacesListAdapter.StudentListViewHolder holder, int position) {
        final PlacesDetailListModel obj = studentList.get(position);
        holder.name.setText("Place Name : " + obj.getPlace_name());
        holder.place_address.setText(obj.getPlace_address());
        //names.add(obj.getPlace_name());
        //names.add(obj.getPlace_name());

    }


    @Override
    public int getItemCount() {
        return studentList.size();
    }

    protected static class StudentListViewHolder extends RecyclerView.ViewHolder {
        TextView name,place_address;

        public StudentListViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.place_name);
            place_address = (TextView) itemView.findViewById(R.id.place_address);

        }
    }

    public void filterList(ArrayList<String> filterdNames) {
        this.names = filterdNames;
        notifyDataSetChanged();
    }
}



