package com.example.renttools.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.renttools.R;
import com.example.renttools.model.Tool;


import org.w3c.dom.Text;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<Tool> mToolList;

    private final Context mContext;

    public RecyclerViewAdapter(ArrayList<Tool> mToolList, Context mContext) {
        this.mToolList = mToolList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext).asBitmap().load("https://i.redd.it/jz58dpd593z61.jpg").into(holder.image);

        holder.manufacturerText.setText(mToolList.get(position).getManufacturer());
        holder.modelText.setText(mToolList.get(position).getModel());
        holder.priceText.setText(mToolList.get(position).getPricePerDay()+"");

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on" + mToolList.get(position));
                Toast.makeText(mContext, mToolList.get(position).getManufacturer(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mToolList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView image;
        TextView manufacturerText;
        TextView modelText;
        TextView priceText;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageTool);
            manufacturerText = itemView.findViewById(R.id.toolManufacturer);
            modelText = itemView.findViewById(R.id.toolModel);
            priceText = itemView.findViewById(R.id.toolPrice);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }

}
