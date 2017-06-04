package com.devapp.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.devapp.myapplication.R;
import com.devapp.myapplication.model.InterestLikeModel;

import java.util.ArrayList;

/**
 * Created by SARIN on 6/4/2017.
 */

public class InterestLikeAdapter extends RecyclerView.Adapter<InterestLikeAdapter.InterestLikeHolder> {


    private ArrayList<InterestLikeModel> interestLikeModels;
    private LayoutInflater inflater;

    public InterestLikeAdapter(ArrayList<InterestLikeModel> interestLikeModels, Context c) {
        this.interestLikeModels = interestLikeModels;
        this.inflater = LayoutInflater.from(c);
    }

    @Override
    public InterestLikeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v=inflater.inflate(R.layout.list_interest,parent,false);
        return new InterestLikeHolder(v);
    }

    @Override
    public void onBindViewHolder(InterestLikeHolder holder, int position) {
        InterestLikeModel model=interestLikeModels.get(position);
        Log.e("###","model = "+model.getImg());
        holder.img.setImageResource(model.getImg());

    }

    @Override
    public int getItemCount() {
        return interestLikeModels.size();
    }

    class InterestLikeHolder extends RecyclerView.ViewHolder{

        private ImageView img;
        private ImageView cancel_img;

        public InterestLikeHolder(View itemView) {
            super(itemView);

            img=(ImageView)itemView.findViewById(R.id.interest_like_img);
            cancel_img=(ImageView)itemView.findViewById(R.id.cancel_img);
        }
    }
}
