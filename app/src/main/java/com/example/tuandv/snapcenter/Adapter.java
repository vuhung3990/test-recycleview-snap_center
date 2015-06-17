package com.example.tuandv.snapcenter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Tuandv on 6/17/2015.
 */
public class Adapter extends RecyclerView.Adapter<Holder> {
    private List<Long> list;
    public Adapter(List<Long> list) {
        this.list = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        // skip 2 item start and skip 2 item end
        if(position > 1 && position < list.size()- 2){
            holder.time.setText((list.get(position) * 5) + "");
        }else {
            holder.time.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
