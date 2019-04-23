package com.example.f1driverapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.MyViewHolder> {
    private Context context;
    private List<Driver> drivers;
    private RecyclerViewClickListener recyclerViewClickListener;


    public DriverAdapter(Context context, List<Driver> drivers, RecyclerViewClickListener listener) {
        this.context = context;
        this.drivers = drivers;
        recyclerViewClickListener = listener;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    //creates the ViewHolder by inflating the CardView layout and returning it
    @Override
    public DriverAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);

        return new MyViewHolder(itemView, recyclerViewClickListener);
    }

    //Assigns the appropriate information from the driver object to each widget in the CardView
    //the holder is created as an inner class
    @Override
    public void onBindViewHolder(DriverAdapter.MyViewHolder holder, int position) {
        Driver driver = drivers.get(position);
        holder.driverFirstName.setText(driver.getGivenName());
        holder.driverLastName.setText(driver.getFamilyName());
        holder.driverNationality.setText(driver.getNationality());
        holder.driverCode.setText(driver.getCode());
        holder.driverDOB.setText(driver.getDateOfBirth());


        //Picasso.with(context).load(driver.getThumbnail()).into(holder.thumbnail);

    }

    @Override
    public int getItemCount() {
        return drivers.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {
        private TextView driverFirstName, driverLastName, driverDOB, driverCode, driverNationality ;
        //private ImageView thumbnail;

        private RecyclerViewClickListener recyclerViewClickListener;

        //wire the items in the CardView to instance variables
        public MyViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            driverFirstName = itemView.findViewById(R.id.driverFirstName);
            driverCode = itemView.findViewById(R.id.driverCode);
            driverLastName = itemView.findViewById(R.id.driverLastName);
            driverDOB = itemView.findViewById(R.id.driverDOB);
            driverNationality = itemView.findViewById(R.id.driverNationality);

            //thumbnail.setOnClickListener(this);
            driverFirstName.setOnClickListener(this);
            recyclerViewClickListener = listener;
            //itemView.setOnClickListener(this); //can go on any of the individual items instead.
        }


        @Override
        public void onClick(View view) {
            recyclerViewClickListener.onClick(view, getAdapterPosition());
        }
    }
}
