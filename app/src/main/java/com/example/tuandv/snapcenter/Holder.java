package com.example.tuandv.snapcenter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Tuandv on 6/17/2015.
 */
public class Holder extends RecyclerView.ViewHolder{
    protected final TextView time;

    public Holder(View itemView) {
        super(itemView);
        time = (TextView) itemView.findViewById(R.id.time);
    }
}
